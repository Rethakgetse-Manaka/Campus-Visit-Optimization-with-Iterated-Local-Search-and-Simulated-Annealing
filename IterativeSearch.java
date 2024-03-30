import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class IterativeSearch {
    List<Campus> campuses;
    Cost_Matrix costMatrix;
    int iterations;

    public IterativeSearch(List<Campus> campuses, Cost_Matrix costMatrix, int iterations) {
        this.campuses = campuses;
        this.costMatrix = costMatrix;
        this.iterations = iterations;
    }

    public Solution generateInitialSolution() {
        List<Campus> initialRoute = new ArrayList<>(campuses);
        Collections.shuffle(initialRoute);
        return new Solution(initialRoute,campuses);
    }

    public Solution localSearch(Solution solution) {
        Solution currentSolution = solution;
        double currentDistance = currentSolution.calculateTotalDistance(costMatrix);
        boolean improved = true;
        while (improved) {
            improved = false;
    
            for (int i = 0; i < currentSolution.route.size(); i++) {
                for (int j = i + 1; j < currentSolution.route.size(); j++) {
                    Solution neighborSolution = new Solution(new ArrayList<>(currentSolution.route),campuses);
                    neighborSolution.swap(i, j);
                    double neighborDistance = neighborSolution.calculateTotalDistance(costMatrix);
    
                    if (neighborDistance < currentDistance) {
                        currentSolution = neighborSolution;
                        currentDistance = neighborDistance;
                        improved = true;
                    }
                }
            }
        }
    
        return currentSolution;
    }

    public void perturbation(Solution solution) {
        Random random = new Random();
        int i = random.nextInt(solution.route.size());
        int j = random.nextInt(solution.route.size());
        solution.swap(i, j);
    }

    public Solution run() {
        Solution bestSolution = generateInitialSolution(); //Generate a random initial solution
        double bestSolutionTotalDistance = bestSolution.calculateTotalDistance(costMatrix);

        for(int i = 0; i < iterations; i++){
            Solution candidate = new Solution(new ArrayList<>(bestSolution.route),campuses);
            candidate = localSearch(candidate); //Apply local search and obtain a local optimum
            perturbation(candidate); // Pertub the local optimum 
            double newSolutionTotalDistance = candidate.calculateTotalDistance(costMatrix);

            if(newSolutionTotalDistance < bestSolutionTotalDistance){ // If the new solution is better than the best solution, update the best solution
                bestSolution = candidate;
                bestSolutionTotalDistance = newSolutionTotalDistance;
            }
        }

        return bestSolution;
    }
    
    public double averageObjectiveFunctionValue(int numberOfRuns) {
        double totalObjectiveFunctionValue = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            Solution solution = run();
            double objectiveFunctionValue = solution.calculateTotalDistance(costMatrix);
            totalObjectiveFunctionValue += objectiveFunctionValue;
        }

    
        return totalObjectiveFunctionValue / numberOfRuns;
    }
}
