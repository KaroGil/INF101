package inf101v22.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.model.piece.PieceShape;
import inf101v22.tetris.model.piece.PositionedPieceFactory;

/**
 * Testing the class Grid
 */
public class TetrisModelTest {
   
    
    @Test
    void rotateFallingPieceTest() {
        PieceShape lShape = PieceShape.L;
        boolean[][] rotatedLShape = new boolean[][] {
            { true,  true },
            { false, true },
            { false, true}
        };

        //checks if rows become cols
        assertEquals(lShape.rotatedCopyOfPiece(0).getWidth(), lShape.getHeight());
        assertEquals(lShape.rotatedCopyOfPiece(0).getHeight(), lShape.getWidth());

        //checks if the arrays are the same
        assertFalse(Arrays.deepEquals(lShape.getShape(), lShape.rotatedCopyOfPiece(0).getShape()));

        //checks if the method rotates the shape correctly
        assertTrue(Arrays.deepEquals(lShape.rotatedCopyOfPiece(0).getShape(), rotatedLShape));
    }
    
}
