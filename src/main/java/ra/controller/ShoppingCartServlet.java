package ra.controller;

import ra.model.entity.*;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShoppingCartServlet", value = "/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
    private final GameServiceImp gameService = new GameServiceImp();
    private final CategoryServiceImp categoryService = new CategoryServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userByID");
        if (action!=null&&action.equals("AddCart")){
            if(user==null){
                response.sendRedirect("LoginServlet");
            }else {
                int gameID = Integer.parseInt(request.getParameter("gameID"));
                Game game = gameService.getById(gameID);
                List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
                if(listCart == null){
                    listCart = new ArrayList<>();
                    Cart cart = new Cart(game, 1);
                    listCart.add(cart);
                }else {
                    boolean checkExist = false;
                    for (Cart cart: listCart) {
                        if(cart.getGame().getGameID() == gameID){
                            cart.setQuantity(cart.getQuantity()+1);
                            checkExist = true;
                            break;
                        }
                    }
                    if(!checkExist){
                        Cart cart = new Cart(game, 1);
                        listCart.add(cart);
                    }
                }
                session.setAttribute("totalCarts",listCart.size());
                session.setAttribute("listCart", listCart);
                session.setAttribute("totalAmount", calTotalAmount(listCart));
                getAll(request,response);
            }
        }else if(action!=null&&action.equals("Delete")){
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            String gameName = request.getParameter("gameName");
            for (int i = 0; i < listCart.size(); i++) {
                if (listCart.get(i).getGame().getGameName().equals(gameName)){
                    listCart.remove(i);
                }
            }
            if (listCart.size()==0) {
                request.setAttribute("cko","");
            }
            request.getSession().setAttribute("totalCarts",listCart.size());
            request.getSession().setAttribute("listCart",listCart);
            request.getSession().setAttribute("totalAmount",calTotalAmount(listCart));
            getAll(request,response);
        }else {
            if(user==null){
                response.sendRedirect("LoginServlet");
            }else {
                List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
                if (listCart==null) {
                    request.setAttribute("cko","");
                }
                request.setAttribute("listCart",listCart);
                getAll(request,response);
            }
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listParentCategory = categoryService.getAllParentCategoriesAtHome();
        List<CategoryMenu> listCatMenu = new ArrayList<>();
        for (Category cat : listParentCategory) {
            CategoryMenu categoryMenu = new CategoryMenu();
            categoryMenu.setCategoryId(cat.getCategoryID());
            categoryMenu.setCategoryName(cat.getCategoryName());
            List<Category> listChildCategory = categoryService.getAllChildCategoriesAtHome(cat.getCategoryID());
            List<CategoryMenu> listChild = new ArrayList<>();
            for (Category catChild : listChildCategory) {
                listChild.add(new CategoryMenu(catChild.getCategoryID(),catChild.getCategoryName()));
            }
            categoryMenu.setListChild(listChild);
            listCatMenu.add(categoryMenu);
        }
        request.setAttribute("listCatMenu", listCatMenu);
        request.getRequestDispatcher("views/user/cart.jsp").forward(request, response);
    }

    public static float calTotalAmount(List<Cart> listCart) {
        float totalAmount = 0;
        for (Cart ca : listCart) {
            totalAmount += ca.getQuantity() * ca.getGame().getGamePrice()*(100-ca.getGame().getGameDiscount())/100;
        }
        return totalAmount;
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
