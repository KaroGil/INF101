package inf101v22.tetris.model;


import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.controller.TetrisControllable;
import inf101v22.tetris.model.piece.PositionedPiece;
import inf101v22.tetris.model.piece.PositionedPieceFactory;
import inf101v22.tetris.view.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;




public class TetrisModel implements TetrisViewable, TetrisControllable{

    TetrisBoard tetrisBoard; //(a standard Tetris-board has 15 rows and 10 cols).
    
    //pieces
    PositionedPiece positionedPiece;
    PositionedPiece nextpositionedPiece;

    //factory
    PositionedPieceFactory positionedPieceFactory;

    //millisedonds between each clocktick 
    static final int MILLISEDONDSBETWEEN = 1000;
    int amountOfPiecesMadeInGame;

    //GameState
    GameScreen gameScreen =  GameScreen.HOMESCREEN;

    //score
    private int score = 0;
    List<Integer> highscore = new ArrayList<>(Arrays.asList(0,0,0,0,0));


    public TetrisModel(){
        tetrisBoard = new TetrisBoard(15, 10,new Tile(Color.black, '-'));
        positionedPieceFactory = new PositionedPieceFactory();

        //make beginner pieces
        positionedPiece = getFallingPiece();
        getFallingPiece();
    }

    /**
     * For testing.
     * @param positionedPieceFactory
     */
    public TetrisModel(PositionedPieceFactory positionedPieceFactory){
        tetrisBoard = new TetrisBoard(15, 10,new Tile(Color.black, '-'));

        positionedPiece = getFallingPiece();
        getFallingPiece();        
        
    }

    /**
     * Make a char2dArray that contains the falling piece with the rest of the  board.
     * For testing.
     * @return char[][] array with characters of the piece. 
     */
    public char[][] toCharArray2d(){
  
        char[][] charArray = tetrisBoard.toCharArray2d();

        for (CoordinateItem<Tile> coordinateItem : positionedPiece) {
            int row = coordinateItem.coordinate.row;
            int col = coordinateItem.coordinate.col;
            charArray[row][col] = coordinateItem.item.character;

        }        
        return charArray;
    }


    /**gets the amounf of the rows.
     * @return amount of rows
     */
    @Override
    public int getRows() {
        return tetrisBoard.getRows();
    }

    /**gets the amount of the columns.
     * @return amount of columns
     */
    @Override
    public int getCols() {
        return tetrisBoard.getCols();
    }
    
    /**
     * Gets the tetrisboard.
     * @return tetrisboard
     */
    @Override
    public Iterable<CoordinateItem<Tile>> iterable() {  
        return this.tetrisBoard;
    }

    /**
     * Gets the positioned Piece.
     * @return positionedpiece
     */
    @Override
    public Iterable<CoordinateItem<Tile>> fallingPieces() { 
        return this.positionedPiece;
    }
       
    /**
     * gets the piece thats shown in the next piece window. (gets the next piece.)
     * @return nextpositionedpiece
     */
    @Override
    public Iterable<CoordinateItem<Tile>> NextPieces() {
        return this.nextpositionedPiece;
    }


    /**
     * other method for ghost piece
     * @return Iterable<CoordinateItem<Tile>> ghost piece
     */
     public Iterable<CoordinateItem<Tile>> GhostPiece(){
        for(int row = 0; row < tetrisBoard.getRows(); row++){
            PositionedPiece shadow = positionedPiece.copy(row, 0);
            if(!(pieceOnBoard(shadow))){
                if(row>0){
                    return this.positionedPiece.copy(row-1,0);  
                } 
            }
        }
        return this.positionedPiece;    

     }


    /**Move a falling piece. 
     * @param deltaRow
     * @param deltaCol
     * @return boolean with truth values of if the movement was completed.
     */
	@Override
	public boolean moveFallingPiece(int deltaRow, int deltaCol) {
		PositionedPiece newPosition = positionedPiece.copy(deltaRow, deltaCol);

        if(pieceOnBoard(newPosition)){
            this.positionedPiece = newPosition;
            return true;
        }
		return false;
	}
    

    /**
     * Checks if the placment of the piece is permitted. 
     * @param piece element to check if is on grid.
     * @return boolean with truth value about if the placment is permitted. 
     */
    private boolean pieceOnBoard(PositionedPiece piece){
        if(piece.coordinate.col + piece.getWidth() > this.getCols() || piece.coordinate.row + piece.getHeight() > this.getRows() || piece.coordinate.col < 0 || piece.coordinate.row < 0){
            return false;
        }

        for (CoordinateItem<Tile> coordinateItem : piece) {
            if((tetrisBoard.get(coordinateItem.coordinate).character != '-')){
                return false;
            }
        }
        return true;
    }


    /**
     * Mehtod for rotating fallen piece.
     * @param direction the direction (0 or 1) in which the piece will rotate
     */
	@Override
	public void rotateFallingPiece(int direction) {
		PositionedPiece newPosition = positionedPiece.rotatedCopyOfPositionedPiece(direction);

        if(pieceOnBoard(newPosition)){
            this.positionedPiece = newPosition;
        }
	}


    /**
     * Drops the piece down until it no longer can be dropped, because the piece gets stopped by another piece or the end of the board, 
     * and then glues the piece to the board. 
     */
	@Override
	public void dropPiece() {
        for(int row = 0; row < tetrisBoard.getRows(); row++){
            if(!(this.moveFallingPiece(1,0))){
                break;
            }
            else{this.moveFallingPiece(1,0);}
        }        
		glueTileToBoard();
	}


    /**
     * Makes a falling piece.
     * Adds to amountOfPiecesMadeInGame counter.
     * @return PositionedPiece of a falling piece
     */
    private PositionedPiece getFallingPiece(){
        positionedPieceFactory.setCenterColumn(Math.round(tetrisBoard.getCols()/2));
        nextpositionedPiece = positionedPieceFactory.getNextPositionedPiece();

        //GAME OVER!
        if(!pieceOnBoard(nextpositionedPiece)){
            highscore.add(score);
            Collections.sort(highscore);
            Collections.reverse(highscore);
            gameScreen = GameScreen.GAME_OVER; 
        }
        amountOfPiecesMadeInGame++;

        return nextpositionedPiece;
    }


    /**
     * Adds piece to the board, so its no longer a floating piece on top of the board but a part of the board. 
     */
    private void glueTileToBoard(){
        for (CoordinateItem<Tile> coordinateItem : positionedPiece) {
            tetrisBoard.set(coordinateItem.coordinate, coordinateItem.item);
        }
        score = score + (int) Math.pow((tetrisBoard.removeRows()), 2); 

        positionedPiece = nextpositionedPiece;
        getFallingPiece();
    }


    /**
     * Gets the value of the varieble that tells us which gamestate the game is in. 
     * From this we can know what to draw and calculate.
     * @return gamescreen
     */
    @Override
    public GameScreen getGameScreen() {
        return this.gameScreen;
    }

    
    /**
     * set the GameScreen value.
     * @param gameScreen Gamescreen value
     */
    @Override
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }


    /**
     * Calculates a time-delay looking at how many pieces have been created thus far.
     * [for example X*0.98^Y, where X is a constant (MILLISEDONDSBETWEEN) and Y (amountOfPiecesMadeInGame)] 
     * @return timedelay
     */
    @Override
    public int getMilliseconds() {
        int timedelay = (int) (MILLISEDONDSBETWEEN *  Math.pow(0.98,amountOfPiecesMadeInGame));
        return timedelay;
    }


    /**
     * move the piece down with each clocktick until it cannot move further and then glue it to the board.
     */
    @Override
    public void clockTick() {
        if(!this.moveFallingPiece(1,0)){
            glueTileToBoard();
        } 
    }


    /**
     * gets the game score
     * @return the score
     */
    @Override
    public int getScore() {
        return this.score;
    }


    /**method that gets thte highscore-list
     * @return list with scores
     */
    @Override
    public List<Integer> getHighscore() {
        return this.highscore;
    }


    /**
     * restarts the stats so a new game can start.
     */
    public void newGame(){
        if(this.getGameScreen() == GameScreen.HOMESCREEN){
            score = 0;
            amountOfPiecesMadeInGame = 0;
            tetrisBoard = new TetrisBoard(15, 10,new Tile(Color.black, '-'));
        }
    }

}
