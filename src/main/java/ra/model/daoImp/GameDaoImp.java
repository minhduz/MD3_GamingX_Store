package ra.model.daoImp;

import com.sun.org.apache.bcel.internal.generic.AllocationInstruction;
import ra.model.dao.GameDao;
import ra.model.entity.Category;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.entity.SubImages;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImp implements GameDao<Game,Integer> {
    @Override
    public List<Game> getGamesByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Game> listGame = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getGameByName(?)}");
            callSt.setString(1,name);
            ResultSet rs = callSt.executeQuery();
            listGame = new ArrayList<>();
            while (rs.next()){
                Game ga = new Game();
                ga.setGameID(rs.getInt("GameID"));
                ga.setGameName(rs.getString("GameName"));
                ga.setGamePrice(rs.getFloat("GamePrice"));
                ga.setGameDescriptions(rs.getString("Descriptions"));
                ga.setGamePlatforms(rs.getString("Platforms"));
                ga.setGameDeveloper(rs.getString("Developer"));
                ga.setGamePublisher(rs.getString("Publisher"));
                ga.setGameMainImage(rs.getString("MainImage"));
                ga.setGameReleaseDate(rs.getDate("ReleaseDate"));
                ga.setGameDiscount(rs.getInt("Discount"));
                ga.setGameStatus(rs.getBoolean("GameStatus"));
                ga.setGameCategories(rs.getString("Categories"));
                listGame.add(ga);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listGame;
    }

    @Override
    public List<Game> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Game> listGame = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllGames()}");
            ResultSet rs = callSt.executeQuery();
            listGame = new ArrayList<>();
            while (rs.next()){
                Game ga = new Game();
                ga.setGameID(rs.getInt("GameID"));
                ga.setGameName(rs.getString("GameName"));
                ga.setGamePrice(rs.getFloat("GamePrice"));
                ga.setGameDescriptions(rs.getString("Descriptions"));
                ga.setGamePlatforms(rs.getString("Platforms"));
                ga.setGameDeveloper(rs.getString("Developer"));
                ga.setGamePublisher(rs.getString("Publisher"));
                ga.setGameMainImage(rs.getString("MainImage"));
                ga.setGameReleaseDate(rs.getDate("ReleaseDate"));
                ga.setGameDiscount(rs.getInt("Discount"));
                ga.setGameStatus(rs.getBoolean("GameStatus"));
                ga.setGameCategories(rs.getString("Categories"));
                listGame.add(ga);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listGame;
    }

    public List<Game> getAllGamesByCategoryName(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Game> listGame = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllGamesByCategoryName(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            listGame = new ArrayList<>();
            while (rs.next()){
                Game ga = new Game();
                ga.setGameID(rs.getInt("GameID"));
                ga.setGameName(rs.getString("GameName"));
                ga.setGamePrice(rs.getFloat("GamePrice"));
                ga.setGameDescriptions(rs.getString("Descriptions"));
                ga.setGamePlatforms(rs.getString("Platforms"));
                ga.setGameDeveloper(rs.getString("Developer"));
                ga.setGamePublisher(rs.getString("Publisher"));
                ga.setGameMainImage(rs.getString("MainImage"));
                ga.setGameReleaseDate(rs.getDate("ReleaseDate"));
                ga.setGameDiscount(rs.getInt("Discount"));
                ga.setGameStatus(rs.getBoolean("GameStatus"));
                ga.setGameCategories(rs.getString("Categories"));
                listGame.add(ga);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listGame;
    }

    @Override
    public Game getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Game ga = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getGameByID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                ga = new Game();
                ga.setGameID(rs.getInt("GameID"));
                ga.setGameName(rs.getString("GameName"));
                ga.setGamePrice(rs.getFloat("GamePrice"));
                ga.setGameDescriptions(rs.getString("Descriptions"));
                ga.setGamePlatforms(rs.getString("Platforms"));
                ga.setGameDeveloper(rs.getString("Developer"));
                ga.setGamePublisher(rs.getString("Publisher"));
                ga.setGameMainImage(rs.getString("MainImage"));
                ga.setGameReleaseDate(rs.getDate("ReleaseDate"));
                ga.setGameDiscount(rs.getInt("Discount"));
                ga.setGameStatus(rs.getBoolean("GameStatus"));
                ga.setGameCategories(rs.getString("Categories"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return ga;
    }

    public List<SubImages> getSubImagesByGameID(Integer id){
        Connection conn = null;
        CallableStatement callSt = null;
        List<SubImages> listSubImages = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getSubImagesByID(?)}");
            callSt.setInt(1,id);
            listSubImages = new ArrayList<>();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                SubImages si = new SubImages();
                si.setImageID(rs.getInt("ImageID"));
                si.setImageLink(rs.getString("ImageLink"));
                listSubImages.add(si);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listSubImages;
    }



    public List<Platform> getPlatformByGameID(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Platform> listPlatform = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getPlatformByGameID()}");
            ResultSet rs = callSt.executeQuery();
            listPlatform = new ArrayList<>();
            while (rs.next()){
                Platform pl = new Platform();
                pl.setPlatformID(rs.getInt("PlatformID"));
                pl.setPlatformName(rs.getString("PlatformName"));
                pl.setGameID(rs.getString("gameid"));
                listPlatform.add(pl);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listPlatform;
    }

    public List<Category> getCategoryByGameID(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> listCategory = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getCategoryByGameID()}");
            ResultSet rs = callSt.executeQuery();
            listCategory = new ArrayList<>();
            while (rs.next()){
                Category ca = new Category();
                ca.setCategoryID(rs.getInt("CategoryID"));
                ca.setCategoryName(rs.getString("CategoryName"));
                ca.setGameID(rs.getString("gameid"));
                listCategory.add(ca);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listCategory;
    }

    @Override
    public boolean save(Game game) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertGame(?,?,?,?,?,?,?,?,?,?)}");
            callSt.setString(1,game.getGameName());
            callSt.setFloat(2,game.getGamePrice());
            callSt.setString(3,game.getGameDescriptions());
            callSt.setString(4,game.getGameDeveloper());
            callSt.setString(5,game.getGamePublisher());
            callSt.setString(6,game.getGameMainImage());
            callSt.setDate(7,new Date(game.getGameReleaseDate().getTime()));
            callSt.setInt(8,game.getGameDiscount());
            callSt.setBoolean(9, game.isGameStatus());
            callSt.registerOutParameter(10, Types.INTEGER);
            callSt.execute();
            int gameID = callSt.getInt(10);
            for (Integer cID: game.getListGameCategoriesID()) {
                CallableStatement callSt2 = conn.prepareCall("{call proc_insertGameCategory(?,?)}");
                callSt2.setInt(1,gameID);
                callSt2.setInt(2,cID);
                callSt2.executeUpdate();
                callSt2.close();
            }
            for (Integer plID:game.getListGamePlatformID()){
                CallableStatement callSt3 = conn.prepareCall("{call proc_insertGamePlatforms(?,?)}");
                callSt3.setInt(1,gameID);
                callSt3.setInt(2,plID);
                callSt3.executeUpdate();
                callSt3.close();
            }
            for (String imgLink: game.getListGameSubImages()) {
                CallableStatement callSt4 = conn.prepareCall("{call proc_insertSubImages(?,?)}");
                callSt4.setString(1,imgLink);
                callSt4.setInt(2,gameID);
                callSt4.executeUpdate();
                callSt4.close();
            }
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }



    @Override
    public boolean update(Game game) {
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateGame(?,?,?,?,?,?,?,?,?,?,?)}");
            callSt.setInt(1,game.getGameID());
            callSt.setString(2,game.getGameName());
            callSt.setFloat(3,game.getGamePrice());
            callSt.setString(4,game.getGameDescriptions());
            callSt.setString(5,game.getGameDeveloper());
            callSt.setString(6,game.getGamePublisher());
            callSt.setString(7, game.getGameMainImage());
            callSt.setDate(8,new Date(game.getGameReleaseDate().getTime()));
            callSt.setInt(9,game.getGameDiscount());
            callSt.setBoolean(10,game.isGameStatus());
            callSt.registerOutParameter(11, Types.INTEGER);
            callSt.execute();
            int gameID = callSt.getInt(11);
            CallableStatement callSt1 = conn.prepareCall("{call proc_deleteGameCategory(?)}");
            callSt1.setInt(1,gameID);
            callSt1.executeUpdate();
            callSt1.close();
            for (Integer cID:game.getListGameCategoriesID()) {
                CallableStatement callSt2 = conn.prepareCall("{call proc_insertGameCategory(?,?)}");
                callSt2.setInt(1,gameID);
                callSt2.setInt(2,cID);
                callSt2.executeUpdate();
                callSt2.close();
            }
            CallableStatement callSt3 = conn.prepareCall("{call proc_deleteGamePlatform(?)}");
            callSt3.setInt(1,gameID);
            callSt3.executeUpdate();
            callSt3.close();
            for (Integer plID:game.getListGamePlatformID()){
                CallableStatement callSt4 = conn.prepareCall("{call proc_insertGamePlatforms(?,?)}");
                callSt4.setInt(1,gameID);
                callSt4.setInt(2,plID);
                callSt4.executeUpdate();
                callSt4.close();
            }
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean updateSubImage(SubImages subImages){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateSubImagesByID(?,?)}");
            callSt.setInt(1,subImages.getImageID());
            callSt.setString(2,subImages.getImageLink());
            callSt.executeUpdate();
        }catch (SQLException ex){
            result= false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean saveSubImage(SubImages subImages){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertSubImages(?,?)}");
            callSt.setString(1,subImages.getImageLink());
            callSt.setInt(2,subImages.getGameID());
            callSt.executeUpdate();
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean deleteSubImage(Integer id){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteSubImages(?)}");
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

    @Override
    public boolean delete(Integer id) {
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteGame(?)}");
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

    public boolean insertIntoTrending(Game game){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertTrending(?,?,?,?)}");
            callSt.setInt(1,game.getGameID());
            callSt.setString(2,game.getGameName());
            callSt.setFloat(3,game.getGamePrice());
            callSt.setString(4,game.getGameMainImage());
            callSt.executeUpdate();
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public List<Game> getAllTrendingGame(){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Game> listGame = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllTrending()}");
            ResultSet rs = callSt.executeQuery();
            listGame = new ArrayList<>();
            while (rs.next()){
                Game game = new Game();
                game.setGameID(rs.getInt("GameID"));
                game.setGameName(rs.getString("GameName"));
                game.setGamePrice(rs.getFloat("Price"));
                game.setGameMainImage(rs.getString("MainImage"));
                listGame.add(game);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listGame;
    }

    public boolean deleteTrending(Integer id){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteTrending(?)}");
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

    public Game getGameDetailByGame(String name){
        Connection conn = null;
        CallableStatement callSt = null;
        Game ga = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getGameDetailByName(?)}");
            callSt.setString(1,name);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()) {
                ga = new Game();
                ga.setGameID(rs.getInt("GameID"));
                ga.setGameName(rs.getString("GameName"));
                ga.setGamePrice(rs.getFloat("GamePrice"));
                ga.setGameDescriptions(rs.getString("Descriptions"));
                ga.setGamePlatforms(rs.getString("Platforms"));
                ga.setGameDeveloper(rs.getString("Developer"));
                ga.setGamePublisher(rs.getString("Publisher"));
                ga.setGameMainImage(rs.getString("MainImage"));
                ga.setGameReleaseDate(rs.getDate("ReleaseDate"));
                ga.setGameDiscount(rs.getInt("Discount"));
                ga.setGameStatus(rs.getBoolean("GameStatus"));
                ga.setGameCategories(rs.getString("Categories"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return ga;
    }

    public List<Game> getAllFollowingGame(Integer id){
        Connection conn = null;
        CallableStatement callSt = null;
        List<Game> listFollowing = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllFollowing(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            listFollowing = new ArrayList<>();
            while (rs.next()){
                Game game = new Game();
                game.setGameID(rs.getInt("GameID"));
                game.setGameName(rs.getString("GameName"));
                game.setGamePrice(rs.getFloat("GamePrice"));
                game.setGameMainImage(rs.getString("MainImage"));
                listFollowing.add(game);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listFollowing;
    }

    public boolean insertFollowing(Integer uID,Integer gID){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertFollowing(?,?)}");
            callSt.setInt(1,uID);
            callSt.setInt(2,gID);
            callSt.executeUpdate();
        }catch (SQLException ex){
            result = false;
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean checkFollowing(Integer uID,Integer gID){
        boolean result;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_checkFollowing(?,?,?)}");
            callSt.setInt(1, uID);
            callSt.setInt(2, gID);
            callSt.registerOutParameter(3,Types.INTEGER);
            callSt.execute();
            int ck = callSt.getInt(3);
            if(ck == 1){
                result = true;
            }else {
                result = false;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            result = false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean deleteFollowing(Integer uID,Integer gID){
        boolean result = true;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_deleteFollowing(?,?)}");
            callSt.setInt(1, uID);
            callSt.setInt(2, gID);
            callSt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
            result = false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public boolean checkBoughtGames(Integer uID,Integer gID){
        boolean result;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_checkBoughtGames(?,?,?)}");
            callSt.setInt(1, uID);
            callSt.setInt(2, gID);
            callSt.registerOutParameter(3,Types.INTEGER);
            callSt.execute();
            int ck = callSt.getInt(3);
            if(ck == 0){
                result = false;
            }else {
                result = true;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            result = false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;

    }
}
