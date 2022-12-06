package ra.controller;

import ra.model.entity.*;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;
import ra.model.serviceImp.ReviewServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GameDetailServlet", value = "/GameDetailServlet")
public class GameDetailServlet extends HttpServlet {
    private final GameServiceImp gameService = new GameServiceImp();
    private final ReviewServiceImp reviewService = new ReviewServiceImp();
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int gameID = Integer.parseInt(request.getParameter("gameID"));
        HttpSession session = request.getSession();
        session.setAttribute("gameName",gameID);
        session.setAttribute("gameFollowing",gameID);
        User user = (User) session.getAttribute("userByID");
        Game game = gameService.getById(gameID);
        request.setAttribute("game",game);
        List<SubImages> listSubImages = gameService.getSubImagesByGameID(game.getGameID());
        request.setAttribute("listSubImages",listSubImages);
        List<Review> listReview = reviewService.getAllReviewByID(game.getGameID());
        request.setAttribute("listReview",listReview);
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
        String unfollowing = null;
        String bought = null;
        if(user!=null){
            boolean ckFollowingGame = gameService.checkFollowing(user.getUserID(),game.getGameID());
            boolean ckGameBought = gameService.checkBoughtGames(user.getUserID(),gameID);
            if (ckFollowingGame){
                unfollowing = "";
            }
            if (ckGameBought) {
                bought = "";
            }
        }
        request.setAttribute("un",unfollowing);
        request.setAttribute("ck",bought);
        request.getRequestDispatcher("views/user/game_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("Review")){

        }
    }
}
