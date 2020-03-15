import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;


public class AStarFrame extends JFrame{

    private static boolean hasStart = false;
    private static boolean canSelectStartAndEnd = false;
    private static boolean canDraw = true;

    public AStarFrame(int raws, int columns){
        super();
        this.setSize(500, 500);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(raws, columns));
        pane.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){
                System.out.println(e.getComponent());
                e.getComponent().setBackground(Color.BLACK);
            }
        });
        for(int i = 0; i < raws * columns; i++){
            AStarPanel p = new AStarPanel();
            pane.add(p);
        }
        this.add(pane);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class AStarPanel extends JPanel{

        public AStarPanel(){
            super();
            Border b = BorderFactory.createLineBorder(Color.black, 1);
            this.setBorder(b);
            this.addMouseListener(new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e){
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
            /*
            this.addMouseMotionListener(new MouseMotionAdapter(){
                @Override
                public void mouseDragged(MouseEvent e){
                    if(canDraw){
                        e.getComponent().setBackground(Color.BLACK);
                    }
                }
            });
            */
        }
    }


    public static void main(String[] args){
        AStarFrame a = new AStarFrame(50, 50);

    }


}