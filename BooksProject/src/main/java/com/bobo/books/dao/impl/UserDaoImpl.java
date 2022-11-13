package com.bobo.books.dao.impl;

import com.bobo.books.bean.User;
import com.bobo.books.dao.IUserDao;
import com.bobo.books.utils.DelFlagE;
import com.bobo.books.utils.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Dao接口的实现类
 * 命名规范 放在impl子包中，以Impl结尾
 */
public class UserDaoImpl implements IUserDao {

    @Override
    public List<User> list(User user) {
        // 通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        // 构建SQL语句
        String sql = "select * from t_user where is_deleted = ?";
        List<User> list = null;
        try {
            // BeanListHandler 不会帮我们完成驼峰命名的自动转换
            // list = queryRunner.query(sql, new BeanListHandler<User>(User.class));
            list = queryRunner.query(sql, new ResultSetHandler<List<User>>() {
                @Override
                public List<User> handle(ResultSet resultSet) throws SQLException {
                    List<User> list = new ArrayList<>();
                    while (resultSet.next()){
                        User user = new User();
                        user.setId(resultSet.getInt("id"));
                        user.setUserName(resultSet.getString("user_name"));
                        user.setPassword(resultSet.getString("password"));
                        user.setEmail(resultSet.getString("email"));
                        user.setPhoneNum(resultSet.getString("phone_num"));
                        user.setSalt(resultSet.getString("salt"));
                        user.setIsDeleted(resultSet.getInt("is_deleted"));
                        list.add(user);
                    }
                    return list;
                }
            },DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * 添加用户的方法
     * @param user
     * @return
     */
    @Override
    public Integer save(User user) {
        // 1.获取QueryRunner对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into t_user(user_name,password,phone_num,email,salt)values(?,?,?,?,?)";
        try {
            return  queryRunner.update(sql,user.getUserName()
                    ,user.getPassword()
                    ,user.getPhoneNum()
                    ,user.getEmail()
                    ,user.getSalt());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0; // 返回0说明插入失败
    }

    /**
     * 根据用户编号删除用户
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        // 物理删除
        //String sql = "delete from t_user where id = ?";
        // 逻辑删除
        String sql = "update t_user set is_deleted = ? where id = ?";
        try {
            // 把 is_deleted 字段更新为0 表示这条记录被删除了【逻辑删除】
            return queryRunner.update(sql, DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public User queryById(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from t_user where id = ? and is_deleted = ? ";
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        User user = new User();
                        user.setUserName(rs.getString("user_name"));
                        user.setId(id);
                        user.setPassword(rs.getString("password"));
                        user.setPhoneNum(rs.getString("phone_num"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            },id,DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Integer updateUser(User user) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update t_user set user_name = ? ,password=? ,phone_num=? ,email=? where id = ?";
        try {
            return queryRunner.update(sql,user.getUserName(),user.getPassword(),user.getPhoneNum(),user.getEmail(),user.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public String checkUserName(String userName) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select count(1) from t_user where is_deleted = ? and user_name = ?";
        try {
            int count = queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);
                    return count;
                }
            },DelFlagE.NO.code,userName);
            return count == 0 ? "success" : "error";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "error";
    }

    @Override
    public User checkUserNameAndPassword(String userName, String password) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from t_user where is_deleted=? and user_name=? and password=?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        User user = new User();
                        user.setUserName(rs.getString("user_name"));
                        user.setId(rs.getInt("id"));
                        user.setPassword(rs.getString("password"));
                        user.setPhoneNum(rs.getString("phone_num"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            },DelFlagE.NO.code,userName,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserDaoImpl dao = new UserDaoImpl();
        for (User user : dao.list(null)) {
            System.out.println(user);
        }
    }
}
