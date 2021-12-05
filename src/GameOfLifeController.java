/*
Game of life
Alexey Varatnov 321641086
Controller Class
The Game of life created for infinity board,
to allow work with the cells in the borders, I created board 12x12,
but hide the borders, that always has a dead cells,
user will see and play with 10x10 board.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GameOfLifeController {

    @FXML
    private Canvas canvas;
    @FXML
    private Button startBtn;
    private boolean first = true;
    private final Game game = new Game();

    private static final int START_POSITION = 20;
    private static final int CELL_SIZE = 40;

    /**
     * Create new visual board with random live cells
     */
    private void BuildNewBoard(){
        game.BuildRandomBord();
        DrawBoard(game.getGameBoard());
        startBtn.setText("Next Generation");
        first = false;
    }

    /**
     * Draw visual board on the canvas
     * @param board: game board
     */
    private void DrawBoard(boolean[][]board) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int startX = 20;
        int x = START_POSITION, y = START_POSITION;
        for (int column = 1; column < game.getNumberOfColumns()-1; column++) {
            for (int row = 1; row < game.getNumberOfRows()-1; row++) {
                if (board[column][row]) {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    gc.strokeRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    gc.setFill(Color.WHITE);
                    gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    gc.strokeRect(x,y, CELL_SIZE, CELL_SIZE);
                }
                x = x + CELL_SIZE;
            }
            x = START_POSITION;
            y = y + CELL_SIZE;
        }
    }

    @FXML
    void NextGenPress(ActionEvent event) {
       if (first){
           BuildNewBoard();
       }
       else{
           game.CalculateNextGeneration();
           DrawBoard(game.getGameBoard());
       }
    }

    @FXML
    void resetPress(ActionEvent event) {
        ClearBoard();
        first = true;
        startBtn.setText("Start");
        canvas.getGraphicsContext2D().strokeText("Press Start to play again", START_POSITION,START_POSITION);
    }

    /**
     * Clear board before start new game
     */
    private void ClearBoard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
}