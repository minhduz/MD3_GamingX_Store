package ra.model.entity;

public class Cart {
    private Game game;
    private int quantity;

    public Cart() {
    }

    public Cart(Game game, int quantity) {
        this.game = game;
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
