package mvc.controller;

import mvc.bean.Goods;
import mvc.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QueryGoodsAll", value = "/QueryGoodsAll")
public class QueryGoodsAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GoodsService goodsService = new GoodsService();
        List<Goods> list = goodsService.queryGoodsAll();

        //list数据 传递 复杂类型 只能用 请求转发 request
        request.setAttribute("list",list);   //设置属性，
        request.getRequestDispatcher("adminHome.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}