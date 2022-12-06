package ra.model.entity;

public class Banner {
    private int bannerID;
    private String bannerImage;
    private String bannerTitle;
    private String bannerContent;

    public Banner() {
    }

    public Banner(int bannerID, String bannerImage, String bannerTitle, String bannerContent) {
        this.bannerID = bannerID;
        this.bannerImage = bannerImage;
        this.bannerTitle = bannerTitle;
        this.bannerContent = bannerContent;
    }

    public int getBannerID() {
        return bannerID;
    }

    public void setBannerID(int bannerID) {
        this.bannerID = bannerID;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerContent() {
        return bannerContent;
    }

    public void setBannerContent(String bannerContent) {
        this.bannerContent = bannerContent;
    }
}
