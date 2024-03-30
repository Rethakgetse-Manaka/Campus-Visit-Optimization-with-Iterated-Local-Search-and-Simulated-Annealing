import java.util.Collections;
import java.util.List;

public class Solution {
    List<Campus> route;
    List<Campus> campuses;
    public Solution(List<Campus> route, List<Campus>campuses) {
        this.route = route;
        this.campuses = campuses;
    }

    public double calculateTotalDistance(Cost_Matrix costMatrix) {
        double totalDistance = 0.0;

        for (int i = 0; i < route.size() - 1; i++) {
            int currentCampusIndex = campuses.indexOf(route.get(i));
            int nextCampusIndex = campuses.indexOf(route.get(i + 1));
            double distance = costMatrix.getCost(currentCampusIndex, nextCampusIndex);
            totalDistance += distance;
        }

        // Add distance from last campus back to the starting campus (assuming TSP)
        int lastCampusIndex = campuses.indexOf(route.get(route.size() - 1));
        int firstCampusIndex = campuses.indexOf(route.get(0));
        totalDistance += costMatrix.getCost(lastCampusIndex, firstCampusIndex);

        return totalDistance;
    }

    public void swap(int i, int j) {
        Collections.swap(route, i, j);
    }
}
