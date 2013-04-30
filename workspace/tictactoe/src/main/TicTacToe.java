package main;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class TicTacToe extends java.applet.Applet {
    // Ignore this constant
    private static final long serialVersionUID = 1942709821640345256L;

    // You can change this boolean constant to control debugging log output
    private static final boolean DEBUGGING = false;

    // Constants

    // Size of one side of the board in pixels
    private static final int BOARD_SIZE_PIXELS = 600;
    // Number of squares on one side of the board
    private static final int SQUARES = 3;
    // Diameter of the circle drawn in each square
    private static final int CIRCLE_WIDTH = 90;

    // Colors to be used in the game
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color SQUARE_BORDER_COLOR = Color.BLACK;
    private static final Color GAME_OVER_MESSAGE_COLOR = Color.BLACK;
    private static final Color HUMAN_COLOR = Color.RED;
    private static final Color HUMAN_WINNING_COLOR = Color.MAGENTA;
    private static final Color CPU_COLOR = Color.BLUE;
    private static final Color CPU_WINNING_COLOR = Color.CYAN;

    // Status constant values for the game board
    private static final int EMPTY = 0;
    private static final int HUMAN = 1;
    private static final int HUMAN_WINNING = 2;
    private static final int CPU = -1;
    private static final int CPU_WINNING = -2;

    // String displayed when the game ends
    private static final String GAME_WIN_MESSAGE = "You win! Click to play again.";
    private static final String GAME_LOSE_MESSAGE = "You lose......Click to play again.";
    private static final String GAME_DRAW_MESSAGE = "No one wins? Click to play again...";

    // Instance variables that control the game

    // Whether or not the user just clicked the mouse
    private boolean mouseClicked = false;
    // Whether or not to start the game again
    private boolean restart = false;
    // Whether or not the CPU should start playing a move.
    // USED ONLY WHEN THE CPU PLAYS FIRST!
    private boolean onFirstMove = false;
    // The column and row of the SQUARE the user clicked on
    private int xMouseSquare; // column
    private int yMouseSquare; // row
    // The width (and height) of a single game square
    private int squareWidth = BOARD_SIZE_PIXELS / SQUARES;
    // An array to hold square status values on the board.
    // The status values can be EMPTY, HUMAN, CPU or other values
    private int[][] gameBoard;
    // The column and row of the SQUARE the CPU player will move on
    private int xCPUMove;
    private int yCPUMove;
    final int gameWins[][] = new int[][] {
        {1, 2, 3}, {1, 4, 7}, {1, 5, 9},
        {4, 5, 6}, {2, 5, 8}, {3, 5, 7},
        {7, 8, 9}, {3, 6, 9}
    };
    private int gameWin = -1;
    //horizontal, vertical and diagonal wins in an array
    // TODO
    // Add the rest of your instance variables here, if you need any. (You won't
    // need to, but you may if you want to.)

    // Ignore these instance variables

    // CPUinMove represents if the CPU is thinking (generating the CPU move).
    // If it is true, it means the CPUMove() method is running and no new move
    // should be added
    private boolean CPUinMove;
    
    // Methods that you need to write:

    /*
     * Pre: x and y are x-coordinate and y-coordinate where the user just
     * clicks. squareWidth is the width (and height) of a single game square.
     * 
     * Post: xMouseSquare and yMouseSquare are set to be the column and row
     * where the user just clicked on (depending on x and y).
     * 
     * Hint: You need only two statements in this method.
     */
    private void setMouseSquare(int x, int y) { // 150, 20
        // TODO
        xMouseSquare = x / squareWidth;
        yMouseSquare = y / squareWidth;
    }

    /*
     * Pre: SQUARES is an int that holds the number of game squares along one
     * side of the game board. xSquare is an int such that 0 <= xSquare <
     * SQUARES. CIRCLE_WIDTH is an int that holds the diameter of the circle to
     * be drawn in the center of a square. squareWidth is an int that holds the
     * width and height in pixels of a single game square.
     * 
     * Post: Return the correct x-coordinate (in pixels) of the left side of the
     * circle centered in a square in the column xSquare.
     * 
     * Hint: This method should be very simple. What you need to do is to find
     * the right equation.
     */
    private int getIconDisplayXLocation(int xSquare) {
        // TODO
        
        // This line is an example of using DEBUGGING variable
        if (DEBUGGING) {
            System.out.println("The input that getIconDisplayXLocation() receives is: " + xSquare);
        }
        
        return xSquare * squareWidth + ((squareWidth - CIRCLE_WIDTH) / 2);
    }

    /*
     * Pre: SQUARES is an int that holds the number of game squares along one
     * side of the game board. ySquare is an int such that 0 <= ySquare <
     * SQUARES. CIRCLE_WIDTH is an int that holds the diameter of the circle to
     * be drawn in the center of a square. squareWidth is an int that holds the
     * width and height in pixels of a single game square.
     * 
     * Post: Return the correct y-coordinate (in pixels) of the top of the
     * circle centered in a square in the row ySquare.
     * 
     * Hint: This method should be very simple. What you need to do is to find
     * the right equation.
     */
    private int getIconDisplayYLocation(int ySquare) {
        // TODO
        return ySquare * squareWidth + ((squareWidth - CIRCLE_WIDTH) / 2);
    }

    /*
     * The instance variable gameBoard will be created and initialized
     * 
     * Pre: SQUARES is set to an int. gameBoard is a 2-dimensional array type
     * variable that holds the status of current game board. Each value in the
     * array represents a square on the board
     * 
     * Post: gameBoard must be assigned a new 2-dimensional array. Every square
     * of gameBoard should be initialized to EMPTY.
     * 
     * Hint: A loop.
     */
    private void buildBoard() {
        // TODO
        // This line creates the gameBoard array. You must write several more
        // lines to initialize all its values to EMPTY
        gameBoard = new int[SQUARES][SQUARES];
        for (int i = 0; i < SQUARES; i++) {
            for (int j = 0; j < SQUARES; j++) {
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    /*
     * Returns whether the most recently clicked square is a legal choice in the
     * game.
     * 
     * Pre: gameBoard is a 2-dimensional array type variable that holds the
     * status of current game board. xSquare and ySquare represent the column
     * and row of the most recently clicked square.
     * 
     * Post: Returns true if and only if the square is a legal choice. If the
     * square is empty on current game board, it is legal and the method shall
     * return true; if it is taken by either human or CPU or it is not a square
     * on current board, it is illegal and the method shall return false.
     * 
     * Hint: Should be simple but think carefully to cover all cases.
     */
    private boolean legalSquare(int xSquare, int ySquare) {
        // TODO
        if (DEBUGGING) {
            System.out.println("x: "+xSquare);
            System.out.println("y: "+ySquare);
        }
        if (gameBoard[xSquare][ySquare] != EMPTY) return false;
        return true;
    }

    /*
     * Pre: gameBoard is an array that holds the current status of the game
     * board. xSquare and ySquare represent the column and row of the most
     * recently clicked square. player represent the current player (HUMAN or
     * CPU). This method is always called after checking legalSquare().
     * 
     * Post: Set the square as taken by current player on the game board if the
     * square is empty.
     * 
     * Hint: Very simple.
     */
    private void setMovePosition(int xSquare, int ySquare, int player) {
        // TODO
        if (legalSquare(xSquare, ySquare)) {
            gameBoard[xSquare][ySquare] = player;
        }
    }

    /*
     * Check if HUMAN or CPU wins the game.
     * 
     * Pre: gameBoard is an array to hold square status values on the board. The
     * status values can be EMPTY, HUMAN, CPU or other values.
     * 
     * Post: The method will return true if and only if a player wins the game.
     * Winning a game means there is at least one row, one column, or one
     * diagonal that is taken by the same player. The method does not need to
     * indicate who wins because if it is implemented correctly, the winner must
     * be the one who just made a move.
     * 
     * Hint: Complicated method. Use loops to shorten your code. Think about how
     * to represent "a player takes 3 squares in a row/column/diagonal".
     */

    private boolean gameEnd() {
        // TODO
        for (int i = 0; i < gameWins.length; i++) {
            int[] win = gameWins[i];
            int numres = 0;
            for (int j = 0; j < win.length; j++) {
                numres += gameBoard[(win[j]-1) / win.length][(win[j]-1) % win.length];
            }
            /*
             * 0,0 | 0/3 = 0 | 0 % 3 = 0
             * 0,1 | 1/3 = 0 | 1 % 3 = 1
             * 0,2 | 2/3 = 0 | 2 % 3 = 2
             * 1,0 | 3/3 = 1 | 3 % 3 = 0
             * 1,1 | 4/3 = 1 | 4 % 3 = 1
             * 1,2 | 5/3 = 1 | 5 % 3 = 2
             * 2,0 | 6/3 = 2 | 6 % 3 = 0
             * 2,1 | 7/3 = 2 | 7 % 3 = 1
             * 2,2 | 8/3 = 2 | 8 % 3 = 2
             */
            if (numres == SQUARES || numres == -SQUARES) {
                gameWin = i;
                return true;
            }
        }
        return false;
    }

    /*
     * Check if the game ends as a draw.
     * 
     * Pre: gameBoard is an array to hold square status values on the board. The
     * status values can be EMPTY, HUMAN, CPU or other values. This method is
     * always called after gameEnd().
     * 
     * Post: The method will return true if and only if all squares on the game
     * board are taken by HUMAN or CPU players (no EMPTY squares left).
     * 
     * Hint: Should be simple. Use loops.
     */

    private boolean gameDraw() {
        // TODO
        for (int i = 0, j = SQUARES*SQUARES; i < j; i++) {
            if (gameBoard[i/SQUARES][i%SQUARES] == EMPTY) return false;
        }
        return true;
    }

    /*
     * Marks circles on the line on which a player wins.
     * 
     * Pre: g is Graphics object that is ready to draw on. HUMAN_WINNING_COLOR
     * and CPU_WINNING_COLOR are both Color objects that represent the color to
     * show when HUMAN/CPU wins. gameBoard is gameBoard is an array to hold
     * square status values on the board. The status values can be EMPTY, HUMAN,
     * CPU or other values.
     * 
     * Post: ALL the row(s)/column(s)/diagonal(s) on which a player wins will be
     * marked as the special color.
     * 
     * Hint: You must draw a new circle with the special color (to replace the
     * old circle) on the square if the square belongs to a winning
     * row/column/diagonal. You can change gameBoard because the board will be
     * reset after displaying the winning line. You can use helper methods
     * (existing ones or your own) to finish this method. Pay attention that
     * many functions in this method is similar to gameEnd(). You should think
     * about reusing code.
     * 
     * Hint2: This method is not necessary for the game logic. You don't have to
     * start it early. Start when you know everything else is correct.
     */

    private void markWinningLine(Graphics g) {
        // TODO
        Color WINCOLOR;
        int[] win = gameWins[gameWin];
        int user = gameBoard[(win[0]-1) / win.length][(win[0]-1) % win.length];
        if (user == HUMAN) {
            WINCOLOR = HUMAN_WINNING_COLOR;
        } else {
            WINCOLOR = CPU_WINNING_COLOR;
        }
        for (int i = 0; i < win.length; i++) {
            displayHit(g, WINCOLOR, (win[i]-1) / win.length, (win[i]-1) % win.length);
        }
        gameWin = -1;
    }

    /*
     * Generates the next square where the CPU plays a move.
     * 
     * Pre: gameBoard is gameBoard is an array to hold square status values on
     * the board. The status values can be EMPTY, HUMAN, CPU or other values.
     * 
     * Post: Set xCPUMove and yCPUMove to represent the column and the row which
     * CPU plans to play a move on. The respective square MUST BE EMPTY! It will
     * cause a logical error if this method returns a square that is already
     * taken!
     * 
     * Hint: Don't start too early -- currently this method works (though
     * naively). Make sure that everything else works before touching this
     * method!
     */

    private void CPUMove() {
        // TODO
        // The following block gives a naive solution -- it finds the first
        // empty square and play a move on it. You can use this method to test
        // other methods in the beginning. However, you must replace this block
        // with your own algorithms eventually.
        //naiveAI();
        AI();
    }

    /* Put any helper methods you wish to add here. */
    
    private int[] findMoves(){
        int num = 0;
        int[] returnArr = new int[SQUARES*SQUARES];
        for (int i = 0, j = SQUARES*SQUARES; i < j; i++) {
            if (gameBoard[i%SQUARES][i/SQUARES] == EMPTY) {
                returnArr[num++] = i+1;
            }
        }
        return returnArr;
    }
    
    private int winningAIMove(){
        for (int i = 0; i < gameWins.length; i++) {
            int[] win = gameWins[i];
            int numres = 0;
            for (int j = 0; j < win.length; j++) {
                numres += gameBoard[(win[j]-1) % win.length][(win[j]-1) / win.length];
            }
            if (numres == (-SQUARES)+1) {
                for (int j = 0; j < SQUARES; j++)
                    if (gameBoard[(win[j]-1) % win.length][(win[j]-1) / win.length] == EMPTY)
                        return win[j];
            }
        }
        return 0;
    }
    
    private int blockingAIMove(){
        for (int i = 0; i < gameWins.length; i++) {
            int[] win = gameWins[i];
            int numres = 0;
            for (int j = 0; j < win.length; j++) {
                numres += gameBoard[(win[j]-1) % win.length][(win[j]-1) / win.length];
            }
            if (numres == SQUARES-1) {
                for (int j = 0; j < SQUARES; j++)
                    if (gameBoard[(win[j]-1) % win.length][(win[j]-1) / win.length] == EMPTY)
                        return win[j];
            }
        }
        return 0;
    }
    
    public void AI(){
        int[] moves = findMoves();
        int winningMove = winningAIMove();
        int blockingMove = blockingAIMove();
        if (0 < winningMove || blockingMove > 0) {
            int nextMove = ((0 < winningMove) ? winningMove : blockingMove) - 1;
            if (DEBUGGING) {
                System.out.println("winmove: "+winningMove);
                System.out.println("blockmove: "+blockingMove);
                System.out.println("nextmove: "+nextMove);
            }
            xCPUMove = nextMove % SQUARES;
            yCPUMove = nextMove / SQUARES;
        } else naiveAI();
        if (DEBUGGING) {
            for (int i = 0; i < moves.length; i++) {
                System.out.println(moves[i]);
            }
        }
    }
    
    public void naiveAI(){
        if (DEBUGGING) {
            System.out.println("naiveAI");
        }
        for (int i = 0; i < SQUARES; i++) {
            for (int j = 0; j < SQUARES; j++) {
                if (gameBoard[i][j] == EMPTY) {
                    xCPUMove = i;
                    yCPUMove = j;
                    return;
                }
            }
        }
    }
    
    /* You will not need to change anything below this line. */

    /*
     * DO NOT change this method.
     * 
     * Set the game board to show a new, blank game.
     */
    private void wipeBoard(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, BOARD_SIZE_PIXELS, BOARD_SIZE_PIXELS);
    }

    /*
     * DO NOT change this method.
     * 
     * Displays a circle on g, of the given color, in the center of the given
     * square.
     */
    private void displayHit(Graphics g, Color color, int xSquare, int ySquare) {
        g.setColor(color);
        g.fillOval(getIconDisplayXLocation(xSquare),
                getIconDisplayYLocation(ySquare), CIRCLE_WIDTH, CIRCLE_WIDTH);
    }

    /*
     * DO NOT change this method.
     * 
     * This method handles mouse clicks. You will not need to call it.
     */
    @Override
    public boolean mouseDown(Event e, int xMouse, int yMouse) {
        if (isClickable()) {
            mouseClicked = true;
            setMouseSquare(xMouse, yMouse);
        }
        repaint();
        return true;
    }

    /*
     * DO NOT change this method.
     * 
     * This method handles drawing the board. You will not need to call it.
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    /*
     * DO NOT change this method.
     * 
     * Draws the border between game squares onto canvas. Also, draws the moves
     * that are already made.
     */
    private void displayGame(Graphics canvas) {
        canvas.setColor(SQUARE_BORDER_COLOR);
        for (int i = 0; i < BOARD_SIZE_PIXELS; i += squareWidth) {
            for (int j = 0; j < BOARD_SIZE_PIXELS; j += squareWidth) {
                canvas.drawRect(i, j, squareWidth, squareWidth);
            }
        }
        for (int i = 0; i < SQUARES; i++) {
            for (int j = 0; j < SQUARES; j++) {
                switch (gameBoard[i][j]) {
                case HUMAN:
                case HUMAN_WINNING:
                    displayHit(canvas, HUMAN_COLOR, i, j);
                    break;
                case CPU:
                case CPU_WINNING:
                    displayHit(canvas, CPU_COLOR, i, j);
                    break;
                default:
                    break;
                }
            }
        }
    }

    /*
     * DO NOT change this method.
     * 
     * This method relays information about the availability of mouse clicking
     * in the game. You will not need to call it.
     */
    private boolean isClickable() {
        return !CPUinMove;
    }

    /*
     * DO NOT change the contents this method.
     * 
     * If this method is changed to public void paint(Graphics canvas), it will
     * execute the program with the CPU-first order.
     * 
     * This method is like the "main" method (but for applets). You will not
     * need to call it. It contains most of the game logic.
     */
    // @Override
    public void CPUFirstpaint(Graphics canvas) {
        displayGame(canvas);
        if (mouseClicked) {
            if (onFirstMove) {
                CPUMove();
                setMovePosition(xCPUMove, yCPUMove, CPU);
                displayHit(canvas, CPU_COLOR, xCPUMove, yCPUMove);
                onFirstMove = false;
            } else {
                if (restart) {
                    wipeBoard(canvas);
                    setUpGame();
                    repaint();
                } else if (legalSquare(xMouseSquare, yMouseSquare)) {
                    setMovePosition(xMouseSquare, yMouseSquare, HUMAN);
                    displayHit(canvas, HUMAN_COLOR, xMouseSquare, yMouseSquare);
                    if (gameEnd()) {
                        markWinningLine(canvas);
                        canvas.setFont(new Font("SansSerif", Font.PLAIN, 30));
                        canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                        canvas.drawString(GAME_WIN_MESSAGE, squareWidth / 2,
                                squareWidth);
                        restart = true;
                    } else {
                        CPUinMove = true;
                        CPUMove();
                        setMovePosition(xCPUMove, yCPUMove, CPU);
                        displayHit(canvas, CPU_COLOR, xCPUMove, yCPUMove);
                        CPUinMove = false;
                        if (gameEnd()) {
                            markWinningLine(canvas);
                            canvas
                                    .setFont(new Font("SansSerif", Font.PLAIN,
                                            30));
                            canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                            canvas.drawString(GAME_LOSE_MESSAGE,
                                    squareWidth / 2, squareWidth);
                            restart = true;
                        } else if (gameDraw()) {
                            canvas
                                    .setFont(new Font("SansSerif", Font.PLAIN,
                                            30));
                            canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                            canvas.drawString(GAME_DRAW_MESSAGE,
                                    squareWidth / 2, squareWidth);
                            restart = true;
                        }
                    }
                }
            }
            mouseClicked = false;
        }
    }

    /*
     * DO NOT change this method.
     * 
     * This method is like the "main" method (but for applets). You will not
     * need to call it. It contains most of the game logic.
     */
    @Override
    public void paint(Graphics canvas) {
        // display the current game board
        displayGame(canvas);

        // the following block will run every time the user clicks the mouse
        if (mouseClicked) { // when the user clicks the mouse
            // if the game is ready to start or to be restarted
            if (restart) {
                // clear the window and set up the game again
                wipeBoard(canvas);
                setUpGame();
                repaint();
            }
            // else, if the game is in play, check if the click is on a legal
            // square
            else if (legalSquare(xMouseSquare, yMouseSquare)) {
                // if the square is legal, mark the corresponding position as
                // taken by HUMAN
                setMovePosition(xMouseSquare, yMouseSquare, HUMAN);
                // display the new position with a HUMAN_COLOR circle
                displayHit(canvas, HUMAN_COLOR, xMouseSquare, yMouseSquare);
                // check if the game ends (if it is, HUMAN wins)
                if (gameEnd()) {
                    // if HUMAN wins, mark the winning line as a special color.
                    markWinningLine(canvas);
                    // display the human winning message
                    canvas.setFont(new Font("SansSerif", Font.PLAIN, 30));
                    canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                    canvas.drawString(GAME_WIN_MESSAGE, squareWidth / 2,
                            squareWidth);
                    // mark the game as ready to restart
                    restart = true;
                } else if (gameDraw()) {
                    // if HUMAN doesn't win but the board is full, it is a draw
                    // display the draw message
                    canvas.setFont(new Font("SansSerif", Font.PLAIN, 30));
                    canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                    canvas.drawString(GAME_DRAW_MESSAGE, squareWidth / 2,
                            squareWidth);
                    // mark the game as ready to restart
                    restart = true;
                } else {
                    // if HUMAN doesn't win and the board is not full, the CPU
                    // is ready to move
                    CPUinMove = true;
                    // calculates the next CPU move
                    CPUMove();
                    // mark the corresponding position as taken by CPU
                    setMovePosition(xCPUMove, yCPUMove, CPU);
                    // display the new position with a CPU_COLOR circle
                    displayHit(canvas, CPU_COLOR, xCPUMove, yCPUMove);
                    CPUinMove = false;
                    if (gameEnd()) {
                        // if CPU wins, mark the winning line as a special
                        // color.
                        markWinningLine(canvas);
                        // display the human losing message
                        canvas.setFont(new Font("SansSerif", Font.PLAIN, 30));
                        canvas.setColor(GAME_OVER_MESSAGE_COLOR);
                        canvas.drawString(GAME_LOSE_MESSAGE, squareWidth / 2,
                                squareWidth);
                        // mark the game as ready to restart
                        restart = true;
                    }
                    // else (if the game is not ended after the CPU move), the
                    // game is ready to get the next HUMAN move
                }
            }
            mouseClicked = false;
        }
    }

    /*
     * DO NOT change this method.
     * 
     * This method initializes the applet. You will not need to call it.
     */
    @Override
    public void init() {
        setSize(BOARD_SIZE_PIXELS, BOARD_SIZE_PIXELS);
        setBackground(BACKGROUND_COLOR);
        setUpGame();
    }

    /*
     * DO NOT change this method.
     * 
     * Creates a fresh game board and sets up the game state to get ready for a
     * new game.
     */
    private void setUpGame() {
        buildBoard();
        CPUinMove = false;
        restart = false;
        onFirstMove = true;
    }

}