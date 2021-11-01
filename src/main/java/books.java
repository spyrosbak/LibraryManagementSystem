import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import Project.DBConnection;

public class books extends JFrame{
    private JLabel ID;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel Title;
    private JLabel Author;
    private JComboBox comboBox2;
    private JTextField textField3;
    private JButton registerBookButton;
    private JButton cancelButton;
    private JTextField textField4;
    public JPanel books;

    public books() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); //WINDOW IS NOT CLOSING
            }
        });
        registerBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookID = textField1.getText();
                String title = textField2.getText();
                String author = textField4.getText();
                String edition = (String)comboBox2.getSelectedItem();
                String price = textField3.getText();
                try{
                    Connection con = DBConnection.getCon();
                    Statement s = con.createStatement();
                    s.executeUpdate("insert into books values('"+bookID+"','"+title+"','"+author+"','"+edition+"','"+price+"')");
                    JOptionPane.showMessageDialog(null, "New book successfully registered!");

                    JFrame frame1 = new JFrame("books");
                    frame1.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dispose();
                        }
                    });

                    JFrame frame = new JFrame("books");
                    frame.setContentPane(new books().books);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Book ID already exist");
                    JFrame frame1 = new JFrame("books");
                    frame1.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dispose();
                        }
                    });

                    JFrame frame = new JFrame("books");
                    frame.setContentPane(new books().books);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });
    }
}