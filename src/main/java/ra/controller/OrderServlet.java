package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import ra.model.entity.Cart;
import ra.model.entity.Order;
import ra.model.entity.OrderDetail;
import ra.model.entity.User;
import ra.model.serviceImp.OrderServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    private final OrderServiceImp orderService = new OrderServiceImp();
    private static final Gson GSON = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("GetByID")){
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            Order order = orderService.getById(orderID);
            List<OrderDetail> listOrderDetail = orderService.getAllOrderDetailByOrderID(orderID);
            request.setAttribute("order",order);
            request.setAttribute("listOrderDetail",listOrderDetail);
            request.getRequestDispatcher("views/admin/order_detail.jsp").forward(request,response);
        }else if(action!=null&&action.equals("CheckOut")){
            HttpSession session = request.getSession();
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            User user = (User) session.getAttribute("userByID");
            Order order = new Order();
            order.setOrderDate(java.time.LocalDate.now());
            order.setOrderTotalAmount(ShoppingCartServlet.calTotalAmount(listCart));
            order.setOrderStatus(false);
            order.setOrderUserID(user.getUserID());
            order.setListOrderCart(listCart);
            boolean result = orderService.save(order);
            if(result){
                response.sendRedirect("HomeServlet?action=CheckOut");
            }
        } else {
            getAll(request,response);
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> listOrder = orderService.getAll();
        request.setAttribute("listOrder",listOrder);
        request.getRequestDispatcher("views/admin/orders.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("GetByID")){
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            Order orderUpdate = orderService.getById(orderID);
            String json = GSON.toJson(orderUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }else if(action.equals("Update")){
            Order order = new Order();
            order.setOrderID(Integer.parseInt(request.getParameter("orderID")));
            order.setOrderStatus(Boolean.parseBoolean(request.getParameter("orderStatus")));
            boolean result = orderService.update(order);
            if(result){
                getAll(request,response);
            }
        }
    }
}
