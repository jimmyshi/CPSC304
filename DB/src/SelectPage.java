import java.awt.EventQueue;

import javax.swing.Icon;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.JComboBox;

public class SelectPage extends JFrame {

	private JPanel contentPane;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private ResultSet tableRS;
	private JTable jtable;
	String tablename;
	JScrollPane scrollpane;
	String selectedValue;
	private JTextField textField1;
	private JTextField textField2;
	private JPanel panel2;
	private int sval;
	String[] tableList;
	// String[] tableList = { "DNA", "Coding_region", "Contains",
	// "Interacting_Stimuli", "Large_Ribosomal_Subunit", "mRNA",
	// "Produces", "Protein", "Regulatory_Proteins", "RNA", "rRNA",
	// "Small_Ribosomal_Subunit", "tRNA", };
	private ArrayList rsList = new ArrayList();
	// rsList[CODING_REGION,PROTEIN,PRODUCES,SMALL_RIBOSOMALSUBUNIT,LARGE_RIBOSOMAL_SUBUNIT,CONTAINS,DNA,REGULATORY_PROTEINS,INTERACTING_STIMULI,RNA_POLYMERASE,MRNA,TRNA,RRNA]
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	int panelHeight = 320;
	String selectedString;
	private String firstColumnName;

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
		tableRS = jdbc.GetAllTableNames();
		try {
			convertTablenames(tableRS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		convertToString(rsList);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);

		panel2 = new JPanel();
		panel2.setBounds(20, 247, 400, 193);
		contentPane.add(panel2);
		panel2.setLayout(null);

		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 41, 414, 164);
		contentPane.add(scrollpane);

		final JComboBox comboBox = new JComboBox(tableList);
		tablename = (String) comboBox.getSelectedItem();
		createScrollTable();
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollpane.setBounds(10, 41, 414, 164);
				tablename = (String) comboBox.getSelectedItem();
				for (int i = 0; i < tableList.length - 1; i++) {
					if (tablename == tableList[i]) {
						if (i <= 12) {
							sval = i;
							break;
						}
						if (i <= tableList.length - 1) {
							sval = 13;
							break;
						}
					}
				}
				switch (sval) {
				case 0:
					coding_region();
					break;
				case 1:
					protein();
					break;
				case 2:
					produces();
					break;
				case 3:
					small_ribosomal_subunit();
					break;
				case 4:
					large_ribosomal_subunit();
					break;
				case 5:
					contains();
					break;
				case 6:
					dna();
					break;
				case 7:
					regulatory_proteins();
					break;
				case 8:
					interacting_stimuli();
					break;
				case 9:
					rna_polymerase();
					break;
				case 10:
					mrna();
					break;
				case 11:
					trna();
					break;
				case 12:
					rrna();
					break;
				case 13:
					noaddtable();
					break;
				}
				createScrollTable();
			}

		});

		comboBox.setBounds(57, 10, 367, 27);
		contentPane.add(comboBox);

		JButton btnDelete = new JButton("Delete Selected");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedString == null) {
					JOptionPane.showMessageDialog(null,
							"You have not selected any row to delete",
							"No Selection", JOptionPane.ERROR_MESSAGE);
				} else {
					System.out.println(selectedString + "in" + firstColumnName
							+ " is going to be deleted from " + tablename);
					int selectedOption = JOptionPane
							.showConfirmDialog(
									null,
									"You are about to delete "
											+ selectedString
											+ " in " + tablename + ", do you want to continue?",
									"Confirm Deletion",
									JOptionPane.YES_NO_OPTION);
					if (selectedOption==JOptionPane.YES_OPTION){
					rs = jdbc.DeleteData(tablename, firstColumnName,
							selectedString);
					createScrollTable();
					}
					if (selectedOption==JOptionPane.NO_OPTION){
						System.out.println(selectedString + "did not get deleted");
					}

				}

				// JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
				// JOptionPane optionpane = new JOptionPane();
				// optionpane.showMessageDialog(null, new JScrollPane(jtable));
				// optionpane.setMe
				// JButton jButton = new getButton(optionPane, "Delete");
				// optionpane.setOptions(new Object [] {jButton});

			}
		});

		btnDelete.setBounds(89, 205, 257, 30);
		contentPane.add(btnDelete);

		JPanel panel = new JPanel();
		panel.setBounds(414, 302, -397, -53);
		contentPane.add(panel);
		coding_region();

		// panel2.removeAll();
		// textField1 = new JTextField();
		// textField1.setBounds(10, 75, 375, 30);
		// panel2.add(textField1);
		// textField1.setColumns(10);
		//
		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);
		//
		// JLabel lblDnucleotidesequence = new JLabel("D NUCLEOTIDE SEQUENCE:");
		// lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		// panel2.add(lblDnucleotidesequence);
		//
		// JLabel lblNewLabel = new JLabel("D SEQUENCE LENGTH:");
		// lblNewLabel.setBounds(10, 125, 200, 20);
		// panel2.add(lblNewLabel);
		//
		// JButton btnInsert = new JButton("Enter Following Values to Add DNA");
		// btnInsert.setBounds(10, 10, 375, 30);
		// btnInsert.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// tablename = "DNA";
		// String value1 = textField1.getText();
		// String value2 = textField2.getText();
		// rs = jdbc.InsertData2(tablename, value1, value2);
		// System.out.println(value1 + " and " + value2 + " is added to "
		// + tablename);
		// createScrollTable();
		// textField1.setText("");
		// textField2.setText("");
		// }
		// });
		// panel2.add(btnInsert);

	}

	private void dna() {
		// TODO Auto-generated method stub
		setBounds(100, 100, 450, 460);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("D NUCLEOTIDE SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("D SEQUENCE LENGTH:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JButton btnInsert = new JButton("Enter Following Values to Add DNA:");
		btnInsert.setBounds(10, 10, 375, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablename = "DNA";
				String value1 = textField1.getText();
				String value2 = textField2.getText();
				rs = jdbc.InsertData2(tablename, value1, value2);
				System.out.println(value1 + " and " + value2 + " is added to "
						+ tablename);
				createScrollTable();
				textField1.setText("");
				textField2.setText("");
			}
		});
		panel2.add(btnInsert);

	}

	protected void coding_region() {
		setBounds(100, 100, 450, 460);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("D NUCLEOTIDE SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("CORRESPONDING PROTEIN:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add Coding region");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();
	}

	protected void contains() {
		// TODO Auto-generated method stub
		setBounds(100, 100, 450, 460);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("D NUCLEOTIDE SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("D SEQUENCE LENGTH:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add Contains:");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();

	}

	protected void interacting_stimuli() {
		setBounds(100, 100, 450, 460);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("DESCRIPTION OF STIMULI:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("AA SEQUENCE:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add Interacting Stimuli");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();

	}

	protected void large_ribosomal_subunit() {
		setBounds(100, 100, 450, 390);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("PROTEIN RRNA STRUCTURE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add Large Ribosomal Subunit");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();

	}

	protected void mrna() {
		setBounds(100, 100, 450, 730);
		panel2.setBounds(20, 250, 405, 450);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel newLabel1 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("PROTEIN RRNA STRUCTURE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to mRNA");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(10, 375, 375, 30);
		panel2.add(textField5);

		label1 = new JLabel("AA SEQUENCE:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

		label3 = new JLabel("R SEQUENCE LENGTH:");
		label3.setBounds(10, 350, 200, 20);
		panel2.add(label3);

	}

	protected void produces() {
		setBounds(100, 100, 450, 460);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("AA SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("SIXTEENS SEQUENCE:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to Produces");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();

	}

	protected void protein() {
		setBounds(100, 100, 450, 620);
		panel2.setBounds(20, 250, 405, 340);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel newLabel1 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("PROTEIN RRNA STRUCTURE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to mRNA");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		label1 = new JLabel("AA SEQUENCE:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

	}

	protected void regulatory_proteins() {
		setBounds(100, 100, 450, 730);
		panel2.setBounds(20, 250, 405, 450);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel newLabel1 = new JLabel("AA SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("D NUCLEOTIDE SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to Regulatory Proteins");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(10, 375, 375, 30);
		panel2.add(textField5);

		label1 = new JLabel("CORRESPONDING PROTEIN:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("REG NAME:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

		label3 = new JLabel("DESCRIPTION OF STIMULI:");
		label3.setBounds(10, 350, 200, 20);
		panel2.add(label3);

	}

	protected void rna_polymerase() {
		// TODO Auto-generated method stub

	}

	protected void rrna() {
		setBounds(100, 100, 450, 620);
		panel2.setBounds(20, 250, 405, 340);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel newLabel1 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to rRNA");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		label1 = new JLabel("PROTEIN RRNA STRUCTURE:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("R SEQUENCE LENGTH:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

	}

	protected void small_ribosomal_subunit() {
		setBounds(100, 100, 450, 390);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("SIXTEENS SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add Small Ribosomal Subunit");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);
		panel2.updateUI();

	}

	protected void trna() {
		setBounds(100, 100, 450, 730);
		panel2.setBounds(20, 250, 405, 450);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		JLabel newLabel1 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnNewButton = new JButton(
				"Enter Following Values to Add to Regulatory Proteins");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		textField5 = new JTextField();
		textField5.setColumns(10);
		textField5.setBounds(10, 375, 375, 30);
		panel2.add(textField5);

		label1 = new JLabel("PROTEIN RRNA STRUCTURE:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("ANTI CONDON:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

		label3 = new JLabel("R SEQUENCE LENGTH:");
		label3.setBounds(10, 350, 200, 20);
		panel2.add(label3);

	}

	private DefaultTableModel buildTable(ResultSet rs2) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		firstColumnName = metaData.getColumnName(1);

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		// setBounds(100, 100, 450, panelHeight + columnCount*70);

		return new DefaultTableModel(data, columnNames);
	}

	public void createScrollTable() {
		panel2.updateUI();
		String where = "";
		rs = jdbc.SelectData(tablename, where);
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
		ListSelectionModel jtableSelectedModel = jtable.getSelectionModel();
		jtableSelectedModel.clearSelection();
		selectedString = null;

		jtableSelectedModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedindex = jtable.getSelectedRow();
						selectedString = (String) jtable.getModel().getValueAt(
								selectedindex, 0);
					}

				});
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

	private void noaddtable() {
		// TODO Auto-generated method stub
		panel2.removeAll();
		panel2.setBounds(20, 393, 400, 50);
		setBounds(100, 100, 450, 460);
		scrollpane.setBounds(10, 41, 414, 350);
		JLabel noaddlabel = new JLabel(
				"This table does not allow adding or deleting");
		noaddlabel.setBounds(55, 6, 340, 40);
		panel2.add(noaddlabel);

	}
}
