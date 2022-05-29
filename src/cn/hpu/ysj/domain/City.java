package cn.hpu.ysj.domain;

import java.io.Serializable;

/**
 * 城市表
 *
 * @TableName t_city
 */
public class City implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 城市代码
     */
    private Integer code;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 上级城市代码
     */
    private Integer pcode;

    /**
     * 层级（1，2, 3）
     */
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPcode() {
        return pcode;
    }

    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}