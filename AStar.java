import java.util.PriorityQueue;
import java.util.Comparator;

public class AStar{

    //diagonal / vertical and horizontal cost
    public static final int DIAGONAL_COST = 14;
    public static final int VERTICAL_HORZIZONTAL_COST = 10;

    //the grid on which the algorithm works
    private CellStar[][] grid;
    private PriorityQueue<CellStar> openCells;
    private boolean[][] closedCells;

    private CellStar startingCell;
    private CellStar finalCell;

    public CellStar[][] getGrid(){
        return this.grid;
    }

    public PriorityQueue<CellStar> getOpenCells(){
        return this.openCells;
    }

    public boolean[][] getClosedCells(){
        return this.closedCells;
    }

    public CellStar getStartingCell(){
        return this.startingCell;
    }

    public CellStar getFinalCell(){
        return this.finalCell;
    }

    public AStar(int width, int height){
        this.grid = new CellStar[width][height];
        this.closedCells = new boolean[width][height];
        this.openCells = new PriorityQueue<CellStar>(new Comparator<CellStar>(){
            @Override
            public int compare(CellStar c1, CellStar c2){
                if(c1.getFinalCost() < c2.getFinalCost()) return -1;
                else if(c1.getFinalCost() > c2.getFinalCost()) return 1;
                else return 0;
            }
        });
        this.setGrid();
    }

    /**
     * @param width the width of the grid
     * @param height the height of the grid
     */
    private void setGrid(){
        for(int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid[i].length; j++){
                CellStar c = new CellStar(i, j);
                this.grid[i][j] = c;
            }
        }
    }

    public void setHeuristics(){
        for(int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid[i].length; j++){
                this.grid[i][j].setHeuristic(this.finalCell);
            }
        }
    }

    public void setStartingCell(int x, int y){
        this.startingCell = new CellStar(x, y);
    }

    public void setFinalCell(int x, int y){
        this.finalCell = new CellStar(x, y);
    }

    public static void main(String[] args){
        AStar s = new AStar(50, 50);
    }
}