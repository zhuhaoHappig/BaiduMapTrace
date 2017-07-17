# BaiduMapTrace
**百度地图运动轨迹纠偏、去噪、绑路，百度鹰眼sdk服务详细介绍**

**Android百度地图开发**

利用百度地图画运动轨迹不难发现，当位置处于建筑物密集区、桥梁、高架桥下，gps信号较差时，
画出来的运动轨迹效果会比较差。即使是在空旷地带，也难免会出现gps漂移的情况而造成轨迹的偏差。
这时就需要我们对位置点进行纠偏、去噪、抽稀、绑路操作。
百度鹰眼sdk则提供了相应的api，本demo将介绍如果使用百度鹰眼sdk画出效果相对较好的运动轨迹。

demo中api详细介绍请阅读我的[zhh_happig的简书博客](http://www.jianshu.com/u/d82bd37b1d29)

[Android百度地图(四):百度地图运动轨迹纠偏、去噪、绑路之百度鹰眼sdk服务](http://www.jianshu.com/p/3c3d9e92739d)

原始轨迹图

![github](/a.png)


使用百度鹰眼sdk处理后的轨迹图

![github](/b.png)


使用百度鹰眼sdk处理后点，时时动态画轨迹效果图

![github](/cb.png)


**请注意申请apikey和serviceId**
