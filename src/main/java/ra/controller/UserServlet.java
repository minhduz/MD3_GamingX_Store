package ra.controller;

import ra.model.entity.Category;
import ra.model.entity.Game;
import ra.model.entity.Platform;
import ra.model.entity.User;
import ra.model.serviceImp.UserServiceImp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "UserServlet", value = "/UserServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10
)
public class UserServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();
    private final UserServiceImp userService = new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("GetAll")) {
            getAllUser(request,response);
        }else if(action.equals("GetByID")){
            int userID = Integer.parseInt(request.getParameter("userID"));
            User us = userService.getById(userID);
            request.setAttribute("us", us);
            request.getRequestDispatcher("views/admin/user_detail.jsp").forward(request, response);
        }else if(action.equals("GetByIDUpdate")){
            int userUpdateID = Integer.parseInt(request.getParameter("userID"));
            User userUpdate = userService.getById(userUpdateID);
            String json = GSON.toJson(userUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }else if(action.equals("Search")){
            String userName = request.getParameter("userName");
            List<User> listUser = userService.getUsersByUserName(userName);
            request.setAttribute("listUser", listUser);
            request.getRequestDispatcher("views/admin/user.jsp").forward(request, response);
        }
    }

    public void getAllUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = userService.getAll();
        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("views/admin/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String pathFolderImage = "C:\\Users\\minhd\\OneDrive\\Desktop\\Source\\MD3_Project_Final_Gaming_Store\\src\\main\\webapp\\images";
        File file = new File(pathFolderImage);
        if (!file.exists()) {
            file.mkdir();
        }
        if(action.equals("Create")){
            User user = new User();
            user.setUserName(request.getParameter("userName"));
            String ps = request.getParameter("userPassword");
            String ps2 = request.getParameter("userConfirmPassword");
            if(!Objects.equals(ps, ps2)){
                String alert = "show";
                request.setAttribute("alert",alert);
                getAllUser(request,response);
            }
            user.setUserPassword(ps);
            user.setUserConfirmPassword(ps2);
            user.setUserFullName(request.getParameter("userFullName"));
            for (Part part : request.getParts()) {
                if (part.getName().equals("userAvatar")) {
                    user.setUserAvatar(part.getSubmittedFileName());
                    part.write(pathFolderImage + File.separator + part.getSubmittedFileName());
                }
            }
            user.setUserAge(Integer.parseInt(request.getParameter("userAge")));
            user.setUserPhone(request.getParameter("userPhone"));
            user.setUserEmail(request.getParameter("userEmail"));
            user.setUserPermission(false);
            user.setUserStatus(true);
            boolean result = userService.save(user);
            if(result){
                response.sendRedirect("LoginServlet");
            }
        } else if(action.equals("Update")){
            User user = new User();
            user.setUserID(Integer.parseInt(request.getParameter("userID")));
            user.setUserPassword(request.getParameter("userPassword"));
            user.setUserConfirmPassword(request.getParameter("userConfirmPassword"));
            user.setUserStatus(Boolean.parseBoolean(request.getParameter("userStatus")));
            boolean result = userService.update(user);
            if (result){
                getAllUser(request,response);
            }
        }
    }
}
