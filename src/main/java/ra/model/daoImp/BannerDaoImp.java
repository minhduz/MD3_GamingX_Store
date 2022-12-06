package ra.model.daoImp;

import ra.model.dao.BannerDao;
import ra.model.entity.Banner;
import ra.model.entity.Category;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BannerDaoImp implements BannerDao<Banner,Integer> {
    @Override
    public List<Banner> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Banner> listBanner = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllBanner()}");
            ResultSet rs = callSt.executeQuery();
            listBanner = new ArrayList<>();
            while (rs.next()){
                Banner ba = new Banner();
                ba.setBannerID(rs.getInt("BannerID"));
                ba.setBannerImage(rs.getString("BannerImage"));
                ba.setBannerTitle(rs.getString("BannerTitle"));
                ba.setBannerContent(rs.getString("BannerContent"));
                listBanner.add(ba);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listBanner;
    }

    @Override
    public Banner getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Banner ba = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getBannerByID(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            ba = new Banner();
            if(rs.next()){
                ba.setBannerID(rs.getInt("BannerID"));
                ba.setBannerImage(rs.getString("BannerImage"));
                ba.setBannerTitle(rs.getString("BannerTitle"));
                ba.setBannerContent(rs.getString("BannerContent"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return ba;
    }

    @Override
    public boolean save(Banner banner) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertBanner(?,?,?)}");
            callSt.setString(1,banner.getBannerImage());
            callSt.setString(2,banner.getBannerTitle());
            callSt.setString(3,banner.getBannerContent());
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
    public boolean update(Banner banner) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateBanner(?,?,?,?)}");
            callSt.setInt(1,banner.getBannerID());
            callSt.setString(2,banner.getBannerImage());
            callSt.setString(3,banner.getBannerTitle());
            callSt.setString(4,banner.getBannerContent());
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
    public boolean delete(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteBanner(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
            result =false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }
}
