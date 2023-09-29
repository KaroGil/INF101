package inf101v22.model.piece;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PieceShape;


public class PieceShapeTest{


    @Test
    void PieceShapeSanityTest() {
        PieceShape p = new PieceShape(new Tile(Color.MAGENTA, 'O'), new boolean[][] {
            {  true,  true },
            {  true,  true }    
        });

        assertEquals(PieceShape.O.tile.color, p.tile.color);
      
    }

    @Test
    void getWidthTest() {
        assertEquals(PieceShape.O.getWidth(), 2);
        assertEquals(PieceShape.L.getWidth(), 3);
        assertEquals(PieceShape.J.getWidth(), 3);
        assertEquals(PieceShape.I.getWidth(), 4);
      
    }

    @Test
    void getHeightTest() {
        assertEquals(PieceShape.O.getHeight(), 2);
        assertEquals(PieceShape.L.getHeight(), 2);  
        assertEquals(PieceShape.J.getHeight(), 2);  
        assertEquals(PieceShape.I.getHeight(), 1);     
    }

    @Test
    void getShapeTest() {

        assertTrue(Arrays.deepEquals(PieceShape.O.getShape(), new boolean[][] {
            {  true,  true },
            {  true,  true }    
        }));
    }

    @Test
    void getTileTest() {

        assertEquals(PieceShape.O.tile.character, 'O');
    }
    
    @Test
    void rotatedCopyOfPieceTest() {

        assertFalse(Arrays.deepEquals(PieceShape.L.rotatedCopyOfPiece(0).getShape(),PieceShape.L.getShape()));
    }
    
}
