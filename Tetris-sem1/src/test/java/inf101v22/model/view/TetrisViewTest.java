package inf101v22.model.view;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import inf101v22.tetris.model.TetrisModel;
import inf101v22.tetris.model.Tile;
import inf101v22.tetris.model.piece.PieceShape;
import inf101v22.tetris.view.TetrisView;


public class TetrisViewTest {
    
    @Test
    void getPreferredSizeTest() {
      TetrisView tW = new TetrisView(new TetrisModel());
      
      assertEquals(tW.getPreferredSize().getHeight(),  610);
      assertEquals(tW.getPreferredSize().getWidth(),  835);
      
    }
}
