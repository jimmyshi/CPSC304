import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class SelectPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private JTable jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectPage frame = new SelectPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(47, 12, 266, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(47, 41, 266, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblValues = new JLabel("Where:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);

		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);

		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String table = textField.getText();
				String where = textField_1.getText();
				rs = jdbc.SelectData(table, where);
				try {
					if(!rs.isBeforeFirst())
					{
						jtable = new JTable();
					}
					jtable = new JTable(buildTable(rs));

				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				jtable.setBounds(10, 89, 434, 183);
				contentPane.add(jtable);
				jtable.updateUI();
			}
		});
//		btnSelect.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent evt) {
//				String table = textField.getText();
//				String where = textField_1.getText();
//				rs = jdbc.SelectData(table, where);
//		try {
//			if(!rs.isBeforeFirst())
//			{
//				table_1 = new JTable();
//			}
//			table_1 = new JTable(buildTable(rs));
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		table_1.setBounds(10, 95, 434, 161);
//		contentPane.add(table_1);
//	}


//			private void NextActionPerformed(ActionEvent evt) {
//				String table = textField.getText();
//				String where = textField_1.getText();
//				rs = jdbc.SelectData(table, where);
//				try {
//					if(!rs.isBeforeFirst())
//					{
//						table_1 = new JTable();
//					}
//					table_1 = new JTable(buildTable(rs));
//					
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				table_1.setBounds(10, 95, 434, 161);
//				contentPane.add(table_1);
//			}
//
//		});
		
		
		btnSelect.setBounds(323, 11, 89, 23);
		contentPane.add(btnSelect);
		
//		jtable = new JTable();
//		jtable.setBounds(10, 89, 434, 183);
//		contentPane.add(jtable);
	


	}

	private DefaultTableModel buildTable(ResultSet rs2) throws SQLException {
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
}
