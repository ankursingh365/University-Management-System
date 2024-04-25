package portal;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

public class TeacherAttendanceDetail extends JFrame implements ActionListener{

    JTable j1;
    JButton b1,b2;
    JComboBox<String> filterComboBox;
    JTextField filterTextField;
    String h[]={"Employee id","Date Time","First Half","Second Half"};
    String d[][]=new String[15][4];
    int i=0,j=0;
    DefaultTableModel model;

    TeacherAttendanceDetail(){
        super("View Teachers Attendance");
        setSize(800,300);
        setLocation(450,150);

        try{
            String q="select * from attendance_teacher";
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);
            while(rs.next()){
                d[i][j++]=rs.getString("emp_id");
                d[i][j++]=rs.getString("Date");
                d[i][j++]=rs.getString("first");
                d[i][j++]=rs.getString("second");
                i++;
                j=0;
            }

            model = new DefaultTableModel(d, h) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            j1 = new JTable(model);

        }catch(Exception e){}

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
        filterTextField.addActionListener(this);
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
                // Export data to a file (you can customize this)
                String filename = "teacher_attendance_export.csv";
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
                        row[j] = model.getValueAt(i, j).toString();
                    }
                    writer.writeNext(row);
                }
                writer.close();
                JOptionPane.showMessageDialog(this, "Data exported successfully to " + filename);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if (ae.getSource() == filterTextField) {
            String filterText = filterTextField.getText().toLowerCase();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            j1.setRowSorter(sorter);
            if (filterText.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + filterText);
                sorter.setRowFilter(rowFilter);
            }
        }
    }

    public static void main(String[] args){
        new TeacherAttendanceDetail().setVisible(true);
    }
}
