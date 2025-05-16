public enum Speed {


    MAXIMUM("MAXIMUM",100),
    EIGHTH("EIGHTH",150),
    SEVENTH("SEVENTH",200),
    SIXTH("SIXTH",250),
    FIFTH("FIFTH",300),
    FOURTH("FOURTH",350),
    THIRD("THIRD",400),
    SECOND("SECOND",450),
    FIRST("FIRST",500);

    private String speed;
    private int delay;

    Speed (String speed, int delay){
        this.speed=speed;
        this.delay = delay;
    }

    public int getDelay(){return this.delay;}

    public String getSpeed() {
        return speed;
    }
}
