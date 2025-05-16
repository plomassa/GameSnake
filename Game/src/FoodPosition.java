import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class FoodPosition {

    private Position position;
    private ArrayList<SnakeParts> snake;
    private static final int centerFoodXY = 7;

    public FoodPosition(ArrayList<SnakeParts> snake, Grid grid) {
        this.snake = snake;
        position =  new Position(grid);
        createFood(snake);
    }

    public void createFood(ArrayList<SnakeParts> snake) {
        this.snake = snake;
        if (position.getPicture() != null){
            position.getPicture().delete();
        }
        position.setCol((int) (Math.random() * position.getGrid().getCols()));
        position.setRow((int) (Math.random() * position.getGrid().getRows()));

        Picture picture = new Picture(position.getGrid().columnToX(position.getCol())+centerFoodXY,position.getGrid().rowToY(position.getRow())+centerFoodXY,"resources/applebig.png");
        picture.draw();
        picture.grow(7,7);
        position.setPicture(picture);

        checkConflict();
    }
    public void checkConflict(){
        for (SnakeParts s: snake){

            if ((Grid.columnToX(s.getPos().getCol()) >= Grid.columnToX(position.getCol())) &&                                                            // from left
                    (Grid.columnToX(s.getPos().getCol()) + Grid.getCellSize()) <= (Grid.columnToX(position.getCol()) + Grid.getCellSize()) &&              //from right
                    Grid.rowToY(s.getPos().getRow()) >= Grid.rowToY(position.getRow()) &&                                                          // from top
                    ((Grid.rowToY(s.getPos().getRow()) + Grid.getCellSize()) <= (Grid.rowToY(position.getRow()) + Grid.getCellSize()))) {             //   from buttom
                createFood(snake);
                System.out.println("Conflict between food position and snake, new food position!");
            }
        }
    }

    public int getCols() {
        return position.getCol();
    }

    public int getRows() {
        return position.getRow();
    }
}
