package com.taopao.rxtoast;

/**
 * @Author：淘跑
 * @Date: 2018/12/5 10:09
 * @Use：
 */
public interface IToastStyle {
    //////////////////////间距位置///////////////////////
    int getGravity(); // 吐司的重心

    int getXOffset(); // X轴偏移

    int getYOffset(); // Y轴偏移

    int getZ(); // 吐司Z轴坐标

    ////////////////////////文字设置/////////////////////
    int getTextColor(); // 文本颜色

    default float getTextSize() {
        // 文本大小
        return 0;
    }

    int getMaxLines(); // 最大行数

    ////////////////////////背景间距设置//////////////////////////////
    int getCornerRadius(); // 圆角大小

    int getBackgroundColor(); // 背景颜色

    int getPaddingLeft(); // 左边内边距

    int getPaddingTop(); // 顶部内边距

    int getPaddingRight(); // 右边内边距

    int getPaddingBottom(); // 底部内边距

}
