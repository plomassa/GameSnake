

public class SnakeParts {

    private Position pos;
    private Direction direction;
    private boolean crashed;

    public SnakeParts(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isCrashed() {
        return crashed;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setCrashed() {
        this.crashed = true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {

        if (!crashed) {
            switch (direction) {

                case UP:
                    pos.getRectangle().translate(0, pos.getGrid().getCellSize() * -1);
                    pos.setRow(pos.getRow()-1);
                    break;
                case DOWN:
                    pos.getRectangle().translate(0, pos.getGrid().getCellSize());
                    pos.setRow(pos.getRow()+1);
                    break;
                case LEFT:
                    pos.getRectangle().translate(pos.getGrid().getCellSize() * -1, 0);
                    pos.setCol(pos.getCol()-1);
                    break;
                case RIGHT:
                    pos.getRectangle().translate(pos.getGrid().getCellSize(), 0);
                    pos.setCol(pos.getCol()+1);
                    break;
                default:
                    pos.getRectangle().translate(0, 0);
                    break;
            }
        }

    }
}
