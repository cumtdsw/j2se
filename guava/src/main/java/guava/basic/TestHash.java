package guava.basic;

import com.google.common.hash.Hashing;

/**
 * 2015年9月15日 17:36:13
 */
public class TestHash {

	public static void main(String[] args) {
		// guava可以很方便的将byte[]转测md5 sha1  XXX 看一下这个工具的实现
		System.out.println(Hashing.md5().hashBytes("hello".getBytes()).toString());
		System.out.println(Hashing.sha1().hashBytes("hello".getBytes()).toString());
	}
	
}
