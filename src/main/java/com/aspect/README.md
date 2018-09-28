# 动态代理

```$xslt
以下文字为学习(https://blog.csdn.net/luanlouis/article/details/24589193)的笔记：
```
## 代理模式的角色
1. Subject : 负责定义RealSubject和Proxy应该实现的接口   
2. RealSubject : 真正完成业务服务的功能
3. Proxy : 接收请求，并调研RealSubject的方法来实现特定功能

## 代理的种类
代理类的主要功能，其实就是在调用某个方法前后，做一些额外的业务。实现这个功能有几种办法，一个是静态代理，一个是动态代理。

1. 静态代理：代理类Proxy中的方法，都写死了调用指定某个RealSubject
2. 动态代理：将自己的方法功能的实现交给InvocationHandler，外界对Proxy角色中的每个方法的调用，Proxy都会交给InvocationHandler来处理，而InvocationHandler则调用具体的角色

## 动态代理的实现
动态生成的Proxy需要实现RealSubject的所有功能，这样Proxy才能代理RealSubject。在Java中，如果要Proxy要覆盖RealSubject的功能，有2种方式：
1. 定义一个功能接口，让Proxy和RealSubject都来实现这个接口 -- JDK的动态代理
2. Proxy继承RealSubject -- cglib

### 基于接口的JDK动态代理
1. 获取RealSubject的所有接口列表
2. 确认生成代理类的类名，默认为: com.sun.proxy.$ProxyXXX
3. 根据需要实现的接口信息，在代码中动态创建该Proxy类的字节码
4. 将对应的字节码转为对应的class对象
5. 创建InvocationHandler实例handler，用来处理Proxy所有方法调用
6. Proxy的class对象，以创建的handler对象为参数，实例化一个proxy对象

为了实现以上6点，需要下面的方法来支持：
```
Proxy类：
Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
返回代理类的实例，该接口可以将方法调用指派到指定的调用处理程序
```
```
InvocationHandler类：
Object invoke(Object proxy,Method method,Object[] args)
在代理实例上处理方法调用并返回结果
```

缺点：
1. RealSubject必须要继承Subject接口，如果不继承接口，则无法实现代理
2. Proxy只能代理在Subject中声明的方法，RealSubject中声明的非继承方法，都无法实现代理

### cglib动态代理
1. 查找RealSubject上所有非final的public类型的方法定义
2. 将这些方法转为字节码
3. 将组成的字节码转换成相应的代理class对象
4. 实现MethodInterceptor接口，用来处理对代理类上的所有方法的请求(类似于InvocationHandler)

生成的代理类，会继承RealSubject，如果callback不为空，那么在调用RealSubject方法的时候，会变为调用MethodInterceptor的inceptor()方法



