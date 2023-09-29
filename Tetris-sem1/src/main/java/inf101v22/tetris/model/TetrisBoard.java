package inf101v22.tetris.model;

import java.awt.Color;

import inf101v22.grid.Coordinate;
import inf101v22.grid.Grid;

public class TetrisBoard extends Grid<Tile> {

    int rows = 15, cols = 10;
    Tile tile = new Tile(Color.black, '-');

    /**
     * Constructor for a tetrisboard element. Takes in rows and cols, the dimentions of the board, and tile type element to set the right type. 
     * @param rows
     * @param cols
     * @param tile
     */
    public TetrisBoard(int rows, int cols, Tile tile) {
        super(rows, cols, tile);
    }


    /**
     * Makes a char - 2d-array out of the characters the tiles get assigned.
     * @return char[][]
     */
    public char[][] toCharArray2d(){
        char[][] charArray;
        charArray = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(this.tile == null){
                    charArray[row][col] = '-';
                }else{
                    charArray[row][col] = this.get(new Coordinate(row, col)).character;                
                }
            }
        }
        return charArray;    
    }


    /**
     * Takes in a char[][] and prints out its content as a string. 
     * @param char2dArray
     * @return String form of the array
     */
    public String charArray2dToString(char[][] char2dArray){
        String output = "";
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                output += char2dArray[row][col];
            }
            output = "\n";
        }
        System.out.println(output);
        return output; 
    }


    /**
     * mehtod that checks if an element/tile is present in a row.
     * @param row
     * @param tile
     * @return
     */
    private boolean elementCheck(int row, Tile tile){ 
            for(int col = 0; col < this.cols; col++){
                Coordinate coordinate = new Coordinate(row, col);
                Tile boardTile = this.get(coordinate);
                if(boardTile.character == tile.character){
                    return true;
                }
            }
        return false;
    }
    

    /**
     * method that fills a row with a given tile
     * @param row
     * @param tile
     */
    private void fillRow(int row, Tile tile){
        for(int col = 0; col < this.cols; col++){
            this.set(new Coordinate(row, col), tile);
        }
    }
    

    /**
     * method that copies one row onto another row.
     * @param rowFrom
     * @param rowTo
     */
    private void copyRow(int rowFrom, int rowTo){
        for(int col = 0; col < this.cols; col++){
            this.set(new Coordinate(rowTo, col), this.get(new Coordinate(rowFrom, col)));
        }
    }

    
    /**
     * method that removes a full row
     * @return
     */
    public int removeRows(){

        int count = 0;
        
        int a = rows-1;
        int b = rows-1;
        while(a >=0){
            while(b>=0){
                if(b<0){b=0;}
                if(!elementCheck(b, this.tile)){
                    count++;
                    b--;
                }
                else{
                    if(b>=0){
                        copyRow(b,a);
                    }
                    else{
                        fillRow(a, this.tile);
                    }
                    b--;
                    a--;
                }
            }
            a--;
            b--;
        }
        return count;
    } 

}
