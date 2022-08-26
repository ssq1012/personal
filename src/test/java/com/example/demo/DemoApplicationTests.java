package com.example.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URI;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
   public void contextLoads() {

    }

    @Test
    public void testMethod(){
        String s = "We are very happy.";
        char[] array = s.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (char a :array) {
            if ((int) a == 32){
                buffer.append("%20");
            }else {
                buffer.append(a);
            }
        }
        System.out.println(buffer.toString());
    }

    @Test
    public void sum(){
        int sum = getSum(10);
        System.out.println( "递归结果" + sum);
        int i = sumTail(10, 0);
        System.out.println("尾递归结果:" + i);
    }

    int getSum(int n){
        if(1 == n){
            return 1;
        }
        return n + getSum(n - 1);
    }

    int sumTail(int n, int s) {
        if (n == 1) {
            return 1 + s;
        }
        return sumTail(n - 1, n + s);
    }

    @Test
    public void testCopyEntity() throws Exception{
//        URL url = new URL("http://www.baidu.com");
        String filePath = "D:\\文档存放\\test.txt";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36";
        URL url = new URL("http://fantia.jp/posts/1134255/post_content_photo/4676125");
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("User-Agent",userAgent);
        httpGet.addHeader("accept","*/*");
        httpGet.addHeader("connection","Keep-Alive");
        httpGet.addHeader("cookie","_session_id=90490105aec1204309f76ba516af2237; _ga=GA1.2.1799273109.1645582320; _gid=GA1.2.958424600.1645582320");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (null != httpResponse){
            HttpEntity entity = httpResponse.getEntity();
            if (httpResponse.getEntity().isStreaming()){
                writeToPdf(entity,filePath);
            }
            System.out.println(httpResponse.getEntity().getContent());
        }
    }

    @Test
    public void testTwo() throws Exception{
//        URL url = new URL("http://www.baidu.com");
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36";
        URL url = new URL("https://cc.fantia.jp/uploads/post_content_photo/file/4676125/cbc063e2-b59f-48cf-bd09-c39c36eb2f9a.jpeg?Key-Pair-Id=APKAIOCKYZS7WKBB6G7A&Policy=eyJTdGF0ZW1lbnQiOlt7IlJlc291cmNlIjoiaHR0cHM6Ly9jYy5mYW50aWEuanAvdXBsb2Fkcy9wb3N0X2NvbnRlbnRfcGhvdG8vZmlsZS80Njc2MTI1L2NiYzA2M2UyLWI1OWYtNDhjZi1iZDA5LWMzOWMzNmViMmY5YS5qcGVnIiwiQ29uZGl0aW9uIjp7IkRhdGVMZXNzVGhhbiI6eyJBV1M6RXBvY2hUaW1lIjoxNjQ1NTk0NTI3fX19XX0_&Signature=kml83iElITNyFl0lTyJu4vKV2ppUBwSMJoFWnU3yhQC3TGF9C5TGT-naztJyJl-deB0vNcWssuGewQno1e89meEAK8-WBWxAHZrWzzPXVXZ~tfRXzFtap93BT~KaoLP2lgGcVDLaayF9Ft-ccmYmAvKJhaDsbCaIaQfKivg1zeiAvaksMM33yYUAd4Bc6PHG2z9oOY4Ng4DYpKTZ3PkbH8w3NzjMgf3cmadwD02j90MZOjWv5lYXDZRmIlCfVhLC6dVeV6kgl4CAZkECn7XCl-se3rp3fGHSNtGWVK0~QEtyQJUylhuy2e8GnPE4upLKsTjnL8iVpyqDujFiuLt1bA__");
        URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("User-Agent",userAgent);
        httpGet.addHeader("connection","Keep-Alive");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (null != httpResponse)
        System.out.println(httpResponse.getEntity().getContent());
    }

    public long writeToPdf(HttpEntity entity, String filePath)throws Exception{
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int size = 0;
        try{
            byte[] bytes = EntityUtils.toByteArray(entity);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer,0,length);
                length = bis.read(buffer);
            }
            bos.flush();
            return bis.available();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                bos.close();
                fos.close();
                bis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return size;
    }

        public byte[] create(int n){
            byte[] bits = new byte[getIndex(n) + 1];

            for(int i = 0; i < n; i++){
                add(bits, i);
            }

            System.out.println(contains(bits, 11));

            int index = 1;
            for(byte bit : bits){
                System.out.println("-------" + index++ + "-------");
                showByte(bit);

            }

            return bits;
        }

        /**
         * 标记指定数字（num）在bitmap中的值，标记其已经出现过<br/>
         * 将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了
         * @param bits
         * @param num
         */
        public void add(byte[] bits, int num){
            bits[getIndex(num)] |= 1 << getPosition(num);
        }

        /**
         * 判断指定数字num是否存在<br/>
         * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
         * @param bits
         * @param num
         * @return
         */
        public boolean contains(byte[] bits, int num){
            return (bits[getIndex(num)] & 1 << getPosition(num)) != 0;
        }

        /**
         * num/8得到byte[]的index
         * @param num
         * @return
         */
        public int getIndex(int num){
            return num >> 3;
        }

        /**
         * num%8得到在byte[index]的位置
         * @param num
         * @return
         */
        public int getPosition(int num){
            return num & 0x07;
        }

        /**
         * 重置某一数字对应在bitmap中的值<br/>
         * 对1进行左移，然后取反，最后与byte[index]作与操作。
         * @param bits
         * @param num
         */
        public void clear(byte[] bits, int num){
            bits[getIndex(num)] &= ~(1 << getPosition(num));
        }

        /**
         * 打印byte类型的变量<br/>
         * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
         */

        public void showByte(byte b){
            byte[] array = new byte[8];
            for(int i = 7; i >= 0; i--){
                array[i] = (byte)(b & 1);
                b = (byte)(b >> 1);
            }

            for (byte b1 : array) {
                System.out.print(b1);
                System.out.print(" ");
            }

            System.out.println();
        }

        @Test
        public  void testBitMap() {
            int temp = 0;
            int[] args = {10,2,5,3,4,9,1,8,7};
            //冒泡排序
            for (int i = 0; i < args.length -1; i++) {
                //逐个两两比较
                for (int j = 0; j < args.length - i - 1; j++) {
                 if (args[j] > args[j+1]){
                     //交换
                    temp = args[j+1];
                    args[j+1] = args[j];
                    args[j] = temp;
                 }
                }
            }
            for (int a:args) {
                System.out.print(a + ",");
            }
        }
        public void swap(int[] args ,int i, int j){
            int  temp = 0;
            temp = args[i];
            args[i] = args[j];
            args[j] = temp;
        }

        @Test
        public void testRead() throws Exception{
            String filePath = "D:\\文档存放\\数据读取.txt";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader streamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String text = null;
            while (null != (text = bufferedReader.readLine())){
                stringBuffer.append(text);
            }
            bufferedReader.close();
            streamReader.close();
            fileInputStream.close();
            System.out.println(stringBuffer.toString());
        }

}
