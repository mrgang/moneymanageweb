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
@WebServlet("/servlets/userGetHistoryB")
public class UserGetHistoryB extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String kk = req.getParameter("userName");
        String res = SqlHelpers.sqlGetHistory(kk);
        PrintWriter out = resp.getWriter();
        out.println(res);
        out.flush();
    }
}
