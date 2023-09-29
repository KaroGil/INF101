package inf101v22.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.Color;


import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.TetrisBoard;
import inf101v22.tetris.model.Tile;


public class TetrisBoardTest {
    
    @Test
    void toCharArray2dTest(){
        TetrisBoard tetrisBoard = new TetrisBoard(15,10, new Tile(Color.black, '-'));
        
        String boardToString = tetrisBoard.charArray2dToString(tetrisBoard.toCharArray2d());
        System.out.println(boardToString);
    }
}
