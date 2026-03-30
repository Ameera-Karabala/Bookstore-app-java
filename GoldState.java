public class Customer extends User {

    private int points;
    private CustomerStatus status;

    public Customer(String username, String password) {
        super(username, password);
        this.points = 0;
        this.status = new SilverState();
    }

    public void buyBooks(double amount) {
        points = status.calculatePoints(points, amount, false);
        updateStatus();
    }

    public void redeemAndBuy(double amount) {
        points = status.calculatePoints(points, amount, true);
        updateStatus();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
        updateStatus();
    }

    public String getStatus() {
        return status.getStatus();
    }

    private void updateStatus() {
        if (points >= 1000) {
            status = new GoldState();
        } else {
            status = new SilverState();
        }
    }
}