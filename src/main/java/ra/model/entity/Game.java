package ra.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {
    private int gameID;
    private String gameName;
    private float gamePrice;
    private String gameDescriptions;
    private String gameDeveloper;
    private String gamePublisher;
    private String gameMainImage;
    private Date gameReleaseDate;
    private int gameDiscount;
    private boolean gameStatus;
    private List<String> listGameSubImages = new ArrayList<>();
    private List<Integer> listGameCategoriesID = new ArrayList<>();
    private String gameCategories;
    private List<Integer> listGamePlatformID = new ArrayList<>();
    private String gamePlatforms;

    public Game() {
    }
    public Game(int gameID, String gameName, float gamePrice, String gameDescriptions, String gameDeveloper,
                String gamePublisher, String gameMainImage, Date gameReleaseDate, int gameDiscount,
                boolean gameStatus, List<String> listGameSubImages, List<Integer> listGameCategoriesID,
                String gameCategories, List<Integer> listGamePlatformID, String gamePlatforms) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gamePrice = gamePrice;
        this.gameDescriptions = gameDescriptions;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
        this.gameMainImage = gameMainImage;
        this.gameReleaseDate = gameReleaseDate;
        this.gameDiscount = gameDiscount;
        this.gameStatus = gameStatus;
        this.listGameSubImages = listGameSubImages;
        this.listGameCategoriesID = listGameCategoriesID;
        this.gameCategories = gameCategories;
        this.listGamePlatformID = listGamePlatformID;
        this.gamePlatforms = gamePlatforms;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public float getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(float gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getGameDescriptions() {
        return gameDescriptions;
    }

    public void setGameDescriptions(String gameDescriptions) {
        this.gameDescriptions = gameDescriptions;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public String getGamePublisher() {
        return gamePublisher;
    }

    public void setGamePublisher(String gamePublisher) {
        this.gamePublisher = gamePublisher;
    }

    public String getGameMainImage() {
        return gameMainImage;
    }

    public void setGameMainImage(String gameMainImage) {
        this.gameMainImage = gameMainImage;
    }

    public Date getGameReleaseDate() {
        return gameReleaseDate;
    }

    public void setGameReleaseDate(Date gameReleaseDate) {
        this.gameReleaseDate = gameReleaseDate;
    }

    public int getGameDiscount() {
        return gameDiscount;
    }

    public void setGameDiscount(int gameDiscount) {
        this.gameDiscount = gameDiscount;
    }

    public boolean isGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<String> getListGameSubImages() {
        return listGameSubImages;
    }

    public void setListGameSubImages(List<String> listGameSubImages) {
        this.listGameSubImages = listGameSubImages;
    }

    public List<Integer> getListGameCategoriesID() {
        return listGameCategoriesID;
    }

    public void setListGameCategoriesID(List<Integer> listGameCategoriesID) {
        this.listGameCategoriesID = listGameCategoriesID;
    }

    public String getGameCategories() {
        return gameCategories;
    }

    public void setGameCategories(String gameCategories) {
        this.gameCategories = gameCategories;
    }

    public List<Integer> getListGamePlatformID() {
        return listGamePlatformID;
    }

    public void setListGamePlatformID(List<Integer> listGamePlatformID) {
        this.listGamePlatformID = listGamePlatformID;
    }

    public String getGamePlatforms() {
        return gamePlatforms;
    }

    public void setGamePlatforms(String gamePlatforms) {
        this.gamePlatforms = gamePlatforms;
    }
}
