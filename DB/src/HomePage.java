import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class HomePage extends JFrame {

	private JPanel contentPane;
	private JDBC jdbc = new JDBC();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setTitle("Home");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setTitle(String Title){
		
	}
	/**
	 * Create the frame.
	 */
	public HomePage() {
		setTitle("Home");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 434, 145);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInsert = new JButton("Insert Tuple");
		btnInsert.setBounds(20, 11, 120, 49);
		panel.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete Tuple");
		btnDelete.setBounds(160, 11, 120, 49);
		panel.add(btnDelete);
		
		JButton btnJoin = new JButton("Select Multiple attributes from different table");
		btnJoin.setBounds(297, 11, 120, 49);
		panel.add(btnJoin);
		
		JButton btnSelect = new JButton("View");
		btnSelect.setBounds(20, 86, 120, 48);
		panel.add(btnSelect);
		
		JButton btnView = new JButton("Create Table from Existing Data");
		btnView.setBounds(160, 86, 120, 48);
		panel.add(btnView);
		
		JButton btnGroup = new JButton("Count");
		btnGroup.setBounds(297, 86, 120, 48);
		panel.add(btnGroup);
		
		btnInsert.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                InsertPage newpage = new InsertPage();
                newpage.setTitle("Insert");
               // newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                DeletePage newpage = new DeletePage();
                newpage.setTitle("Delete");
               // newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnJoin.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                JoinPage newpage = new JoinPage();
                newpage.setTitle("Join");
              //  newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnSelect.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                SelectPage newpage = new SelectPage();
                newpage.setTitle("Select");
               // newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnView.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                CreateViewPage newpage = new CreateViewPage();
                newpage.setTitle("Select");
              //  newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnGroup.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                Groups newpage = new Groups();
                newpage.setTitle("Group");
               // newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
	}


	public DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
}
