import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a cost matrix
        Cost_Matrix costMatrix = null;
        try {
            costMatrix = new Cost_Matrix("Cost_Matrix.txt", 5, 5);
        } catch (Exception e) {
            System.out.println("File not found");
            System.exit(1);
        }
        List<Campus> campuses = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            campuses.add(new Campus("Campus " + i));
        }

        int iterations = 1000;

        IterativeSearch ILS = new IterativeSearch(campuses, costMatrix, iterations);
        Solution bestSolution = ILS.run();

        // Print the best solution
        System.out.println("Best Route:");
        for (Campus campus : bestSolution.route) {
            System.out.println(campus.name);
        }
        System.out.println("Total Distance: " + bestSolution.calculateTotalDistance(costMatrix));

        int numIterations = 1000;
        double avgObjFuncValue = ILS.averageObjectiveFunctionValue(numIterations);
        System.out.println("Average Objective Function Value: " + avgObjFuncValue);


    }
}
