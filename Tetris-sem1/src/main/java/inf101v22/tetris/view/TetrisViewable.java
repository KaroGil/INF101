package inf101v22.tetris.view;

import java.util.List;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;

public interface TetrisViewable {
    
     /**
     * 
     * @return num of rows
     */

    int getRows();

    /**
     * 
     * 
     * @return num of cols
     */

    int getCols();

    /**
     *Goes through the pieces on the board and 
     *returns the coordinate and tile values.
     * @return coordinates of pieces and values. 
     */

    Iterable<CoordinateItem<Tile>> iterable();

    /**
     * Goes through the falling pieces and returns the coordinate and tile values.
     * @return coordinates and tiles of fallen pieces
     */
    Iterable<CoordinateItem<Tile>> fallingPieces();


    /**
     * gets the next piece and returns it.
     * @return coordinates and tiles of next falling pieces
     */
    Iterable<CoordinateItem<Tile>> NextPieces();

    /**
     * gets the gamescreen value.
     * @return Gamescreen value
     */
    GameScreen getGameScreen();

    /**
     * gets the score
     * @return score value in int
     */
    int getScore();

     /**
     * gets the highscore array
     * @return List<Integer> of highscores
     */
    List<Integer> getHighscore();

     
    /**
     * gets the shadow/ghost piece and returns it.
     * @return coordinates and tiles of the shadow of the piece
     */
    Iterable<CoordinateItem<Tile>> GhostPiece();
}
