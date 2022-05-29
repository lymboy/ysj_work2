package cn.hpu.ysj.util;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-27
 */
public class DBUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBUtil.class) {
                if (connection == null) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connection = DriverManager.getConnection("jdbc:mysql://clustera04.lymboy.com:3310/stu_manager?characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false", "ysj", "ysj@666$666");
                    } catch (Exception e) {
                        System.out.println("数据库连接获取出错！");
                    }

                }
            }
        }
        return connection;
    }
}
