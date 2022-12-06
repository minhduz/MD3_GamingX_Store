package ra.model.daoImp;

import ra.model.dao.PlatformDao;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlatformDaoImp implements PlatformDao<Platform,Integer> {
    @Override
    public List<Platform> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Platform> listPlatform = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllPlatforms()}");
            ResultSet rs = callSt.executeQuery();
            listPlatform = new ArrayList<>();
            while (rs.next()) {
                Platform pl = new Platform();
                pl.setPlatformID(rs.getInt("PlatformID"));
                pl.setPlatformName(rs.getString("PlatformName"));
                listPlatform.add(pl);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listPlatform;
    }

    @Override
    public Platform getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Platform platform = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getPlatformByID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                platform = new Platform();
                platform.setPlatformID(rs.getInt("PlatformID"));
                platform.setPlatformName(rs.getString("PlatformName"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
           ConnectionDB.closeConnection(conn,callSt);
        }
        return platform;
    }

    @Override
    public boolean save(Platform platform) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertPlatform(?)}");
            callSt.setString(1,platform.getPlatformName());
            callSt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
            result = false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean update(Platform platform) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updatePlatform(?,?)}");
            callSt.setInt(1,platform.getPlatformID());
            callSt.setString(2,platform.getPlatformName());
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
    public boolean delete(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deletePlatform(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
}
