public class CellStar{

    private int xCell;
    private int yCell;
    private CellStar parent;
    private int heuristic;
    private int finalCost;

    public int getXCell(){
        return this.xCell;
    }

    public int getYCell(){
        return this.yCell;
    }

    public CellStar(int x, int y){
        this.xCell = x;
        this.yCell = y;
    }

}