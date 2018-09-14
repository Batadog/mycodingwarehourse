package arithmetic.sort;

/**
 * 排序
 */
public class sortedUtils  {
// 冒泡排序
  public static int [] bubbling( int [] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
          for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j]>arr[j+1]){
                int temp = arr[j];
                arr[j]=arr[j+1];
                arr[j+1]=temp;
             }
          }
      }
        return arr;
  }
  // 选择排序
  public static int [] selectSort(int[] arr){
      for (int i= 0;i<arr.length-1;i++){
          for (int j = i;j<arr.length-1;j++){
              if (arr[i]>arr[j+1]){
                  int temp = arr[i];
                  arr[i]=  arr[j+1];
                  arr[j+1]=temp;
              }
          }
      }
     return arr;
  }
  // 二分查找，数组必须有序从小到大。
  public static int halfSearch(int [] arr,int key) {
      int low = 0;
      int h = arr.length - 1;
      while (low <= h) {
          int mid = (h + low) / 2;
          if (arr[mid] == key) {
              return mid;
          } else if (arr[mid] > key) {
              h = mid - 1;
          } else if (arr[mid] < key) {
              low = mid + 1;
          }
      }
      return -1;
  }
  //快排
  public static void sort(int[] a,int low,int high){
      int start = low;
      int end = high;
      int key = a[low];
      while(end>start){
          //从后往前比较
          while(end>start&&a[end]>=key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
              end--;
          if(a[end]<=key){
              int temp = a[end];
              a[end] = a[start];
              a[start] = temp;
          }
          //从前往后比较
          while(end>start&&a[start]<=key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
              start++;
          if(a[start]>=key){
              int temp = a[start];
              a[start] = a[end];
              a[end] = temp;
          }
          //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
      }
      //递归
      if(start>low) sort(a,low,start-1);//左边序列。第一个索引位置到关键值索引-1
      if(end<high) sort(a,end+1,high);//右边序列。从关键值索引+1到最后一个
  }

    public static void main(String[] args) {
        int [] arr = {12,34,56,33,221,67,15,67,85};
     //  bubbling(arr);
        //sort(arr,0,8);
        for (int i =0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
}
