
package com.example.sunpengfei.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by sunpengfei on 16/4/7.
 */
public class MyPop extends Dialog {

    private SimpleDraweeView simpleDraweeView;

    private Context context;

    private String path;

    public MyPop(Context context) {
        super(context, R.style.Dialog_Fullscreen);
        this.context = context;
        initView();
    }

    public void setPath(String path) {
        this.path = path;
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        setContentView(view);
        simpleDraweeView = (SimpleDraweeView)view.findViewById(R.id.iv);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(View v) {
        if (isShowing()) {
            dismiss();
        } else {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
            simpleDraweeView.setLayoutParams(params);
            simpleDraweeView.setImageURI(Uri.parse(path));
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
            float xd = (float)metrics.widthPixels/v.getWidth();
            float yd = (float)metrics.heightPixels/v.getHeight();
            final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, xd, 1.0f, yd);
            scaleAnimation.setDuration(1000);
            scaleAnimation.setFillAfter(true);
            simpleDraweeView.startAnimation(scaleAnimation);

            show();
        }
    }

}
