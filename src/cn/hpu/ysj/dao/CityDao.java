package cn.hpu.ysj.dao;

import cn.hpu.ysj.domain.City;
import cn.hpu.ysj.domain.StuInfo;
import cn.hpu.ysj.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-27
 */
public class CityDao {

    Connection connection = DBUtil.getConnection();

    public List<City> listAll() {

        List<City> cityList = new ArrayList<>();
        try {
            //
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM t_city WHERE level != 3");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getLong("id"));
                city.setCode(resultSet.getInt("code"));
                city.setName(resultSet.getString("name"));
                city.setPcode(resultSet.getInt("pcode"));
                city.setLevel(resultSet.getInt("level"));
                cityList.add(city);
            }
        } catch (SQLException throwables) {

        }
        return cityList;
    }


}
