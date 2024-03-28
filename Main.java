public class Main {
    public static void main(String[] args) {
        // Create a cost matrix
        Cost_Matrix costMatrix = null;
        // IterativeSearch ILS = null;
        try {
            costMatrix = new Cost_Matrix("Cost_Matrix.txt", 5, 5);
        } catch (Exception e) {
            System.out.println("File not found");
            System.exit(1);
        }
       System.out.println(costMatrix);
    }
}
