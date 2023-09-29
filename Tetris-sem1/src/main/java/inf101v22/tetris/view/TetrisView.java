package inf101v22.tetris.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import inf101v22.grid.CoordinateItem;
import inf101v22.tetris.model.GameScreen;
import inf101v22.tetris.model.Tile;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TetrisView extends JComponent {


    TetrisViewable tetrisViewable;
    BufferedImage backgroundImage;

    int rows, cols;
    Color color;


    public TetrisView(TetrisViewable tetrisViewable) {
        this.tetrisViewable = tetrisViewable;

        this.setFocusable(true);
        getPreferredSize();
        getImage();

    }


    /**
     * Paint method to use to paint the tiles and the board on screen,
     * by calling on the other methods.
     * @param Graphics
     */

    public void paint(Graphics canvas) {

        super.paint(canvas);
        int padding = 10;
        int sidePadding = 210;
        int y = 100;
        
        drawTetrisBoard(canvas, sidePadding, padding, this.getWidth() - 2 * padding-400, this.getHeight() - 2 * padding,padding/4);
        drawScore(canvas, 10, getHeight()/2);
  
        //info
        String info = "Controls: \n esc - pause \n space - drop \n up/D/A - rotate \n left - move left \n right - move right \n down - move down";
        for (String line : info.split("\n")){
            canvas.drawString(line, getWidth()-200, y += canvas.getFontMetrics().getHeight());
        }

        if(tetrisViewable.getGameScreen() == GameScreen.GAME_OVER){
            drawGameOver(canvas, 0, 0, this.getWidth(), this.getHeight());
        }
        else if(tetrisViewable.getGameScreen() == GameScreen.PAUSED){
            drawPausedScreen(canvas, 0, 0, this.getWidth(), this.getHeight());
        }
        else if(tetrisViewable.getGameScreen() == GameScreen.HOMESCREEN){
            drawHomeScreen(canvas, 0, 0, this.getWidth(), this.getHeight());
        }

    }


    /**
     * Sets the preffered size of the component,
     * this values can be changed by the user.
     * @return dimension of the component.
     */

    public Dimension getPreferredSize() {
        // This method returns the "preferred" size of the component. However, if
        // the user resize the window, the values returned here will not matter.
        // Hence, do not use the return value from here to calculate the size of
        // your components; use this.getWidht() and this.getHeight() instead.

        int padding = 10;

        // default size of tetris board
        int numberOfColumns = 15;
        int numberOfRows = 10;

        int width = 45;
        int height = 50;

        int preferredWidth = (width + padding) * numberOfColumns + padding;
        int preferredHeight = (height + padding) * numberOfRows + padding;

        return new Dimension(preferredWidth, preferredHeight);
    }


    /**
     * Sets the correct color and then draws the tile with the padding.
     * The padding being the "grid-lines" shown in white between the tiles.
     * @param canvas
     * @param x
     * @param y
     * @param width
     * @param height
     * @param padding
     * @param color
     */

    private void drawTileWithRightBottomPadding(Graphics canvas, int x, int y, int width, int height, int padding,
            Color color) {

        if(color!= Color.BLACK){
            Graphics2D g2 = (Graphics2D) canvas;
            g2.setColor(color);
            g2.fill3DRect(x, y, width-padding, height-padding, true);
        }else{
            canvas.setColor(color);
            canvas.fillRect(x, y, width - padding, height - padding);
        }
    }


    /**
     * Draws the board of tiles with padding.
     * The padding being the white on the sides of the whole board of tiles.
     * Calculates the values to draw the image on the canvas.
     * 
     * @param canvas
     * @param xBoard
     * @param yBoard
     * @param width
     * @param height
     * @param padding
     */


    private void drawBoardWithRightBottomPadding(Graphics canvas, int xBoard, int yBoard, int width, int height,
            int padding, Iterable<CoordinateItem<Tile>> iterable, boolean shadow) {
        int cols = tetrisViewable.getCols();
        int rows = tetrisViewable.getRows();
        for (CoordinateItem<Tile> coordinateItem : iterable) {
            Color color = Color.BLACK;
            if(coordinateItem.item != null){
                color = coordinateItem.item.color;
            }

            if(shadow){
                color = new Color(color.getRed(),color.getGreen(),color.getBlue(), 90);
            }

            int xPaint = xBoard + coordinateItem.coordinate.col * width / cols;
            int yPaint = yBoard + coordinateItem.coordinate.row * height / rows;
            int nextX = xBoard + (coordinateItem.coordinate.col + 1) * width / cols;
            int nextY = yBoard + (coordinateItem.coordinate.row + 1) * height / rows;
            int cellWidth = nextX - xPaint;
            int cellHeight = nextY - yPaint;
            
            drawTileWithRightBottomPadding(canvas, xPaint, yPaint, cellWidth, cellHeight, padding, color);
            
        }
    }


    /**
     * Passes the values to draw the tetris board,
     * the calculated values from the drawBoardWithRightBottomPadding method.
     */
    private void drawTetrisBoard(Graphics canvas, int x, int y, int width, int height, int padding) {
        drawBoardWithRightBottomPadding(canvas, -100, 55, width, height, padding, tetrisViewable.NextPieces(), false);

        drawBoardWithRightBottomPadding(canvas, x + padding, y + padding, width, height, padding, tetrisViewable.iterable(), false);
        
        drawBoardWithRightBottomPadding(canvas, x + padding, y + padding, width, height, padding, tetrisViewable.GhostPiece(), true);
        
        drawBoardWithRightBottomPadding(canvas, x + padding, y + padding, width, height, padding, tetrisViewable.fallingPieces(), false);

    }


    /**
     * Draws the game over screen.
     * @param canvas
     * @param x
     * @param y
     * @param width
     * @param height
     * @param padding
     */
    private void drawGameOver(Graphics canvas, int x, int y, int width, int height){
            canvas.setColor(new Color(0, 0, 0, 128));
            canvas.fillRect(x, y, width, height);

            canvas.setColor(Color.PINK);
            canvas.setFont(new Font("SansSerif", Font.BOLD, 50));
            GraphicHelperMethods.drawCenteredString(canvas, "GAME OVER!", x, y, this.getWidth() - x, this.getHeight() - y);
            GraphicHelperMethods.drawCenteredString(canvas, "Score: " + tetrisViewable.getScore(), x , y+50, this.getWidth() - x, this.getHeight() - y);

            canvas.setColor(Color.white);
            canvas.setFont(new Font("SansSerif", Font.PLAIN, 40));
            GraphicHelperMethods.drawCenteredString(canvas, "Highscore:", x , y+100, this.getWidth() - x, this.getHeight() - y);
            canvas.setFont(new Font("SansSerif", Font.PLAIN, 20));
            int scoreY = y+150;
            List<Integer> highscore = tetrisViewable.getHighscore();
            int i = 0;
            while(i<5){
                 GraphicHelperMethods.drawCenteredString(canvas, String.valueOf(highscore.get(i)), x , scoreY, this.getWidth() - x, this.getHeight() - y);
                 i++;
                 scoreY += 30;
            }      
    }


    /**
     * draw the pause screen.
     * @param canvas
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void drawPausedScreen(Graphics canvas, int x, int y, int width, int height){
        canvas.setColor(new Color(0, 0, 0, 128));
        canvas.fillRect(x, y, width, height);

        canvas.setColor(Color.WHITE);
        canvas.setFont(new Font("SansSerif", Font.PLAIN, 50));
        GraphicHelperMethods.drawCenteredString(canvas, "PAUSED.", x, y, this.getWidth() - x, this.getHeight() - y);

    }


    /**
     * Getting image from res folder.
     */
    private void getImage(){
        try{
            backgroundImage  = ImageIO.read(getClass().getResourceAsStream("/tetris.jpg"));
        }catch(IOException e) {
    		e.printStackTrace();
    	}
    }


     /**
      * Draw the Home screen.
      * @param canvas
      * @param x
      * @param y
      * @param width
      * @param height
      */
    private void drawHomeScreen(Graphics canvas, int x, int y, int width, int height){
        canvas.drawImage(backgroundImage, x, y, width, height, this);

        canvas.setColor(Color.black);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 40));
        GraphicHelperMethods.drawCenteredString(canvas,  "Press enter down to begin.", x+20, y-220, this.getWidth() - x, this.getHeight() - y);

    }

    /**
     * Draws the score of the game
     * @param canvas
     * @param x
     * @param y
     */
    private void drawScore(Graphics canvas, int x, int y){
        //score
        canvas.setColor(Color.BLACK);
        canvas.setFont(new Font("SansSerif", Font.BOLD, 20));
        canvas.drawString("Score: " + tetrisViewable.getScore(), x, y);
    }

}
