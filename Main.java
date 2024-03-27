public class Main {
    public static void main(String[] args) {
        // Create a cost matrix
        Cost_Matrix costMatrix = null;
        try {
            costMatrix = new Cost_Matrix("Cost_Matrix.txt", 5, 4);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("File not found");
            System.exit(1);
        }
        System.out.println("Cost Matrix:");
        System.out.println(costMatrix.toString());

    }
}