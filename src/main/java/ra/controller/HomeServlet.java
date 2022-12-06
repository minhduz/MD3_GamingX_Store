package ra.controller;

import ra.model.entity.*;
import ra.model.serviceImp.BannerServiceImp;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private final BannerServiceImp bannerService= new BannerServiceImp();
    private final GameServiceImp gameService = new GameServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("LogOut")){
            HttpSession session = request.getSession();
            session.removeAttribute("userByID");
            session.removeAttribute("listCart");
            getAll(request,response);
        }else if(action!=null&&action.equals("CheckOut")){
            String alert = "show";
            request.setAttribute("alert",alert);
            getAll(request, response);
        }else {
            getAll(request, response);
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
        List<Banner> listBanner = bannerService.getAll();
        List<Game> listTrending = gameService.getAllTrendingGame();
        request.setAttribute("listTrending",listTrending);
        request.setAttribute("listBanner",listBanner);
        request.setAttribute("listCatMenu", listCatMenu);
        request.getRequestDispatcher("views/user/home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
