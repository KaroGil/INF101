package inf101v22.tetris.model.piece;


import inf101v22.grid.Coordinate;

public class PositionedPieceFactory {

    //class variables.
    int col;
    
    /**
     * sets the center column of the new piece. 
     * @param col
     * @return center column coordinate.
     */
    public void setCenterColumn(int col){
        this.col = col;
    }


    /**
     * Makes a new positioned piece with a random shape and a centered column position. 
     * @return PositionedPiece
     */
    public PositionedPiece getNextPositionedPiece(){
        int randomInt = (int) Math.floor(Math.random() * 7);
        PieceShape randomPiece = PieceShape.STANDARD_TETRIS_PIECES[randomInt];
            
        Coordinate finalCoordinate = new Coordinate(0, col- randomPiece.getWidth()/2);

        PositionedPiece randomPositionedPiece = new PositionedPiece(randomPiece, finalCoordinate);
        return randomPositionedPiece;
    }
}
