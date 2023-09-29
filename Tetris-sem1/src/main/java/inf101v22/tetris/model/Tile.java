package inf101v22.tetris.model;

import java.awt.Color;

public class Tile {
    //class variables.
    public final Color color;
    public final char character;


    //constructor
    public Tile(final Color color, final char character){
        this.color = color;
        this.character = character;
    }

}
