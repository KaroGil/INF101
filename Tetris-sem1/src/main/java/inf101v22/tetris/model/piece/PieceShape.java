package inf101v22.tetris.model.piece;

import java.awt.Color;


import inf101v22.tetris.model.Tile;

public class PieceShape {

    /**
     * Default Tetris pieces 
     */
    public static final PieceShape T = new PieceShape(new Tile(Color.BLUE, 'T'), new boolean[][] {
        {  true,  true,  true },
        { false,  true, false }
    });
    public static final PieceShape S = new PieceShape(new Tile(Color.RED, 'S'), new boolean[][] {
        { false,  true,  true },
        {  true,  true, false }
    });
    public static final PieceShape Z = new PieceShape(new Tile(Color.GREEN, 'Z'), new boolean[][] {
        {  true,  true, false },
        { false,  true,  true }    
    });
    public static final PieceShape I = new PieceShape(new Tile(Color.PINK, 'I'), new boolean[][] {
        { true,  true,  true, true }
    });
    public static final PieceShape J = new PieceShape(new Tile(Color.YELLOW, 'J'), new boolean[][] {
        { true, false, false },
        { true, true,  true }
    });
    public static final PieceShape L = new PieceShape(new Tile(Color.CYAN, 'L'), new boolean[][] {
        { false, false,  true },
        {  true,  true,  true }
    });
    public static final PieceShape O = new PieceShape(new Tile(Color.MAGENTA, 'O'), new boolean[][] {
        {  true,  true },
        {  true,  true }    
    });

    //Default Tetris pieces list.
    public static final PieceShape[] STANDARD_TETRIS_PIECES = { T, S, Z, I, J, L, O };

    //Class variables.
    public Tile tile;
    boolean[][] shape;

    //Constructor
    public PieceShape(Tile tile, boolean[][] shape){ 
        this.tile = tile;
        this.shape = shape;
    }


    /**
     * Gets the shape of the piece.
     * @return the shape of the piece as a boolean[][]
     */
    public boolean[][] getShape(){
        return this.shape;
    }


    /** COLS
     * gets the width of the piece.
     * @return the width of the piece. 
     */
    public int getWidth(){
        return shape[0].length;
    }


    /** ROWS
     * gets the height of the piece.
     * @return the height of the piece.
     */
    public int getHeight(){
        return shape.length;
    }


    /**
     * gets the tile type of the piece. 
     * @return the tile of the piece.
     */
    public Tile getTile(){
        return this.tile;
    }
    

    /**
     * Method that makes a rotated copy of the piece.
     * @return rotated pieceshape
     */
    public PieceShape rotatedCopyOfPiece(int direction){
        boolean[][] shapeInverse = new boolean[this.getWidth()][this.getHeight()];
        for(int x = 0; x<this.getHeight(); x++){
            for(int y = 0; y <this.getWidth(); y++){
                if(direction == 0){
                    shapeInverse[y][x] = this.shape[x][this.getWidth()-y-1];
                }
                else if(direction == 1){
                    shapeInverse[y][x] = this.shape[this.getHeight()-x-1][y];
                }
            }

        }        
        PieceShape shapeRotated = new PieceShape(this.tile, shapeInverse);
        return shapeRotated;
    }   
    
}
