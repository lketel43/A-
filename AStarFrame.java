import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class AStarFrame extends JFrame{

    private static final long serialVersionUID = 1L;

    private JPanel pane;

    private AStar astar;

    private boolean hasStart = false;
    private boolean canSelectStartAndEnd = false;
    private boolean canDraw = false;
    private boolean isDrawing = false;
    private boolean finishDrawing = false;

    /**
     * @param x the raw of the panel we want to get
     * @param y the line of the panel we want to get
     * @return the panel which is on the raw n°x and on the line n°y
     */
    public AStarPanel coordonates(int x, int y){
        for(Component c : this.pane.getComponents()){
            if(c instanceof AStarPanel){
                if(((AStarPanel)c).getXCoor() == x && ((AStarPanel)c).getYCoor() == y) {
                    return ((AStarPanel)c);
                }
            }
        }
        return null;
    }

    public AStarFrame(int raws, int columns){
        super();
        this.setSize(500, 500);
        this.addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e){
                if(e.getKeyChar() == 'd' && !AStarFrame.this.finishDrawing) canDraw = true;
                if(e.getKeyChar() == 'a' && !AStarFrame.this.finishDrawing){
                    canDraw = false;
                    AStarFrame.this.canSelectStartAndEnd = true;
                }
            }
        });

        this.pane = new JPanel();
        GridLayout layoutStar = new GridLayout(raws, columns);
        layoutStar.preferredLayoutSize(pane);
        this.pane.setLayout(layoutStar);
        for(int i = 0; i < raws; i++){
            for(int j = 0; j < columns; j++){
                AStarPanel p = new AStarPanel(j, i);
                pane.add(p);
            }
        }

        this.astar = new AStar(raws, columns);
        this.add(pane);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class AStarPanel extends JPanel{

        private static final long serialVersionUID = 1L;

        private final int xCoor;
        private final int yCoor;

        private boolean isStartingCell = false;
        private boolean isFinalCell = false;
        private boolean canCross = true;
    
        public int getXCoor(){
            return this.xCoor;
        }

        public int getYCoor(){
            return this.yCoor;
        }
        

        public AStarPanel(int x, int y) {
            super();
            this.xCoor = x;
            this.yCoor = y;
            Border b = BorderFactory.createLineBorder(Color.black, 1);
            this.setBorder(b);
            this.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e){
                    if(AStarFrame.this.canDraw && !AStarFrame.this.canSelectStartAndEnd){
                        if(AStarFrame.this.isDrawing) isDrawing = false;
                        else AStarFrame.this.isDrawing = true;
                    }   
                    if(AStarFrame.this.canSelectStartAndEnd){
                        if(!AStarFrame.this.hasStart){
                            e.getComponent().setBackground(Color.GREEN);
                            ((AStarPanel)e.getComponent()).isStartingCell = true;
                            hasStart = true;
                            AStarFrame.this.astar.setStartingCell(((AStarPanel)e.getComponent()).getXCoor(), ((AStarPanel)e.getComponent()).getYCoor());
                        }
                        else{
                            e.getComponent().setBackground(Color.RED);
                            ((AStarPanel)e.getComponent()).isFinalCell = true;
                            AStarFrame.this.canSelectStartAndEnd = false;
                            AStarFrame.this.finishDrawing = true;
                            AStarFrame.this.astar.setFinalCell(((AStarPanel)e.getComponent()).getXCoor(), ((AStarPanel)e.getComponent()).getYCoor());
                            AStarFrame.this.astar.setHeuristics();
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e){
                    System.out.println(((AStarPanel)e.getComponent()).getXCoor());
                    System.out.println(((AStarPanel)e.getComponent()).getYCoor());
                }

            });

            this.addMouseMotionListener(new MouseMotionAdapter(){

                @Override
                public void mouseMoved(MouseEvent e){
                    if(AStarFrame.this.isDrawing && AStarFrame.this.canDraw){
                        e.getComponent().setBackground(Color.BLACK);
                        ((AStarPanel)e.getComponent()).canCross = false;
                    }
                }
            });
        }
    }


    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                AStarFrame a = new AStarFrame(50, 50);
            }
        });
    }


}