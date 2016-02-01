package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 2016年2月1日 15:44:41 序列化工具：将object序列化成byte[]，反之将byte[]反序列化成Object
 * 
 * 这个工具用apache的SerializationUtils，一样的
 * 
 * 工具类，从一定程度上来看，就是一种【模版模式】
 */
public class SerializeTools {

	/**
	 * 将对象序列化成byte[]
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(final Serializable obj) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		serialize(obj, baos);
		return baos.toByteArray();
	}
	
	/**
	 * 序列化对象
	 * @param obj
	 * @param out
	 */
	public static void serialize(final Serializable obj, OutputStream outputStream) throws IOException {
		if(outputStream == null) {
			throw new IllegalArgumentException("The OutputStream must not be null");
		}
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(outputStream); // 是靠ObjectOutputStream来序列化的
			out.writeObject(obj);
		} finally {
			try {
				if (out != null) out.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(final byte[] bytes) throws IOException, ClassNotFoundException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		return deserialize(bais);
	}
	
	/**
	 * 反序列化对象，支持不停地读取inputstream
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(final InputStream inputStream) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(inputStream);
			return in.readObject();
		} finally {
			try {
				if(in != null) in.close();
			} catch (Exception e) {
				// ignore
			}
		}
	}

}
