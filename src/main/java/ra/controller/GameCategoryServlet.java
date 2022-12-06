package ra.controller;

import ra.model.entity.Category;
import ra.model.entity.CategoryMenu;
import ra.model.entity.Game;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GameCategoryServlet", value = "/GameCategoryServlet")
public class GameCategoryServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private final GameServiceImp gameService = new GameServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer categoryID = Integer.parseInt(request.getParameter("categoryID"));
        List<Game> listGame = gameService.getAllGamesByCategoryName(categoryID);
        Category category = categoryService.getById(categoryID);
        request.setAttribute("listGame",listGame);
        request.setAttribute("categoryName",category.getCategoryName());
        getAll(request,response);
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
        request.getRequestDispatcher("views/user/games_category.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
