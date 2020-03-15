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
    private static boolean hasStart = false;
    private static boolean canSelectStartAndEnd = false;
    private static boolean canDraw = false;
    private static boolean isDrawing = false;

    public AStarFrame(int raws, int columns){
        super();
        this.setSize(500, 500);
        this.addKeyListener(new KeyAdapter(){

            @Override
            public void keyTyped(KeyEvent e){
                if(e.getKeyChar() == 'd') canDraw = true;
                if(e.getKeyChar() == 'a'){
                    canDraw = false;
                    canSelectStartAndEnd = true;
                }
            }
        });

        JPanel pane = new JPanel();
        GridLayout layoutStar = new GridLayout(raws, columns);
        layoutStar.preferredLayoutSize(pane);
        pane.setLayout(layoutStar);
        for(int i = 0; i < raws * columns; i++){
            AStarPanel p = new AStarPanel();
            pane.add(p);
        }

        this.add(pane);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class AStarPanel extends JPanel{

        private static final long serialVersionUID = 1L;

        public AStarPanel() {
            super();
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
                            setBackground(Color.GREEN);
                            hasStart = true;
                        }
                        else{
                            setBackground(Color.RED);
                            canSelectStartAndEnd = false;
                        }
                    }
                }

            });

            this.addMouseMotionListener(new MouseMotionAdapter(){

                @Override
                public void mouseMoved(MouseEvent e){
                    if(isDrawing){
                        setBackground(Color.BLACK);
                    }
                }
            });
        }
    }


    public static void main(String[] args){
        AStarFrame a = new AStarFrame(50, 50);

    }


}