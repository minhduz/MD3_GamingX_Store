package ra.model.daoImp;

import ra.model.dao.CategoryDao;
import ra.model.entity.Category;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDao<Category,Integer> {
    @Override
    public List<Category> getCategoriesByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getCategoryByName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()) {
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                ca.setCategoryParentName(rs.getString("ParentName"));
                listCategory.add(ca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategory;
    }


    public List<Category> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllCategoriesByList()}");
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()) {
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                ca.setCategoryParentName(rs.getString("ParentName"));
                listCategory.add(ca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategory;
    }

    @Override
    public Category getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Category ca = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getCategoryByID(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            ca = new Category();
            if (rs.next()) {
                ca.setCategoryID(rs.getInt("categoryId"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                ca.setCategoryParentName(rs.getString("ParentName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return ca;
    }

    @Override
    public boolean save(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertCategory(?,?,?,?)}");
            callSt.setString(1, category.getCategoryName());
            callSt.setBoolean(2, category.isCategoryAgeRestricted());
            callSt.setBoolean(3, category.isCategoryStatus());
            if (category.getCategoryParentID() == 0) {
                callSt.setNull(4, category.getCategoryParentID());
            } else {
                callSt.setInt(4, category.getCategoryParentID());
            }
            callSt.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    @Override
    public boolean update(Category category) {
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateCategory(?,?,?,?,?)}");
            callSt.setInt(1, category.getCategoryID());
            callSt.setString(2, category.getCategoryName());
            callSt.setBoolean(3, category.isCategoryAgeRestricted());
            callSt.setBoolean(4, category.isCategoryStatus());
            if (category.getCategoryParentID() == 0) {
                callSt.setNull(5, category.getCategoryParentID());
            } else {
                callSt.setInt(5, category.getCategoryParentID());
            }
            callSt.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }

        return result;
    }

    @Override
    public boolean delete(Integer id) {
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteCategory(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (SQLException ex) {
            result = false;
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public int getNumberOfPages(int objectInAPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        int pageNumber = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getNumberOfPageCategories(?,?)}");
            callSt.setInt(1, objectInAPage);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            pageNumber = callSt.getInt(2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return pageNumber;
    }

    public List<Category> getAllOnAPage(int pNumber,int oInAPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllCategories(?,?)}");
            callSt.setInt(1,pNumber);
            callSt.setInt(2,oInAPage);
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()) {
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                ca.setCategoryParentName(rs.getString("ParentName"));
                listCategory.add(ca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategory;
    }

    public List<Category> getAllParentCategoriesAtHome(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllParentCategoriesAtHome()}");
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()){
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                listCategory.add(ca);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listCategory;
    }

    public List<Category> getAllChildCategoriesAtHome(int pID){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllChildCategoriesAtHome(?)}");
            callSt.setInt(1,pID);
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()){
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setCategoryAgeRestricted(rs.getBoolean("AgeRestricted"));
                ca.setCategoryStatus(rs.getBoolean("CategoryStatus"));
                ca.setCategoryParentID(rs.getInt("ParentID"));
                listCategory.add(ca);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listCategory;
    }
}














