public class Main {
    public static void main(String[] args) {
        // Create a cost matrix
        Cost_Matrix costMatrix = null;
        SimmulatedAnnealing sa = null;
        try {
            costMatrix = new Cost_Matrix("Cost_Matrix.txt", 5, 5);
        } catch (Exception e) {
            System.out.println("File not found");
            System.exit(1);
        }
        sa = new SimmulatedAnnealing(costMatrix);
        int numberOfCities = costMatrix.getRows(); // Assuming square matrix
        int[] bestTour = sa.simulatedAnnealing(numberOfCities);

        System.out.println("Best tour found:");
        sa.printTour(bestTour);
        System.out.println("Total distance: " + sa.calculateTotalDistance(bestTour));
    }
}