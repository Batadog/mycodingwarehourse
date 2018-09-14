package baidu.day01;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;

public class JavaSparkWordCount{
    public static void main(String[] args) {

        SparkConf conf = new SparkConf()
                .setAppName("JavaSparkWC")
                .setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        // 获取数据
        JavaRDD<String> lines = jsc.textFile(args[0]);

        // 切分
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            public Iterable<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" "));
            }
        });

        // 生成元组
        JavaPairRDD<String, Integer> tuples = words.mapToPair(
                new PairFunction<String, String, Integer>() {

                    public Tuple2<String, Integer> call(String word) throws Exception {
                        return new Tuple2<String, Integer>(word, 1);
                    }
                });

        // 聚合
        JavaPairRDD<String, Integer> sumed = tuples.reduceByKey(
                new Function2<Integer, Integer, Integer>() {

                    public Integer call(Integer v1, Integer v2) throws Exception {
                        return v1 + v2;
                    }
                });

        // java api中没有提供sortBy算子，如果需要以value进行排序，
        // 需要把tuple里的数据反转一下，排序完成后，在反转回来
        JavaPairRDD<Integer, String> swaped = sumed.mapToPair(new PairFunction<Tuple2<String, Integer>, Integer, String>() {

            public Tuple2<Integer, String> call(Tuple2<String, Integer> tup) throws Exception {
//                return new Tuple2<Integer, String>(tup._2, tup._1);
                return tup.swap();
            }
        });

        // 降序排序
        JavaPairRDD<Integer, String> sorted = swaped.sortByKey(false);

        // 再反转回来
        JavaPairRDD<String, Integer> res = sorted.mapToPair(
                new PairFunction<Tuple2<Integer, String>, String, Integer>() {

                    public Tuple2<String, Integer> call(Tuple2<Integer, String> tup) throws Exception {
                        return tup.swap();
                    }
                });

        System.out.println(res.collect());
        res.saveAsTextFile(args[1]);

        jsc.stop();
    }
}
