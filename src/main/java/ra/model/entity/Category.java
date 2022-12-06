package ra.model.entity;

public class Category {
    private int categoryID;
    private String categoryName;
    private boolean categoryAgeRestricted;
    private boolean categoryStatus;
    private int categoryParentID;
    private String categoryParentName;
    private String gameID;
    public Category() {
    }

    public Category(int categoryID, String categoryName, boolean categoryAgeRestricted, boolean categoryStatus, int categoryParentID, String categoryParentName, String gameID) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.categoryAgeRestricted = categoryAgeRestricted;
        this.categoryStatus = categoryStatus;
        this.categoryParentID = categoryParentID;
        this.categoryParentName = categoryParentName;
        this.gameID = gameID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCategoryAgeRestricted() {
        return categoryAgeRestricted;
    }

    public void setCategoryAgeRestricted(boolean categoryAgeRestricted) {
        this.categoryAgeRestricted = categoryAgeRestricted;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public int getCategoryParentID() {
        return categoryParentID;
    }

    public void setCategoryParentID(int categoryParentID) {
        this.categoryParentID = categoryParentID;
    }

    public String getCategoryParentName() {
        return categoryParentName;
    }
    public void setCategoryParentName(String categoryParentName) {
        this.categoryParentName = categoryParentName;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
