package mvc.service;

import mvc.bean.User;
import mvc.dao.UserDao;

import java.util.ArrayList;
import java.util.HashMap;

public class UserService {  //针对用户的业务类

    private UserDao userDao = new UserDao();

    public String login(String username, String password) {

        //1.通过username查询用户信息
        ArrayList<HashMap<String,Object>> list = userDao.queryUserByUsername(username);

        //2.我要使用 USer类 承载业务数据，list中数据 取出来 放在USer实体类中

        //2.1:确认一下list中有没有数据，username是否存在
        if (list.isEmpty()){
            return "error";
        }

        //2.2:校验密码
        HashMap<String,Object> map = list.get(0);

        User user = new User(Integer.parseInt(map.get("id").toString()),map.get("username").toString(),
                map.get("password").toString(),Integer.parseInt(map.get("role").toString()),
                Double.parseDouble(map.get("money").toString()),map.get("create_time").toString());

        if (!user.getPassword().equals(password)){
            return "error";
        }

        //密码没问题 判断登录人的身份即可
        if (user.getRole() == 0){
            return "user";
        }

        return "admin";


    }
}
