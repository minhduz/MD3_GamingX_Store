package ra.model.daoImp;

import ra.model.dao.ReviewDao;
import ra.model.entity.Review;
import ra.model.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImp implements ReviewDao<Review,Integer> {
    @Override
    public List<Review> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Review> listReview = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllReviewByAdmin()}");
            ResultSet rs = callSt.executeQuery();
            listReview = new ArrayList<>();
            while (rs.next()){
                Review review = new Review();
                review.setReviewID(rs.getInt("ReviewID"));
                review.setUserName(rs.getString("Username"));
                review.setReviewGameName(rs.getString("GameName"));
                review.setReviewContent(rs.getString("Contents"));
                review.setReviewStatus(rs.getBoolean("ReviewStatus"));
                listReview.add(review);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listReview;
    }

    public List<Review> getAllReviewByGameID(Integer id){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Review> listReview = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllReviewByGameID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            listReview = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review();
                review.setUserAvatar(rs.getString("Avatar"));
                review.setUserName(rs.getString("Username"));
                review.setReviewContent(rs.getString("Contents"));
                listReview.add(review);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listReview;
    }

    @Override
    public Review getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Review review = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getReviewByID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            review = new Review();
            if(rs.next()){
                review.setReviewID(rs.getInt("ReviewID"));
                review.setUserName(rs.getString("Username"));
                review.setReviewGameName(rs.getString("GameName"));
                review.setReviewContent(rs.getString("Contents"));
                review.setReviewStatus(rs.getBoolean("ReviewStatus"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return review;
    }

    @Override
    public boolean save(Review review) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertReview(?,?,?,?)}");
            callSt.setInt(1,review.getUserID());
            callSt.setInt(2,review.getGameID());
            callSt.setString(3,review.getReviewContent());
            callSt.setBoolean(4,review.isReviewStatus());
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
    public boolean update(Review review) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateReviewStatus(?,?)}");
            callSt.setInt(1,review.getReviewID());
            callSt.setBoolean(2, review.isReviewStatus());
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
        return false;
    }
}
