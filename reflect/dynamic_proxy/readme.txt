2012年10月27日 12:38:16
动态代理是AOP的原理

动态代理只支持接口
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
