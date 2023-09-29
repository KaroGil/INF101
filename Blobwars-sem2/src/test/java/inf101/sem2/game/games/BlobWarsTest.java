package inf101.sem2.game.games;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf101.grid.Location;
import inf101.grid.Move;
import inf101.sem2.GUI.DummyGraphics;
import inf101.sem2.game.Game;
import inf101.sem2.game.TestGame;
import inf101.sem2.player.Player;

public class BlobWarsTest extends TestGame {
    BlobWars game;

    @BeforeEach
	protected void init() {
		super.init();
		game = new BlobWars(new DummyGraphics(), player1, player2);
		assertEquals(player1,game.getCurrentPlayer());
	}

    @Override
    public Game<?> getGame() {
        return game;
    }

    @Test
	void canConstruct() {
		assertEquals("Blob Wars", game.getName());
		BlobWars game2 = new BlobWars(game.getGraphics(), game.players());
		assertEquals("Blob Wars", game2.getName());
		boolean lik = game.getGameBoard().equals(game2.getGameBoard());
		assertTrue(lik);
	}

    @Test
	void startsWith4pieces() {
		assertEquals(player1,game.getGameBoard().get(new Location(0, 0)));
		assertEquals(player1,game.getGameBoard().get(new Location(0, 7)));
		assertEquals(player2,game.getGameBoard().get(new Location(7, 0)));
		assertEquals(player2,game.getGameBoard().get(new Location(7, 7)));
	}

    @Test
	void validMoveTest() {
		Location from = new Location(1, 1);
		assertTrue(game.validMove(new Move(from,new Location(3, 2))));
		assertTrue(game.validMove(new Move(from,new Location(2, 2))));
		assertFalse(game.validMove(new Move(from,new Location(3, 5))));
		assertFalse(game.validMove(new Move (from, new Location(5, 3))));
		assertFalse(game.validMove(new Move (from,new Location(0, 0))));
		assertFalse(game.validMove(new Move(from,new Location(-1, -1))));
	}

    @Test
	void testMakeMove() {
		Move move = new Move(new Location(0,0), new Location(0,2));
		assertTrue(game.validMove(move));
		game.makeMove(move);
		game.nextPlayer();
		assertEquals(player1, game.getGameBoard().get(move.getTo()));
		assertEquals(player1, game.getGameBoard().get(new Location(0,2)));
		assertEquals(player2, game.getCurrentPlayer());
	}

	@Test
	void testMoveOne(){
		Move move = new Move(new Location(0,0), new Location(0,1));
		assertTrue(game.validMove(move));
		game.makeMove(move);
		game.nextPlayer();
		assertEquals(player1, game.getGameBoard().get(move.getTo()));
		assertEquals(player1, game.getGameBoard().get(new Location(0,1)));
		assertEquals(player1, game.getGameBoard().get(new Location(0,0)));
		assertEquals(player2, game.getCurrentPlayer());
	}

    @Test
	void testGetPossibleMoves() {
		List<Move> moves = game.getPossibleMoves();
		for (Move move : moves) {
			System.out.println(move.getFrom());
		}

		assertEquals(30, moves.size());

	}
	
	@Test
	void testCopy() {
		TestGame.testCopy(game);
		assertTrue(Arrays.equals("test".getBytes(),"test".getBytes()));
	}


    @Test
	void testRestart() {
		game.makeMove(new Move(new Location(1,1), new Location(2,2)));
        Player playerCheck = game.getGameBoard().get(new Location(0,0));
		assertEquals(player1,playerCheck);
	}

	
	@Test
	void testGameOver(){
		//checks gameOver if one player has no more pieces left
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(0, 0), new Location(2, 0)));
		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(7, 0), new Location(7, 1)));
		game.nextPlayer();
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(2, 0), new Location(4, 0)));
		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(7, 1), new Location(6, 1)));
		game.nextPlayer();
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(4, 0), new Location(6, 0)));

		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(7, 7), new Location(7, 6)));
		game.nextPlayer();
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(0, 7), new Location(2, 7)));
		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(7, 6), new Location(7, 5)));
		game.nextPlayer();
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(2, 7), new Location(4, 6)));
		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(7, 5), new Location(6, 5)));
		game.nextPlayer();
		assertEquals(player1,game.getCurrentPlayer());
		game.makeMove(new Move(new Location(4, 6), new Location(6, 6)));
		game.nextPlayer();
		assertEquals(player2,game.getCurrentPlayer());

		assertTrue(game.gameOver());
	}

	

}