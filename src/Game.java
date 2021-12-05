/*
Game of life
Alexey Varatnov 321641086
Game logic class
The Game of life created for infinity board,
to allow work with the cells in the borders, I created board 12x12,
but hide the borders, that always has a dead cells,
user will see and play with 10x10 board.
 */
import java.util.Random;

public class Game {

    private static final int COLUMNS = 12;
    private static final int ROWS = 12;
    private boolean[][] Bord = new boolean[COLUMNS][ROWS];

    //getters
    public boolean[][] getGameBoard() {return Bord;}
    public int getNumberOfColumns() {return COLUMNS;}
    public int getNumberOfRows() {return ROWS;}

    //constructor
    public Game(){
        BuildRandomBord();
    }

    /**
     * Create random bord for first use
     */
    public void BuildRandomBord(){
        Random rand = new Random();
        for (int i=1; i<COLUMNS-1; i++)
            for (int j=1; j<ROWS-1; j++)
                Bord[i][j] = rand.nextBoolean();
    }

    /**
     * Calculate next generation and apply on the game bord
     */
    public void CalculateNextGeneration()
    {
        boolean[][] nextGen = new boolean[COLUMNS][ROWS];

        for (int column = 1; column < COLUMNS-1; column++){
            for(int row = 1; row < ROWS-1; row++)
            {
                int neighbours = 0;
                //find neighbours
                for (int i=-1; i<=1; i++){
                    for(int j=-1; j<=1; j++){
                        if(Bord[column+i][row+j])
                            neighbours++;
                    }
                }
                //remove self from the neighbours number
                if(Bord[column][row])
                    neighbours -= 1;

                //fill next generation board
                //if less than 2 neighbours or more than 3 - die
                if(Bord[column][row] && (neighbours < 2 || neighbours > 3))
                {
                    nextGen[column][row] = false;
                }
                //if exactly 3 neighbours - born new
                else if((!Bord[column][row]) && neighbours == 3)
                {
                    nextGen[column][row] = true;
                }
                //else stay alive
                else{
                    nextGen[column][row] = Bord[column][row];
                }
            }
        }
        Bord = nextGen;
    }
}
