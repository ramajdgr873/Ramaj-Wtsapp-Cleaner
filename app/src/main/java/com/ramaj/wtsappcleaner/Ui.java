package com.ramaj.wtsappcleaner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

final class Ui {
    static int dp(Context c,float v){return Math.round(v*c.getResources().getDisplayMetrics().density);}
    static TextView text(Context c,String s,float sp,int color,boolean bold){
        TextView t=new TextView(c);t.setText(s);t.setTextSize(sp);t.setTextColor(color);t.setGravity(Gravity.START|Gravity.CENTER_VERTICAL);
        if(bold)t.setTypeface(android.graphics.Typeface.DEFAULT,android.graphics.Typeface.BOLD); return t;
    }
    static GradientDrawable bg(int color,float radius){GradientDrawable g=new GradientDrawable();g.setColor(color);g.setCornerRadius(radius);return g;}
    static GradientDrawable strokeBg(int color,int stroke,float radius){GradientDrawable g=bg(color,radius);g.setStroke(stroke,Color.argb(90,41,230,165));return g;}
    static LinearLayout card(Context c){LinearLayout l=new LinearLayout(c);l.setOrientation(LinearLayout.VERTICAL);l.setPadding(dp(c,16),dp(c,14),dp(c,16),dp(c,14));l.setBackground(strokeBg(Color.rgb(14,28,25),1,dp(c,18)));return l;}
    static Space space(Context c,int h){Space s=new Space(c);s.setLayoutParams(new LinearLayout.LayoutParams(1,dp(c,h)));return s;}
    static Button button(Context c,String s,int color){Button b=new Button(c);b.setAllCaps(false);b.setText(s);b.setTextSize(15);b.setTextColor(Color.WHITE);b.setBackground(bg(color,dp(c,16)));b.setPadding(dp(c,12),0,dp(c,12),0);return b;}
    static String bytes(long b){if(b<1024)return b+" B";double v=b;String[] u={"KB","MB","GB","TB"};int i=-1;do{v/=1024;i++;}while(v>=1024&&i<u.length-1);return String.format(java.util.Locale.US,"%.1f %s",v,u[i]);}
}
