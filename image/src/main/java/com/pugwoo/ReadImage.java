package com.pugwoo;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class ReadImage {

	public static void main(String[] args) throws Exception {
		String url = "https://www.baidu.com/img/bd_logo1.png";
		BufferedImage image = ImageIO.read(new URL(url)); // 可以从网络上获取图片
		// 可以从File InputStream获取图片
		System.out.println("height:" + image.getHeight() + ",width:" + image.getWidth());
	}
	
}
