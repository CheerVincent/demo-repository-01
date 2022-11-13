package com.bobo.books.bean;

/**
 * 学生的实体对象
 */
public class Student {
    private Integer id;
    private String stuNum;
    private String stuName;
    private String phoneNum;
    private String gender;
    private String address;
    private Integer classId;
    private String className;
    private Integer departId;
    private String departName;
    private Integer isDeleted;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", classId=" + classId +
                ", className='" + className + '\'' +
                ", deptartId=" + departId +
                ", departName='" + departName + '\'' +
                '}';
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
