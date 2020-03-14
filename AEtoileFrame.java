import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AEtoileFrame extends JFrame{

    private static boolean hasStart = false;
    private static boolean canSelectStartAndEnd = true;
    private static boolean canDraw = true;

    public AEtoileFrame(int raws, int columns){
        super();
        this.setSize(500, 500);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(raws, columns));
        for(int i = 0; i < raws * columns; i++){
            AEtoilePanel p = new AEtoilePanel();
            pane.add(p);
        }
        this.add(pane);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    static class AEtoilePanel extends JPanel{

        public AEtoilePanel(){
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
        }
    }


    public static void main(String[] args){
        AEtoileFrame a = new AEtoileFrame(50, 50);

    }


}