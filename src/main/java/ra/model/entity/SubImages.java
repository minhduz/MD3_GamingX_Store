package ra.model.entity;

public class SubImages {
    private int imageID;
    private String imageLink;
    private int gameID;

    public SubImages() {
    }

    public SubImages(int imageID, String imageLink, int gameID) {
        this.imageID = imageID;
        this.imageLink = imageLink;
        this.gameID = gameID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
