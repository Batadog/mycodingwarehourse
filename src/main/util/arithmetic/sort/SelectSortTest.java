package arithmetic.sort;

/**
 * 选择排序：
 * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
 * 然后在剩下的数当中再找最小的与第二个位置的数交换
 * 如此循环到倒数第二个数和最后一个数比较为止
 */
public class SelectSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        selectSort(numbers);
        System.out.print("选择排序后：");
        printArr(numbers);
    }

    public static void selectSort(int[] numbers){
        int size = numbers.length;
        int temp = 0;

        for (int i=0; i<size; i++){
            int k=i;
            for (int j=size-1; j>i; j--){
                if (numbers[j] < numbers[k]){
                    k=j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
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
