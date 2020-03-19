public class CellStar{

    private int xCellCoor;
    private int yCellCoor;
    private CellStar parent;
    private int heuristic;
    private int finalCost;

    public int getXCellCoor(){
        return this.xCellCoor;
    }

    public int getYCellCoor(){
        return this.yCellCoor;
    }

    public CellStar getParent(){
        return this.parent;
    }

    public int getHeuristic(){
        return this.heuristic;
    }

    public int getFinalCost(){
        return this.finalCost;
    }

    public void setXCellCoor(int x){
        this.xCellCoor = x;
    }

    public void setYCellCoor(int y){
        this.yCellCoor = y;
    }

    public CellStar(int x, int y){
        this.xCellCoor = x;
        this.yCellCoor = y;
    }

    /**
     * @param heuristic the heuristic to set
     */
    public void setHeuristic(CellStar finalCell){
        this.heuristic = Math.abs(finalCell.getXCellCoor() - this.getXCellCoor()) + Math.abs(finalCell.getYCellCoor() - this.getYCellCoor());
    }

    @Override
    public boolean equals(Object c){
        if(c instanceof CellStar) return this.getXCellCoor() == ((CellStar)c).getXCellCoor() && this.getYCellCoor() == ((CellStar)c).getYCellCoor();
        return false;
    }

}