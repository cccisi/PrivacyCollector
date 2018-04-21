# 移动测评作业第一题(PrivacyCollector)

PrivacyCollector is a homework of Measurement of mobile.
- **目前仅为一个Demo，里面有几个Activity，等待着我们完善！**
- 我们的任务：
- 1.收集Android基本信息
- 2.总结哪些信息容易收集，哪些比较困难
- 3.根据收集到的信息，实现一些潜在作用
- **注意：本项目仅作为测试，绝不支持用于违反道德和法律的用途！**

## 一、小组分工

- 1.CZH,XSY,LYC，按照自己名字找到自己负责的Package开发即可，在MainActivity上有不同入口；
- 2.PP,HM,RXS负责实验报告，技术方法等信息可参考本页；
- 3.NoteActivity目前呈现GitHub上该页面，作为项目的功能及使用方法记录本；

## 二、功能介绍

### [主页面](https://github.com/cccisi/PrivacyCollector/blob/master/app/src/main/java/com/cccisi/privacycollector/MainActivity.java)

**效果展示**

- *桌面图标*
- <img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/icon.png" width = "100" height = "100" alt="icon" align=center />
- *主界面*
- <img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/prscan.jpg" width = "360" height = "540" alt="主页面" align=center />

### [CZH](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/czh)

**效果展示**
- ![主页面]()

- **功能**
- 1.浏览器；
- 2.捕捉用户输入；
- ...

### [XSY](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/xsy)

**效果展示**
- ![主页面]()
- **功能**
- 用户页面获取位置及通讯录内容，鉴于有些人习惯于将联系人称呼写入通讯录，因此可以获取用户关系密切之人信息（暂未实现）

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_user.png" width = "360" height = "540" alt="用户页面" align=center />

* 系统页面获取手机品牌，android版本，SDK版本，手机运营商，手机号码（有些可获取）

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_system.png" width = "360" height = "540" alt="系统页面" align=center />

* App页面获取应用程序安装列表以及正在后台运行的应用

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_app.png" width = "360" height = "540" alt="App页面" align=center />

### [LYC](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/lyc)

**效果展示**
- ![主页面]()

- **功能**
- 1.获取手机中已保存的wifi的SSID（wifi名称），网络状态（可用，禁用，当前连接），加密方式（WEP，无，WPA-PSK/WPA2-PSK，EAP，802.1x EAP）
- 2.获取周围可扫描到的wifi，尤其是加密方式为“无”的wifi
- 3.选择一个上述加密方式为“无”的wifi，新建一个假冒的热点，可以用这个钓鱼wifi吸引其他人连接，从而实施一些攻击（具体攻击方法未实现，只实现开启一个假冒的热点）

2/3正在写……1已经实现了……

## 三、其他参考资料

- 1.[Android权限列表说明](https://blog.csdn.net/lianyi68/article/details/78588565)
- 2.[该项目较完善地获取了移动端基本信息（感觉这个比较相关）](https://github.com/zhantong/PrivacyCollector)
- 3.[整理隐私数据](https://github.com/PrivacyStreams/PrivacyStreams)
- 4.[一些看不懂的隐私泄露分析](https://github.com/StevenGAO95/AndroidPrivacyLeakAnalysis)

## 四、[APK下载](https://github.com/cccisi/PrivacyCollector/blob/master/app/release/app-release.apk)


- **扫码下载**（请使用浏览器，不要使用微信扫描！）

![APK下载](https://github.com/cccisi/PrivacyCollector/blob/master/doc/QR.png)

## 五、元素引用说明

- **所用部分图片非本人原创，取自开源网站，如有争议，请联系。**

 ICON来源：http://sc.chinaz.com/tubiao/180403237450.htm

 图片来源：www.graphicsfuel.com

## [License](https://github.com/cccisi/PrivacyCollector/blob/master/LICENSE)

```
MIT License

Copyright (c) 2018 Zachary Chen, XSY, LYC, PP, HM, RXS

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

