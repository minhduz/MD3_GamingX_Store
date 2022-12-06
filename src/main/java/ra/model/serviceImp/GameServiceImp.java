package ra.model.serviceImp;

import ra.model.dao.GameDao;
import ra.model.daoImp.GameDaoImp;
import ra.model.entity.Category;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.entity.SubImages;
import ra.model.service.GameService;

import java.util.List;


public class GameServiceImp implements GameService<Game,Integer> {
    private final GameDaoImp gameDao = new GameDaoImp();
    @Override
    public List<Game> getGamesByName(String name) {
        return gameDao.getGamesByName(name);
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    @Override
    public Game getById(Integer id) {
        return gameDao.getById(id);
    }

    @Override
    public boolean save(Game game) {
        return gameDao.save(game);
    }

    @Override
    public boolean update(Game game) {
        return gameDao.update(game);
    }

    @Override
    public boolean delete(Integer id) {
        return gameDao.delete(id);
    }
    public List<SubImages> getSubImagesByGameID(Integer id) { return gameDao.getSubImagesByGameID(id);}
    public List<Platform> getPlatformByGameID(){return gameDao.getPlatformByGameID();}
    public List<Category> getCategoryByGameID(){return gameDao.getCategoryByGameID();}
    public boolean saveSubImage(SubImages subImages){return gameDao.saveSubImage(subImages);}
    public boolean deleteSubImage(Integer id){return gameDao.deleteSubImage(id);}
    public boolean updateSubImage(SubImages subImages){return gameDao.updateSubImage(subImages);}
    public boolean insertIntoTrending(Game game){return gameDao.insertIntoTrending(game);}
    public List<Game> getAllTrendingGame(){return gameDao.getAllTrendingGame();}
    public boolean deleteTrending(Integer id){return gameDao.deleteTrending(id);}
    public Game getGameDetailByGame(String name){return gameDao.getGameDetailByGame(name);}
    public List<Game> getAllFollowingGame(Integer id){return gameDao.getAllFollowingGame(id);}
    public boolean insertFollowing(Integer uID,Integer gID){return gameDao.insertFollowing(uID,gID);}
    public boolean checkFollowing(Integer uID,Integer gID){return gameDao.checkFollowing(uID,gID);}
    public boolean deleteFollowing(Integer uID,Integer gID){return gameDao.deleteFollowing(uID,gID);}
    public List<Game> getAllGamesByCategoryName(Integer id) {return gameDao.getAllGamesByCategoryName(id);}
    public boolean checkBoughtGames(Integer uID,Integer gID){return gameDao.checkBoughtGames(uID,gID);}
}