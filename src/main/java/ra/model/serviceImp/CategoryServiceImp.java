package ra.model.serviceImp;

import ra.model.dao.CategoryDao;
import ra.model.daoImp.CategoryDaoImp;
import ra.model.entity.Category;
import ra.model.service.CategoryService;

import java.util.List;

public class CategoryServiceImp implements CategoryService<Category,Integer> {
    private final CategoryDaoImp categoryDao = new CategoryDaoImp();
    @Override
    public List<Category> getCategoriesByName(String name) {
        return categoryDao.getCategoriesByName(name);
    }
    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryDao.getById(id);
    }

    @Override
    public boolean save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public boolean delete(Integer id) {
        return categoryDao.delete(id);
    }
    public int getNumberOfPages(int objectInAPage) {return categoryDao.getNumberOfPages(objectInAPage);}
    public List<Category> getAllOnAPage(int pNumber,int oInAPage) { return categoryDao.getAllOnAPage(pNumber,oInAPage);}
    public List<Category> getAllParentCategoriesAtHome(){return categoryDao.getAllParentCategoriesAtHome();}
    public List<Category> getAllChildCategoriesAtHome(int pID){return categoryDao.getAllChildCategoriesAtHome(pID);}
}
