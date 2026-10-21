package mvc.controller;

import mvc.service.GoodsService;
import mvc.tools.FileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AddGoods", value = "/AddGoods")
public class AddGoods extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //form表单来的（数据）
        HashMap<String, String> map = FileUpload.getFromValues(request);

        //MVC开发模式：接收数据，调用业务，根据业务返回结果决定去往那个视图
        GoodsService goodsService = new GoodsService();
        boolean bol = goodsService.addGoods(map);

        //根据业务返回结果决定去往那个视图
        if (bol){
            response.sendRedirect("QueryGoodsAll");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}