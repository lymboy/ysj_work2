package main.resources;

import cn.hpu.ysj.dao.StuInfoDao;
import cn.hpu.ysj.domain.StuInfo;
import com.github.javafaker.Faker;

import java.sql.Date;
import java.util.Locale;

/**
 * <p></p>
 *
 * @author 小怪兽
 * @version 1.0
 * @since 2022-05-27
 */
public class DBTest {

    private static StuInfoDao stuInfoDao = new StuInfoDao();

    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);
        for (int i = 0; i < 100; i++) {
            StuInfo stuInfo = new StuInfo();
            String name = faker.name().fullName(); // Miss Samanta Schmidt
            String address = faker.address().fullAddress(); // Emory
            Date birthday = new Date(faker.date().birthday().getTime());

            stuInfo.setName(name);
            stuInfo.setGender("男");
            stuInfo.setAddress(address);
            stuInfo.setBirthday(birthday);
            stuInfoDao.add(stuInfo);
        }
    }
}
