import com.codeforall.online.simplegraphics.graphics.Color;
import com.codeforall.online.simplegraphics.graphics.Rectangle;
import com.codeforall.online.simplegraphics.pictures.Picture;

public class     Position {
    private int col;
    private int row;
    private Grid grid;
    private Rectangle rectangle;
    private Picture picture;

    public Position(Grid grid) {
        this.grid = grid;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRecColor(Color color) {
        if (rectangle != null) {
            rectangle.setColor(color);
        }
    }

    public Grid getGrid(){return this.grid;}

    public void delRectangle(){this.rectangle.delete();}

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Picture getPicture() {
        return picture;
    }

}