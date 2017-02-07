/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = {
        "classpath*:config/appcontext-*.xml"
})
public class SpringTestNGSupport extends AbstractTestNGSpringContextTests {
}
