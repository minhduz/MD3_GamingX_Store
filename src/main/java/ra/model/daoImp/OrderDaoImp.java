package ra.model.daoImp;

import ra.model.dao.OrderDao;
import ra.model.entity.Cart;
import ra.model.entity.Order;
import ra.model.entity.OrderDetail;
import ra.model.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImp implements OrderDao<Order,Integer> {
    @Override
    public List<Order> getOrderByUsername(String name) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Order> listOrder  = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllOrder()}");
            ResultSet rs = callSt.executeQuery();
            listOrder = new ArrayList<>();
            while (rs.next()){
                Order or = new Order();
                or.setOrderID(rs.getInt("OrderID"));
                or.setOrderUserName(rs.getString("UserName"));
                or.setOrderUserEmail(rs.getString("Email"));
                or.setOrderDate(rs.getDate("OrderDate").toLocalDate());
                or.setOrderTotalAmount(rs.getFloat("TotalAmount"));
                or.setOrderStatus(rs.getBoolean("OrderStatus"));
                listOrder.add(or);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listOrder;
    }

    @Override
    public Order getById(Integer id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Order order  = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getOrderByID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            order = new Order();
            if(rs.next()){
                order.setOrderID(rs.getInt("OrderID"));
                order.setOrderUserName(rs.getString("UserName"));
                order.setOrderUserEmail(rs.getString("Email"));
                order.setOrderDate(rs.getDate("OrderDate").toLocalDate());
                order.setOrderTotalAmount(rs.getFloat("TotalAmount"));
                order.setOrderStatus(rs.getBoolean("OrderStatus"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return order;
    }

    @Override
    public boolean save(Order order) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_insertOrder(?,?,?,?,?)}");
            callSt.setDate(1, Date.valueOf(order.getOrderDate()));
            callSt.setFloat(2,order.getOrderTotalAmount());
            callSt.setBoolean(3,order.isOrderStatus());
            callSt.setInt(4,order.getOrderUserID());
            callSt.registerOutParameter(5,Types.INTEGER);
            callSt.execute();
            int orderID = callSt.getInt(5);
            for (Cart cart:order.getListOrderCart()) {
                CallableStatement callSt2 = conn.prepareCall("{call proc_insertOrderDetail(?,?,?,?,?)}");
                callSt2.setInt(1,orderID);
                callSt2.setInt(2,cart.getGame().getGameID());
                float gamePrice = cart.getGame().getGamePrice()*(100-cart.getGame().getGameDiscount())/100;
                callSt2.setFloat(3,gamePrice);
                callSt2.setInt(4,cart.getQuantity());
                float amount = gamePrice*cart.getQuantity();
                callSt2.setFloat(5,amount);
                callSt2.executeUpdate();
                callSt2.close();
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
    public boolean update(Order order) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = true;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_updateOrderStatus(?,?)}");
            callSt.setInt(1,order.getOrderID());
            callSt.setBoolean(2, order.isOrderStatus());
            callSt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
            result = false;
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
    public List<OrderDetail> getAllOrderDetailByOrderID(Integer id){
        Connection conn = null;
        CallableStatement callSt = null;
        List<OrderDetail> listOrderDetail  = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call proc_getAllOrderDetailByOrderID(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            listOrderDetail = new ArrayList<>();
            while (rs.next()){
                OrderDetail od = new OrderDetail();
                od.setOrderDetailGameID(rs.getInt("GameID"));
                od.setOrderDetailMainImage(rs.getString("MainImage"));
                od.setOrderDetailGameName(rs.getString("GameName"));
                od.setOrderDetailGamePrice(rs.getFloat("GamePrice"));
                od.setOrderDetailGameDiscount(rs.getInt("Discount"));
                od.setOrderDetailQuantity(rs.getInt("Quantity"));
                od.setOrderDetailAmount(rs.getFloat("Amount"));
                listOrderDetail.add(od);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listOrderDetail;
    }
}
