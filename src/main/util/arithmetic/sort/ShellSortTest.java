package arithmetic.sort;

/**
 * 希尔排序：
 * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序
 * 待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
 */
public class ShellSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        shellSort(numbers);
        System.out.print("希尔排序后：");
        printArr(numbers);
    }
    public static void shellSort(int[] numbers){
        int j = 0;
        int temp = 0;
        for (int increment=numbers.length/2; increment>0; increment/=2){
            for (int i=increment; i<numbers.length; i++){
                temp = numbers[i];
                for (j=i; j>=increment; j-=increment){
                    if (temp > numbers[j-increment]){   //如想从小到大排只需修改这里
                        numbers[j] = numbers[j-increment];
                    }else {
                        break;
                    }
                }
                numbers[j] = temp;
            }
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
