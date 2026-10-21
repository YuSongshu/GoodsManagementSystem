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

@WebServlet(name = "UpdateGoodsById", value = "/UpdateGoodsById")
public class UpdateGoodsById extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> map = FileUpload.getFromValues(request);
        GoodsService goodsService = new GoodsService();
        boolean bol = goodsService.updateGoodsById(map);
        if (bol){
            response.sendRedirect("QueryGoodsAll");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}