package com.taopao.rxtoast;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taopao.rxtoast.style.ToastBlackStyle;

import java.time.chrono.IsoChronology;

/**
 * @Author：淘跑
 * @Date: 2018/11/29 14:36
 * @Use： toast工具类
 */
public class RxToastUtils {


    private RxToastUtils() {
        throw new UnsupportedOperationException("You can't initialize me");
    }


    //******************************************初始化框架***************************************
    //static Application mApplication;
    static Context sContext = null;

    private static Context getContext() {
        if (sContext == null) {
            throw new IllegalStateException("RxToastUtils has not been initialized. Please add some code to the oncreate method of application : RxToastUtils.init(this);");
        }
        return sContext;
    }

    /**
     * 初始化RxToastUtils，在Application中初始化
     *
     * @param application 应用的上下文
     */
    public static void init(Application application) {
        sContext = application.getApplicationContext();
//        mApplication = application;
    }

    public static class Config {
        private Config() {

        }

        //        public static void Builder(Application application) {
//            return this;
//        }
        public Config init(Application application) {
            return this;
        }
    }

    //******************************************初始化框架***************************************


    //******************************************系统 Toast 替代方法(立即显示无需等待)***************************************
    //是否显示的短时长
    static boolean isShort = true;
    /*
     * Toast 替代方法 ：立即显示无需等待
     */
    private static Toast sToast;

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    public static void show(String msg) {
        isShort = true;
        //如果是空 或者当前不是显示的短时长那就重新初始化
        if (sToast == null || !isShort) {
            sToast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void show(int resId) {
        show(getContext().getString(resId));
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param resId String资源ID
     */
    public static void showLong(int resId) {
        showLong(getContext().getString(resId));
    }

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 要显示的字符串
     */
    private static void showLong(String msg) {
        isShort = false;
        if (sToast == null || isShort) {
            sToast = Toast.makeText(getContext(), msg, Toast.LENGTH_LONG);
        } else {
            sToast.setText(msg);
        }
        sToast.show();
    }


    //******************************************系统 Toast 替代方法(立即显示无需等待)***************************************//


    //******************************************系统 Toast 替代方法(需要等待)**********************************************//

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showWait(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showWait(int resId) {
        showWait(getContext().getString(resId));
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showLongWait(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
    }

    /**
     * 封装了Toast的方法 :需要等待
     */
    public static void showLongWait(int resId) {
        showLong(getContext().getString(resId));
    }
    //******************************************系统 Toast 替代方法(需要等待)****************************//


    //******************************************自定义背景**********************************************//
    private static Toast currentToast;

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull String message, int duration, int gravity) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);

        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setGravity(gravity, 0, 0);
        currentToast.setDuration(duration);
        return currentToast;
    }

    /**
     * 居中显示 黑色背景
     */
    public static void normal(@NonNull String message) {
        normal(message, Toast.LENGTH_SHORT);
    }

    /**
     * 居中显示 黑色背景
     *
     * @param message
     * @param duration
     */
    public static void normal(@NonNull String message, int duration) {
        if (message == null || message.isEmpty()) {
            return;
        }
        custom(getContext(), message, duration, Gravity.TOP).show();
    }

    /**
     * 居中显示 黑色背景
     */
    public static void normal(int resId) {
        normal(getContext().getString(resId));
    }

    //******************************************自定义背景**********************************************//
    private static long mExitTime = 2000;

    /**
     * 双击退出
     *
     * @param message
     * @return
     */
    public static boolean doubleExitApp(String message) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            normal(message);
            mExitTime = System.currentTimeMillis();
            return false;
        }
        return true;
    }

    /**
     * 双击退出
     *
     * @param resId
     * @return
     */
    public static boolean doubleExitApp(int resId) {
        return doubleExitApp(getContext().getString(resId));
    }

    /**
     * 双击退出
     */
    public static boolean doubleExitApp() {
        return doubleExitApp(R.string.exitapp_msg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static Toast sCustomToast;
    //默认的显示信息
    private static IToastStyle sDefaultStyle;

    private static TextView createToastView() {
        checkStyle();
        GradientDrawable drawable = new GradientDrawable();
        // 设置背景色
        drawable.setColor(sDefaultStyle.getBackgroundColor());
        // 设置圆角大小
        drawable.setCornerRadius(dp2px(getContext(), sDefaultStyle.getCornerRadius()));

        TextView textView = new TextView(getContext());
        textView.setId(android.R.id.message);
        textView.setTextColor(sDefaultStyle.getTextColor());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(getContext(), sDefaultStyle.getTextSize()));
        textView.setPadding(dp2px(getContext(), sDefaultStyle.getPaddingLeft()), dp2px(getContext(), sDefaultStyle.getPaddingTop()),
                dp2px(getContext(), sDefaultStyle.getPaddingRight()), dp2px(getContext(), sDefaultStyle.getPaddingBottom()));
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // setBackground API版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(drawable);
        } else {
            textView.setBackgroundDrawable(drawable);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置 Z 轴阴影
            textView.setZ(sDefaultStyle.getZ());
        }

        if (sDefaultStyle.getMaxLines() > 0) {
            // 设置最大显示行数
            textView.setMaxLines(sDefaultStyle.getMaxLines());
        }
        return textView;
    }


    public static Toast showNormal(String message) {
        if (sCustomToast == null) {
            createToast();
        }
        sCustomToast.setText(message);
        sCustomToast.show();
        return sCustomToast;
    }

    public static Toast showNormal(int id) {
        if (sCustomToast == null) {
            createToast();
        }
        sCustomToast.setText(id);
        sCustomToast.show();
        return sCustomToast;
    }


    private static boolean isCustomView = false;

    public static void setView(View view, int gravity, int XOffset, int YOffset) {
        //当前是自定义布局
        isCustomView = true;
        // 如果吐司已经创建，就重新初始化吐司
        if (sCustomToast != null) {
            // 取消原有吐司的显示
            sCustomToast.cancel();

        }
        final int mygravity;
        if (sCustomToast == null) {
            sCustomToast = new Toast(getContext());

            // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mygravity = Gravity.getAbsoluteGravity(gravity, getContext().getResources().getConfiguration().getLayoutDirection());
            } else {
                mygravity = gravity;
            }
            sCustomToast.setGravity(mygravity, XOffset, YOffset);

            sCustomToast.setView(view);
            sCustomToast.setDuration(Toast.LENGTH_SHORT);
        } else {
            if (!isCustomView) {
                sCustomToast.setView(view);
                // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mygravity = Gravity.getAbsoluteGravity(gravity, getContext().getResources().getConfiguration().getLayoutDirection());
                } else {
                    mygravity = gravity;
                }
                sCustomToast.setGravity(mygravity, XOffset, YOffset);
            }
        }

        isCustomView = true;
    }


    public static void setView(int layoutId, int gravity, int XOffset, int YOffset) {
        setView(View.inflate(getContext(), layoutId, null), gravity, XOffset, YOffset);
    }


    private static Toast createToast() {
        checkStyle();
        if (sCustomToast == null ||(sCustomToast != null && isCustomView)) {
            sCustomToast = new Toast(getContext());
            final int gravity;
            // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                gravity = Gravity.getAbsoluteGravity(sDefaultStyle.getGravity(), getContext().getResources().getConfiguration().getLayoutDirection());
            } else {
                gravity = sDefaultStyle.getGravity();
            }
            sCustomToast.setGravity(gravity, sDefaultStyle.getXOffset(), sDefaultStyle.getYOffset());
            sCustomToast.setView(createToastView());
            sCustomToast.setDuration(Toast.LENGTH_SHORT);
        }
        isCustomView = false;
        return sCustomToast;
    }

    /**
     * 如果用用户没有自定义布局的话就是默认布局
     */
    private static void checkStyle() {
        if (sDefaultStyle == null) {
            sDefaultStyle = new ToastBlackStyle();
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    private static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spValue sp值
     * @return px值
     */
    private static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
