package mvc.tools;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

public class FileUpload {    //文件上传工具类

    //工具类：一个类中，方法，复用的，解决某个地方问题

    public static HashMap<String, String> getFromValues(HttpServletRequest request) {

        HashMap<String, String> map = new HashMap<>();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {  //有下一条数据 进入{}

                //已知 两条数据
                FileItemStream stream = iterator.next();
                String key = stream.getFieldName();
                String value = stream.getName();


                //打开文件项流，输入流，输入读取数据，inputStream指向提交的数据 遍历出来的第一个
                InputStream inputStream = stream.openStream();

                //判断 当前取出数据，是普通数据还是文件数据
                if (stream.isFormField()) {
                    //普通数据，改变表单编码格式：字符流 传递  字节流

                    //java字符流，转为字节流
                    InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");

                    //没学过 缓冲流，提高读取效率
                    BufferedReader bufferedReader = new BufferedReader(reader);

                    String data = bufferedReader.readLine();

                    map.put(key, data);  //得到 form表单中的name值  输入的数据
                } else {

                    if (!value.equals("")) {
                        //选择文件上穿
                        //文件数据
                        String path = request.getSession().getServletContext().getRealPath("img");
                        //时间戳 + 随机数
                        LocalDateTime localDateTime = LocalDateTime.now(); //本地年月日时分秒
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss"); //时间格式转为字符串
                        String timestamp = formatter.format(localDateTime);
                        Random random = new Random();
                        int i = random.nextInt(1000);
                        value = "测试文件_" + timestamp + "_" + i + value;

                        //文件名字存入map
                        map.put(key, value);

                        File file = new File(path + "\\" + value);
                        map.put("path",path);
                        int size;
                        byte[] bytes = new byte[1024];

                        OutputStream outputStream = new FileOutputStream(file);

                        while ((size = inputStream.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, size);
                        }
                        outputStream.close();  //关闭资源
                    } else {
                        //用户 没有选择文件
                        map.put(key, "没有上传文件");
                    }
                    inputStream.close();  //关闭资源
                }
            }

        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException("IO读取报错了");
        }
        return map;
    }

}
