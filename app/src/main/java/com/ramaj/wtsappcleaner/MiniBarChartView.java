package com.ramaj.wtsappcleaner;
import android.content.Context;import android.graphics.*;import android.view.View;
public class MiniBarChartView extends View{
 private long[] vals={0,0,0,0};private final Paint p=new Paint(Paint.ANTI_ALIAS_FLAG),t=new Paint(Paint.ANTI_ALIAS_FLAG);private final String[] labels={"Exact","Visual","Possible","Moved"};
 public MiniBarChartView(Context c){super(c);t.setTextAlign(Paint.Align.CENTER);}
 public void setValues(long a,long b,long c,long d){vals=new long[]{a,b,c,d};invalidate();}
 protected void onDraw(Canvas c){super.onDraw(c);long mx=1;for(long v:vals)mx=Math.max(mx,v);float gap=getWidth()/16f,bw=(getWidth()-gap*5)/4f,base=getHeight()-Ui.dp(getContext(),24);for(int i=0;i<4;i++){float h=(base-Ui.dp(getContext(),12))*vals[i]/(float)mx;float x=gap+(bw+gap)*i;p.setColor(i==0?Color.rgb(41,230,165):i==1?Color.rgb(124,92,255):i==2?Color.rgb(255,191,74):Color.rgb(73,165,255));c.drawRoundRect(x,base-h,x+bw,base,12,12,p);t.setTextSize(Ui.dp(getContext(),10));t.setColor(Color.rgb(147,170,161));c.drawText(labels[i],x+bw/2,getHeight()-Ui.dp(getContext(),6),t);}}
}
