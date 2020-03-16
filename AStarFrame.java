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

    private static boolean hasStart = false;
    private static boolean canSelectStartAndEnd = false;
    private static boolean canDraw = false;
    private static boolean isDrawing = false;
    private static boolean finishDrawing = false;

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
                if(e.getKeyChar() == 'd' && !finishDrawing) canDraw = true;
                if(e.getKeyChar() == 'a' && !finishDrawing){
                    canDraw = false;
                    canSelectStartAndEnd = true;
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

        this.add(pane);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class AStarPanel extends JPanel{

        private static final long serialVersionUID = 1L;

        private final int xCoor;
        private final int yCoor;

        private boolean isStart = false;
        private boolean isEnd = false;
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
                    if(canDraw && !canSelectStartAndEnd){
                        if(isDrawing) isDrawing = false;
                        else isDrawing = true;
                    }   
                    if(canSelectStartAndEnd){
                        if(!hasStart){
                            e.getComponent().setBackground(Color.GREEN);
                            ((AStarPanel)e.getComponent()).isStart = true;
                            hasStart = true;
                        }
                        else{
                            e.getComponent().setBackground(Color.RED);
                            ((AStarPanel)e.getComponent()).isEnd = true;
                            canSelectStartAndEnd = false;
                            finishDrawing = true;
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
                    if(isDrawing && canDraw){
                        e.getComponent().setBackground(Color.BLACK);
                        ((AStarPanel)e.getComponent()).canCross = false;
                    }
                }
            });
        }
    }


    public static void main(String[] args){
        AStarFrame a = new AStarFrame(50, 50);
        System.out.println(a.coordonates(25, 25));

    }


}