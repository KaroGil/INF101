package inf101v22.tetris.model.piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import inf101v22.grid.Coordinate;
import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.Tile;

public class PositionedPiece implements Iterable<CoordinateItem<Tile>>{
    
    //class variables.
    public PieceShape pieceShape;
    public Coordinate coordinate;



    //constructor.
    PositionedPiece(PieceShape pieceShape, Coordinate coordinate){
        this.pieceShape = pieceShape;
        this.coordinate = coordinate;
    }


    /**
     * iterator to go through the tiles of the piece and get the coordinates of these tiles.
     * @return  Iterator<CoordinateItem<Tile>>
     */
    @Override
    public Iterator<CoordinateItem<Tile>> iterator() {
        
        List<CoordinateItem<Tile>> tileList;
        tileList = new ArrayList<CoordinateItem<Tile>>();

        boolean[][] shape = pieceShape.shape;
        Tile shapeTile = pieceShape.getTile();
        
        for (int row = 0; row < pieceShape.getHeight(); row++) {
            for (int col = 0; col < pieceShape.getWidth(); col++) {
                if(shape[row][col]){
                    Coordinate newCoordinate = new Coordinate(coordinate.row + row, coordinate.col + col);
                    tileList.add(new CoordinateItem<Tile>(newCoordinate, shapeTile));
                }  
            } 
        }
        return tileList.iterator();
    }


    /**
     * method to get the width of the block.
     * @return width of the piece
     */
    public int getWidth(){  
        return pieceShape.getWidth();
    }


    /**
     * method to get the height of the piece
     * @return height
     */
    public int getHeight(){
        return pieceShape.getHeight();
    }


    /**
     * method to get the tile element of the current piece.
     * @return tile element
     */
    public Tile getTile(){ 
        return pieceShape.tile;
    }


    /**
     * method that copies the piece with different x and y values. 
     */
    public PositionedPiece copy(int deltaRow, int deltaCol){
        Coordinate newCoordinate = new Coordinate(this.coordinate.row + deltaRow, this.coordinate.col + deltaCol);
        PositionedPiece positionedPieceCopy = new PositionedPiece(this.pieceShape, newCoordinate);
        return positionedPieceCopy;
    }


    /**
     * Method to make a copy and rotate itself.
     * @return copy of rotated piece
     */
    public PositionedPiece rotatedCopyOfPositionedPiece(int direction){
        Coordinate middlCoordinate =  findMiddlePiece(direction);        
        PieceShape rotatedCopyOfPiece = pieceShape.rotatedCopyOfPiece(direction);
        PositionedPiece rotatedDiffCoordinates = new PositionedPiece(rotatedCopyOfPiece, middlCoordinate);

        return rotatedDiffCoordinates;
    }
    

    /**
     * Method to find middle coordinate of the piece
     * @param piece
     * @return middle coordinate of the piece
     */
    private Coordinate findMiddlePiece(int direction){
        int middleColOld = (int) pieceShape.getWidth()/2;
        int middleRowOld = (int) pieceShape.getHeight()/2;
        int middleColNew = (int) pieceShape.rotatedCopyOfPiece(direction).getWidth()/2;
        int middleRowNew = (int) pieceShape.rotatedCopyOfPiece(direction).getHeight()/2;

        Coordinate middleCoordinate = new Coordinate(this.coordinate.row + middleRowOld - middleRowNew, this.coordinate.col + middleColOld - middleColNew);

        return middleCoordinate;
    }
}
