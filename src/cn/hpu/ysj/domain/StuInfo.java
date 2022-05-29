package cn.hpu.ysj.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @TableName t_stuinfo
 */
public class StuInfo implements Serializable {

    public StuInfo() {
    }

    public StuInfo(String name, String gender, String address, Date birthday) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
    }

    /**
     * 主键
     */
    private Integer id;

    /**
     * 学生姓名，非空
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生地
     */
    private String address;

    /**
     * 出生日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}