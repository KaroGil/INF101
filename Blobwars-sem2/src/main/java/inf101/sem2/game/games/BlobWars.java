package inf101.sem2.game.games;

import java.util.ArrayList;
import java.util.List;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.grid.Move;

import inf101.sem2.GUI.Graphics;

import inf101.sem2.game.Game;
import inf101.sem2.game.GameBoard;
import inf101.sem2.player.Player;

public class BlobWars extends Game<Move>{

    /**
     * constuctor for a new Blob wars game
     * @param graphics takes in graphics for the game
     * @param p1    one player practicipating in the game   
     * @param p2    second player practicipating in the game
     */
    public BlobWars(Graphics graphics, Player p1, Player p2) {
		super(new GameBoard(8, 8), graphics);
		addPlayer(p1);
		addPlayer(p2);
        initializeBoard();
	}

    /**
     * another constructor for a new Blob wars game
     * @param graphics takes in graphics 
     * @param players practicipating in the game
     */
    public BlobWars(Graphics graphics, Iterable<Player> players) {
        super(new GameBoard(8, 8), graphics, players);
        initializeBoard();
    }
    
    /**
     * copies the game
     * @return new game with a list of locations
     */
    @Override
    public Game<Move> copy() {
        Game<Move> gameCopy = new BlobWars(this.graphics, players);
		copyTo(gameCopy);
		return gameCopy;
    }

    /** makes a move
     * @param move to make
     */
    @Override
    public void makeMove(Move move) {
        if (!validMove(move))
			throw new IllegalArgumentException("Cannot make move:\n" + move);
        
        Player current = getCurrentPlayer();
        List<Location> toFlip = getFlipped(move);

        /*if player tries to move something different than his piece it skips to next player so that 
        its the same players turn again when the game runs */
        if((board.get(move.getFrom()) != getCurrentPlayer()) ){
            nextPlayer();
        } 
    
        //if piece is current players piece it gets moved if not it doesnt.
        if(board.get(move.getFrom()) != null && board.get(move.getFrom()).equals(current)){
            move(move, current,2);          
            move(move, current,1);

            /*if the moveTo location is not the current players piece, after the move, it skips to 
            the next player so that its the same players the same players turn because the move didnt happen */
            if(board.get(move.getTo())!= current){
                nextPlayer();
            }
            
            //flips the surrounding pieces of the places piece
            for (Location l : toFlip) {
                if(board.get(move.getTo()) == current){
                    board.swap(l, current);
                }
            }
        }
        displayBoard(); 
    }


    /**
     * method to make a move, and to choose the distance of the move.
     * @param move location from where to move and location to move to.
     * @param current the player thats making the move
     * @param distance the distance of the move.  (either 1 or 2 for the amount of tiles to move.)
     */
    private void move(Move move, Player current,int distance){
        if(distance == 1){
            List<Location> onePlaceMovement = board.getNeighbourhood(move.getFrom(),1);
            for (Location loc : onePlaceMovement) {
                if(move.getFrom()!=null && move.getTo().equals(loc)){
                    //if closest neighbour
                    board.set(move.getFrom(), current); 
                    break;
                }
            }
        }
        else{
            List<Location> twoPlaceMovement = board.getNeighbourhood(move.getFrom(),2); 
            for (Location location : twoPlaceMovement) {
                if(move.getFrom() != null && move.getTo().equals(location)){
                    board.movePiece(move.getFrom(), move.getTo());  
                    break; 
                }
                
            }
        }
    }


    /**
     * checks if a move is valid
     * @param move location to check if is a valid move
     * @return boolean value of if a move is a valid move
     */
    @Override
    public boolean validMove(Move move) {   
        if (!board.canPlace(move.getTo()))
			return false;
        if(!board.getNeighbourhood(move.getFrom(), 2).contains(move.getTo())){
            return false;
        }
        else{
            return true;
        }      
    }

    /**
     * checks if a player is a winner or not.
     * @param player who is being checked for winner possibilities
     * @return boolean value of if a player is a winner
     */
    @Override
    public boolean isWinner(Player player) {
        if (!gameOver())
			return false;
		int count = getGameBoard().countPieces(player);
		for (Player p : players) {
			if (p.equals(player))
				continue;
			if (board.countPieces(p) >= count)
				return false;
		}
		return true;
    }

    /**
     * gets all the possible moves 
     * @return list with locations of possible moves
     */
    @Override
    public List<Move> getPossibleMoves() {
        ArrayList<Move> moves = new ArrayList<>();
		for (Location locFrom : board.locations()) {
            if(board.get(locFrom) != null && board.get(locFrom).equals(getCurrentPlayer())){
                for(Location locTo : board.getNeighbourhood(locFrom, 2)){
                    if(board.get(locTo)==null){
                        Move move = new Move(locFrom,locTo);
                        moves.add(move);
                    }
                }
            }
		}
		return moves;
    }

    /**
     * gets the game name
     * @return String with game name
     */
    @Override
    public String getName() {
        return "Blob Wars";
    }

    /**
     * Puts the players startet pieces on the board.
     */
    private void initializeBoard() {
		board.set(new Location(0, 0), getCurrentPlayer());
		board.set(new Location(0, 7), getCurrentPlayer());
		players.nextPlayer();   
		board.set(new Location(7, 7), getCurrentPlayer());
		board.set(new Location(7, 0), getCurrentPlayer());
		players.nextPlayer();
	}
    
    /**
     * ends the game.
     * @return boolean value of if the game is over
     */
    @Override
	public boolean gameOver() {
        //if there is no possible moves
        if(getPossibleMoves().isEmpty()){
            return true;
        }

        //if there is no pieces left for one player
        Player current = getCurrentPlayer();
        int count = getGameBoard().countPieces(current);
        if(count <= 0){
            return true;
        }

        //if board is full == game over
		return board.isFull();
	}

    /**
     * restarts the game
     */
    @Override
	public void restart() {
		super.restart();
		initializeBoard();
	}

     
     /** checks if the value of the location is your opponents.
     * @param loc location to check
     * @return truth value of if the opponent is at the checked location.
     */
    public boolean isOpponent(Location loc) {
		if (!board.isOnBoard(loc))
			return false;
		if (board.get(loc) == getCurrentPlayer())
			return false;
		if (board.get(loc) == null)
			return false;
		return true;
	}


    /**
     * checks the getFlipped for all 8 diretions from the selected location.
     * @param move selected location
     * @return list of locations to flipped/changed to other players
     */
    private List<Location> getFlipped(Move move) {
		List<Location> flipped = new ArrayList<Location>();
		for (GridDirection dir : GridDirection.EIGHT_DIRECTIONS) {
			flipped.addAll(getFlipped(move.getTo(), dir));
		}
		return flipped;
	}

    /**
     * @param loc to check around
     * @param dir direction to check in
     * @return list of neighbours of selected location in a specific location
     */
	private List<Location> getFlipped(Location loc, GridDirection dir) {
		List<Location> flipped = new ArrayList<Location>();
		Location target = loc.getNeighbor(dir);
        if(isOpponent(target)){
            flipped.add(target);
        }
        return flipped;
	}

}
