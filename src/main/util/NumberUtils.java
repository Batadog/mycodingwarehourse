

import java.math.BigDecimal;

/**
 * 数字格式化工具类
 *
 * @author Administrator
 */
public class NumberUtils {

    /**
     * 格式化小数
     *
     * @param num   浮点
     * @param scale 四舍五入的位数
     * @return 格式化小数
     */
    public static double formatDouble(double num, int scale) {
        BigDecimal bd = new BigDecimal(num);
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断是否是素数、遍历0- n 之间的素数
     *
     * @param n
     * @return
     */
    public static boolean checkPrimeNumber(int n) {
        for (int i = 2; i <= n; i++) // 1不是素数，所以直接从2开始循环
        {
            int j = 2;
            while (i % j != 0) {
                j++; // 测试2至i的数字是否能被i整除，如不能就自加
            }
            if (j == i)
            //当有被整除的数字时，判断它是不是自身,若是，则说明是素数
            {
                System.out.println(i); // 如果是就打印出数字

                // BigFileSplitAndMerge.saveRecordInFile(i+"\t","//"); 保存到文件
            }
            // 返回个判断值，判断输入的n是否是质数
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    // 判读是否是质数
    public static boolean primeNmuber(int number) {
        boolean check = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }




}
