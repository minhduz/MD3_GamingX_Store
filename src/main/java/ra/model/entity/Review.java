package ra.model.entity;

public class Review {
    private int reviewID;
    private int userID;
    private String userAvatar;
    private String userName;
    private int gameID;
    private String reviewContent;
    private boolean reviewStatus;
    private String reviewGameName;

    public Review() {
    }

    public Review(int reviewID, int userID,String userAvatar,String userName, int gameID, String reviewContent, boolean reviewStatus, String reviewGameName) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.userAvatar = userAvatar;
        this.userName = userName;
        this.gameID = gameID;
        this.reviewContent = reviewContent;
        this.reviewStatus = reviewStatus;
        this.reviewGameName = reviewGameName;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public boolean isReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(boolean reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewGameName() {
        return reviewGameName;
    }

    public void setReviewGameName(String reviewGameName) {
        this.reviewGameName = reviewGameName;
    }
}
