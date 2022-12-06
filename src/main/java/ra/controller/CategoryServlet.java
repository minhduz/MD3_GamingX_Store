package ra.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ra.model.entity.Category;
import ra.model.serviceImp.CategoryServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    private final CategoryServiceImp categoryService = new CategoryServiceImp();
    private static final Gson GSON = new GsonBuilder().create();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action!=null&&action.equals("GetByID")){
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            Category categoryUpdate = categoryService.getById(categoryID);
            String json = GSON.toJson(categoryUpdate);
            response.setStatus(200);
            response.setHeader("Content-Type","application/json");
            Writer out = response.getWriter();
            out.write(json);
            out.flush();
        }else if(action!=null&&action.equals("Search")){
            String categoryName = request.getParameter("categoryName");
            List<Category> listCategory = categoryService.getCategoriesByName(categoryName);
            request.setAttribute("ListCategory",listCategory);
            request.getRequestDispatcher("views/admin/categories.jsp").forward(request,response);
        }
        else {
            getAllCategory(request,response);
        }

    }

    public void getAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int pN = Integer.parseInt(request.getParameter("pageNumber"));
        List<Category> listCategory = categoryService.getAllOnAPage(pN-1,10);
        Integer pageNumber = categoryService.getNumberOfPages(10);
        request.setAttribute("currentPage",pN);
        request.setAttribute("pageNumbers",pageNumber);
        request.setAttribute("ListCategory",listCategory);
        request.getRequestDispatcher("views/admin/categories.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("Create")){
            Category category = new Category();
            category.setCategoryName(request.getParameter("categoryName"));
            category.setCategoryAgeRestricted(Boolean.parseBoolean(request.getParameter("categoryAgeRestricted")));
            category.setCategoryStatus(Boolean.parseBoolean(request.getParameter("categoryStatus")));
            category.setCategoryParentID(Integer.parseInt(request.getParameter("categoryParentID")));
            boolean result = categoryService.save(category);
            if(result){
                getAllCategory(request,response);
            }
        } else if (action.equals("Delete")){
            int categoryID = Integer.parseInt(request.getParameter("catDeleteId"));
            boolean result = categoryService.delete(categoryID);
            if(result){
                getAllCategory(request,response);
            }else {
                String alert = "show";
                request.setAttribute("alert",alert);
                getAllCategory(request,response);
            }
        } else if (action.equals("Update")){
            Category category = new Category();
            category.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
            category.setCategoryName(request.getParameter("categoryName"));
            category.setCategoryAgeRestricted(Boolean.parseBoolean(request.getParameter("categoryAgeRestricted")));
            category.setCategoryStatus(Boolean.parseBoolean(request.getParameter("categoryStatus")));
            category.setCategoryParentID(Integer.parseInt(request.getParameter("categoryParentID")));
            boolean result = categoryService.update(category);
            if (result){
                getAllCategory(request,response);
            }
        }
    }
}
