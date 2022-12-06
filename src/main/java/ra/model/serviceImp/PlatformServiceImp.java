package ra.model.serviceImp;

import ra.model.dao.GameDao;
import ra.model.dao.PlatformDao;
import ra.model.daoImp.GameDaoImp;
import ra.model.daoImp.PlatformDaoImp;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.service.PlatformService;

import java.util.List;

public class PlatformServiceImp implements PlatformService<Platform,Integer> {
    private final PlatformDao<Platform,Integer> platformDao = new PlatformDaoImp();
    @Override
    public List<Platform> getAll() {
        return platformDao.getAll();
    }

    @Override
    public Platform getById(Integer id) {
        return platformDao.getById(id);
    }

    @Override
    public boolean save(Platform platform) {
        return platformDao.save(platform);
    }

    @Override
    public boolean update(Platform platform) {
        return platformDao.update(platform);
    }

    @Override
    public boolean delete(Integer id) {
        return platformDao.delete(id);
    }
}
