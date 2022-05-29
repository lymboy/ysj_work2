package cn.hpu.ysj.dao;

import cn.hpu.ysj.domain.StuInfo;
import cn.hpu.ysj.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-27
 */
public class StuInfoDao {

    Connection connection = DBUtil.getConnection();

    public List<StuInfo> listAll() {

        List<StuInfo> stuInfos = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM t_stuinfo");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                StuInfo stuInfo = new StuInfo();
                stuInfo.setId(resultSet.getInt("id"));
                stuInfo.setName(resultSet.getString("name"));
                stuInfo.setGender(resultSet.getString("gender"));
                stuInfo.setAddress(resultSet.getString("address"));
                stuInfo.setBirthday(resultSet.getDate("birthday"));
                stuInfos.add(stuInfo);
            }
        } catch (SQLException throwables) {

        }
        return stuInfos;
    }

    public void add(StuInfo stuInfo) {
        String sql = "INSERT INTO t_stuinfo(name, gender,address,birthday) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stuInfo.getName());
            preparedStatement.setString(2, stuInfo.getGender());
            preparedStatement.setString(3, stuInfo.getAddress());
            preparedStatement.setDate(4, stuInfo.getBirthday());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(StuInfo stuInfo) {
        String sql = "UPDATE t_stuinfo SET name=?, gender=?,address=?,birthday=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stuInfo.getName());
            preparedStatement.setString(2, stuInfo.getGender());
            preparedStatement.setString(3, stuInfo.getAddress());
            preparedStatement.setDate(4, stuInfo.getBirthday());
            preparedStatement.setInt(5, stuInfo.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String stuId) {
        String sql = "DELETE FROM t_stuinfo WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, stuId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
