import java.util.Observable;
import java.util.Observer;

// BWLifeConsoleView class implements the Observer interface to observe changes in the BWLifeModel
public class BWLifeConsoleView implements Observer {
    private BWLifeModel model;  // Reference to the BWLifeModel being observed

    // Constructor to initialize the BWLifeConsoleView with a BWLifeModel
    public BWLifeConsoleView(BWLifeModel model) {
        this.model = model;
        model.addObserver(this);  // Register this view as an observer of the BWLifeModel
    }

    // Display the current state of the board in the console
    protected void toonBord() {
        System.out.print("\n");
        // Iterate through each row of the board
        for (int r = 0; r < model.bord.length; r++) {
            // Iterate through each column of the board
            for (int k = 0; k < model.bord[r].length; k++) {
                // Display 'X' for live cell, '.' for dead cell
                if (model.bord[r][k])
                    System.out.print("X ");
                else
                    System.out.print(". ");
            }
            System.out.print("\n");  // Move to the next line after each row
        }
    }

    // Observer method, called when the observed object (BWLifeModel) is updated
    @Override
    public void update(Observable o, Object arg) {
        toonBord();  // Display the updated board when notified of a change in the BWLifeModel
    }
}
