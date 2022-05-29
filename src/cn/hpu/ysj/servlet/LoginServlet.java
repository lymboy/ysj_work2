package cn.hpu.ysj.servlet;

import cn.hutool.core.util.StrUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-29
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");
        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");

        if ("admin".equals(username) &&
                "123456".equals(password) &&
                StrUtil.isNotEmpty(captcha) &&
                StrUtil.isNotEmpty(sessionCaptcha) &&
                captcha.equals(sessionCaptcha)) {

            req.getSession().setAttribute("isLogin", true);

            resp.sendRedirect("stu.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
