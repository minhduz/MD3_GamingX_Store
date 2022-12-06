package ra.model.entity;

public class User {
    private int userID;
    private String userName;
    private String userFullName;
    private String userPassword;
    private String userConfirmPassword;
    private String userAvatar;
    private int userAge;
    private String userPhone;
    private String userEmail;
    private boolean userPermission;
    private boolean userStatus;
    private String userFollowing;

    public User() {
    }

    public User(int userID, String userName, String userFullName, String userPassword, String userConfirmPassword, String userAvatar, int userAge, String userPhone, String userEmail, boolean userPermission, boolean userStatus, String userFollowing) {
        this.userID = userID;
        this.userName = userName;
        this.userFullName = userFullName;
        this.userPassword = userPassword;
        this.userConfirmPassword = userConfirmPassword;
        this.userAvatar = userAvatar;
        this.userAge = userAge;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userPermission = userPermission;
        this.userStatus = userStatus;
        this.userFollowing = userFollowing;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isUserPermission() {
        return userPermission;
    }

    public void setUserPermission(boolean userPermission) {
        this.userPermission = userPermission;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserFollowing() {
        return userFollowing;
    }

    public void setUserFollowing(String userFollowing) {
        this.userFollowing = userFollowing;
    }
}
