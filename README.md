## android沉浸式状态栏StatusBar实例


### StatusBar沉浸式的2种实现方式

对于沉浸式状态栏的实现，我觉得有**两种实现方式**。

一是：**将状态栏的颜色和状态栏下面的View颜色保持一致或相近**。如图：

![image](http://oe9ggtbcb.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20170616115111.png)

二是：**将View充满全屏，状态栏覆盖在View上，将状态栏设置为透明色**。

![image](http://oe9ggtbcb.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20170616115116.jpg)


### StatusBar沉浸式实现的真相

我们使用Android Studio的提供的截图工具Layout Inspector工具分析可以知道，实际上显示时间、信号等SystemBar是系统级别的一个Window（悬浮窗）。而StatusBar是SystemBar下面的View，是DecorView的一部分。我们通常设置StatusBar的颜色其实就是给SystemBar覆盖的StatusBar设置一个背景色。

LayoutInspetor工具的截图：

![image](http://oe9ggtbcb.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20170616123325.png)

由上图可知，StatusBar是DecorView的一部分，是一个View设置了背景色，没有系统的时间、信号等信息。

所以我们所设置状态栏的颜色，就是设置DecorView中的StatusBar的View的颜色。

![image](http://oe9ggtbcb.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20170616123328.png)

上图说明：ImageView占满了怎个DecorView，没有StatusBar。


### StatusBar的在不同Window上的实现

我们不管是Activity，Fragment还是在DialogFragment以及Window悬浮窗，都是有可能有使用沉浸式状态栏的。那么如何实现呢，以下实现是基于Android5.0以上，对Android4.4不兼容？

具体实现看代码：