package cn.hpu.ysj.servlet;

import cn.hpu.ysj.dao.CityDao;
import cn.hpu.ysj.dao.StuInfoDao;
import cn.hpu.ysj.domain.City;
import cn.hpu.ysj.domain.StuInfo;
import cn.hpu.ysj.util.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

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
@WebServlet("/cityList")
public class CityListServlet  extends HttpServlet {

    private CityDao cityDao = new CityDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("content-type", "application/json;charset=UTF-8");
        List<City> cityList = cityDao.listAll();
        try (PrintWriter out = resp.getWriter()) {
            out.print(JSONObject.toJSONString(AjaxResult.success(cityList)
                    , SerializerFeature.PrettyFormat
                    , SerializerFeature.WriteMapNullValue
                    , SerializerFeature.WriteDateUseDateFormat));
            out.flush();
        } catch (IOException e) {
            System.out.println("结果序列化错误！");
        }
    }
}
