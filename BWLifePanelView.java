import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class BWLifePanelView extends JPanel implements Observer {
    private JFrame lifeFrame;
    private BWLifeModel model;
    private JButton[][] jButtons;
    private JButton quitButton;  // Change from Button to JButton

    // Constructor for BWLifePanelView
    public BWLifePanelView(BWLifeModel model) {
        this.model = model;
        model.addObserver(this);

        // Initialize graphical user interface components
        maakJButtons();
        toonGUI();
    }

    // Set up the main graphical user interface
    private void toonGUI() {
        setLayout(new GridLayout(model.ROWSIZE, model.COLUMNSIZE));

        // Customize the look and feel of the UI
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.green));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.red));

        // Create the main JFrame

        lifeFrame = new JFrame();
        lifeFrame.setBackground(Color.red);
        lifeFrame.setBounds(100, 100, model.COLUMNSIZE * BWLifeModel.getPanelSizeinput(), model.ROWSIZE * BWLifeModel.getPanelSizeinput());
        lifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lifeFrame.setTitle("Bala & William's Game Of Life Project");

        // Add this panel to the main JFrame
        lifeFrame.add(this);
        lifeFrame.setVisible(true);

        // Create and configure the quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the application when the quit button is clicked
                System.exit(0);
            }
        });

        // Add the quit button and this panel to the main JFrame
        lifeFrame.add(quitButton, BorderLayout.NORTH);
        lifeFrame.add(this, BorderLayout.CENTER);
        lifeFrame.setVisible(true);
    }

    // Create JButtons grid and add it to the panel
    private void maakJButtons() {
        jButtons = new JButton[model.ROWSIZE][model.COLUMNSIZE];
        for (int r = 0; r < model.ROWSIZE; r++)
            for (int k = 0; k < model.COLUMNSIZE; k++) {
                // Initialize and configure JButtons
                jButtons[r][k] = new JButton();
                jButtons[r][k].setBackground(getBGColor(r, k));
                jButtons[r][k].setOpaque(true);
                jButtons[r][k].setBorderPainted(false);
                // Add JButtons to the panel
                add(jButtons[r][k]);
            }
    }

    // Create a new grid of JButtons for the next generation
    private JButton[][] nextGenButtons() {
        JButton[][] nieuwJButtons = new JButton[model.ROWSIZE][model.COLUMNSIZE];
        for (int r = 0; r < model.ROWSIZE; r++) {
            for (int k = 0; k < model.COLUMNSIZE; k++) {
                // Initialize and configure JButtons for the next generation
                nieuwJButtons[r][k] = new JButton();
                nieuwJButtons[r][k].setBackground(getBGColor(r, k));
                nieuwJButtons[r][k].setOpaque(true);
                nieuwJButtons[r][k].setBorderPainted(false);
            }
        }
        return nieuwJButtons;
    }

    // Redraw the board by removing all components, repainting, and adding the new JButton grid
    private void redrawBord() {
        removeAll();
        repaint();
        jButtons = nextGenButtons();
        for (JButton[] bArr : jButtons)
            for (JButton b : bArr)
                add(b);
        revalidate();
    }

    // Get the background color based on the cell state in the model
    private Color getBGColor(int rij, int kolom) {
        if (model.bord[rij][kolom])
            return Color.blue;
        else
            return Color.gray;
    }

    // Observer method, called when the observed object (BWLifeModel) is updated
    @Override
    public void update(Observable o, Object arg) {
        redrawBord();
    }
}
