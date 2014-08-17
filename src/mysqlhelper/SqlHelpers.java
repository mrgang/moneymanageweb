package mysqlhelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

/**
 * Created by 刚 on 2014/7/28.
 */
public class SqlHelpers {
    public static String sqlQuery(String userName, String password) {
        String rtString = "fail";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/managemoney";
            String user = "root", psw = "";
            Connection conn = DriverManager.getConnection(url, user, psw);
            if (conn != null) {
                System.out.println("链接数据库成功!");
            }
            Statement statement = conn.createStatement();
            String sql = "select * from userInfo where userName = '" + userName + "'";
            ResultSet rs = statement.executeQuery(sql);
            String res;
            while (rs.next()) {
                res = rs.getString("password");
                if (res.equals(password)) {
                    rtString = "success";
                }
                System.out.println(res + ":" + password);
            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登陆结果：" + rtString);
        return rtString;
    }

    public static String SqlInsert(String userName, String password) {
        String resInsert = "fail";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/managemoney";
            String user = "root", psw = "";
            conn = DriverManager.getConnection(url, user, psw);
            if (conn != null) {
                System.out.println("链接数据库成功!");
            }
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO userInfo VALUES ('" + userName + "','" + password
                    + "')";
            statement.executeUpdate(sql);
            System.out.println("插入数据：" + userName + " and " + password);
            resInsert = "success";
            String sqlAdd = "CREATE TABLE "+userName+"(flag char(20),num char(20)," +
                    "mdescribe varchar(255),mDate char (20))";
            Statement ps = conn.createStatement();
            ps.executeUpdate(sqlAdd);
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(resInsert);
        return resInsert;
    }

    public static String SqlAddData(String key,String flag,String mdescribe,String num,String mDate) {
        String resInsert = "fail";
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/managemoney";
            String user = "root", psw = "";
            conn = DriverManager.getConnection(url, user, psw);
            if (conn != null) {
                System.out.println("链接数据库成功!");
            }
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO "+key+" VALUES ('" + flag + "','" + num +"','"+mdescribe+"','"+mDate
                    + "')";
            statement.executeUpdate(sql);
            System.out.println("插入数据：" + flag + " and " + num+" and "+mdescribe+" and "+mDate);
            resInsert = "success";
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        System.out.println(resInsert);
        return resInsert;
    }
    public static String sqlGetHistory(String userName) {
        JSONArray jsonArray = new JSONArray();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/managemoney";
            String user = "root", psw = "";
            Connection conn = DriverManager.getConnection(url, user, psw);
            if (conn != null) {
                System.out.println("链接数据库成功!");
            }
            Statement statement = conn.createStatement();
            String sql = "select * from "+userName;
            ResultSet rs = statement.executeQuery(sql);

            JSONObject objectTemp;
            while (rs.next()) {
                objectTemp = new JSONObject();
                objectTemp.put("flag",rs.getString("flag"));
                objectTemp.put("num",rs.getString("num"));
                objectTemp.put("mdescribe",rs.getString("mdescribe"));
                objectTemp.put("date",rs.getString("mDate"));
                System.out.println("从数据库得到的数据： "+rs.getString("mdescribe"));
                jsonArray.put(objectTemp);

            }
            rs.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray.toString();
    }
}
