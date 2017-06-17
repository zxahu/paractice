/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class factory  {
    public static void main(String args[]){
        ClassPathResource resource = new ClassPathResource("/Users/zxahu/zx/code/practice/src/main/resources/config/appcontext-core.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource
        );
    }

}
