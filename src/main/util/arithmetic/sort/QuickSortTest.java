package arithmetic.sort;

/**
 * 快速排序：
 * 通过一趟排序将待排序记录分割成独立的两部分
 * 其中一部分记录的关键字均比另一部分关键字小
 * 则分别对这两部分继续进行排序，直到整个序列有序
 */
public class QuickSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        quick(numbers);
        System.out.print("快速排序后：");
        printArr(numbers);

    }

    /**
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     * @param numbers 待查找数组
     * @param low  开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int getMiddle(int[] numbers,int low,int high){
        int temp = numbers[low]; //数组的第一个作为中轴
        while(low < high) {
            while(low < high && numbers[high] > temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while(low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low] ; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low ; // 返回中轴的位置
    }

    /**
     * 递归形式的分治排序算法
     * @param numbers
     * @param low
     * @param high
     */
    public static void quickSort(int[] numbers,int low,int high){
        if (low < high){
            int middle = getMiddle(numbers,low,high);
            quickSort(numbers,low,middle-1);
            quickSort(numbers,middle+1,high);
        }
    }

    /**
     * 快速排序
     * @param numbers
     */
    public static void quick(int[] numbers){
        if (numbers.length > 0){
            quickSort(numbers,0,numbers.length-1);
        }
    }

    /**
     * 打印
     * @param numbers
     */
    public static void printArr(int[] numbers){
        for (int i=0; i<numbers.length; i++){
            System.out.print(numbers[i] + ",");
        }
        System.out.println("");
    }
}

