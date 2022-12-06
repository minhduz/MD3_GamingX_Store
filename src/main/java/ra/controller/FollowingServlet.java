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

@WebServlet(name = "FollowingServlet", value = "/FollowingServlet")
public class FollowingServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private final GameServiceImp gameService = new GameServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userByID");
        if(action!=null&&action.equals("AddFollowing")){
            if(user==null){
                response.sendRedirect("LoginServlet");
            }else {
                int gameID = Integer.parseInt(request.getParameter("gameID"));
                boolean result = gameService.insertFollowing(user.getUserID(),gameID);
                if(result){
                    getAll(request,response);
                }
            }
        }else if(action!=null&&action.equals("UnFollowing")){
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            boolean result = gameService.deleteFollowing(user.getUserID(),gameID);
            if(result){
                getAll(request,response);
            }
        } else {
            getAll(request,response);
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listParentCategory = categoryService.getAllParentCategoriesAtHome();
        List<CategoryMenu> listCatMenu = new ArrayList<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userByID");
        if(user==null){
            response.sendRedirect("LoginServlet");
        }else {
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
            List<Game> listFollowing= gameService.getAllFollowingGame(user.getUserID());
            request.setAttribute("listFollowing",listFollowing);
            request.setAttribute("listCatMenu", listCatMenu);
            request.getRequestDispatcher("views/user/following.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
