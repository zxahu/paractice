/* Project of UGC team

======================
Authors:zhangxin

======================
Description:


======================
*/
package com.zxahu.base;


import java.util.concurrent.ConcurrentHashMap;

public class SymbolTest {



    public static void main(String args[]) {
        System.out.println(1 << 30);
    }

    /**
     * 位移运算
     */
    public static void displacement() {
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

    /**
     * 位运算
     */
    private static void bitOp() {
        int base = 11; // 1011
        int cal = 5; // 0101
        // 位与
        int res = 11 & 5;
        System.out.println(res + " " + Integer.toBinaryString(res));
        // 位或
        res = 11 | 5;
        System.out.println(res + " " + Integer.toBinaryString(res));
        // 非
        res = ~ 11;
        System.out.println(res + " " + Integer.toBinaryString(res));
        // 异或
        res = base ^ cal;
        System.out.println(res + " " + Integer.toBinaryString(res));
    }

    /**
     * HashMap的hash方法执行过程
     * h ^= (h >>> 20) ^ (h >>> 12);
     * return h ^ (h >>> 7) ^ (h >>> 4);
     */
    private static void hash() {
        int hashCode = -998662594;
        System.out.println(hashCode + " " + Integer.toBinaryString(hashCode));

        int step1 = hashCode >>> 20;
        System.out.println(step1 + " " + Integer.toBinaryString(step1));

        int step2 = hashCode >>> 12;
        System.out.println(step2 + " " + Integer.toBinaryString(step2));

        int step3 = step1 ^ step2;
        System.out.println(step3 + " " + Integer.toBinaryString(step3));

        int step4 = hashCode ^ step3;
        System.out.println(step4 + " " + Integer.toBinaryString(step4));

        int step5 = step4 >>> 7;
        System.out.println(step5 + " " + Integer.toBinaryString(step5));

        int step6 = step4 >>> 4;
        System.out.println(step6 + " " + Integer.toBinaryString(step6));

        int res = step4 ^ step5 ^ step6;
        System.out.println(res + " " + Integer.toBinaryString(res));

        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        res = hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
        System.out.println(res + " " + Integer.toBinaryString(res));
    }

    private static void test() {
        final ConcurrentHashMap map = new ConcurrentHashMap();
        new Thread() {
            public void run() {
                while(true) {
                    for(int i = 0; i< 5; i++) {
                        map.put(i, "abc");
                    }
                }
            }
        }.start();
        new Thread() {
            public void run() {
                while (true) {
                    for(int i = 0; i< 5; i++) {
                        map.put(i, "def");
                    }
                }
            }
        }.start();
        new Thread() {
            public void run() {
                while(true) {
                    String res = "";
                    for(int i = 0; i< 5; i++) {
                        res += map.get(i) + " ";
                    }
                    System.out.println(res);
                }
            }
        }.start();

    }

}
