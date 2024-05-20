/**
 * The Main class is the entry point of the application.
 * It initializes the Program class with specific parameters to plot the graph.
 */
public class Main {
    /**
     * The main method which serves as the entry point of the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the Program class with parameters:
        // A = 3, B = 5, tMin = 0, tMax = 2 * PI, deltaT = 0.001

        Program graph = new Program(3, 5, 0, Math.PI * 2, 0.001);
    }
}
