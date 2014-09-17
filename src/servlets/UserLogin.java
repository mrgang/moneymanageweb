package servlets;

import mysqlhelper.SqlHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 刚 on 2014/7/28.
 */
@WebServlet("/servlets/userLogin")
public class UserLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req
            , HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        String res = SqlHelpers.sqlQuery(userName, password);
        out.println(res);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("请使用Post方法访问！");
        out.flush();
    }
}
