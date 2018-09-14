package arithmetic.sort;

/**
 * 插入排序：
 * 每步将一个待排序的记录
 * 按其顺序码大小插入到前面已经排序的字序列的合适位置（从后向前找到合适位置后）
 * 直到全部插入排序完为止。
 */
public class InsertSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        insertSort(numbers);
        System.out.print("插入排序后：");
        printArr(numbers);
    }

    public static void insertSort(int[] numbers){
        int size = numbers.length;
        int temp = 0;
        int j = 0;

        for (int i=0; i<size; i++){
           temp = numbers[i];
            //假如temp比前面的值小，则将前面的值后移
           for (j=i; j>0 && temp<numbers[j-1]; j--){
               numbers[j] = numbers[j-1];

           }
           numbers[j] = temp;
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
