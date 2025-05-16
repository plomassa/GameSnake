import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;

import java.util.ArrayList;

public class Game {

    private Grid grid;
    private ArrayList<SnakeParts> snakelist;
    private MyKeyboard keyboard;
    private FoodPosition foodPosition;
    private Direction firstDir;
    private Direction currDir;
    private int score;
    private int[] highScores;
    private FileManagement file;
    private boolean borderless;
    private Speed speed;
    private Gamescreen gamescreen;
    private Sound sound;

    public Game(int cols, int rows) {

        grid = new Grid(cols, rows);
        this.snakelist = new ArrayList<>();
        keyboard = new MyKeyboard(this);
        highScores = new int[10];
        file = new FileManagement();
        speed = Speed.FIRST;
        this.borderless = false;
        gamescreen = new Gamescreen();
        sound = new Sound("resources/sound.wav");
    }

    public void setBorderless(){
        this.borderless = !borderless;
        if(borderless){
            gamescreen.textBorderInit();
        }else {
            gamescreen.borderDel();
        }
    }

    public ArrayList<SnakeParts> getSnakelist(){
        return snakelist;
    }

    public void snakeInit(){

        Position snakeHeadPos = new Position(grid);
        snakeHeadPos.setRow(grid.getRows()/2);
        snakeHeadPos.setCol(grid.getCols()/2);

        Rectangle headRectangle = new Rectangle(grid.columnToX(snakeHeadPos.getCol()), grid.rowToY(snakeHeadPos.getRow()), grid.getCellSize(), grid.getCellSize());
        headRectangle.fill();

        snakeHeadPos.setRectangle(headRectangle);
        SnakeParts headSnake = new SnakeParts(snakeHeadPos);
        headSnake.setDirection(Direction.values()[(int) (Math.random() * Direction.values().length)]);

        snakelist.add(headSnake);
    }

    public void foodInit(){
        this.foodPosition = new FoodPosition(snakelist, grid);
    }

    public void init() throws InterruptedException {

        gamescreen.init(speed.getSpeed(),score);
        grid.init();
        snakeInit();
        foodInit();
        highScores = file.getScores();
        start();
    }

    public void start() throws InterruptedException {
        sound.loop();

        while (true) {

            // Pause for a while
            Thread.sleep(speed.getDelay());
            foodCollision();
            collisionDetector();
            speed();
            moveSnake();
            keyboard.setDisableKey(false);
        }
    }
    public void speed(){
        if (score >= 800 ){ speed = Speed.MAXIMUM;
        }else if (score >= 700){ speed = Speed.EIGHTH;
        }else if (score >= 600){ speed = Speed.SEVENTH;
        }else if (score >= 500){ speed = Speed.SIXTH;
        }else if (score >= 400){ speed = Speed.FIFTH;
        }else if (score >= 300){ speed = Speed.FOURTH;
        }else if (score >= 200){ speed = Speed.THIRD;
        }else if (score >= 100){ speed = Speed.SECOND;}

    }

    public void moveSnake() {
        for (SnakeParts s : snakelist) {

            s.move();
        }
        passDirection();
    }

    public void passDirection() {
        for (SnakeParts r : snakelist) {

            if (r.equals(snakelist.get(0))) {

                firstDir = r.getDirection();
                currDir = firstDir;
            } else {

                currDir = r.getDirection();
                r.setDirection(firstDir);
                firstDir = currDir;
            }
        }
    }

    public void eat() {

        Position pos = new Position(grid);
        SnakeParts snake = new SnakeParts(pos);

        switch (snakelist.get(snakelist.size()-1).getDirection()) {

            case UP:
                snake.getPos().setCol(snakelist.get(snakelist.size()-1).getPos().getCol());
                snake.getPos().setRow(snakelist.get(snakelist.size()-1).getPos().getRow() + 1);
                snake.setDirection(Direction.UP);
                break;
            case DOWN:
                snake.getPos().setCol(snakelist.get(snakelist.size()-1).getPos().getCol());
                snake.getPos().setRow(snakelist.get(snakelist.size()-1).getPos().getRow() - 1);
                snake.setDirection(Direction.DOWN);
                break;
            case LEFT:
                snake.getPos().setCol(snakelist.get(snakelist.size()-1).getPos().getCol() + 1);
                snake.getPos().setRow(snakelist.get(snakelist.size()-1).getPos().getRow());
                snake.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                snake.getPos().setCol(snakelist.get(snakelist.size()-1).getPos().getCol() - 1);
                snake.getPos().setRow(snakelist.get(snakelist.size()-1).getPos().getRow());
                snake.setDirection(Direction.RIGHT);
                break;
            default:
                snake.getPos().setCol(snakelist.get(snakelist.size()-1).getPos().getCol() + 1);
                snake.getPos().setRow(snakelist.get(snakelist.size()-1).getPos().getRow());
                snake.setDirection(Direction.UP);
        }

        Rectangle rec = new Rectangle(grid.columnToX(snake.getPos().getCol()), grid.rowToY(snake.getPos().getRow()), grid.getCellSize(), grid.getCellSize());
        rec.fill();
        snake.getPos().setRectangle(rec);
        snakelist.add(snake);
        gamescreen.scoreActualize(score);
        gamescreen.speedActualiz(speed.getSpeed());
    }

    public void foodCollision() {
//   a = food position    /  B=  snake head

        if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(foodPosition.getCols())) &&                                                            // from left
                (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(foodPosition.getCols()) + grid.getCellSize()) &&              //from right
                grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(foodPosition.getRows()) &&                                                          // from top
                ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(foodPosition.getRows()) + grid.getCellSize()))) {             //   from buttom

            eat();
            this.score+=10;
            System.out.println("Game score: "+score);
            foodPosition.createFood(snakelist);
            System.out.println(speed.getDelay());
        }
    }

    public void collisionDetector() {
        //collision with snake body
        for (SnakeParts c : snakelist) {
            if (!c.equals(snakelist.get(0))){

                if ((grid.columnToX(snakelist.get(0).getPos().getCol()) >= grid.columnToX(c.getPos().getCol())) &&                                                              // from left
                        (grid.columnToX(snakelist.get(0).getPos().getCol()) + grid.getCellSize()) <= (grid.columnToX(c.getPos().getCol()) + grid.getCellSize()) &&              //from right
                        grid.rowToY(snakelist.get(0).getPos().getRow()) >= grid.rowToY(c.getPos().getRow()) &&                                                                  // from top
                        ((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) <= (grid.rowToY(c.getPos().getRow()) + grid.getCellSize()))) {//from bottom

                    if (!snakelist.get(0).isCrashed()) {
                        gameOver();
                    }
                    for (SnakeParts n: snakelist){
                        n.setCrashed();
                        n.getPos().setRecColor(Color.RED);
                    }
                }
            }
        }
        // collision with borders if not borderless
        if (!borderless){
            if ((grid.columnToX(snakelist.get(0).getPos().getCol()) <= grid.getPadding()  && snakelist.get(0).getDirection() == Direction.LEFT )||                                                          // collide with left limit wall
                    ((grid.columnToX(snakelist.get(0).getPos().getCol())+grid.getCellSize()) >= grid.columnToX(grid.getCols()) && snakelist.get(0).getDirection() == Direction.RIGHT)||                     // collide with right limit wall
                    (grid.rowToY(snakelist.get(0).getPos().getRow()) <= grid.getPadding() && snakelist.get(0).getDirection() == Direction.UP)||                                                         // collide with top limit wall
                    (((grid.rowToY(snakelist.get(0).getPos().getRow()) + grid.getCellSize()) >= grid.rowToY(grid.getRows()))&& snakelist.get(0).getDirection() == Direction.DOWN)) {       // collide with bottom limit wall

                if (!snakelist.get(0).isCrashed()) {
                    gameOver();
                }
                for (SnakeParts n: snakelist){
                    n.setCrashed();
                    n.getPos().setRecColor(Color.RED);
                }
            }
        }else{ // Without borders
            // pass by left limit wall
            Rectangle rect;
            Position pos;

            for(SnakeParts s: snakelist){
                if (grid.columnToX(s.getPos().getCol()) <= grid.getPadding()  && s.getDirection() == Direction.LEFT ){

                    pos = s.getPos();
                    s.getPos().delRectangle();

                    rect = new Rectangle(grid.columnToX(grid.getCols()), grid.rowToY(pos.getRow()), grid.getCellSize(), grid.getCellSize());
                    rect.fill();

                    pos.setRectangle(rect);
                    pos.setCol(grid.getCols());

                    s.setPos(pos);
                } else if (((grid.columnToX(s.getPos().getCol()) + grid.getCellSize()) >= grid.columnToX(grid.getCols()) && s.getDirection() == Direction.RIGHT)) {

                    pos = s.getPos();
                    s.getPos().delRectangle();

                    rect = new Rectangle(grid.columnToX(-1), grid.rowToY(pos.getRow()), grid.getCellSize(), grid.getCellSize());
                    rect.fill();

                    pos.setRectangle(rect);
                    pos.setCol(-1);

                    s.setPos(pos);
                }else if  (grid.rowToY(s.getPos().getRow()) <= grid.getPadding() && s.getDirection() == Direction.UP) {
                    pos = s.getPos();
                    s.getPos().delRectangle();

                    rect = new Rectangle(grid.columnToX(pos.getCol()), grid.rowToY(grid.getRows()), grid.getCellSize(), grid.getCellSize());
                    rect.fill();

                    pos.setRectangle(rect);
                    pos.setRow(grid.getRows());

                    s.setPos(pos);
                }else if (((grid.rowToY(s.getPos().getRow()) + grid.getCellSize()) >= grid.rowToY(grid.getRows()))&& s.getDirection() == Direction.DOWN) {
                    pos = s.getPos();
                    s.getPos().delRectangle();

                    rect = new Rectangle(grid.columnToX(pos.getCol()), grid.rowToY(-1), grid.getCellSize(), grid.getCellSize());
                    rect.fill();

                    pos.setRectangle(rect);
                    pos.setRow(-1);

                    s.setPos(pos);
                }
            }
        }
    }

    public void gameOver(){
// insert new score if belongs to the high scores. sort it and write to file
        sound.stop();
        gamescreen.gameOver();
        boolean scorePresent = false;

        for (int ii= 0; ii< highScores.length;ii++){
            if (score == highScores[ii]){
                scorePresent = true;
            }
        }
        if (!scorePresent){
            for (int i=0; i<highScores.length;i++){
                int savedScore;
                int secSavedScore;
                if (score > highScores[i]){
                    savedScore = highScores[i];
                    highScores[i] = score;
                    for (int j= i+1;j<highScores.length; j++){
                        secSavedScore = highScores[j];
                        highScores[j]=savedScore;
                        savedScore = secSavedScore;
                        secSavedScore =0;
                    }
                    break;
                }
            }
        }
        System.out.println("Final Game Score: "+score+"\nHighscores: ");
        for (int k=0; k<highScores.length;k++){
            System.out.println(highScores[k]);
        }

        file.saveScores(highScores);

    }


}
