import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

public class CreateViewPage extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField From;
	private JTextField Select;
	private JLabel lblWhere;
	private JTextField Where;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private ResultSet mergeRS;
	private JTable jtable;
	private JTable jtable2;
	private JTable jtable3;
	private JCheckBox duplicate;
	String[] tableList;
	String tablename;
	String tablename2;
	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	JScrollPane scrollpane3;
	String selectedValue;
	private ResultSet tableRS;
	private ArrayList rsList = new ArrayList();
	String selectedString;
	String selectedString2;
	private JTextField textField;
	String newtablename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateViewPage frame = new CreateViewPage();
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
	public CreateViewPage() {
		// newtablename = "";
		tableRS = jdbc.GetAllTableNames();
		try {
			convertTablenames(tableRS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		convertToString(rsList);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 41, 358, 350);
		contentPane.add(scrollpane);

		scrollpane2 = new JScrollPane();
		scrollpane2.setBounds(400, 41, 358, 350);
		contentPane.add(scrollpane2);

		final JComboBox comboBox = new JComboBox(tableList);
		tablename = (String) comboBox.getSelectedItem();
		createScrollTable();
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// scrollpane.setBounds(10, 41, 414, 164);
				tablename = (String) comboBox.getSelectedItem();
				createScrollTable();
			}
		});

		comboBox.setBounds(6, 10, 367, 27);
		contentPane.add(comboBox);

		final JComboBox comboBox2 = new JComboBox(tableList);
		tablename2 = (String) comboBox.getSelectedItem();
		createScrollTable2();
		comboBox2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// scrollpane2.setBounds(10, 41, 414, 164);
				tablename2 = (String) comboBox2.getSelectedItem();
				createScrollTable2();
			}
		});

		comboBox2.setBounds(395, 10, 367, 27);
		contentPane.add(comboBox2);

		JButton mergeButton = new JButton("Create New Table");
		mergeButton.setBounds(246, 440, 279, 28);
		contentPane.add(mergeButton);

		textField = new JTextField();
		textField.setBounds(397, 412, 128, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblEnterNewTable = new JLabel("Enter New Table Name:");
		lblEnterNewTable.setBounds(246, 418, 154, 16);
		contentPane.add(lblEnterNewTable);

		mergeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newtablename = textField.getText();
				if (!newtablename.isEmpty()) {
					jdbc.ViewData(true, newtablename, " * ", (String) comboBox.getSelectedItem() + " , "+ (String) comboBox2.getSelectedItem(), "");
					mergeRS = jdbc.SelectData(newtablename, "");
					tablename = newtablename;
					contentPane.removeAll();
					scrollpane = new JScrollPane();
					scrollpane.setBounds(10, 41, 740, 350);
					contentPane.add(scrollpane);
					createScrollTable();
					JButton Refresh = new JButton("Go back");
					Refresh.setBounds(246, 440, 279, 28);
					Refresh.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							contentPane.removeAll();
							jdbc.DropView(newtablename);
							CreateViewPage frame = new CreateViewPage();
							frame.setVisible(true);
							
						}
						
					});
					contentPane.add(Refresh);
					contentPane.updateUI();
//					createNewPage();
				} else {
					JOptionPane.showMessageDialog(null, "Enter a table name",
							"No Tablename", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	protected void createNewPage() {
		
		JButton Refresh = new JButton("Go back");
		Refresh.setBounds(246, 440, 279, 28);
		Refresh.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				CreateViewPage frame = new CreateViewPage();
				frame.setVisible(true);
				
			}
			
		});
		contentPane.add(Refresh);
//		
//		scrollpane3 = new JScrollPane();
//		scrollpane3.setBounds(10, 41, 358, 700);
////		tablename = newtablename;
//		try {
//			if (!mergeRS.isBeforeFirst()) {
//				jtable3 = new JTable();
//			}
//			jtable3 = new JTable(buildTable(mergeRS));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		jtable3.updateUI();
//		scrollpane.setViewportView(jtable3);
//		scrollpane.updateUI();
//		jtable3.setRowSelectionAllowed(true);
//		jtable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		contentPane.add(scrollpane3);

	}

	private void convertTablenames(ResultSet RS) throws SQLException {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = RS.getMetaData();
		int columnCount = metaData.getColumnCount();
		rsList = new ArrayList();
		while (tableRS.next()) {
			for (int i = 1; i <= columnCount; i++) {
				String value = tableRS.getString(i);
				rsList.add(value);
			}

		}

	}

	private void convertToString(ArrayList rsList2) {
		// TODO Auto-generated method stub
		tableList = new String[rsList2.size()];
		tableList = (String[]) rsList2.toArray(tableList);

	}

	public void createScrollTable() {
		rs = jdbc.SelectData(tablename, "");
		try {
			if (!rs.isBeforeFirst()) {
				jtable = new JTable();
			}
			jtable = new JTable(buildTable(rs));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		jtable.updateUI();
		scrollpane.setViewportView(jtable);
		jtable.setRowSelectionAllowed(true);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ListSelectionModel jtableSelectedModel = jtable.getSelectionModel();
		// jtableSelectedModel.clearSelection();
		// selectedString = null;

		// jtableSelectedModel
		// .addListSelectionListener(new ListSelectionListener() {
		//
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		// int selectedindex = jtable.getSelectedRow();
		// selectedString = (String) jtable.getModel().getValueAt(
		// selectedindex, 0);
		// }
		//
		// });
	}

	public void createScrollTable2() {
		String where = "";
		rs = jdbc.SelectData(tablename2, where);
		try {
			if (!rs.isBeforeFirst()) {
				jtable2 = new JTable();
			}
			jtable2 = new JTable(buildTable(rs));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		jtable2.updateUI();
		scrollpane2.setViewportView(jtable2);
		jtable2.setRowSelectionAllowed(true);
		// jtable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ListSelectionModel jtableSelectedModel = jtable2.getSelectionModel();
		// jtableSelectedModel.clearSelection();
		// selectedString2 = null;

		// jtableSelectedModel
		// .addListSelectionListener(new ListSelectionListener() {
		//
		// @Override
		// public void valueChanged(ListSelectionEvent e) {
		// int selectedindex = jtable2.getSelectedRow();
		// selectedString2 = (String) jtable2.getModel().getValueAt(
		// selectedindex, 0);
		// }
		//
		// });
	}

	private DefaultTableModel buildTable(ResultSet rs2) throws SQLException {
		ResultSetMetaData metaData = rs2.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		// firstColumnName = metaData.getColumnName(1);

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs2.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs2.getObject(columnIndex));
			}
			data.add(vector);
		}

		// setBounds(100, 100, 450, panelHeight + columnCount*70);

		return new DefaultTableModel(data, columnNames);
	}
}
