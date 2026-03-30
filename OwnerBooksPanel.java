public class GoldState implements CustomerStatus {

    @Override
    public String getStatus() {
        return "Gold";
    }

    @Override
    public int calculatePoints(int currentPoints, double bookCost, boolean redeem) {
        int updatedPoints = currentPoints;

        if (redeem) {
            double discount = currentPoints / 100.0;
            double finalCost = bookCost - discount;

            if (finalCost < 0) {
                finalCost = 0;
            }

            int pointsUsed = (int) ((bookCost - finalCost) * 100);
            updatedPoints -= pointsUsed;

            if (updatedPoints < 0) {
                updatedPoints = 0;
            }

            updatedPoints += (int) (finalCost * 10);
        } else {
            updatedPoints += (int) (bookCost * 10);
        }

        return updatedPoints;
    }
}