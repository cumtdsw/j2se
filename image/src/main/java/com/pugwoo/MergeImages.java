package com.pugwoo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MergeImages {

	public static void main(String[] args) throws Exception {
		BufferedImage background = 
				ImageIO.read(MergeImages.class.getResourceAsStream("/background.jpg"));
	    BufferedImage star = 
	    		ImageIO.read(MergeImages.class.getResourceAsStream("/star.jpg"));
	    
	    // 新增一张图片，和背景一样大
	    BufferedImage combined = new BufferedImage(background.getWidth(),
	    	background.getHeight(), BufferedImage.TYPE_INT_RGB); // 注意：RGB用于jpg格式，ARGB常用于png格式
	     
	    // 画背景
	    Graphics g = combined.createGraphics();
	    g.drawImage(background, 0, 0, null);
	    
	    // 画上面的图案
	    g.drawImage(star, 0, 0, 30, 30, null); // 可以指定图片xy位置和宽高大小
	    
	    // 输出
	    ImageIO.write(combined, "JPG", new File("d:/combined.jpg"));
	}
	
}
