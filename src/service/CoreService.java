package service;


import util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;


/**
 * Created by kail on 2017/4/26.
 */
public class CoreService {


    /**
     * 处理接收到的消息
     */
    public static String processRequest(HttpServletRequest request) {

        String timeStr = request.getParameter("time");
        String mCurrentLon = request.getParameter("east");
        String mCurrentLat = request.getParameter("north");
        String userId = request.getParameter("userId");

        System.out.println(userId+","+timeStr+","+mCurrentLat+","+mCurrentLon);
        if (timeStr == null || mCurrentLon == null || mCurrentLat == null||userId==null) {
//        if(true){
            return "入参错误";
        }
        if (mCurrentLon.equals("0.0")||mCurrentLat.equals("0.0")){
            return "入参错误";
        }
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.134.161.68:1521:orcl", "LSPWYW", "lspwyw123");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@26.47.182.109:1521:orcl", "LSPWYW", "lspwyw123");
            stmt = conn.createStatement();
            stmt.execute("insert into t_qx_zb (sj,east,north,userid) values(to_date('" + timeStr + "','yyyy-mm-dd hh24:mi:ss'),'" + mCurrentLon + "',+'" + mCurrentLat +"',+'"+userId+ "')");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "数据库连接错误";

        } catch (SQLException e) {
            e.printStackTrace();
            return "数据插入错误";
        } finally {
            try {
               /* if (rs != null) {
                    rs.close();
                    rs = null;
                }*/
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                return "数据插入错误";
            }
        }
        return "ok";
    }


}
