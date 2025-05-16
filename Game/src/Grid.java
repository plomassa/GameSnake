import com.codeforall.online.simplegraphics.graphics.Rectangle;

public class Grid {

    public static final int PADDING = 50;
    private static final int cellSize = 50;
    private int cols;
    private int rows;


    /**
     * Simple graphics grid constructor with a certain number of rows and columns
     *
     * @param cols number of the columns
     * @param rows number of rows
     */
    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }
    /**
     * Initializes the field simple graphics rectangle and draws it
     */
    public void init() {
        Rectangle field = new Rectangle(PADDING, PADDING, cols * cellSize, rows * cellSize);
        field.draw();
    }

    public static int getCellSize() {
        return cellSize;
    }

    public int getCols() {
        return this.cols;
    }

    public int getRows() {
        return this.rows;
    }

    public int getPadding(){return PADDING;}

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     *
     * @param row index
     * @return y pixel value
     */
    public static int rowToY(int row) {
        return PADDING + cellSize * row;
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     *
     * @param column index
     * @return x pixel value
     */
    public static int columnToX(int column) {
        return PADDING + cellSize * column;
    }

}
