package com.bobo.books.bean;

/**
 * 借书卡的Bean
 */
public class ReaderCard {

    private Integer id; // 借书卡ID
    private Integer stuId; // 学生编号
    private String stuName; // 学生名称
    private String password; // 借书卡密码
    private Integer state ; // 借书卡的状态
    private Integer isDeleted; // 是否被删除

    @Override
    public String toString() {
        return "ReaderCard{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
