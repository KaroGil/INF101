package inf101.grid;


/**
 * Class to register the move of a piece.
 * Uses one location to save where the piece is moving from and another to register where the piece is moving to.
 * 
 * Useful for games like Blob wars because there you need to select a piece to see where you can move it and not just place a new piece on the board. 
 */
public class Move {

    private Location moveFrom;
    private Location moveTo;
    
    /**
     * constructor to make a move.
     * @param moveFrom location from where the piece is moved from
     * @param moveTo   location to where the piece is moved to
     */
    public Move(Location moveFrom, Location moveTo){
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
    }

    /**
     * gets the moveFrom variable
     * @return location of where the piece is moved from.
     */
    public Location getFrom(){
        return moveFrom;
    }

    /**
     * gets the moveTo variable
     * @return location of where the piece is moved to.
     */
    public Location getTo(){
        return moveTo;
    }
}
