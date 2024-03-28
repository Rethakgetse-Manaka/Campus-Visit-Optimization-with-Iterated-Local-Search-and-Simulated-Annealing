import java.util.Collections;
import java.util.List;

public class Solution {
    List<Campus> route;

    public Solution(List<Campus> route) {
        this.route = route;
    }

    public double calculateTotalDistance(Cost_Matrix costMatrix) {
        double totalDistance = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Campus currentCampus = route.get(i);
            Campus nextCampus = route.get(i + 1);
            int currentCampusIndex = Integer.parseInt(currentCampus.name.substring(7)) - 1;
            int nextCampusIndex = Integer.parseInt(nextCampus.name.substring(7)) - 1;
            totalDistance += costMatrix.getCost(currentCampusIndex, nextCampusIndex);
        }
        // Add distance from the last campus back to the first one
        Campus firstCampus = route.get(0);
        Campus lastCampus = route.get(route.size() - 1);
        int firstCampusIndex = Integer.parseInt(firstCampus.name.substring(7)) - 1;
        int lastCampusIndex = Integer.parseInt(lastCampus.name.substring(7)) - 1;
        totalDistance += costMatrix.getCost(lastCampusIndex, firstCampusIndex);
        return totalDistance;
    }

    public void swap(int i, int j) {
        Collections.swap(route, i, j);
    }
}
