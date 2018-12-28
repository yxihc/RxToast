package com.taopao.rxtoast;

import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author：淘跑
 * @Date: 2018/12/28 11:02
 * @Use： Toast工具类
 */
public class RxToast {
    private static ToastHandler sToastHandler;

    public RxToast() {
        throw new UnsupportedOperationException("You can't initialize me");
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>系统Toast替代方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static void showShort(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(@StringRes int msgid) {
        showShort(getContext().getString(msgid));
    }

    public static void showLong(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@StringRes int msgid) {
        showShort(getContext().getString(msgid));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<系统Toast替代方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //******************************************初始化框架***************************************
    //static Application mApplication;
    static Application mApplication;

    private static Context getContext() {
        if (mApplication == null) {
            throw new IllegalStateException("RxToastUtils has not been initialized.");
        }
        return mApplication.getApplicationContext();
    }

    /**
     * 初始化RxToastUtils，在Application中初始化
     *
     * @param application 应用的上下文
     */
    public static Config init(Application application) {
        mApplication = application;
        mConfig = new Config(mApplication.getApplicationContext());
        return mConfig;
    }

    private static Config mConfig;
    private static Toast sCoustomToast;

    public static class Config {
        Context mContext;
        int mGravity = 0; // 吐司的重心
        int mXOffset = 0; // X轴偏移
        int mYOffset = 0;// Y轴偏移
        int mZ = 0;// 吐司Z轴坐标
        int mCornerRadius = 0;// 圆角大小
        int mBackgroundColor;// 背景颜色
        int mTextColor;// 文本颜色
        float mTextSize = 0;// 文本大小
        int mMaxLines = 0;// 最大行数
        int mPaddingLeft = 0; // 左边内边距
        int mPaddingTop = 0; // 顶部内边距
        int mPaddingRight = 0; // 右边内边距
        int mPaddingBottom = 0; // 底部内边距
        boolean isSystem = true;//是否是系统toast
        View mView = null;
        float mLineSpacing = 0;//行间距

        public Config(Context context) {
            this.mContext = context;
            setBackgroundColor(R.color.black_35);
            setTextColor(R.color.white);
            isSystem = true;
        }

        /**
         * 吐司的重心
         *
         * @param gravity
         * @param xOffset X轴偏移
         * @param yOffset Y轴偏移
         * @return
         */
        public Config setGravity(int gravity, int xOffset, int yOffset) {
            mGravity = gravity;
            mXOffset = xOffset;
            mYOffset = yOffset;
            return mConfig;
        }

        /**
         * 吐司的重心
         *
         * @param gravity
         * @return
         */
        public Config setGravity(int gravity) {
            mGravity = gravity;
            mXOffset = 0;
            mYOffset = 0;
            return mConfig;
        }

        /**
         * 设置自定义界面 textview的id必须是=>  android:id="@android:id/message"
         *
         * @param layoutId
         * @return
         */
        public Config setCustomView(@LayoutRes int layoutId) {
            isSystem = false;
            mView = View.inflate(mContext, layoutId, null);
            return mConfig;
        }

        /**
         * 设置自定义界面 textview的id必须是=>  android:id="@android:id/message"
         *
         * @param view
         * @return
         */
        public Config setCustomView(View view) {
            isSystem = false;
            mView = view;
            return mConfig;
        }

        /**
         * 设置Z轴高度（阴影）
         *
         * @param z
         * @return
         */
        public Config setZ(int z) {
            mZ = z;
            isSystem = false;
            return mConfig;
        }

        /**
         * 设置圆角大小
         *
         * @param cornerRadius 单位dp
         * @return
         */
        public Config setCornerRadius(int cornerRadius) {
            mCornerRadius = cornerRadius;
            isSystem = false;
            return mConfig;
        }


        /**
         * 设置背景颜色
         *
         * @param backgroundColor
         * @return
         */
        public Config setBackgroundColor(@ColorRes int backgroundColor) {
            mBackgroundColor = getContext().getResources().getColor(backgroundColor);
            isSystem = false;
            return mConfig;
        }

        /**
         * 设置背景颜色
         *
         * @param backgroundColor
         * @return
         */
        public Config setBackgroundColor(String backgroundColor) {
            mBackgroundColor = Color.parseColor(backgroundColor);
            isSystem = false;
            return mConfig;

        }

        /**
         * 设置字体颜色
         *
         * @param textColor
         * @return
         */
        public Config setTextColor(@ColorRes int textColor) {
            mTextColor = getContext().getResources().getColor(textColor);
            isSystem = false;
            return mConfig;
        }

        /**
         * 设置字体颜色
         *
         * @param textColor
         * @return
         */
        public Config setTextColor(String textColor) {
            mTextColor = Color.parseColor(textColor);
            isSystem = false;
            return mConfig;
        }

        /**
         * 设置字体大小
         *
         * @param textSize 单位dp
         * @return
         */
        public Config setTextSize(float textSize) {
            mTextSize = textSize;
            isSystem = false;
            return mConfig;
        }

        /**
         * 设置最最大行数
         *
         * @param maxLines
         * @return
         */
        public Config setMaxLines(int maxLines) {
            mMaxLines = maxLines;
            isSystem = false;
            return mConfig;

        }

        /**
         * 设置行间距
         *
         * @param lineSpacing
         * @return
         */
        public Config setLineSpacing(float lineSpacing) {
            mLineSpacing = lineSpacing;
            return mConfig;
        }

        /**
         * 设置边距（dp）
         *
         * @param paddingLeft   左 单位dp
         * @param paddingRight  右 单位dp
         * @param paddingTop    上 单位dp
         * @param paddingBottom 下 单位dp
         * @return
         */
        public Config setPadding(int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) {
            mPaddingLeft = paddingLeft;
            mPaddingTop = paddingTop;
            mPaddingRight = paddingRight;
            mPaddingBottom = paddingBottom;
            isSystem = false;
            return mConfig;
        }

        /**
         * 使设置生效
         */
        public Toast apply() {
            return createToast();
        }
    }

    private static Toast createToast() {
        if (mConfig.isSystem) {
            sCoustomToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
            Log.d("RxToast", "makeText: ");
        } else {
            // 判断有没有通知栏权限
            if (isNotificationEnabled(getContext())) {
                sCoustomToast = new ToastBase(mApplication);
            } else {
                sCoustomToast = new SupportToast(mApplication);
            }

            Log.d("RxToast", "createToast: ");
            if (mConfig.mView == null) {
                sCoustomToast.setView(createTextView(getContext()));
            } else {
                sCoustomToast.setView(mConfig.mView);
            }


        }

        if (mConfig.mGravity != 0) {
            final int gravity;
            // 适配 Android 4.2 新特性，布局反方向（开发者选项 - 强制使用从右到左的布局方向）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                gravity = Gravity.getAbsoluteGravity(mConfig.mGravity, getContext().getResources().getConfiguration().getLayoutDirection());
            } else {
                gravity = mConfig.mGravity;
            }
            sCoustomToast.setGravity(gravity, mConfig.mXOffset, mConfig.mYOffset);
        }


        // 创建一个吐司处理类
        sToastHandler = new ToastHandler(sCoustomToast);
        return sCoustomToast;
    }

    //******************************************初始化框架***************************************

    /**
     * Toast 替代方法 ：立即显示无需等待
     *
     * @param msg 显示内容
     */
    public static void show(String msg) {
        checkToastState();
//        sToastHandler.setText(msg);
//        sToastHandler.show();
        sCoustomToast.setText(msg);
        sCoustomToast.show();
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
     * 检查吐司状态
     */
    private static void checkToastState() {
        // 吐司工具类还没有被初始化，必须要先调用init方法进行初始化
        if (sCoustomToast == null) {
            throw new IllegalStateException("RxToast has not been initialized");
        }
    }

    /**
     * 生成 TextView 对象
     */
    private static TextView createTextView(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        // 设置背景色
        drawable.setColor(mConfig.mBackgroundColor);
        // 设置圆角大小
        drawable.setCornerRadius(dp2px(context, mConfig.mCornerRadius));
        TextView textView = new TextView(context);
        textView.setId(android.R.id.message);
        textView.setTextColor(mConfig.mTextColor);
        if (mConfig.mTextSize > 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, sp2px(context, mConfig.mTextSize));
        }
        textView.setPadding(dp2px(context, mConfig.mPaddingLeft), dp2px(context, mConfig.mPaddingTop),
                dp2px(context, mConfig.mPaddingRight), dp2px(context, mConfig.mPaddingBottom));
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // setBackground API版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(drawable);
        } else {
            textView.setBackgroundDrawable(drawable);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置 Z 轴阴影
            textView.setZ(mConfig.mZ);
        }
        if (mConfig.mMaxLines > 0) {
            // 设置最大显示行数
            textView.setMaxLines(mConfig.mMaxLines);
        }

        if (mConfig.mLineSpacing > 0) {
            textView.setLineSpacing(0, mConfig.mLineSpacing);
        }
        return textView;
    }


    private static long mExitTime = 2000;

    /**
     * 双击退出
     *
     * @param message
     * @return
     */
    public static boolean doubleExitApp(String message) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            show(message);
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

    /**
     * 检查通知栏权限有没有开启
     * 参考SupportCompat包中的： NotificationManagerCompat.from(context).areNotificationsEnabled();
     */
    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).areNotificationsEnabled();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;

            try {
                Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (Integer) opPostNotificationValue.get(Integer.class);
                return (Integer) checkOpNoThrowMethod.invoke(appOps, value, uid, pkg) == 0;
            } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException | ClassNotFoundException ignored) {
                return true;
            }
        } else {
            return true;
        }
    }

}
