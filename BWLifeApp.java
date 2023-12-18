import javax.swing.*;
import java.util.Scanner;

// Main class for the Game of Life application
public class BWLifeApp {
    // Instance variables for the model, views, and controller
    private BWLifeModel model;
    private BWLifeConsoleView view;
    private BWLifePanelView panelView;
    private BWLifeController controller;

    // Constructor for BWLifeApp, initializes the model, views, and controller
    public BWLifeApp() {
        // Create a new BWLifeModel instance
        model = new BWLifeModel();
        // Create instances of BWLifeConsoleView and BWLifePanelView, passing the model
        view = new BWLifeConsoleView(model);
        panelView = new BWLifePanelView(model);
        // Get user input for the timer duration
        int timerDuration = BWLifeModel.getNewTimerUserInput();
        // Create a new BWLifeController instance, passing the model and timer duration
        controller = new BWLifeController(model, timerDuration);


    }


    // Main method for the BWLifeApp application
    public static void main(String[] args) {
                    // Run the application on the Event Dispatch Thread to ensure proper Swing behavior
                    SwingUtilities.invokeLater(() -> {
                        // Create an instance of BWLifeApp, initializing the application
                        new BWLifeApp();

            });
            }
}
