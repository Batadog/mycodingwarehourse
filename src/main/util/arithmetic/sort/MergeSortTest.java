package arithmetic.sort;

/**
 * 归并排序
 * 将两个（或两个以上）有序表合并成一个新的有序表
 * 即把待排序序列分为若干个子序列，每个子序列是有序的
 * 然后再把有序子序列合并为整体有序序列
 */
public class MergeSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

       mergeSort(numbers,0,numbers.length-1);
        System.out.print("插入排序后：");
        printArr(numbers);


    }

    private static int[] mergeSort(int[] numbers,int low,int high) {
        int mid = (low + high) / 2;
        if (low < high){
            // 左边
            mergeSort(numbers, low, mid);
            // 右边
            mergeSort(numbers, mid + 1, high);
            // 左右归并
            merge(numbers, low, mid, high);
        }
        return numbers;
    }

    private static void merge(int[] numbers, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (numbers[i] < numbers[j]) {
                temp[k++] = numbers[i++];
            } else {
                temp[k++] = numbers[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = numbers[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = numbers[j++];
        }

        // 把新数组中的数覆盖numbers数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            numbers[k2 + low] = temp[k2];
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
