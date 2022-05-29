package cn.hpu.ysj.domain;

import java.io.Serializable;

/**
 * 
 * @TableName t_user
 */
public class User implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 账户名，非空
     */
    private String name;

    /**
     * 密码，非空
     */
    private String password;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}