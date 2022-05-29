package cn.hpu.ysj.servlet;

import cn.hpu.ysj.dao.StuInfoDao;
import cn.hpu.ysj.domain.StuInfo;
import cn.hpu.ysj.util.AjaxResult;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-28
 */
@WebServlet("/stu/add")
public class StuAddServlet extends HttpServlet {

    private StuInfoDao stuInfoDao = new StuInfoDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type", "application/json;charset=UTF-8");

        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String address = req.getParameter("address");
        String birthdayStr = req.getParameter("birthday");

        System.out.println(req.getContentType());

        Date birthday = new Date(DateUtil.parse(birthdayStr).getTime());
        //try {
        //    birthday = new Date(format.parse(birthdayStr).getTime());
        //} catch (ParseException e) {
        //    System.out.println("日期格式化出错！");
        //}
        StuInfo stuInfo = new StuInfo(name, gender, address, birthday);
        stuInfoDao.add(stuInfo);

        try (PrintWriter out = resp.getWriter()) {
            out.print(JSONObject.toJSONString(AjaxResult.success()));
            out.flush();
        } catch (IOException e) {
            System.out.println("结果序列化错误！");
        }
    }
}
