package mvc.dao;

import mvc.tools.DbUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {   //针对用户表 数据库访问 操作 CRUD

    private DbUtils dbUtils = new DbUtils();


    public ArrayList<HashMap<String, Object>> queryUserByUsername(String username) {

        String sql = "select * from user where username = '"+username+"'";

        ArrayList<HashMap<String, Object>> list = dbUtils.query(sql);

        return list;

    }
}
