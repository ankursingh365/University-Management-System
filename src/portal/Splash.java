package portal;

import java.awt.*;
import javax.swing.*;

public class Splash{
    public static void main(String s[]){
        Frame f = new Frame("University of Petroleum and Energy Studies, Dehradun");
        f.setVisible(true);
        int i;
        int x=1;
        for(i=2;i<=600;i+=4,x+=1){
            f.setLocation((500-((i+x)/2) ),300-(i/2));
            f.setSize(i+3*x,i+x/2);

            try{
                Thread.sleep(10);
            }catch(Exception e) { }
        }
    }
}
class Frame extends JFrame implements Runnable{
    Thread t1;
    Frame(String s){
        super(s);
        setLayout(new FlowLayout());
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("portal/icons/first.jpg"));
        Image i1 = c1.getImage().getScaledInstance(1200, 650,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);

        JLabel m1 = new JLabel(i2);
        add(m1);
        t1 = new Thread(this);
        t1.start();
    }
    public void run(){
        try{
            Thread.sleep(4000);
            this.setVisible(false);
            Login f1 = new Login();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}