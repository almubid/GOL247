import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BWLifeController class implements ActionListener to handle timer events
public class BWLifeController implements ActionListener {
    // Timer for triggering events at regular intervals
    private Timer timer;

    // Reference to the BWLifeModel
    private BWLifeModel model;

    // Counter to keep track of the number of iterations
    private int counter;

    // Constructor for BWLifeController, taking a BWLifeModel and timer duration as parameters
    public BWLifeController(BWLifeModel model, int timerDuration) {
        // Create a new Timer with the specified duration and set this class as the ActionListener
        timer = new Timer(timerDuration, this);
        this.model = model;
        this.counter = 0; // Initialize the counter

        // Start the timer
        timer.start();
    }

    // ActionListener method, called when the timer triggers an event
    @Override
    public void actionPerformed(ActionEvent e) {
        // Increment the iteration counter
        counter++;
        System.out.println("Number of Generations: " + counter);

        // Call the updateBord method of the BWLifeModel to update the board state
        model.updateBord();

        // Check if the desired number of iterations (N in this case) is reached
        if (counter == BWLifeModel.getNoofiterations()) {
            // Stop the timer after N (User Input) iterations
            timer.stop();
            System.out.println("Game of Life stopped after "+BWLifeModel.getNoofiterations()+" Generations.");
        }
    }
}
