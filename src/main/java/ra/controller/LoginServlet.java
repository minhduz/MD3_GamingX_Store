package ra.controller;

import ra.model.entity.Category;
import ra.model.entity.CategoryMenu;
import ra.model.entity.User;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.UserServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private final UserServiceImp userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getAllCategories(request,response);
    }
    public void getAllCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.getRequestDispatcher("views/user/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action.equals("Login")){
            String username = request.getParameter("userName");
            String password = request.getParameter("userPassword");
            boolean result = userService.checkPermission(username,password);
            boolean checkUser;
            if(result){
                response.sendRedirect("CategoryServlet?pageNumber=1");
                checkUser = true;
            }else {
                boolean result2 = userService.checkLogin(username,password);
                if(result2){
                    Integer gameName = (Integer) session.getAttribute("gameName");
                    Integer gameFollowing = (Integer) session.getAttribute("gameFollowing");
                    session.removeAttribute("gameName");
                    session.removeAttribute("gameFollowing");
                    if(gameName!=null){
                        response.sendRedirect("GameDetailServlet?gameID="+gameName);
                    }else if(gameFollowing!=null){
                        response.sendRedirect("GameDetailServlet?gameID="+gameFollowing);
                    }else {
                        response.sendRedirect("HomeServlet");
                    }
                    checkUser = true;
                }else {
                    checkUser = false;
                    String wrong = "Nhập sai tài khoản hoặc mật khẩu";
                    request.setAttribute("wrong",wrong);
                    request.getRequestDispatcher("views/user/login.jsp").forward(request,response);
                }
            }
            if (checkUser) {
                int userID = userService.getUserIDByUsername(username);
                User user = userService.getById(userID);
                session.setAttribute("userByID",user);
            }
        }
    }
}
