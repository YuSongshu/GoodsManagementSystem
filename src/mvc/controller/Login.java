package mvc.controller;

import mvc.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 控制器 ： 调配 M + V，接值
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //业务类：专门user表 操作
        UserService service = new UserService();

        // 3种结果: 管理员   用户    失败  -> 跳转不同页面
        String result = service.login(username,password);

        if (result.equals("user")){
            response.sendRedirect("index.jsp");
        }else if (result.equals("error")){
            response.sendRedirect("login.jsp?error=500");  //重定向：自动跳转
        }else {
            //管理员
//            response.sendRedirect("adminHome.jsp");
            response.sendRedirect("QueryGoodsAll");  //重定向 默认  get
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}