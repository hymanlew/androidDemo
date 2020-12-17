package com.hyman.demo;

/**
 * [官方开发文档]: https://developer.android.google.cn/docs
 * [官方 API 文档]: https://developer.android.google.cn/reference/classes?hl=zh_cn
 *
 * 相关 API 如下：
 *
 * Activity：四大应用组件之一
 *   - onCreate()：自动调用的方法,在其中加载布局显示
 *   - setContentView(layoutld)：加载布局
 *   - View findViewByld(id)：根据id找到对应的视图对象
 *
 * R：应用的资源类
 *   - R.drawable：包含所有图片资源标识的内部类
 *   - R.layout：包含所有布局资源标识的内部类
 *   - R.id：包含所有视图id标识的内部类
 *   - R.string：包含所有字符串标识的内部类
 *
 * View/Button:视图/按钮
 *   - setonClickListener(listener)：给视图设置点击监听
 *
 * View.OnClickListener：内部接口
 *   - void onClick(View v)：点击事件的回调方法
 *
 * Toast：用来显示短时间提示文本的类
 *   - static Toast makeText(...)：创建一个 toast 对象
 *   - show()：显示小提示
 */

/**
 * 设置监听的四种方式：
 * Layout 中：android:onclick=“方法名”，然后在 Activity 中声明该方法：public void 方法名(View v) {  }
 * view.setOnclickListener（new View.OnclickListener(){}）
 * view.setOnclickListener（this）
 * view.setOnclickListener（onclickListener 成员变量）
 * setOnclickListener（xxx）是设置为点击监听
 * setOnLongClickListener（onLongListener listener）是设置为长按监听
 */

/**
 * 1，手机的尺寸：屏幕对角线的长度，单位为英寸（2.54cm）。
 *
 * 2，手机的分辨率：屏幕能显示的像素的数量，一般为 **长方向上数量*宽方向上数量** 来表达。
 *
 * 3，手机的像素密度：pixels per inch，也称 PPi，即每英寸屏幕能显示的像素数，像素密度越大，显示画面细节就越丰富。
 * 计算：像素密度 = {1+V[(长度像素数-1)^2+(宽度像素数-1)^2]}/屏幕尺寸。
 *
 * 4，DPl：Dots Per Inch（每英寸所打印的点数或线数）的缩写，用来表示打印机打印分辨率，有时也会用 dpi 来代指 ppi。
 *
 * 5，手机的密度：Density，以 160ppi 为基准，即像素密度为 160 时 Density 为 1。
 * ldpi	120dpi	0.75
 * mdpi	160dpi	1.0
 * hdpi	240dpi	1.5
 * xhdpi	320dpi	2.0
 *
 * 6，px：pixels（像素），1px 的长度就对应屏幕一个像素点的大小。
 *
 * 7，dp/dip：device-independent pixels（设备无关像素）（是真正开发使用时用的单位）。
 * 1dp = (dpi / 160) px
 * 1dp = density px（160 -> 1px，120 -> 0.75px，320 -> 2px）
 * 1px = 1/density dp
 *
 * 8，sp：scaled pixels（可缩放像素），与 dp 类似，但是可以根据用户的字体大小首选项进行缩放。
 *
 * 9，注意：Android 在运行时会自动将 dp/dip/sp 为单位的尺寸，转换为像素单位的值。
 * 10，注意：所以在布局文件中，除了字体大小（字体大小以 sp 为单位），其它的都要以 dp 为单位的。
 * 11，在布局文件视图的宽高尽量用 match_parent / wrap_content。
 * 12，如果必须指定特定值，使用 dp/dip 做单位，文本大小使用 sp 做单位。
 */

/**
 * 在 Android 中字体颜色或背景颜色，使用 RGB 来表达（R - red，G - green，B - blue）。
 * 每个色相用一个 2 位的十六进制的数来表达。
 * 颜色值的前面为带一个字符 # 号。
 * 具体需要什么颜色可以查颜色表。
 */
public class MainAPI {
}
