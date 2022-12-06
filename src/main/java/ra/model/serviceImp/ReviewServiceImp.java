package ra.model.serviceImp;

import ra.model.daoImp.ReviewDaoImp;
import ra.model.entity.Review;
import ra.model.service.ReviewService;

import java.util.List;

public class ReviewServiceImp implements ReviewService<Review,Integer> {
    private final ReviewDaoImp reviewDao = new ReviewDaoImp();
    @Override
    public List<Review> getAll() {
        return reviewDao.getAll();
    }
    public List<Review> getAllReviewByID(Integer id){return reviewDao.getAllReviewByGameID(id);}

    @Override
    public Review getById(Integer id) {
        return reviewDao.getById(id);
    }

    @Override
    public boolean save(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public boolean update(Review review) {
        return reviewDao.update(review);
    }

    @Override
    public boolean delete(Integer id) {
        return reviewDao.delete(id);
    }
}
