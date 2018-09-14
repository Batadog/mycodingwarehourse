//字符串的模式匹配算法，之BF算法
public class BruteForcce {

    public static  int bruteFore(String src,String sub){
       int i = 0 ;
       int j = 0;
       int index = -1;
       while(i<src.length()&&j<sub.length()){
       if (src.charAt(i)==sub.charAt(j)){
           i++;
           j++;
       }else{
           i= i+1-j;
           j=0;
       }

       }
        if (j==sub.length()){
         return index=i-sub.length();
        }
        return index;
    }
}
