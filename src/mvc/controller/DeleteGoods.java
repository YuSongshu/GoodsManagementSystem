package mvc.controller;


import mvc.service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "DeleteGoods", value = "/DeleteGoods")
public class DeleteGoods extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String path = request.getSession().getServletContext().getRealPath("img");

        GoodsService service = new GoodsService();
        if (service.deleteGoods(id, path)) {
            response.sendRedirect("QueryGoodsAll");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
