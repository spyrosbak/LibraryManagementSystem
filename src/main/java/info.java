import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import Project.DBConnection;
import net.proteanit.sql.DbUtils;

public class info extends JFrame{
    public JPanel info;
    private JTable issueTable;
    private JButton OKButton;

    public info() {
        info.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                try {
                    Connection con = DBConnection.getCon();
                    Statement s = con.createStatement();
                    ResultSet r = s.executeQuery("select issue.MemberID,members.name,issue.BookID,books.Title,issue.IssueDate,issue.DueDate from members inner join books inner join issue where books.BookID=issue.BookID and members.memberID=issue.MemberID and issue.Returned='NO'");
                    issueTable.setModel(DbUtils.resultSetToTableModel(r));
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Connection Error!");
                }
            }
        });

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("info");
                frame1.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        dispose();
                    }
                });
            }
        });
    }
}
