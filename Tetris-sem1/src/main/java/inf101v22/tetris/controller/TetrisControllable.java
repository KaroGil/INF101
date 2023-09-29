package inf101v22.tetris.controller;

import inf101v22.tetris.model.GameScreen;

public interface TetrisControllable {
    
    /**
     * Method to move a fallen piece. 
     * @param deltaRow change in rows
     * @param deltaCol change in columns
     * @return boolean with truth value about if the movment was completed.
     */
    boolean moveFallingPiece(int deltaRow, int deltaCol);

    /**
     * Method to rotate fallen piece.
     * @param direction decides the direction of the rotation
     */
    void rotateFallingPiece(int direction);

    /**
     * method to drop the piece.
     */
    void dropPiece();

    /**
     * gets the gamescreen value.
     * @return Gamescreen value
     */
    GameScreen getGameScreen();


    /**
     * sets the gameScreen value.
     * @param gameScreen the gameScreen we set the game to
     */
    void setGameScreen(GameScreen gameScreen);

    /**
     * gets how many milliseconds there should be between each time the block moves. 
     * @return int (2000 in return means 2 seconds, since its milliseconds it returns.)
     */
    int getMilliseconds();

    /**
     * method that gets called each time the clock ticks
     */
    void clockTick();

    /**
     * resets the board and score to start a new game.
     */
    void newGame();
}
