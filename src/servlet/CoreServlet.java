package servlet;


import service.CoreService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 类名: CoreServlet </br>
 * 描述: 来接收微信服务器传来信息 </br>
 * 开发人员： souvc</br>
 * 创建时间：2015-9-29 </br>
 * 发布版本：V1.0 </br>
 */
public class CoreServlet extends HttpServlet {

    /**
     * 确认请求来自微信服务器
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String str = CoreService.processRequest(request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\"code\":100,\"info\":\"保存成功\"}";

        if (!str.equals("ok")) {
            jsonStr = "{\"code\":101,\"info\":\"失败:"+str+"\"}";
        }
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}