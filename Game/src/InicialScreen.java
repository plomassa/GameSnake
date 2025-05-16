import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.graphics.Text;

public class InicialScreen {

    private Text text;
    private Rectangle rectangle;
    private Grid grid;

    public InicialScreen(Grid grid) {
        this.grid = grid;
        screenInit();
    }

    public void screenInit() {
        rectInit();
        textInit();
    }

    public void rectInit() {
        rectangle = new Rectangle(grid.getPadding(), grid.getPadding(), grid.getCols() * Grid.getCellSize(), grid.getRows() * Grid.getCellSize());
        rectangle.fill();
    }

    public void textInit() {
        text = new Text(0, 0, "To start \nPress SPACE");
        text.setColor(Color.WHITE);

        int centerX = Grid.PADDING + (grid.getCols() * Grid.getCellSize() - text.getWidth()) / 2;
        int centerY = Grid.PADDING + (grid.getRows() * Grid.getCellSize() - text.getHeight()) / 2;

        text.translate(centerX, centerY);
        text.draw();
    }

    public void screenDel() {
       // this.text.delete();
        rectangle.delete();
        System.out.println("screenDel");
    }

}