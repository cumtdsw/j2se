泛型

泛型最重要的作用是：【编译期】让编译器进行类型检查，从而避免了运行期ClassCastException异常。
【本质】上，泛型只是编译期的概念，编译完之后，代码只有类声明、方法声明的地方有泛型标志，
代码本身并没有泛型的信息。

使用泛型的好处：for不需要自己cast类型了。

当原生态类型和泛型之间发生赋值时，就会出现unchecked警告。要尽可能去消除。

泛型的几种形式：

1. 类和接口定义时名称上加上<T>或<T1,T2>等。
2. 方法在关键词后面，返回值前面加上<E>。

泛型支持限制<T extends Number>

泛型不支持基本类型 int long等，只支持引用类型。

PECS producer-extends consumer-super  **难点**
所有的comparable和comparator都是消费者。