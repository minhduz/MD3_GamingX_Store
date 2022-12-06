package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ra.model.entity.Review;
import ra.model.entity.User;
import ra.model.serviceImp.ReviewServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "ReviewServlet", value = "/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private final ReviewServiceImp reviewService = new ReviewServiceImp();
    private static final Gson GSON = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("GetByID")){
            int reviewID = Integer.parseInt(request.getParameter("reviewID"));
            Review reviewUpdate = reviewService.getById(reviewID);
            String json = GSON.toJson(reviewUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }else {
            List<Review> listReview = reviewService.getAll();
            request.setAttribute("listReview",listReview);
            request.getRequestDispatcher("views/admin/reviews.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userByID");
        if(action.equals("Update")){
            Review review = new Review();
            review.setReviewID(Integer.parseInt(request.getParameter("reviewID")));
            review.setReviewStatus(Boolean.parseBoolean(request.getParameter("reviewStatus")));
            boolean result = reviewService.update(review);
            if(result){
                List<Review> listReview = reviewService.getAll();
                request.setAttribute("listReview",listReview);
                request.getRequestDispatcher("views/admin/reviews.jsp").forward(request,response);
            }
        }else if(action.equals("Create")){
            Review review = new Review();
            int gameID = Integer.parseInt(request.getParameter("gameID"));
            review.setUserID(user.getUserID());
            review.setGameID(gameID);
            review.setReviewContent(request.getParameter("reviewContent"));
            review.setReviewStatus(false);
            boolean result = reviewService.save(review);
            if(result){
                response.sendRedirect("GameDetailServlet?gameID="+gameID);
            }
        }
    }
}
