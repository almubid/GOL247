import com.sun.jdi.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class BWLifeModel extends Observable
{

    protected final int ROWSIZE ;
    protected final int COLUMNSIZE ;
    private final int KANS_OP_LEVENDE_CEL = 25;

    private BWLifeController controller;

    protected boolean[][] bord;

    public static String input;

    public static String panelSize;

    public static String numberofiterations;

    public static char panel;

    public static String ROWSIZEINPUT;

    public static String COLUMNSIZEINPUT;
    public BWLifeModel()
    {



        // Scanner for user input to pattern file name
        Scanner user = new Scanner(System.in);
        String inputFileName;
        final int CHANCE_ALIVE = 6, SEED = 10;


        System.out.print("Welcome to Bala & William's Game Of Life Project" + "\n----------------------------------------------------------"+"\n");

        final String exitMessage = "----------------------------"
                + "\nEnd of Conway's Game Of Life";

        final String optionList = "1)Glider"+"\n2)R-pentomino"
                + "\n3)From Pattern File" + "\nChoose a pattern: ";

        // Scanner to get user input
        Scanner in = new Scanner(System.in);

        Scanner scanner = new Scanner(System.in);



        int userChoice = 0;

        // Scanner for user input to get sleeper time
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter Sleeper Time in Milliseconds: ");
        input = scanner1.nextLine();

        // Scanner for user input to get panel size
        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Choose Panel Size (S/M/L): ");
        panelSize = scanner2.nextLine();

        panel = panelSize.charAt(0);

        // Scanner for user input to get number of generations
        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Number of Generations: ");
        numberofiterations = scanner3.nextLine();

        // Scanner for user input to get Column Size
        Scanner scanner6 = new Scanner(System.in);
        System.out.print("Enter Column Size: ");
        COLUMNSIZEINPUT = scanner6.nextLine();

        COLUMNSIZE = Integer.parseInt(COLUMNSIZEINPUT);

        // Scanner for user input to get Row Size
        Scanner scanner5 = new Scanner(System.in);
        System.out.print("Enter Row Size: ");
        ROWSIZEINPUT = scanner5.nextLine();

        ROWSIZE = Integer.parseInt(ROWSIZEINPUT);

        bord = new boolean[ROWSIZE][COLUMNSIZE];

        do {
            // Display menu

            System.out.print(optionList);
            //int value2 = scanner.nextInt();


            userChoice = getIntChoice(in, 1, 3);
            //System.out.println(userChoice);


            if (userChoice != 4) {
                if (userChoice == 1) {
                    // Glider pattern
                    bord[1][1] = true;
                    bord[2][2] = true;
                    bord[2][3] = true;
                    bord[3][1] = true;
                    bord[3][2] = true;

                }


                else if (userChoice == 2) {

                    // R-pentomino pattern
                    bord[1][3] = true;
                    bord[1][2] = true;
                    bord[2][1] = true;
                    bord[2][2] = true;
                    bord[3][2] = true;

                    }


                else if (userChoice == 3) {
                    // Pattern from file
                    System.out.print("Enter the Pattern File Name including its network path: ");
                    inputFileName = user.nextLine().trim();

                    String filePath = inputFileName;

                    try {
                        // Read the pattern file
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));


                        int rows = 100;
                        int cols = 100;
                        boolean[][] booleanMatrix = new boolean[rows][cols];

                        String line;
                        int row1 = 0;

                        // Read each line from the file
                        while ((line = reader.readLine()) != null && row1 < rows) {
                            // Process each character in the line
                            for (int col = 0; col < Math.min(line.length(), cols); col++) {
                                // If the character is '1', set the corresponding matrix element to true
                                //booleanMatrix[row1][col] = (line.charAt(col) == '*');
                                bord[row1][col] = (line.charAt(col) <= CHANCE_ALIVE);
                                //System.out.println(world[row1][col]);
                                String s1=String.valueOf(bord[row1][col]);
                                //System.out.println("S1:"+s1);
                                if(s1 == "false") {
                                    bord[row1][col] = true;
                                    //System.out.println("Row: "+row1+"Col: "+col);
                                }


                              }
                            row1++;

                        }

                        // Close the file reader
                        reader.close();

                        // Print the Boolean matrix
                        //printBooleanMatrix(booleanMatrix);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }


                String stringChoice = "";
                int generationNum = 0;

            }



        } while (userChoice==4) ;

        //System.out.println(exitMessage);

        // Close scanner
        in.close();


                    }
    protected void updateBord()
    {
        boolean[][] nieuwBord = new boolean[ROWSIZE][COLUMNSIZE];

        for (int r = 0; r < ROWSIZE; r++)
          for (int k = 0; k < COLUMNSIZE; k++)
             nieuwBord[r][k] = checkLevend(r, k);

        bord = nieuwBord;

        setChanged();
        notifyObservers();


    }

    private boolean checkLevend(int rij, int kolom)
    {
        if (bord[rij][kolom] & getAantalBurenCel(rij, kolom) == 2)
            return true;
        else if (getAantalBurenCel(rij, kolom) == 3)
            return true;
        else
            return false;
    }

    private int getAantalBurenCel(int rij, int kolom)
    {


        int aantalBuren=0;
        for (int r = rij-1; r <= rij+1; r++)
            for (int k = kolom-1; k <= kolom+1; k++)
            {
                if (r < 0 | k < 0)
                    continue;
                else if (k >= COLUMNSIZE | r >= ROWSIZE)
                    continue;

                if(bord[r][k] & (r != rij | k != kolom))
                    aantalBuren++;
            }
        return aantalBuren;


    }

    public static int getIntChoice(Scanner input, int min, int max) {
        // Get user choice
        int userChoice = 0;

        while (true) {
            boolean error = false;
            try {
                userChoice = Integer.parseInt(input.next());
                if ((min <= userChoice) && (userChoice <= max))
                    error = false;
                else
                    error = true;
            } catch (NumberFormatException e) {
                error = true;
            }

            if (error) {
                // If user enters anything other than a value between 1-7
                System.out.print("Enter a number between 1 and 3: ");
            } else {
                break;
            }
        }

        return userChoice;
    }


    public static int getNewTimerUserInput() {
        int i=Integer.parseInt(input);
        return i;
    }

    public static int getPanelSizeinput() {
        int i= 0;
        if (panel=='S')
        { i = 20;}
        else if (panel=='M')
        { i = 30;}
        else if(panel=='L')
        { i = 40;}
        return i;
    }

    public static int getNoofiterations() {
        int i=Integer.parseInt(numberofiterations);
        return i;
    }

}