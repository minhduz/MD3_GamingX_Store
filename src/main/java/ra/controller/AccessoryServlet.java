package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.ir.BaseNode;
import ra.model.entity.Banner;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.serviceImp.BannerServiceImp;
import ra.model.serviceImp.GameServiceImp;
import ra.model.serviceImp.PlatformServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "AccessoryServlet", value = "/AccessoryServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class AccessoryServlet extends HttpServlet {
    private final PlatformServiceImp platformService = new PlatformServiceImp();
    private final GameServiceImp gameService = new GameServiceImp();
    private final BannerServiceImp bannerService = new BannerServiceImp();
    public static List<Game> listTrendingGame;
    private static final Gson GSON = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action!=null&&action.equals("GetPlatformByID")){
            int platformID = Integer.parseInt(request.getParameter("platformID"));
            Platform platformUpdate = platformService.getById(platformID);
            String json = GSON.toJson(platformUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }else if(action!=null&&action.equals("GetBannerByID")){
            int bannerID = Integer.parseInt(request.getParameter("bannerID"));
            Banner bannerUpdate = bannerService.getById(bannerID);
            String json = GSON.toJson(bannerUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        } else {
            getAll(request,response);
        }
    }

    public void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Platform> listPlatform = platformService.getAll();
        List<Game> listGame = gameService.getAll();
        List<Banner> listBanner = bannerService.getAll();
        List<Game> listTrendingGame = gameService.getAllTrendingGame();
        request.setAttribute("listTrendingGame",listTrendingGame);
        request.setAttribute("listPlatform",listPlatform);
        request.setAttribute("listGame",listGame);
        request.setAttribute("listBanner",listBanner);
        request.getRequestDispatcher("views/admin/accessories.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String pathFolderImage = "C:\\Users\\minhd\\OneDrive\\Desktop\\Source\\MD3_Project_Final_Gaming_Store\\src\\main\\webapp\\images";
        File file = new File(pathFolderImage);
        if (!file.exists()) {
            file.mkdir();
        }
        if (action.equals("AddNewPlatform")){
            Platform platform = new Platform();
            platform.setPlatformName(request.getParameter("platformName"));
            boolean result = platformService.save(platform);
            if (result){
                getAll(request,response);
            }
        } else if(action.equals("EditPlatform")){
            Platform platform = new Platform();
            platform.setPlatformID(Integer.parseInt(request.getParameter("platformID")));
            platform.setPlatformName(request.getParameter("platformName"));
            boolean result = platformService.update(platform);
            if(result){
                getAll(request,response);
            }
        }else if(action.equals("AddNewBanner")){
            Banner banner = new Banner();
            for (Part part : request.getParts()) {
                if (part.getName().equals("bannerImage")) {
                    banner.setBannerImage(part.getSubmittedFileName());
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                }
            }
            banner.setBannerTitle(request.getParameter("bannerTitle"));
            banner.setBannerContent(request.getParameter("bannerContent"));
            boolean result = bannerService.save(banner);
            if (result){
                getAll(request,response);
            }
        }else if(action.equals("EditBanner")){
            Banner banner = new Banner();
            banner.setBannerID(Integer.parseInt(request.getParameter("bannerID")));
            for (Part part : request.getParts()) {
                if (part.getName().equals("bannerImage")) {
                    String bannerImage = part.getSubmittedFileName();
                    if(bannerImage==""|| bannerImage==null){
                        banner.setBannerImage(request.getParameter("oldBannerImage"));
                    }else {
                        banner.setBannerImage(bannerImage);
                        part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                    }
                }
            }
            banner.setBannerTitle(request.getParameter("bannerTitle"));
            banner.setBannerContent(request.getParameter("bannerContent"));
            boolean result = bannerService.update(banner);
            if(result){
                getAll(request,response);
            }
        }else if(action.equals("DeletePlatform")){
            int platformID = Integer.parseInt(request.getParameter("platformDeleteID"));
            boolean result = platformService.delete(platformID);
            if (result){
                getAll(request,response);
            }
        }else if(action.equals("DeleteBanner")){
            int bannerID = Integer.parseInt(request.getParameter("bannerDeleteID"));
            boolean result = bannerService.delete(bannerID);
            if (result){
                getAll(request,response);
            }
        }else if(action.equals("AddTrending")){
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            Game game = gameService.getById(gameID);
            boolean result = gameService.insertIntoTrending(game);
            if (result){
                getAll(request,response);
            }
        }else if(action.equals("DeleteTrending")){
            int gameID = Integer.parseInt(request.getParameter("gameDeleteID"));
            boolean result = gameService.deleteTrending(gameID);
            if(result){
                getAll(request,response);
            }
        }
    }
}
