public enum Direction {

    UP,
    DOWN,
    RIGHT,
    LEFT;

    public boolean isOpposite(Direction dir) {
        return dir.equals(oppositeDirection());
    }

    public Direction oppositeDirection() {

        Direction opposite = Direction.UP;

        switch (this) {
            case UP:
                opposite = Direction.DOWN;
                break;
            case DOWN:
                opposite = Direction.UP;
                break;
            case LEFT:
                opposite = Direction.RIGHT;
                break;
            case RIGHT:
                opposite = Direction.LEFT;
                break;
        }
        return opposite;
    }
}