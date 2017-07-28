/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.pattern.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 每个动态代理类都必须要实现InvocationHandler,每个代理类的实例都关联到一个handler
 * 当我们调用代理对象的方法时,这个方法的调用会被转发为由InvocationHandler这个接口的invoke方法进行调用
 */
public class ProxyHandler implements InvocationHandler {

    private Object proxied;

    public ProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    /**
     *
     * @param proxy 指代 代理的那个真实对象
     * @param method 指代 索要调用真是对象的某个方法的Method对象
     * @param args 指代 调用真是方法时接受的参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke...");
        method.invoke(proxied, args);
        System.out.println("after invoke...");
        return null;
    }
}
