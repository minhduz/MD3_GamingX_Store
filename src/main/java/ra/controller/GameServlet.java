package ra.controller;


import ra.model.entity.Category;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.entity.SubImages;
import ra.model.service.CategoryService;
import ra.model.service.GameService;
import ra.model.service.PlatformService;
import ra.model.serviceImp.CategoryServiceImp;
import ra.model.serviceImp.GameServiceImp;
import ra.model.serviceImp.PlatformServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "GameServlet", value = "/GameServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class GameServlet extends HttpServlet {
    private final GameServiceImp gameService = new GameServiceImp();
    private final CategoryService<Category, Integer> categoryService = new CategoryServiceImp();
    private final PlatformService<Platform, Integer> platformService = new PlatformServiceImp();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("GetAll")) {
            getAllGame(request, response);
        } else if (action.equals("GetByID")) {
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            Game ga = gameService.getById(gameID);
            List<SubImages> listSubImages = gameService.getSubImagesByGameID(gameID);
            request.setAttribute("listSubImages",listSubImages);
            request.setAttribute("ga", ga);
            request.getRequestDispatcher("views/admin/game_detail.jsp").forward(request, response);
        } else if (action.equals("Update")){
            getByID(request,response);
        } else if(action.equals("Search")){
            String gameName = request.getParameter("gameName");
            List<Game> listGame = gameService.getGamesByName(gameName);
            List<Category> listCategory = categoryService.getAll();
            List<Platform> listPlatform = platformService.getAll();
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("listPlatform", listPlatform);
            request.setAttribute("listGame", listGame);
            request.getRequestDispatcher("views/admin/games.jsp").forward(request, response);

        }
    }

    public void getByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int gameID = Integer.parseInt(request.getParameter("gameID"));
        Game ga = gameService.getById(gameID);
        List<Platform> listPlatform = gameService.getPlatformByGameID();
        List<Category> listCategory = gameService.getCategoryByGameID();
        List<SubImages> listSubImages = gameService.getSubImagesByGameID(gameID);
        request.setAttribute("listPlatform", listPlatform);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listSubImages",listSubImages);
        request.setAttribute("gameUpdate", ga);
        request.getRequestDispatcher("views/admin/update_game.jsp").forward(request, response);
    }

    public void getAllGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Game> listGame = gameService.getAll();
        List<Category> listCategory = categoryService.getAll();
        List<Platform> listPlatform = platformService.getAll();
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listPlatform", listPlatform);
        request.setAttribute("listGame", listGame);
        request.getRequestDispatcher("views/admin/games.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String pathFolderImage = "C:\\Users\\minhd\\OneDrive\\Desktop\\Source\\MD3_Project_Final_Gaming_Store\\src\\main\\webapp\\images";
        File file = new File(pathFolderImage);
        if (!file.exists()) {
            file.mkdir();
        }
        if (action.equals("Create")) {
            Game game = new Game();
            game.setGameName(request.getParameter("gameName"));
            game.setGamePrice(Float.parseFloat(request.getParameter("gamePrice")));
            String[] arrPlatform = request.getParameterValues("gamePlatforms");
            for (String pl : arrPlatform) {
                game.getListGamePlatformID().add(Integer.parseInt(pl));
            }
            game.setGameDescriptions(request.getParameter("gameDescriptions"));
            game.setGameDeveloper(request.getParameter("gameDeveloper"));
            game.setGamePublisher(request.getParameter("gamePublisher"));
            for (Part part : request.getParts()) {
                if (part.getName().equals("gameMainImage")) {
                    game.setGameMainImage(part.getSubmittedFileName());
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                } else if(part.getName().equals("gameSubImages")){
                    game.getListGameSubImages().add(part.getSubmittedFileName());
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                }
            }
            String[] arrCategory = request.getParameterValues("gameCategories");
            for (String ca : arrCategory) {
                game.getListGameCategoriesID().add(Integer.parseInt(ca));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try{
                game.setGameReleaseDate(sdf.parse(request.getParameter("gameReleaseDate")));
            }catch (ParseException e){
                throw new RuntimeException();
            }
            game.setGameDiscount(Integer.parseInt(request.getParameter("gameDiscount")));
            game.setGameStatus(Boolean.parseBoolean(request.getParameter("gameStatus")));
            boolean result = gameService.save(game);
            if(result){
                getAllGame(request,response);
            }
        } else if(action.equals("Delete")){
            int gameID = Integer.parseInt(request.getParameter("gaDeleteId"));
            boolean result = gameService.delete(gameID);
            if(result) {
                getAllGame(request, response);
            }
        } else if(action.equals("Update")){
            Game game = new Game();
            game.setGameID(Integer.parseInt(request.getParameter("gameID")));
            game.setGameName(request.getParameter("gameName"));
            game.setGamePrice(Float.parseFloat(request.getParameter("gamePrice")));
            String[] arrPlatform = request.getParameterValues("gamePlatforms");
            for (String pl : arrPlatform) {
                game.getListGamePlatformID().add(Integer.parseInt(pl));
            }
            game.setGameDescriptions(request.getParameter("gameDescriptions"));
            game.setGameDeveloper(request.getParameter("gameDeveloper"));
            game.setGamePublisher(request.getParameter("gamePublisher"));
            for (Part part : request.getParts()) {
                if (part.getName().equals("gameMainImage")) {
                    String gameMainImage = part.getSubmittedFileName();
                    if(gameMainImage==""|| gameMainImage==null){
                        game.setGameMainImage(request.getParameter("oldImage"));
                    }else {
                        game.setGameMainImage(gameMainImage);
                        part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                    }
                }
            }
            String[] arrCategory = request.getParameterValues("gameCategories");
            for (String ca : arrCategory) {
                game.getListGameCategoriesID().add(Integer.parseInt(ca));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try{
                game.setGameReleaseDate(sdf.parse(request.getParameter("gameReleaseDate")));
            }catch (ParseException e){
                throw new RuntimeException();
            }
            game.setGameDiscount(Integer.parseInt(request.getParameter("gameDiscount")));
            game.setGameStatus(Boolean.parseBoolean(request.getParameter("gameStatus")));
            boolean result = gameService.update(game);
            if(result){
                getAllGame(request,response);
            }
        } else if (action.equals("Add")) {
            SubImages subImages = new SubImages();
            subImages.setGameID(Integer.parseInt(request.getParameter("gameID")));
            subImages.setImageLink(request.getParameter("subImageLink"));
            boolean result = gameService.saveSubImage(subImages);
            if(result){
                getByID(request,response);
            }
        } else if(action.equals("DeleteSubImage")){
            int subImageID = Integer.parseInt(request.getParameter("subImageID"));
            boolean result = gameService.deleteSubImage(subImageID);
            if(result){
                getByID(request,response);
            }
        } else if(action.equals("UpdateSubImage")){
            SubImages subImages = new SubImages();
            subImages.setImageID(Integer.parseInt(request.getParameter("subImageID")));
            subImages.setImageLink(request.getParameter("subImageLinkUpdate"));
            boolean result = gameService.updateSubImage(subImages);
            if(result){
                getByID(request,response);
            }
        }
    }
}



























