import java.util.Random;

public class SimmulatedAnnealing {
    private Cost_Matrix costMatrix;

    private Random random;

    public SimmulatedAnnealing(Cost_Matrix costMatrix) {
        this.costMatrix = costMatrix;
        this.random = new Random();
    }

    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    public int calculateTotalDistance(int[] tour) {
        int totalDistance = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            totalDistance += costMatrix.getCost(tour[i], tour[i + 1]);
        }
        totalDistance += costMatrix.getCost(tour[tour.length - 1], tour[0]); // Return to the starting city
        return totalDistance;
    }

    public int[] generateInitialSolution(int numberOfCities) {
        int[] tour = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            tour[i] = i;
        }
        shuffleArray(tour);
        return tour;
    }

    private void shuffleArray(int[] array) {
        int index, temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public int[] simulatedAnnealing(int numberOfCities) {
        long startTime = System.nanoTime();
        int[] currentSolution = generateInitialSolution(numberOfCities);
        int[] bestSolution = currentSolution.clone();

        double temperature = 1000; // Initial temperature
        double coolingRate = 0.003; // Cooling rate

        while (temperature > 1) {
            int[] newSolution = currentSolution.clone();
            int city1 = random.nextInt(numberOfCities);
            int city2 = random.nextInt(numberOfCities);
            int temp = newSolution[city1];
            newSolution[city1] = newSolution[city2];
            newSolution[city2] = temp;

            int currentEnergy = calculateTotalDistance(currentSolution);
            int newEnergy = calculateTotalDistance(newSolution);

            if (acceptanceProbability(currentEnergy, newEnergy, temperature) > Math.random()) {
                currentSolution = newSolution;
            }

            if (calculateTotalDistance(currentSolution) < calculateTotalDistance(bestSolution)) {
                bestSolution = currentSolution.clone();
            }

            temperature *= 1 - coolingRate; // Cool down
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000; // in microseconds
        System.out.println("Execution time: " + duration + "µs");
        return bestSolution;
    }

    public void printTour(int[] tour) {
        for (int city : tour) {
            System.out.print(city + " ");
        }
        System.out.println();
    }
}
