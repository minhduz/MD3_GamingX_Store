package ra.model.serviceImp;

import ra.model.daoImp.BannerDaoImp;
import ra.model.entity.Banner;
import ra.model.service.BannerService;

import java.util.List;

public class BannerServiceImp implements BannerService<Banner,Integer> {
    private final BannerDaoImp bannerDaoImp = new BannerDaoImp();
    @Override
    public List<Banner> getAll() {
        return bannerDaoImp.getAll();
    }

    @Override
    public Banner getById(Integer id) {
        return bannerDaoImp.getById(id);
    }

    @Override
    public boolean save(Banner banner) {
        return bannerDaoImp.save(banner);
    }

    @Override
    public boolean update(Banner banner) {
        return bannerDaoImp.update(banner);
    }

    @Override
    public boolean delete(Integer id) {
        return bannerDaoImp.delete(id);
    }
}
