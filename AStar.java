import java.util.PriorityQueue;

public class AStar{

    //diagonal / vertical and horizontal cost
    public static final int DIAGONAL_COST = 14;
    public static final int VERTICAL_HORZIZONTAL_COST = 10;

    //the grid on which the algorithm works
    private CellStar[][] grid;
    private PriorityQueue<CellStar> openCells;
    private boolean[][] closedCells;

    private int startX;
    private int startY;

    private int endX;
    private int endY;

    public CellStar[][] getGrid(){
        return this.grid;
    }

    public PriorityQueue<CellStar> getOpenCells(){
        return this.openCells;
    }

    public boolean[][] getClosedCells(){
        return this.closedCells;
    }

    public int getStartX(){
        return this.startX;
    }

    public int getStartY(){
        return this.startY;
    }

    public int getEndX(){
        return this.endX;
    }

    public int getEndY(){
        return this.endY;
    }

    public AStar(int width, int height){
        this.grid = new CellStar[width][height];
        this.closedCells = new boolean[width][height];
        this.openCells = new PriorityQueue<CellStar>((CellStar c1, CellStar c2) -> {
            if(c1.getFinalCost() < c2.getFinalCost()) return -1;
            else if(c1.getFinalCost() > c2.getFinalCost()) return 1;
            else return 0;
        });
    }
}