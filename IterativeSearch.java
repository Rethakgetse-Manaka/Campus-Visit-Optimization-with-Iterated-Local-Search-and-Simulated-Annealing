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
        return new Solution(initialRoute);
    }

    public Solution localSearch(Solution solution) {
        // Implement local search by swapping two campuses if it improves the solution
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 0; i < solution.route.size(); i++) {
                for (int j = i + 1; j < solution.route.size(); j++) {
                    Solution candidate = new Solution(new ArrayList<>(solution.route));
                    candidate.swap(i, j);
                    double candidateDistance = candidate.calculateTotalDistance(costMatrix);
                    double currentDistance = solution.calculateTotalDistance(costMatrix);
                    if (candidateDistance < currentDistance) {
                        solution = candidate;
                        improved = true;
                    }
                }
            }
        }
        return solution;
    }

    public void perturbation(Solution solution) {
        Random random = new Random();
        int i = random.nextInt(solution.route.size());
        int j = random.nextInt(solution.route.size());
        solution.swap(i, j);
    }

    public Solution run() {
        Solution bestSolution = generateInitialSolution();
        double bestDistance = bestSolution.calculateTotalDistance(costMatrix);

        for (int i = 0; i < iterations; i++) {
            Solution candidate = new Solution(new ArrayList<>(bestSolution.route));
            perturbation(candidate);
            candidate = localSearch(candidate);
            double candidateDistance = candidate.calculateTotalDistance(costMatrix);

            if (candidateDistance < bestDistance) {
                bestSolution = candidate;
                bestDistance = candidateDistance;
            }
        }

        return bestSolution;
    }
}
