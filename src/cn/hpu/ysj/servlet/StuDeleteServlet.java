package cn.hpu.ysj.servlet;

import cn.hpu.ysj.dao.StuInfoDao;
import cn.hpu.ysj.domain.City;
import cn.hpu.ysj.util.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-28
 */
@WebServlet("/stu/delete")
public class StuDeleteServlet extends HttpServlet {

    private StuInfoDao stuInfoDao = new StuInfoDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuId = req.getParameter("stuId");
        stuInfoDao.delete(stuId);
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type", "application/json;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.print(JSONObject.toJSONString(AjaxResult.success()));
            out.flush();
        } catch (IOException e) {
            System.out.println("结果序列化错误！");
        }
    }
}
