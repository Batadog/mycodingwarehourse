package com.qianfeng.common.MyUtils;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class selectWordOfFile {

    public static List ReadOfLine(String src, String destPath) {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> countlist = new ArrayList<Integer>();
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        File file = new File(src);
        BufferedReader reader = null;
        String temp = null;
        try {
            int count = 0;
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                count++;
                int NumString = RowSize(temp); //每行单词数
                mp.put(count, NumString); // map(行号，单词数)

                saveRowOfSpark(temp,list, count,destPath); //1、保存 Spark所在的行号。
                saveRowOfAandB(temp,count,destPath);      // 3、保存 存在a和b 的行
            }
                getMaxRow(mp, countlist, destPath);       // 2、单词数最多的行号，保存到文件，
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    // 取出map中  最大value 对应的key
    private static void getMaxRow(Map<Integer, Integer> mp, List list, String destPath) {
        for (int key : mp.keySet()) {
            list.add(mp.get(key));
        }
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int size = (int) list.get(i);
            max = (max > size) ? max : size;
        }
        for (int ke : mp.keySet()) {
            if (max == mp.get(ke)) {
                saveRecordInFile(ke + "", destPath);// 保存单词最多的 行数
            }
        }
    }
    // 是否存在 Spark该字符串
    private static  void saveRowOfSpark(String temp,List list,int count,String destPath) {
        String[] arrayStr = temp.split(" ");
        for (int i = 0; i < arrayStr.length; i++) {
            if (arrayStr[i].equals("Spark")) {
                    list.add(count);
                    saveRecordInFile(count + "", destPath); // 1、保存  Spark所在的行号。
            }
        }

    }
    // 保存ab 行号
    private static void saveRowOfAandB(String temp,int count,String destPath) {
        if (temp.contains("a")) {
            saveRecordInFile(count + ",rowOfA", destPath);
        } else if (temp.contains("b")) {
            saveRecordInFile(count + ",rowOfb", destPath);
        }
    }
    //行 单词数
    private static int RowSize(String temp) {
        String[] arrayStr = temp.split(" ");
        return arrayStr.length;
    }
    // 测试打印： 存在Spark字符的行号
    private static void printRow(List list) {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            int x = Integer.valueOf((int) (list.get(i)));
            System.out.println(x);
        }
    }

    // 接收字符保存。
    public static void saveRecordInFile(String newStr, String destPath) {
        File record = new File(destPath); // 保存结果文件
        FileWriter writer = null;
        try {
            if (!record.exists()) {
                // 文件不存在则新建
                File dir = new File(record.getParent());
                dir.mkdirs();
                record.createNewFile();
            }
            writer = new FileWriter(record, true);//追加写入文件。
            writer.write(newStr + ",\r\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        String sourcePath = "C:\\Users\\Administrator\\Documents\\Tencent Files\\939383186\\FileRecv\\6班试题\\testdata.txt";
        String destPath = "E:\\1.1.txt";
        ReadOfLine(sourcePath, destPath);
        printRow(ReadOfLine(sourcePath, destPath));


    }
}
