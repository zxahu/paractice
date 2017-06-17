/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.base;

public class SymbolTest {



    public static void main(String args[]) {

        int count = 3;
        // num << count  num 左移count位,相当于乘2 count次
        int num  = 1 << count;
        System.out.println(num + " " + Integer.toBinaryString(num));
        // num >> count  num 右移count位,相当于除2 count次
        num = (16 >> count);
        System.out.println(num + " " + Integer.toBinaryString(num));
        // 无符号右移,忽略符号位,空位以0补齐
        num = 65535 >>> 8;
        System.out.println(num + " " + Integer.toBinaryString(num));
        // 位与, 将2个数字按位与
        num =  511 & count;
        System.out.println(num + " " + Integer.toBinaryString(num));
    }


}
