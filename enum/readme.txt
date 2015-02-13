如果没有用枚举的话，一般常沿用c模式，使用public static final int ABC = 0; 来用。

但是这种方式本质是int，不同的枚举之间没法编译期检查。
此外必须保证int的值确定之后就不要变化了，不然其他程序就得跟着改。

枚举种类其本质是final对象。

==EnumSet==
在C语言中，经常用移位来表示bit set，在java中，就可以用Set<Enum>来表示，或者用EnumSet来表示。

枚举类型也可以implements接口的。

==使用interface来定义枚举值==
2015年2月13日 14:38:28
使用interface来定义变量有2个好处，第一是默认public，第二是默认final。
所以特别适合用于定义了基本类型或String的常量。
然后通过import static XXX.* 导入进来，就可以像C/C++一样来使用了