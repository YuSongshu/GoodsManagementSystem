package mvc.tools;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

//数据库工具类 方法 sql发送
public class DbUtils {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    private static String url;
    private static String username;
    private static String password;
    private static String driver;

    //静态代码块
    static {
        //1.读取 properties文件  现在 stream 之中
        InputStream stream  = DbUtils.class.getClassLoader().getResourceAsStream("db.properties");
        //2.创建Properties对象
        Properties properties = new Properties();
        try {
            //3.加载配置文件
            properties.load(stream);
            //4.获取数据
            driver = properties.getProperty("db.driver");
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            //5.加载驱动
            Class.forName(driver);
            //6.获取连接
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("ok了");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //之前 的更新方法
    public int update(String sql){
       try {
           statement = connection.createStatement();
           return statement.executeUpdate(sql);
       }catch (Exception e){
           e.printStackTrace();
       }
       return 0;
    }

    /**
     * 查询数据库，传入查询sql
     * @param sql
     */
    public ArrayList<HashMap<String,Object>> query(String sql){

        //java程序中 查询 ArrayList<HashMap<String,Object>>
        //目的：封装查询结果，简单易用 提高程序数据性能
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            //获取全面的信息,几列，每一列列名
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){
                //第一条数据 创建一个HashMap做缓存
                HashMap<String,Object> map = new HashMap<>();

                //有几列
                for (int i = 1; i <= metaData.getColumnCount();i++){
                    //列名 map key值
                    String key = metaData.getColumnName(i);
                    Object value = resultSet.getObject(metaData.getColumnName(i));
                    //存储到 map
                    map.put(key,value);
                }
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 预处理查询方法 防止sql注入
     * @param sql ? 参数占位
     * @param param 数据
     * @return
     */
    public ArrayList<HashMap<String,Object>>  preparedQuery(String sql,Object... param){
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();

        //声明
        PreparedStatement preparedStatement = null;
        try {

            //1.赋值
            preparedStatement = connection.prepareStatement(sql);

            //2.?赋值
            if (param != null && param.length > 0){
                for (int i = 0; i < param.length;i++){
                    preparedStatement.setObject(i + 1,param[i]);
                }
            }

            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()){  //每一条数据
                HashMap<String,Object> map = new HashMap<>();

                for (int i = 1; i < metaData.getColumnCount();i++){ //有几列数据
                    String key = metaData.getColumnName(i);
                    Object value = resultSet.getObject(key);
                    map.put(key,value);
                }
                list.add(map);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //释放预处理语句 对象 资源
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return  list;
    }

}
