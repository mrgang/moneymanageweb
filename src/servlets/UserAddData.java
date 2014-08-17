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
 * Created by 刚 on 2014/7/29.
 */
@WebServlet("/servlets/userAddData")
public class UserAddData extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        String key = req.getParameter("key");
        String flag = req.getParameter("flag");
        String describe = req.getParameter("describe");
        String num = req.getParameter("num");
        String mDate = req.getParameter("date");
        System.out.println("服务器得到的数据"+describe);
        String res = SqlHelpers.SqlAddData(key,flag,describe,num,mDate);
        PrintWriter out = resp.getWriter();
        out.println(res);
        out.flush();
    }
}
