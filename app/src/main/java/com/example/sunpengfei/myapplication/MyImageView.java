package com.example.sunpengfei.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by sunpengfei on 16/4/20.
 */
public class MyImageView extends SimpleDraweeView{

    Path path = new Path();

    private PaintFlagsDrawFilter pdf=new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(pdf);
        path.addCircle(getWidth()/2,getHeight()/2,getWidth()/2, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

}
