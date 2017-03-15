## 简介

主要介绍Android 插件化之换肤的基本原理

## Demo介绍

### 安装体验
	
- [点击下载](http://blog.bihe0832.com/public/resource/plugin-theme-host.apk)
	
- 扫码下载APK：
	
	![Demo下载二维码](https://github.com/bihe0832/Android-Plugin/blob/master/res/theme-host.png )

### 效果展示

![Demo下载二维码](https://github.com/bihe0832/Android-Plugin/blob/master/res/theme-host.gif )

## 工程介绍

### 目录结构
	
 
 	├── Host : 宿主项目的工程
	│ 
	├── Theme1: 皮肤插件1的工程
	│ 
	├── Theme2: 皮肤插件2的工程
	│ 
	├── bin: 自动构建输出目录
	│ 
	└── build.sh: 自动构建脚本

### 运行方式

- 环境配置

	如何修改配置及运行工程，请参考本人博客：[终端基于gradle的开源项目运行环境配置指引](
http://blog.bihe0832.com/android-as-gradle-config.html)

- 运行顺序

	**如果是使用shell，直接执行命令以后，在根目录bin目录就会生成对应的theme-host.apk**，安装即可体验，如果是使用AS，请使用下面的顺序运行
	
	1. 运行Theme1，生成apk后拷贝到Host的assets，名称为Theme1.apk
	2. 运行Theme2，生成apk后拷贝到Host的assets，名称为Theme2.apk
	3. 运行Host，生成apk后安装体验


### 代码分析

**待添加**