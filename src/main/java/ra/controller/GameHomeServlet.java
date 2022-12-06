package ra.controller;

import ra.model.entity.*;
import ra.model.serviceImp.BannerServiceImp;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;
import ra.model.serviceImp.ReviewServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GameHomeServlet", value = "/GameHomeServlet")
public class GameHomeServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private final GameServiceImp gameService = new GameServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("Search")){
            String gameName = request.getParameter("gameName");
            List<Game> listGame = gameService.getGamesByName(gameName);
            request.setAttribute("listGame",listGame);
            getAll(request,response);
        }else{
            List<Game> listGame = gameService.getAll();
            request.setAttribute("listGame",listGame);
            getAll(request,response);
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
        request.getRequestDispatcher("views/user/games.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
