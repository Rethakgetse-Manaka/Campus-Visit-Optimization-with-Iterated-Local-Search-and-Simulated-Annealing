import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cost_Matrix {
    private int[][] matrix;
    private int rows;
    private int cols;

    public Cost_Matrix(String filename,int rows,int cols) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        this.rows = rows;
        this.cols = cols;

        matrix = new int[this.rows][this.cols];

        // Read the matrix data
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
    }

    public int getCost(int i, int j) {
        return matrix[i][j];
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
    // Other methods as needed...
}

