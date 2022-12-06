package ra.model.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryMenu {
    private int categoryId;
    private String categoryName;
    private List<CategoryMenu> listChild = new ArrayList<>();

    public CategoryMenu() {
    }

    public CategoryMenu(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public CategoryMenu(int categoryId, String categoryName, List<CategoryMenu> listChild) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.listChild = listChild;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategoryMenu> getListChild() {
        return listChild;
    }

    public void setListChild(List<CategoryMenu> listChild) {
        this.listChild = listChild;
    }
}
