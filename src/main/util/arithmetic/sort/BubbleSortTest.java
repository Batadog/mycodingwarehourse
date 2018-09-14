package arithmetic.sort;

import static arithmetic.sort.HeapSortTest.swap;

/**
 * 冒泡排序:
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，最后的元素应该会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */
public class BubbleSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        bubbleSort(numbers);
        System.out.print("冒泡排序后：");
        printArr(numbers);
    }
    /**
     * 冒泡排序
     * @param numbers
     */
    public static void bubbleSort(int[] numbers){
        int temp = 0;
        boolean flag = false;
        int size = numbers.length;
        for (int i=0; i<size-1; i++){
            for (int j=0; j<size-1-i; j++){
                if (numbers[j] > numbers[j+1]){

                    temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                    flag =true;
                }
            }
            if (flag==false){
                break;
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
