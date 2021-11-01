import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import Project.DBConnection;

public class lending extends JFrame {
    public JPanel lending;
    private JTextField textField1;
    private JTextField textField2;
    private JDateChooser JDateChooser1;
    private JDateChooser JDateChooser2;
    private JLabel bookid;
    private JLabel memberid;
    private JLabel issuedate;
    private JLabel duedate;
    private JButton issueBookButton;
    private JButton cancelButton;

    public lending() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                String bookID = textField1.getText();
                String memberID = textField2.getText();
                String issueDate = date.format(JDateChooser1.getDate());
                String dueDate = date.format(JDateChooser2.getDate());
                String isReturned = "NO";
                try{
                    Connection con = DBConnection.getCon(); //THROWS CONNECTION ERROR
                    Statement s = con.createStatement();
                    ResultSet r1 = s.executeQuery("select *from books where BookID='"+bookID+"'");
                    if(r1.next()){
                        ResultSet r2 = s.executeQuery("select *from members where memberID='"+memberID+"'");
                        if(r2.next()){
                            s.executeUpdate("insert into issue values('"+bookID+"','"+memberID+"','"+issueDate+"','"+dueDate+"','"+isReturned+"')");
                            JOptionPane.showMessageDialog(null, "Book successfully issued!");

                            JFrame frame1 = new JFrame("lending");
                            frame1.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    dispose();
                                }
                            });

                            JFrame frame = new JFrame("lending");
                            frame.setContentPane(new lending().lending);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Member is not register");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Book is not registered");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Connection Error!");
                }
            }
        });
    }

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDateChooser2 = new JDateChooser();
    }
}