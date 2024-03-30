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
        long startTime = System.nanoTime();
        int[] bestTour = sa.simulatedAnnealing(numberOfCities);        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000; // in microseconds
        System.out.println("Execution time: " + duration + " µs");

        System.out.println("Best tour found:");
        sa.printTour(bestTour);
        System.out.println("Total distance: " + sa.calculateTotalDistance(bestTour));
        System.out.println("Average Objective Function Value: " + sa.averageObjectiveFunctionValue(numberOfCities, 3));
    }
}