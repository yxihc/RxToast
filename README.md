# RxToast
# 框架引入

#### 先在 build.gradle(Project:XXXX) 的 repositories 添加:
~~~
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
~~~
#### 然后在 build.gradle(Module:app) 的 dependencies 添加:

~~~
dependencies {
	        implementation 'com.github.TaoPaox:RxToast:1.3'
	}
~~~

#  使用
#### 再Application的onCreate方法里面初始化
~~~
RxToast.init(this)
                .setBackgroundColor("#A5000000")//背景颜色：35%的黑色透明度
                .setTextColor("#FFFFFF")//字体颜色
                .setGravity(Gravity.CENTER)//显示位置
                .setCornerRadius(6)//圆角大小  单位dp
                .setPadding(24, 24, 16, 16)//textview左右上下间距 单位dp
                .setMaxLines(2)//textview最大行数
                .setTextSize(14)//textview字体大小  单位dp
                .setZ(30)//Z轴的高度（阴影）
                .setLineSpacing(1.5f)//设置行间距
                .apply();//应用设置

~~~

# 关于样式方面 （随意发挥吧） 下面介绍下我自己写的样式
#### QQ样式
~~~
 RxToast.init(getApplication())
                .setBackgroundColor("#CC000000")
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setPadding(16, 16, 12, 12)
                .setMaxLines(2)
                .setTextSize(12)
                .setZ(30)
                .setMaxLines(3)
                .apply();
~~~
#### 白色样式
~~~
 RxToast.init(getApplication())
                .setBackgroundColor("#FFFFFF")
                .setTextColor("#333333")
                .setGravity(Gravity.CENTER)
                .setCornerRadius(6)
                .setPadding(24, 24, 16, 16)
                .setMaxLines(2)
                .setTextSize(14)
                .setZ(30)
                .setMaxLines(3)
                .apply();
~~~
#### 美团样式
~~~
   RxToast.init(getApplication())
                .setBackgroundColor("#A5000000")//35%的黑色透明度
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setCornerRadius(6)
                .setPadding(14, 14, 10, 10)
                .setMaxLines(2)
                .setTextSize(14)
                .setZ(30)
                .setMaxLines(3)
                .apply();
~~~






