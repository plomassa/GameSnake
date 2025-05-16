import com.codeforall.online.simplegraphics.keyboard.Keyboard;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.online.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.online.simplegraphics.keyboard.KeyboardHandler;
import java.util.ArrayList;
public class MyKeyboard implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private boolean disableKey;

    public MyKeyboard(Game game) {
        this.keyboard = new Keyboard(this);
        this.game= game;
        disableKey = false;
        keyInit();
    }
    public void keyInit() {

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KeyboardEvent.KEY_SPACE);
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(space);

        KeyboardEvent goRight = new KeyboardEvent();
        goRight.setKey(KeyboardEvent.KEY_RIGHT);
        goRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goRight);

        KeyboardEvent goLeft = new KeyboardEvent();
        goLeft.setKey(KeyboardEvent.KEY_LEFT);
        goLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goLeft);

        KeyboardEvent goUp = new KeyboardEvent();
        goUp.setKey(KeyboardEvent.KEY_UP);
        goUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goUp);

        KeyboardEvent goDown = new KeyboardEvent();
        goDown.setKey(KeyboardEvent.KEY_DOWN);
        goDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(goDown);

        KeyboardEvent exit = new KeyboardEvent();
        exit.setKey(KeyboardEvent.KEY_ESC);
        exit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(exit);

        KeyboardEvent border = new KeyboardEvent();
        border .setKey(KeyboardEvent.KEY_B);
        border .setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(border);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (!disableKey) {

            if ((keyboardEvent.getKey() == KeyboardEvent.KEY_RIGHT) && (!game.getSnakelist().get(0).getDirection().isOpposite(Direction.RIGHT))) {
                game.getSnakelist().get(0).setDirection(Direction.RIGHT);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_LEFT) && (!game.getSnakelist().get(0).getDirection().isOpposite(Direction.LEFT))) {

                game.getSnakelist().get(0).setDirection(Direction.LEFT);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_UP) && (!game.getSnakelist().get(0).getDirection().isOpposite(Direction.UP))) {

                game.getSnakelist().get(0).setDirection(Direction.UP);
                disableKey = true;

            } else if ((keyboardEvent.getKey() == KeyboardEvent.KEY_DOWN) && (!game.getSnakelist().get(0).getDirection().isOpposite(Direction.DOWN))) {

                game.getSnakelist().get(0).setDirection(Direction.DOWN);
                disableKey = true;
            }
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_B){
            game.setBorderless();
        }


        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC){
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}

    public void setDisableKey(boolean state){ this.disableKey = state;}

    public boolean getDisablekey(){return this.disableKey;}
}
