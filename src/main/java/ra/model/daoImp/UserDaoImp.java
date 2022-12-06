package ra.model.daoImp;

import ra.model.dao.UserDao;
import ra.model.entity.Category;
import ra.model.entity.User;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao<User,Integer> {

    @Override
    public List<User> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> listUser = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllUser()}");
            ResultSet rs = callSt.executeQuery();
            listUser = new ArrayList<>();
            while (rs.next()){
                User us = new User();
                us.setUserID(rs.getInt("UserID"));
                us.setUserName(rs.getString("Username"));
                us.setUserFullName(rs.getString("FullName"));
                us.setUserAvatar(rs.getString("Avatar"));
                us.setUserPermission(rs.getBoolean("Permission"));
                us.setUserStatus(rs.getBoolean("UserStatus"));
                listUser.add(us);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listUser;
    }

    @Override
    public User getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getUserByID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("Username"));
                user.setUserPassword(rs.getString("Password"));
                user.setUserFullName(rs.getString("FullName"));
                user.setUserAge(rs.getInt("Age"));
                user.setUserAvatar(rs.getString("Avatar"));
                user.setUserPhone(rs.getString("Phone"));
                user.setUserEmail(rs.getString("Email"));
                user.setUserPermission(rs.getBoolean("Permission"));
                user.setUserStatus(rs.getBoolean("UserStatus"));
                user.setUserFollowing(rs.getString("Following"));
            }
        }catch (SQLException ex){
            ex .printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertUser(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1,user.getUserName());
            callSt.setString(2,user.getUserFullName());
            callSt.setString(3,user.getUserPassword());
            callSt.setString(4,user.getUserConfirmPassword());
            callSt.setString(5,user.getUserAvatar());
            callSt.setInt(6,user.getUserAge());
            callSt.setString(7,user.getUserPhone());
            callSt.setString(8,user.getUserEmail());
            callSt.setBoolean(9,user.isUserPermission());
            callSt.setBoolean(10,user.isUserStatus());
            callSt.executeUpdate();
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateUserByAdmin(?,?,?,?)}");
            callSt.setInt(1,user.getUserID());
            callSt.setString(2,user.getUserPassword());
            callSt.setString(3,user.getUserConfirmPassword());
            callSt.setBoolean(4, user.isUserStatus());
            callSt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
            result= false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<User> getUsersByUserName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<User> listUser = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getUserByUsername(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            listUser = new ArrayList<>();
            while (rs.next()){
                User us = new User();
                us.setUserID(rs.getInt("UserID"));
                us.setUserName(rs.getString("Username"));
                us.setUserFullName(rs.getString("FullName"));
                us.setUserAvatar(rs.getString("Avatar"));
                us.setUserPermission(rs.getBoolean("Permission"));
                us.setUserStatus(rs.getBoolean("UserStatus"));
                listUser.add(us);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listUser;
    }

    public boolean checkLogin(String uName, String uPassword){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_checkLogin(?,?,?)}");
            callSt.setString(1,uName);
            callSt.setString(2,uPassword);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int count = callSt.getInt(3);
            if(count == 1){
                result = true;
            }else {
                result = false;
            }
        }catch (SQLException ex){
            result =false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean checkPermission(String uName, String uPassword){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_checkPermission(?,?,?)}");
            callSt.setString(1,uName);
            callSt.setString(2,uPassword);
            callSt.registerOutParameter(3, Types.INTEGER);
            callSt.execute();
            int count = callSt.getInt(3);
            if(count == 1){
                result = true;
            }else {
                result = false;
            }
        }catch (SQLException ex){
            result =false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public int getUserIDByUsername(String uName){
        Connection conn = null;
        CallableStatement callSt = null;
        int result = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getUserIDByUsername(?,?)}");
            callSt.setString(1,uName);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            result = callSt.getInt(2);
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
}
