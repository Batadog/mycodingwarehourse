package ideas;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
 /**
   * 程序实现了ZIP压缩。共分为2部分 ：
   * 压缩（compression）与解压（decompression）
   * <p>
   * 大致功能包括用了多态，递归等JAVA核心技术，可以对单个文件和任意级联文件夹进行压缩和解压。
    * 需在代码中自定义源输入路径和目标输出路径。
    * <p>
    * 在本段代码中，实现的是解压部分；压缩部分见本包中compression部分。
    * @author HAN
    *
    */
public class deCompression {
    /**
     * 解压到指定目录
     */
    public static void unZipFiles(String zipPath,String descDir)throws IOException
    {
        unZipFiles(new File(zipPath), descDir);
    }
    /**
     * 解压文件到指定目录
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile,String descDir)throws IOException
    {
        File pathFile = new File(descDir);
        if(!pathFile.exists())
        {
            pathFile.mkdirs();
        }
        //解决zip文件中有中文目录或者中文文件
        ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
        for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
        {
            ZipEntry entry = (ZipEntry)entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir+zipEntryName).replaceAll("\\*", "/");;
            //判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if(!file.exists())
            {
                file.mkdirs();
            }
            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if(new File(outPath).isDirectory())
            {
                continue;
            }
            //输出文件路径信息
            System.out.println(outPath);
            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while((len=in.read(buf1))>0)
            {
                out.write(buf1,0,len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }
    public static void main(String[] args) throws IOException {
        /**
         * 解压文件
         */
        File zipFile = new File("d:/资料.zip");
        String path = "d:/zipfile/";
        unZipFiles(zipFile, path);
    }
}