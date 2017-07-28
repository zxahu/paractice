/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.pattern.dynamic;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String args[]) {
        RealSubject realSub = new RealSubject();
        ProxyHandler handler = new ProxyHandler(realSub);
        Object obj = Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                handler);
        Subject proxySubject = (Subject) obj;

        proxySubject.doSth();
        createProxyClassFile();
    }

    /**
     * loader : 一个ClassLoader对象, 定义由哪个ClassLoader对象来对生成的代理对象进行加载
     * interfaces : 一个Interface对象的数组, 表示我将要给我需要代理的对象提供一组什么接口, 如果我提供一组接口给它,则这个代理对象就宣称实现了该接口,这样我们就能调用这组接口的方法了
     * handler : 一个InvocationHandler对象, 表示的是我这个动态代理对象在调用方法的时候,会关联到哪个InvocationHandler对象上
     */
    //public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,  InvocationHandler h)  throws IllegalArgumentException

    public static void createProxyClassFile() {
        String name = "ProxySubject";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{Subject.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
