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

public class JoinPage extends JFrame {

	private JPanel contentPane;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private ResultSet mergeRS;
	private JTable jtable;
	private JTable jtable2;
	String[] tableList;
	String[] columnList1;
	String[] columnList2;
	String tablename;
	String tablename2;
	String columnName1;
	String columnName2;
	JScrollPane scrollpane;
	JScrollPane scrollpane2;
	JScrollPane scrollpane3;
	String selectedValue;
	private ResultSet tableRS;
	private ResultSet viewRS;
	private ArrayList rsList = new ArrayList();
	private ArrayList<String> tableColumn = new ArrayList<String>();
	private ArrayList<String> tableColumn1 = new ArrayList<String>();
	private ArrayList<String> tableColumn2 = new ArrayList<String>();
	String selectedString;
	String selectedString2;
	String newtablename;
	private JComboBox comboBox3;
	private JComboBox comboBox4;
	private JLabel lblNewLabel;
	private JLabel lblChooseAColumn;
	private JTextField textField;
	private JLabel lblCommon;
	static JoinPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JoinPage();
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
	public JoinPage() {
		// newtablename = "";
		rsList = new ArrayList();
		tableRS = jdbc.GetAllTableNames();
		try {
			convertTablenames(tableRS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		viewRS = jdbc.GetAllViewNames();
		try {
			convertTablenames(viewRS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		convertToString(rsList);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 613);
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
		comboBox3 = new JComboBox(columnList1);
		comboBox3.setBounds(10, 432, 367, 27);
		columnName1 = (String) comboBox3.getSelectedItem();
		comboBox3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				columnName1 = (String) comboBox3.getSelectedItem();
				System.out.println(columnName1);
			}

		});

		contentPane.add(comboBox3);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// scrollpane.setBounds(10, 41, 414, 164);
				contentPane.remove(comboBox3);
				tablename = (String) comboBox.getSelectedItem();
				createScrollTable();
				comboBox3 = new JComboBox(columnList1);
				columnName1 = (String) comboBox3.getSelectedItem();
				// comboBox3.updateUI();
				// contentPane.updateUI();
				comboBox3.setBounds(10, 432, 367, 27);
				comboBox3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						columnName1 = (String) comboBox3.getSelectedItem();
						System.out.println(columnName1);
					}

				});

				contentPane.add(comboBox3);
				contentPane.updateUI();
			}
		});

		comboBox.setBounds(6, 10, 367, 27);
		contentPane.add(comboBox);

		final JComboBox comboBox2 = new JComboBox(tableList);
		tablename2 = (String) comboBox.getSelectedItem();
		createScrollTable2();
		comboBox4 = new JComboBox(columnList2);
		comboBox4.setBounds(391, 432, 367, 27);
		columnName2 = (String) comboBox4.getSelectedItem();
		comboBox4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				columnName2 = (String) comboBox4.getSelectedItem();
				System.out.println(columnName2);

			}

		});
		contentPane.add(comboBox4);
		comboBox2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// scrollpane2.setBounds(10, 41, 414, 164);
				contentPane.remove(comboBox4);
				tablename2 = (String) comboBox2.getSelectedItem();
				createScrollTable2();
				comboBox4 = new JComboBox(columnList2);
				// comboBox4.updateUI();
				// contentPane.updateUI();
				columnName2 = (String) comboBox4.getSelectedItem();
				comboBox4.setBounds(391, 432, 367, 27);
				comboBox4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						columnName2 = (String) comboBox4.getSelectedItem();
						System.out.println(columnName2);

					}

				});
				contentPane.add(comboBox4);
				contentPane.updateUI();
			}
		});

		comboBox2.setBounds(395, 10, 367, 27);
		contentPane.add(comboBox2);

		JButton mergeButton = new JButton("Create New Table");
		mergeButton.setBounds(241, 521, 279, 28);
		contentPane.add(mergeButton);

		lblNewLabel = new JLabel("Choose a column");
		lblNewLabel.setBounds(81, 404, 162, 16);
		contentPane.add(lblNewLabel);

		lblChooseAColumn = new JLabel("Choose a column");
		lblChooseAColumn.setBounds(513, 403, 123, 16);
		contentPane.add(lblChooseAColumn);

		textField = new JTextField();
		textField.setBounds(150, 470, 608, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		lblCommon = new JLabel("Common Variable:");
		lblCommon.setBounds(20, 476, 118, 16);
		contentPane.add(lblCommon);

		mergeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String select = "a." + columnName1 + " ," + " b." + columnName2;
				String from = tablename + " a, " + tablename2 + " b";
				String where = "a." + textField.getText() + "=" + "b."
						+ textField.getText();

				mergeRS = jdbc.JoinData(false, select, from, where);
				try {
					if (!mergeRS.isBeforeFirst()) {
						jtable = new JTable();
					}
					jtable = new JTable(buildTable(mergeRS));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
			}

		});
	}

	protected void createScrollTableJoin() {
		// TODO Auto-generated method stub

	}

	protected void createNewPage() {

		JButton Refresh = new JButton("Go back");
		Refresh.setBounds(246, 440, 279, 28);
		Refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				JoinPage frame = new JoinPage();
				frame.setVisible(true);

			}

		});
		contentPane.add(Refresh);
		//
		// scrollpane3 = new JScrollPane();
		// scrollpane3.setBounds(10, 41, 358, 700);
		// // tablename = newtablename;
		// try {
		// if (!mergeRS.isBeforeFirst()) {
		// jtable3 = new JTable();
		// }
		// jtable3 = new JTable(buildTable(mergeRS));
		// } catch (SQLException e1) {
		// e1.printStackTrace();
		// }
		// jtable3.updateUI();
		// scrollpane.setViewportView(jtable3);
		// scrollpane.updateUI();
		// jtable3.setRowSelectionAllowed(true);
		// jtable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// contentPane.add(scrollpane3);

	}

	private void convertTablenames(ResultSet RS2) throws SQLException {
		// TODO Auto-generated method stub
		ResultSetMetaData metaData = RS2.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (RS2.next()) {
			for (int i = 1; i <= columnCount; i++) {
				String value = RS2.getString(i);
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
		tableColumn1.clear();
		try {
			if (!rs.isBeforeFirst()) {
				jtable = new JTable();
			}
			jtable = new JTable(buildTable(rs));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tableColumn1 = tableColumn;
		columnList1 = new String[tableColumn.size()];
		columnList1 = tableColumn.toArray(columnList1);

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
		tableColumn2.clear();
		rs = jdbc.SelectData(tablename2, "");
		try {
			if (!rs.isBeforeFirst()) {
				jtable2 = new JTable();
			}
			jtable2 = new JTable(buildTable(rs));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		tableColumn2 = tableColumn;
		columnList2 = new String[tableColumn2.size()];
		columnList2 = tableColumn2.toArray(columnList2);

		// final JComboBox comboBox4 = new JComboBox(columnList2);
		// comboBox4.setBounds(395, 400, 367, 27);
		// contentPane.add(comboBox4);

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
		tableColumn.clear();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
			tableColumn.add(metaData.getColumnName(column));
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
