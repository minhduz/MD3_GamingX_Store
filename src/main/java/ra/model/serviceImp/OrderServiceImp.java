package ra.model.serviceImp;

import ra.model.daoImp.OrderDaoImp;
import ra.model.entity.Order;
import ra.model.entity.OrderDetail;
import ra.model.service.OrderService;

import java.util.List;

public class OrderServiceImp implements OrderService<Order,Integer> {
    private final OrderDaoImp orderDao = new OrderDaoImp();
    @Override
    public List<Order> getOrderByUsername(String name) {
        return orderDao.getOrderByUsername(name);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public Order getById(Integer id) {
        return orderDao.getById(id);
    }

    @Override
    public boolean save(Order order) {
        return orderDao.save(order);
    }

    @Override
    public boolean update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Integer id) {
        return orderDao.delete(id);
    }
    public List<OrderDetail> getAllOrderDetailByOrderID(Integer id){ return orderDao.getAllOrderDetailByOrderID(id);}
}
