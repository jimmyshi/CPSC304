import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JComboBox;

public class SelectPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private JTable jtable;
	String tablename;

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
		setBounds(100, 100, 450, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setBounds(57, 41, 367, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblValues = new JLabel("Where:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);

		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		String[] tableList ={
				"",
				"DNA",
				"Coding_region",
				"Contains",
				"Interacting_Stimuli",
				"Large_Ribosomal_Subunit",
				"mRNA",
				"Produces",
				"Protein",
				"Regulatory_Proteins",
				"RNA",
				"rRNA",
				"Small_Ribosomal_Subunit",
				"tRNA",
		};

		final JComboBox comboBox = new JComboBox(tableList);
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				tablename = (String) comboBox.getSelectedItem();
				System.out.println(tablename);
			}
			
		});
		
		comboBox.setBounds(57, 10, 251, 27);
		contentPane.add(comboBox);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String where = textField_1.getText();
				rs = jdbc.SelectData(tablename, where);
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
				JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
			}
		});
		
		btnSelect.setBounds(320, 7, 104, 30);
		contentPane.add(btnSelect);
		

//		
//		final JComboBox comboBox = new JComboBox(tableList);
//		comboBox.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				table = (String) comboBox.getSelectedItem();
//				System.out.println(table);
//			}
//			
//		});
//		
//		comboBox.setBounds(57, 10, 251, 27);
//		contentPane.add(comboBox);
//		


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
