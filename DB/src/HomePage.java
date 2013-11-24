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
		setBounds(100, 100, 480, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 458, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInsert = new JButton("Compare 16s Sequence");
		btnInsert.setBounds(10, 11, 145, 60);
		panel.add(btnInsert);
		
		JButton btnDelete = new JButton("Identify DNA Genus");
		btnDelete.setBounds(158, 11, 145, 60);
		panel.add(btnDelete);
		
		JButton btnJoin = new JButton("Select Values");
		btnJoin.setBounds(305, 11, 145, 60);
		panel.add(btnJoin);
		
		JButton btnSelect = new JButton("View");
		btnSelect.setBounds(10, 75, 145, 60);
		panel.add(btnSelect);
		
		JButton btnView = new JButton("Create Table");
		btnView.setBounds(158, 75, 145, 60);
		panel.add(btnView);
		
		JButton btnGroup = new JButton("Count");
		btnGroup.setBounds(305, 75, 145, 60);
		panel.add(btnGroup);
		
		btnInsert.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
            	ResultSet rs;
            	JTable jtable = null;
            	rs = null;
            	
            	jdbc.ViewData(false, "ABCD", "G_name",  "sixteens_sequence", "G.speciecat = C.cat");
            	rs = jdbc.JoinData(false, "*", "ABCD A, ABCD B", "A.G_name <> B.G_name");
            	jdbc.DropView("ABCD");

            	try{
            		if(!rs.isBeforeFirst())
            		{
            			jtable = new JTable();
            		}
            		else
            		{
            			jtable = new JTable(buildTable(rs));
            		}	
            	}
            	catch(SQLException e1)
            	{
            		e1.printStackTrace();
            	}
            	JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
            }

			private TableModel buildTable(ResultSet rs) throws SQLException{
				// TODO Auto-generated method stub
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
				return new DefaultTableModel(data,columnNames);
			}
        });
		
		btnDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                IdentifyPage newpage = new IdentifyPage();
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
