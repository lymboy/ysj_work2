//package cn.hpu.ysj.filter;
//
//import com.github.javafaker.Bool;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * <p></p>
// *
// * @author 小怪兽
// * @version 1.0
// * @since 2022-05-29
// */
//@WebFilter(urlPatterns = "/stu/*")
//public class PermissionFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//        Boolean isLogin = (Boolean) req.getSession().getAttribute("isLogin");
//        if (isLogin) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            resp.sendRedirect("login.jsp");
//        }
//    }
//}
