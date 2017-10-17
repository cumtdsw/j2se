package com.pugwoo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 测试替换图片颜色，只留下蓝色
 * @author nick
 */
public class ChangeColor {

	public static void main(String[] args) throws Exception {
		BufferedImage qrcode = ImageIO.read(MergeImages.class.getResourceAsStream("/qrcode.jpg"));
        for (int i = 0; i < qrcode.getWidth(); i++) {
            for (int j = 0; j < qrcode.getHeight(); j++) {
            	Color color = new Color(qrcode.getRGB(i, j));
            }
        }
        ImageIO.write(qrcode, "jpg", new File("D:/qrcode.jpg"));
	}
	
}
