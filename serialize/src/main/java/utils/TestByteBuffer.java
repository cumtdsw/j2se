package utils;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 2017年12月19日 17:42:52
 * ByteBuffer可以将基本类型写为byte[]，算是一个工具类
 */
public class TestByteBuffer {

	public static void main(String[] args) {
		
		int id = 3;
		int age = 18;
		
		// 没有自动扩容功能，要用netty的ChannelBuffer.dynamicBuffer()方法
		ByteBuffer byteBuffer = ByteBuffer.allocate(128);  
		byteBuffer.putInt(id); // int 是大端方式，先写高位再写低位
		byteBuffer.putInt(age);
		
		byte[] output = byteBuffer.array(); // 序列化出来的byte[]
		System.out.println(Arrays.toString(output));
		
		// ========= 反序列化
		
		ByteBuffer byteBuffer2 = ByteBuffer.wrap(output);
		System.out.println("id:" + byteBuffer2.getInt());
		System.out.println("age:" + byteBuffer2.getInt());
	}
	
}
