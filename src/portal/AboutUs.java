package portal;

import java.awt.*;
import javax.swing.*;

public class AboutUs extends JFrame{

    private JPanel contentPane;

    public static void main(String[] args) {
        new AboutUs().setVisible(true);
    }

    public AboutUs() {

        super("About Us - UPES, Dehradun");
        setBackground(new Color(173, 216, 230));
        setBounds(250, 50, 700, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel l1 = new JLabel("New label");
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("portal/icons/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(130, 100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(400, 40, 250, 100);
        contentPane.add(l1);


        JLabel l3 = new JLabel("University");
        l3.setForeground(new Color(0, 250, 154));
        l3.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
        l3.setBounds(140, 40, 200, 55);
        contentPane.add(l3);

        JLabel l4 = new JLabel("Mangement System");
        l4.setForeground(new Color(127, 255, 0));
        l4.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 34));
        l4.setBounds(70, 90, 405, 40);
        contentPane.add(l4);


        JLabel l6 = new JLabel("Developed By : UPES Student");
        l6.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l6.setBounds(70, 198, 600, 35);
        contentPane.add(l6);

        JLabel l7 = new JLabel("Contact Number: +919131704452");
        l7.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        l7.setBounds(70, 260, 600, 34);
        contentPane.add(l7);

        JLabel l8 = new JLabel("Contact : upes@stu.ddn.ac.in");
        l8.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        l8.setBounds(70, 290, 600, 34);
        contentPane.add(l8);

        JLabel l9 = new JLabel("Education - B.Tech (Computer Science)");
        l9.setFont(new Font("Trebuchet MS", Font.BOLD , 20));
        l9.setBounds(70, 320, 600, 34);
        contentPane.add(l9);


        JLabel l10 = new JLabel("Phone - +91 9685242477");
        l10.setForeground(new Color(47, 79, 79));
        l10.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 18));
        l10.setBounds(70, 400, 600, 34);
        contentPane.add(l10);


        contentPane.setBackground(Color.WHITE);
    }
}