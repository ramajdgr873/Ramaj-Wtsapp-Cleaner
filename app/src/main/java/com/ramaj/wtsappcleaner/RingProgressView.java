package com.ramaj.wtsappcleaner;

import android.content.Context;
import android.graphics.*;
import android.view.View;

public class RingProgressView extends View {
    private final Paint p=new Paint(Paint.ANTI_ALIAS_FLAG), t=new Paint(Paint.ANTI_ALIAS_FLAG); private int progress=0; private String phase="Ready";
    public RingProgressView(Context c){super(c);p.setStyle(Paint.Style.STROKE);p.setStrokeCap(Paint.Cap.ROUND);t.setTextAlign(Paint.Align.CENTER);}
    public void setProgress(int v,String ph){progress=Math.max(0,Math.min(100,v));phase=ph==null?"":ph;invalidate();}
    @Override protected void onDraw(Canvas c){super.onDraw(c);float cx=getWidth()/2f,cy=getHeight()/2f,r=Math.min(cx,cy)-Ui.dp(getContext(),12);p.setStrokeWidth(Ui.dp(getContext(),12));p.setColor(Color.rgb(28,54,48));c.drawCircle(cx,cy,r,p);p.setColor(Color.rgb(41,230,165));c.drawArc(cx-r,cy-r,cx+r,cy+r,-90,progress*3.6f,false,p);t.setColor(Color.WHITE);t.setTypeface(Typeface.DEFAULT_BOLD);t.setTextSize(Ui.dp(getContext(),34));c.drawText(progress+"%",cx,cy+Ui.dp(getContext(),8),t);t.setTypeface(Typeface.DEFAULT);t.setTextSize(Ui.dp(getContext(),12));t.setColor(Color.rgb(147,170,161));c.drawText(phase,cx,cy+Ui.dp(getContext(),34),t);}
}
