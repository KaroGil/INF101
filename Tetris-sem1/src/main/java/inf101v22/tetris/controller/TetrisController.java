package inf101v22.tetris.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import inf101v22.tetris.midi.TetrisSong;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.view.TetrisView;

public class TetrisController implements java.awt.event.KeyListener, java.awt.event.ActionListener {

    TetrisControllable model;
    TetrisView tetrisView;
    Timer timer;
    TetrisSong tetrisSong = new TetrisSong();
    
    public TetrisController(TetrisControllable model, TetrisView tetrisView){
        this.tetrisView = tetrisView;
        this.model = model; 

        this.timer = new Timer(model.getMilliseconds(), this);
        
        tetrisView.addKeyListener(this);
        timer.start();
        tetrisSong.run();
    }

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
        boolean activeGame = model.getGameScreen() == GameScreen.ACTIVE_GAME;

        //ACTIVE GAME
        if (keyCode == KeyEvent.VK_LEFT && activeGame) {
            model.moveFallingPiece(0, -1);
            tetrisView.repaint();
        }
        else if (keyCode == KeyEvent.VK_RIGHT && activeGame) {
            // Right arrow was pressed
            model.moveFallingPiece(0, 1);
            tetrisView.repaint();
        }
        else if (keyCode == KeyEvent.VK_DOWN && activeGame) {
            // Down arrow was pressed
            if(model.moveFallingPiece(1, 0)){
                timer.restart();
            } 
            tetrisView.repaint();
        }
        else if ((keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_A) && activeGame) {
            // Up arrow was pressed
            model.rotateFallingPiece(0);
            tetrisView.repaint();
        }
        else if(keyCode == KeyEvent.VK_D && activeGame){
            model.rotateFallingPiece(1);
            tetrisView.repaint();
        }
        else if(keyCode == KeyEvent.VK_SPACE && activeGame){
            //spacebar was pressed
            model.dropPiece();
            timer.restart();
            tetrisView.repaint();
        }
        else if(keyCode == KeyEvent.VK_ESCAPE && activeGame){
            //ESC pressed, pause on
            model.setGameScreen(GameScreen.PAUSED);
            tetrisView.repaint();
        }

        //GAME OVER
        else if(model.getGameScreen() == GameScreen.GAME_OVER){
            model.setGameScreen(GameScreen.HOMESCREEN);
            model.newGame();
            tetrisView.repaint();
        }

        //HOMESCREEN
        else if(model.getGameScreen() == GameScreen.HOMESCREEN && keyCode == KeyEvent.VK_ENTER){
            model.setGameScreen(GameScreen.ACTIVE_GAME);
            tetrisView.repaint();
        }

        //PAUSED
        else if(model.getGameScreen()==GameScreen.PAUSED){
            model.setGameScreen(GameScreen.ACTIVE_GAME);
            tetrisView.repaint();
        }
	}

    @Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		
	}


    /**
     * ActionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(model.getGameScreen() == GameScreen.ACTIVE_GAME){
            model.clockTick();
            tetrisView.repaint();
            this.getDelay();
        }
        
    }


    /**
     * Help-method for delay.
     */
    private void getDelay(){
        int delay = model.getMilliseconds();
        timer.setDelay(delay);
        timer.setInitialDelay(delay);
    }

}
