import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import  Project.DBConnection;

public class members extends JFrame{
    public JPanel members;
    private JLabel Name;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JLabel Surname;
    private JLabel Age;
    private JLabel Occupation;
    private JTextField textField4;
    private JLabel ID;
    private JButton registerButton;
    private JButton cancelButton;

    public members() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String memberID = textField4.getText();
                String name = textField1.getText();
                String surname = textField2.getText();
                String age = textField3.getText();
                String occupation = (String)comboBox1.getSelectedItem();
                try {
                    Connection con = DBConnection.getCon();
                    Statement s = con.createStatement();
                    s.executeUpdate("insert into members values('"+memberID+"','"+name+"','"+surname+"','"+age+"','"+occupation+"')");
                    JOptionPane.showMessageDialog(null, "New member successfully registered!");

                    JFrame frame1 = new JFrame("member");
                    frame1.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dispose();
                        }
                    });

                    JFrame frame = new JFrame("members");
                    frame.setContentPane(new members().members);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Member ID already exist");
                    JFrame frame1 = new JFrame("member");
                    frame1.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            dispose();
                        }
                    });

                    JFrame frame = new JFrame("members");
                    frame.setContentPane(new members().members);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("member");
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                    }
                });
            }
        });
    }
}