package ra.model.serviceImp;

import ra.model.daoImp.GameDaoImp;
import ra.model.daoImp.UserDaoImp;
import ra.model.entity.User;
import ra.model.service.UserService;

import java.util.List;

public class UserServiceImp implements UserService<User,Integer> {
    private final UserDaoImp userDao = new UserDaoImp();
    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> getUsersByUserName(String name) {
        return userDao.getUsersByUserName(name);
    }
    public boolean checkLogin(String uName, String uPassword){ return userDao.checkLogin(uName,uPassword);}
    public boolean checkPermission(String uName, String uPassword){ return userDao.checkPermission(uName,uPassword);}
    public int getUserIDByUsername(String uName){ return userDao.getUserIDByUsername(uName);}

}
