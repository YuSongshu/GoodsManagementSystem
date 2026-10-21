package mvc.controller;

import mvc.bean.Goods;
import mvc.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueryGoodsById", value = "/QueryGoodsById")
public class QueryGoodsById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        GoodsService goodsService = new GoodsService();
        Goods goods = goodsService.queryGoodsById(id);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("update_goods.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}