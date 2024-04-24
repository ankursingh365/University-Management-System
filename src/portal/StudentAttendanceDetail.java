package portal;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.opencsv.CSVWriter;

public class StudentAttendanceDetail extends JFrame implements ActionListener{

    JTable j1;
    JButton b1,b2;
    JComboBox<String> filterComboBox;
    JTextField filterTextField;
    String h[]={"Roll Number","Teacher Name","Subject","Date","Time","Attendance"};
    String d[][]=new String[15][6];
    int i=0,j=0;

    StudentAttendanceDetail(){
        super("View Students Attendance");
        setSize(800,300);
        setLocation(450,150);

        try{
            String q="select * from attendance_student";
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next()){
                d[i][j++]=rs.getString("rollno");
                d[i][j++]=rs.getString("teacher name");
                d[i][j++]=rs.getString("subject");
                d[i][j++]=rs.getString("date");
                d[i][j++]=rs.getString("time");
                d[i][j++]=rs.getString("attendance");
                i++;
                j=0;
            }

            j1=new JTable(d,h);
            DefaultTableModel model = new DefaultTableModel(d, h) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            j1.setModel(model);


        }catch(Exception e){
            e.printStackTrace();
        }

        b1=new JButton("Print");
        b2=new JButton("Export");
        filterComboBox = new JComboBox<>(h);
        filterTextField = new JTextField(15);
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter by:"));
        filterPanel.add(filterComboBox);
        filterPanel.add(filterTextField);

        add(filterPanel, BorderLayout.NORTH);

        b1.addActionListener(this);
        b2.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(b1);
        buttonPanel.add(b2);

        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane s1=new JScrollPane(j1);
        add(s1);

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                j1.print();
            } catch(Exception e){
                e.printStackTrace();
            }
        } else if(ae.getSource() == b2){
            try {
                // Export data to a file
                String filename = "attendance_export.csv";
                CSVWriter writer = new CSVWriter(new FileWriter(filename));
                DefaultTableModel model = (DefaultTableModel) j1.getModel();
                int rows = model.getRowCount();
                int cols = model.getColumnCount();
                String[] header = new String[cols];
                for (int i = 0; i < cols; i++) {
                    header[i] = model.getColumnName(i);
                }
                writer.writeNext(header);
                for (int i = 0; i < rows; i++) {
                    String[] row = new String[cols];
                    for (int j = 0; j < cols; j++) {
                        Object value = model.getValueAt(i, j);
                        row[j] = (value != null) ? value.toString() : "";
                    }
                    writer.writeNext(row);
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Data exported successfully to " + filename);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new StudentAttendanceDetail().setVisible(true);
    }
}
