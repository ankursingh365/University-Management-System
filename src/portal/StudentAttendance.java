package portal;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentAttendance extends JFrame implements ActionListener{

    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    Choice c2,fh,sh,tc,sc;

    StudentAttendance(){

        setLayout(new GridLayout(6,2,20,50));
        c2 = new Choice();
        tc = new Choice();
        sc = new Choice();
        try{
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()){
                c2.add(rs.getString("rollno"));
            }
            rs = c.s.executeQuery("select * from teacher");
            while(rs.next()){
                tc.add(rs.getString("name"));
            }

//            rs = c.s.executeQuery("select * from subject");
//            while(rs.next()){
//                sc.add(rs.getString("subject1"));
//                sc.add(rs.getString("subject2"));
//                sc.add(rs.getString("subject3"));
//                sc.add(rs.getString("subject4"));
//                sc.add(rs.getString("subject5"));
//            }
            rs = c.s.executeQuery("SELECT subject1 FROM subject UNION SELECT subject2 FROM subject UNION SELECT subject3 FROM subject UNION SELECT subject4 FROM subject UNION SELECT subject5 FROM subject");
            while(rs.next()){
                sc.add(rs.getString(1)); // Add subject to the choice component
            }


        }catch(Exception e){ }

        add(new JLabel("Select Roll Number"));
        add(c2);
        add(new JLabel("Select Teacher"));
        add(tc);

        add(new JLabel("Select Subject"));
        add(sc);


        l1 = new JLabel("Class Time");
        fh = new Choice();
        fh.add("9:00-9:55");
        fh.add("10:00-10:55");
        fh.add("11:00-11:55");
        fh.add("12:00-12:55");
        fh.add("13:00-13:55");
        fh.add("14:00-14:55");
        fh.add("15:00-15:55");
        fh.add("16:00-16:55");

        add(l1);
        add(fh);

        l2 = new JLabel("Attendance");
        sh = new Choice();
        sh.add("Present");
        sh.add("Absent");

        add(l2);
        add(sh);

        b1 =new JButton("Submit");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2 = new JButton("Cancel");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        add(b1);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
        setSize(400,450);
        setLocation(450,200);

    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == b1) {
            String f = fh.getSelectedItem();
            String s = sh.getSelectedItem();
            String dt = new java.util.Date().toString();
            String id = c2.getSelectedItem();
            String teacherName = tc.getSelectedItem(); // Get selected teacher name
            String subject = sc.getSelectedItem();
            String qry = "insert into attendance_student values(" + id + ",'" + teacherName +"','"+ subject +"','" + dt + "','" + f + "','" + s + "')";

            try {
                conn c1 = new conn();
                c1.s.executeUpdate(qry);
                JOptionPane.showMessageDialog(null, "Attendance confirmed");
                this.setVisible(false);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        else if(ae.getSource() == b2){
            this.dispose();
        }

    }

    public static void main(String s[]){
        new StudentAttendance();
    }
}

