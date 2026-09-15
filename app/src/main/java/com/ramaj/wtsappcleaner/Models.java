package com.ramaj.wtsappcleaner;

final class Models {
    static final class FileEntry {
        final String path, category;
        final long size, mtime;
        final boolean sent;
        FileEntry(String p,long s,long m,String c,boolean x){path=p;size=s;mtime=m;category=c;sent=x;}
    }
    static final class ImageSig {
        final FileEntry f; final long hash; final int width,height;
        ImageSig(FileEntry f,long h,int w,int he){this.f=f;hash=h;width=w;height=he;}
    }
    static final class VideoSig {
        final FileEntry f; final long h1,h2,h3,duration; final int width,height;
        VideoSig(FileEntry f,long a,long b,long c,long d,int w,int he){this.f=f;h1=a;h2=b;h3=c;duration=d;width=w;height=he;}
    }
    static final class Dup {
        long id; String keepPath,dupPath,kind,category; int confidence; long bytes; boolean moved;
    }
    static final class Progress {
        String phase="Ready", detail=""; int percent; long current,total,exact,visual,possible,recovered; double rate; long etaSec;
    }
}
