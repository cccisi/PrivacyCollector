# 移动测评作业第一题(PrivacyCollector)

PrivacyCollector is a homework of Measurement of mobile.
- **目前是Beta版本，里面有几个尚不完善的功能！**
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

### [RXS](https://github.com/rxs0928)

- https://github.com/cccisi/PrivacyCollector/blob/master/doc/RXS.MD

#### 参考:可以收集的信息有：

##### （1）用户信息

- i. 用户性别，年龄，职业，兴趣偏好，比如：经常使用什么APP、经常浏览什么网页、经常听什么音乐等等，以便根据用户特性推送有价值的信息。 
- ii. 用户每天的基本作息，早上几点开始使用手机，哪个时间段持续操作手机等等，可用于提醒特定用户（尤其学生）合理使用手机，勿沉溺。 
- iii. 根据位置信息判断用户的常驻位置（比如家或公司地址），以及是否经常出差或旅行等，以便向用户推送及时有效的信息。
- iv. 收集用户的运动健康状况，比如每天走了多少步，点了几次外卖等等，可用于提醒用户健康饮食，多加运动。
- v. 收集用户通讯录信息，构建用户的社交网络结构，可以集成多个社交平台综合分析，以便对用户的人际网有更全面的了解。
- vi. 根据用户关注或发布的信息内容，收集关键词，刻画出用户的情绪状态起伏，评估用户的情感价值倾向（积极乐观/消极怨世），挖掘用户的心理需求。

##### （2）系统内信息

- i. 手机型号、序列号、处理器等等
- ii. 系统仍存在的漏洞、已安装的补丁等等

##### （3）其他APP信息

- i. 用户已安装的APP列表
- ii. 每个APP拥有的权限
- iii. 每个APP的账户/密码（属于用户机密信息，原则上讲不能收集，除非特殊需求）

### [CZH](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/czh)

**效果展示**
- #### 主页面

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/visit.jpg" width = "360" height = "530" alt="用户页面" align=center />

- **总体设想**
- 设想实现一个能记录用户隐私的浏览器,包括用户名、口令、浏览记录、cookie等,第一步是在本app内置浏览器下记录,未来希望通过绑定service记录其他app的输入记录.

- **功能**
- 1.实现了基本的功能浏览器,并尝试记录浏览历史和cookie；
- 2.探测键盘的开启和关闭状态；
- 3.伪造一个安全登陆页面,如果用户误以为真,他/她的username和password就会被记录;
- 4.可查看记录

- **使用介绍**
- 1.在浏览过程中,可能需要登陆；
<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/home.jpg" width = "360" height = "230" alt="点击登陆" align=center />
- 2.当app判断是登录页面时,弹出钓鱼页面；
<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/login.jpg" width = "360" height = "530" alt="login" align=center />
- 3.可以通过悬浮按钮查看记录;
<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/check.jpg" width = "360" height = "0" alt="查看" align=center />

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/record.png" width = "360" height = "110" alt="记录" align=center />

- **感想**
- 1.app直接获取用户输入很难,因为点击或输入事件会被输入法app拦截,开发者拿不到点击位置的数据
- 2.输入法可以直接获取输入值,下一步可以考虑做privacyCollector输入法,haha~

### [XSY](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/xsy)

**效果展示**
- #### 主页面

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_user.png" width = "360" height = "540" alt="用户页面" align=center />

- **功能**
- 用户页面获取位置及通讯录内容，鉴于有些人习惯于将联系人称呼写入通讯录，因此可以获取用户关系密切之人信息（暂未实现）

* 系统页面获取手机品牌，android版本，SDK版本，手机运营商，手机号码（有些可获取）

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_system.png" width = "360" height = "540" alt="系统页面" align=center />

* App页面获取应用程序安装列表以及正在后台运行的应用

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Xsy_app.png" width = "360" height = "540" alt="App页面" align=center />

### [LYC](https://github.com/cccisi/PrivacyCollector/tree/master/app/src/main/java/com/cccisi/privacycollector/lyc)

**效果展示**
- #### 主页面

<img src="https://github.com/cccisi/PrivacyCollector/blob/master/doc/Lyc_home.jpg" width = "360" height = "530" alt="用户页面" align=center />

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

