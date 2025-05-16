import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Text;
import com.codeforall.online.simplegraphics.pictures.Picture;

public class Gamescreen {

    private Picture background;
    private static int backgroundWidth;
    private static int backgroundHeight;
    private Text scoreText;
    private Text topText;
    private Text borderText;
    private Text speedText;
    private Picture gameOverPic;
    private Text exitText;
    private Text instructions1;
    private Text instructions2;

    public Gamescreen() {
        gameOverPic = new Picture(0, 0, "resources/gameOver.png");
    }

    public void init(String speed,int score){
       pictureInit();
       textScoreInit(score);
       textTopInit();
       textSpeedInit(speed);
       textInstructions();
    }

    public void pictureInit(){
        background = new Picture(0,0,"resources/background.jpeg");
        background.draw();
        backgroundWidth = background.getMaxX();
        backgroundHeight = background.getMaxY();

    }

    public void textScoreInit(int score){
        this.scoreText = new Text(80, 570, "S C O R E  :  "  +  score);
        scoreText.setColor(Color.WHITE);
        scoreText.grow(30, 30);
        scoreText.draw();

    }

    public void scoreActualize(int score){
        scoreText.setText("S C O R E  :  " + score);
        scoreText.draw();
        System.out.println("Score was atualized");
    }

    public void textTopInit(){
        this.topText = new Text(320, 20, "S N A K E");
        topText.setColor(Color.WHITE);
        topText.grow(30, 30);
        topText.draw();
    }

    public void textBorderInit(){
        this.borderText= new Text(300, 570, "W I T H O U T  B O R D E R S");
        borderText.setColor(Color.GREEN);
        borderText.grow(30, 30);
        borderText.draw();
    }

    public void borderDel() {
        borderText.delete();
    }

    public void textSpeedInit(String speed){
        this.scoreText = new Text(580, 570, "S P E E D :  "  +  speed);
        scoreText.setColor(Color.WHITE);
        scoreText.grow(30, 30);
        scoreText.draw();
    }
    public void speedActualiz(String speed){
        scoreText.setText("S P E E D :  "  +  speed);
        scoreText.draw();

    }
    public void textInstructions(){
        this.instructions1 = new Text(10, 10, "ARROW KEYS -> CHANGE SNAKE DIRECTION");
        instructions1.setColor(Color.WHITE);
        instructions1.grow(0, 0);
        instructions1.draw();

        this.instructions2 = new Text(10, 30, "B KEY -> ENABLE/DESABLE BORDER");
        instructions2.setColor(Color.WHITE);
        instructions2.grow(0, 0);
        instructions2.draw();
    }

    public void gameOver(){
        gameOverPic.draw();

        this.scoreText = new Text(300, 470, "P R E S S   E S C   T O   E X I T");
        scoreText.setColor(Color.WHITE);
        scoreText.grow(30, 30);
        scoreText.draw();

    }



    public static int getWidth() {
        return backgroundWidth;
    }
    public static int getHeight() {
        return backgroundHeight;
    }

    public Picture getBackground() {
        return background;
    }
    public int getBackgroundWidth() {
        return backgroundWidth;
    }
    public int getBackgroundHeight() {
        return backgroundHeight;
    }


}
