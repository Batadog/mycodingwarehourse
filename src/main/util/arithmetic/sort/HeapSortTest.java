package arithmetic.sort;

/**
 * 堆排序：
 * 堆排序是一种树形选择排序，是对直接选择排序的有效改进。
 */
public class HeapSortTest {
    public static void main(String[] args) {
        int[] numbers = {10,20,15,3,6,7,2,1,32};
        System.out.print("排序前：");
        printArr(numbers);

        heapSort(numbers);
        System.out.print("堆排序后：");
        printArr(numbers);
    }
    public static void heapSort(int[] numbers){
        int size = numbers.length;
        //循环建堆
        for (int i=0; i<size-1; i++){
            //建堆
            buildMaxHeap(numbers,size-1-i);
            //交换堆顶和最后一个元素
            swap(numbers,0,size-1-i);
        }
    }

    /**
     * 对数组从0到lastIndex建大顶堆
     * @param numbers
     * @param lastIndex
     */
    private static void buildMaxHeap(int[] numbers, int lastIndex) {
        for(int i=(lastIndex-1)/2;i>=0;i--){
            //k保存正在判断的节点
            int k=i;
            //如果当前k节点的子节点存在
            while(k*2+1<=lastIndex){
                //k节点的左子节点的索引
                int biggerIndex=2*k+1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if(biggerIndex<lastIndex){
                    //若果右子节点的值较大
                    if(numbers[biggerIndex]<numbers[biggerIndex+1]){
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if(numbers[k]<numbers[biggerIndex]){
                    //交换他们
                    swap(numbers,k,biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k=biggerIndex;
                }else{
                    break;
                }
            }
        }
    }
    //交换
    private static void swap(int[] numbers, int i, int j) {
        int tmp=numbers[i];
        numbers[i]=numbers[j];
        numbers[j]=tmp;
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
