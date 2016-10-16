# Java反射機制

## 1. 簡介

Java反射機制程序在運行時動態地獲取類信息、域值，調用方法等。反射機制會損耗一些性能，卻提供C/C++語言所沒有的動態特性。Java框架一般大量地使用反射機制，很大程度減少程序員開發的代碼量。在開發效率和運行效率之間，需要折中平衡。Java反射機制涉及的類，這些類均在java.lang.reflect包下：

Class 對Java中類的信息的封裝，通過類名的class屬性或對象的getClass()方法獲得。接口也是屬於Class類。

Field 域，對應於類中的屬性。通過Class對象的getDeclaredFields()方法獲得。

Method 對應於類中的方法。通過Class對象的getMethods()方法獲得該類的public方法，過getDeclaredMethods()方法獲得該類所有方法，但不含父類。

Constructor 對應於類的構造方法。

Modifier 類、方法、域等的權限控制，如private、public等

在Class對象中，有一個詞Declared，表示獲得所有定義的域、方法等，包括private權限。如果沒有加，則只獲得public權限的。

推薦一篇寫得很全面的博客:http://www.cnblogs.com/stephen-liu74/archive/2011/09/04/2166192.html。

## 2. 基本使用

獲得Class對象

有三種方式可以獲得Class對象：

```java
Class<?> stringClass1 = String.class;
Object obj = "hello";
Class<?> stringClass2 = obj.getClass();
Class<?> stringClass3 = Class.forName("java.lang.String");
```

三種方式是一樣的，但是前兩種速度幾無差別，第三種比前兩種慢約14倍。

通過Class對象創建對象

Class<?> stringClass = String.class;
String str = (String) stringClass.newInstance();

這種方式比直接new String()要慢10倍左右。此外可以獲得對象的構造方法，調用構造方法創建對象，好處是可以傳遞參數：

Class<?> stringClass = String.class;
Constructor<?> constructor = stringClass.getConstructor(String.class);
String hello = (String) constructor.newInstance("hello");

動態獲得方法對象並調用

Class<?> stringClass = String.class;
// 遍歷對象的所有方法（只有public方法）
Method[] methods = stringClass.getMethods();
for(int i=0; i< methods.length; i++) {
	System.out.println(methods[i].getName());
}

// 動態調用對象的length()方法
String hello = "hello";
Method method = stringClass.getMethod("length");
int length = (Integer) method.invoke(hello);
System.out.println(length);
一個可以加快調用Method對象的方法，在調用invoke之前將method對象設置為：

method.setAccessible(true); //去掉安全性檢查
動態獲得屬性列表及其值

// 遍歷對象的所有屬性（包括private屬性）
Field fields[] = stringClass.getDeclaredFields();
for(int i=0; i< fields.length; i++) {
	System.out.println(fields[i].getName());
}
 
// 獲得私有對象的值
String hello = "hello";
Field field = stringClass.getDeclaredField("value");
field.setAccessible(true);
char[] value = (char[]) field.get(hello);
// 對應的，調用field.set方法設置屬性值
System.out.println(value); // 輸出hello
一般情況下，很少使用屬性來操作一個對象，除非一些靜態屬性。Java的習慣是使用方法來操作對象。

數組相關

數組本身也是一個類。反射創建數組：

String[] strs = (String[]) Array.newInstance(String.class, 10);

## 3. Java Bean操作

通過java.beans.BeanInfo及相關類可以方法對java bean進行操作，很方便。java bean是為類屬性實現了標準getter和setter方法的類，也稱為POJO。

		// 第一步，獲得beanInfo
		BeanInfo beanInfo = Introspector.getBeanInfo(String.class);
 
		// 第二步，獲得PropertyDescriptor，這些就是getter和setter方法對應的屬性集合
		PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
 
		for (PropertyDescriptor prop : props) {
			System.out.println(prop.getName());
			// 第三步，重要的使用
 
			// 獲得該屬性的類型
			System.out.println(prop.getPropertyType());
			// 獲得該屬性的getter方法
			System.out.println(prop.getReadMethod());
			// 獲得該屬性的setter方法
			System.out.println(prop.getWriteMethod());			
 
		}