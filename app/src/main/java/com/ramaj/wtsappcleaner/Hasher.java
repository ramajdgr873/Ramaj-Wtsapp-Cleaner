package com.ramaj.wtsappcleaner;

import android.graphics.*;
import android.media.MediaMetadataRetriever;
import java.io.*;import java.security.*;import java.util.*;

final class Hasher {
    static String quickHash(String path,long size)throws Exception{
        MessageDigest md=MessageDigest.getInstance("SHA-256");RandomAccessFile r=new RandomAccessFile(path,"r");byte[] b=new byte[16384];
        try{readAt(r,md,b,0);if(size>49152)readAt(r,md,b,Math.max(0,size/2-8192));if(size>16384)readAt(r,md,b,Math.max(0,size-16384));}finally{r.close();}
        return hex(md.digest());
    }
    private static void readAt(RandomAccessFile r,MessageDigest md,byte[] b,long pos)throws IOException{r.seek(pos);int n=r.read(b);if(n>0)md.update(b,0,n);}
    static String sha256(String path,StopCheck stop)throws Exception{MessageDigest md=MessageDigest.getInstance("SHA-256");byte[] b=new byte[1024*1024];try(InputStream in=new BufferedInputStream(new FileInputStream(path),1024*1024)){int n;while((n=in.read(b))>0){if(stop.stop())throw new InterruptedException();md.update(b,0,n);}}return hex(md.digest());}
    static String hex(byte[] a){StringBuilder s=new StringBuilder(a.length*2);for(byte v:a)s.append(String.format(Locale.US,"%02x",v&255));return s.toString();}
    interface StopCheck{boolean stop();}

    static Models.ImageSig imageSig(Models.FileEntry f){
        try{BitmapFactory.Options o=new BitmapFactory.Options();o.inJustDecodeBounds=true;BitmapFactory.decodeFile(f.path,o);if(o.outWidth<=0||o.outHeight<=0)return null;int sm=1;while(o.outWidth/sm>512||o.outHeight/sm>512)sm*=2;o.inJustDecodeBounds=false;o.inSampleSize=sm;o.inPreferredConfig=Bitmap.Config.ARGB_8888;Bitmap b=BitmapFactory.decodeFile(f.path,o);if(b==null)return null;long h=pHash(b);b.recycle();return new Models.ImageSig(f,h,o.outWidth,o.outHeight);}catch(Throwable e){return null;}
    }
    static Models.VideoSig videoSig(Models.FileEntry f){MediaMetadataRetriever r=new MediaMetadataRetriever();try{r.setDataSource(f.path);long d=parseLong(r.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));int w=(int)parseLong(r.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)),h=(int)parseLong(r.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));if(d<=0)return null;long[] ts={d*200,d*500,d*800};long[] hs=new long[3];for(int i=0;i<3;i++){Bitmap b=r.getFrameAtTime(ts[i],MediaMetadataRetriever.OPTION_CLOSEST_SYNC);if(b==null)return null;hs[i]=pHash(b);b.recycle();}return new Models.VideoSig(f,hs[0],hs[1],hs[2],d,w,h);}catch(Throwable e){return null;}finally{try{r.release();}catch(Exception ignored){}}}
    private static long parseLong(String s){try{return Long.parseLong(s);}catch(Exception e){return 0;}}

    static long pHash(Bitmap src){Bitmap b=Bitmap.createScaledBitmap(src,32,32,true);double[][] pix=new double[32][32];for(int y=0;y<32;y++)for(int x=0;x<32;x++){int c=b.getPixel(x,y);pix[y][x]=0.299*Color.red(c)+0.587*Color.green(c)+0.114*Color.blue(c);}if(b!=src)b.recycle();double[][] d=new double[8][8];for(int u=0;u<8;u++)for(int v=0;v<8;v++){double sum=0;for(int x=0;x<32;x++)for(int y=0;y<32;y++)sum+=pix[y][x]*Math.cos((2*x+1)*u*Math.PI/64.0)*Math.cos((2*y+1)*v*Math.PI/64.0);d[u][v]=sum;}double[] a=new double[63];int k=0;for(int u=0;u<8;u++)for(int v=0;v<8;v++)if(u!=0||v!=0)a[k++]=d[u][v];double[] cp=a.clone();Arrays.sort(cp);double med=cp[cp.length/2];long h=0;for(double v:a){h<<=1;if(v>med)h|=1;}return h;}
    static int ham(long a,long b){return Long.bitCount(a^b);}
}
