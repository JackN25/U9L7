import java.util.Scanner;

public class GridGame {
    private Space[][] board;
    private Player player;
    private Scanner scanner;
    private static String[] VALID_MOVES = {"w", "a", "s", "d"};

    public GridGame() {
        scanner = new Scanner(System.in);
        createPlayer();
        setupBoard();
        play();
    }

    private void createPlayer() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        player = new Player(name);
    }

    // initialize the board instance variable to be a 8x8 board;
    // place new Space object with "_" as the symbol into each board position;
    // place the Player object at lower left corner;
    // initialize and place a Goal object with the symbol "X" in the upper right corner;
    // create several Treasure objects (up to you how many), with symbol of your choice,
    // each with a point value that you decide, and place them throughout the board
    private void setupBoard() {
        board = new Space[8][8];
        for (int row = 0; row < 8; row ++) {
            for (int column = 0; column < 8; column ++) {
                board[row][column] = new Space("_");
            }
        }
        board[7][0] = new Space(player.getSymbol());
        board[0][7] = new Space("X");
        board[5][3] = new Treasure("#", 20);
        board[2][4] = new Treasure("#", 20);
        board[4][6] = new Treasure("#", 20);
    }

    /* prints the 2D array board, showing the symbol for each Space, e.g.
       _______X
       __#_____
       _____#__
       _#______
       ________
       ______#_
       ________
       M___#___
     */
    private void printBoard() {
        for (Space[] row : board) {
            for (Space column : row) {
                System.out.print(column.getSymbol());
            }
            System.out.println();
        }
    }

    // plays the game;
    private void play() {

        // WRITE THIS METHOD
        // main game loop:
        // while the player has not yet reached the goal, print the board (complete can call helper method below)
        // then asks user to enter a direction: W, A, S, D (up, left, down, right).
        // if the intended direction is in bounds, move the Player to the new location and fill previous position
        // with a Space object (with "_" symbol).
        // if player moves to a position occupied by a Treasure, add its point value to the players score,
        // and replace that element with a Space object (with "_" symbol).
        // if the player reaches the goal, end the game and print their final score and the number of moves it took
        while (!board[0][7].getSymbol().equals(player.getSymbol())) {
            printBoard();
            System.out.print("Enter a direction: W, A, S, D (up, down, left, right): ");
            String input = scanner.nextLine();
            if (validMove(input)) {

            } else if (!input.equalsIgnoreCase("w") && !input.equalsIgnoreCase("a") && !input.equalsIgnoreCase("s") || !input.equalsIgnoreCase("d")){
                System.out.println("Invalid Move!");
            } else {
                System.out.println("Out Of Bounds!");
            }
        }


    }

    private boolean validMove(String moveMade) {
        int playerX = 0;
        int playerY = 0;
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board[row][column].getSymbol().equals(player.getSymbol())) {
                    playerX = column;
                    playerY = row;
                }
            }
        }
        if (moveMade.equalsIgnoreCase("w") && playerY - 1 >= 0) {
            return true;
        } else if (moveMade.equalsIgnoreCase("a") && playerX - 1 >= 0) {
            return true;
        } else if (moveMade.equalsIgnoreCase("s") && playerY + 1 < 8) {
            return true;
        } else if (moveMade.equalsIgnoreCase("d") && playerX + 1 < 8) {
            return true;
        } else {
            return false;
        }
    }
}