package ra.model.entity;

public class Platform {
    private int platformID;
    private String platformName;
    private String gameID;

    public Platform() {
    }

    public Platform(int platformID, String platformName, String gameID) {
        this.platformID = platformID;
        this.platformName = platformName;
        this.gameID = gameID;
    }

    public int getPlatformID() {
        return platformID;
    }

    public void setPlatformID(int platformID) {
        this.platformID = platformID;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String  gameID) {
        this.gameID = gameID;
    }
}
