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
	private ResultSet viewRS;
	private ResultSet valueRS;
	private JTable jtable;
	String tablename;
	JScrollPane scrollpane;
	String selectedValue;
	private JTextField textField1;
	private JTextField textField2;
	private JPanel panel2;
	private int sval;
	String[] tableList;
	String[] valuelist1;
	String[] pmname = { "pol i", "pol ii", "pol iii" };
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
	private JComboBox comboBox;
	static SelectPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new SelectPage();
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
		setBounds(100, 100, 450, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);

		panel2 = new JPanel();
		panel2.setBounds(20, 393, 400, 79);
		contentPane.add(panel2);
		panel2.setLayout(null);

		scrollpane = new JScrollPane();
		scrollpane.setBounds(10, 41, 414, 164);
		contentPane.add(scrollpane);

		comboBox = new JComboBox(tableList);
		tablename = (String) comboBox.getSelectedItem();
		createScrollTable();
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				scrollpane.setBounds(10, 41, 414, 164);
				tablename = (String) comboBox.getSelectedItem();
				for (int i = 0; i < tableList.length; i++) {
					if (tablename == tableList[i]) {
						if (i <= 12) {
							sval = i;
							break;
						}
						if (i <= tableList.length) {
							sval = 13;
							break;
						}
					}
				}
				switch (sval) {
				case 0:
					trna();
					break;
				case 1:
					regulatory_proteins();
					break;
				case 2:
					contained_coding_region();
					break;
				case 3:
					contains();
					break;
				case 4:
					genus();
					break;
				case 5:
					dna();
					break;
				case 6:
					interacting_stimuli();
					break;
				case 7:
					protein();
					break;
				case 8:
					produces();
					break;
				case 9:
					mrna();
					break;
				case 10:
					rna_polymerase();
					break;
				case 11:
					rrna();
					break;
				case 12:
					transcribe();
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
											+ " in "
											+ tablename
											+ ", do you want to continue? This will also delete any other value that is refering to this value",
									"Confirm Deletion",
									JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						rs = jdbc.DeleteData(tablename, firstColumnName,
								selectedString);
						createScrollTable();
					}
					if (selectedOption == JOptionPane.NO_OPTION) {
						System.out.println(selectedString
								+ "did not get deleted");
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
		trna();

	}

	protected void transcribe() {
		// 3 LABLEL + 3 BOX
		panel2.removeAll();
		setBounds(100, 100, 450, 600);
		panel2.setBounds(20, 250, 400, 290);
		textField1 = new JTextField();
		textField1.setText("01");
		textField1.setBounds(10, 75, 60, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("RNA_POLYMERASE", "AA_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("MRNA", "R_NUCLEOTIDE_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox2 = new JComboBox(valuelist1);
		newComboBox2.setBounds(10, 225, 375, 30);
		panel2.add(newComboBox2);

		// textField3 = new JTextField();
		// textField3.setBounds(10, 225, 375, 30);
		// panel2.add(textField3);
		// textField3.setColumns(10);

		JLabel newlabel1 = new JLabel("DAY:");
		newlabel1.setBounds(117, 52, 41, 20);
		panel2.add(newlabel1);

		JLabel newlabel2 = new JLabel("AA SEQUENCE");
		newlabel2.setBounds(10, 125, 200, 20);
		panel2.add(newlabel2);

		JLabel newlabel3 = new JLabel("R_NUCLEOTIDE_SEQUENCE:");
		newlabel3.setBounds(10, 200, 200, 20);
		panel2.add(newlabel3);

		textField2 = new JTextField();
		textField2.setText("01");
		textField2.setColumns(10);
		textField2.setBounds(100, 75, 60, 30);
		panel2.add(textField2);

		textField3 = new JTextField();
		textField3.setText("13");
		textField3.setColumns(10);
		textField3.setBounds(196, 75, 60, 30);
		panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setText("00");
		textField4.setColumns(10);
		textField4.setBounds(283, 75, 41, 30);
		panel2.add(textField4);

		JLabel lblMonth = new JLabel("MONTH:");
		lblMonth.setBounds(10, 52, 111, 20);
		panel2.add(lblMonth);

		JLabel lblYear = new JLabel("YEAR:");
		lblYear.setBounds(198, 54, 111, 20);
		panel2.add(lblYear);

		JLabel lblHour = new JLabel("HOUR:");
		lblHour.setBounds(289, 54, 111, 20);
		panel2.add(lblHour);

		textField5 = new JTextField();
		textField5.setText("00");
		textField5.setColumns(10);
		textField5.setBounds(333, 75, 47, 30);
		panel2.add(textField5);

		JLabel label = new JLabel(":");
		label.setBounds(323, 80, 11, 20);
		panel2.add(label);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to Transcribe");
		insertButton.setBounds(10, 10, 375, 30);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField1.getText().isEmpty()
						&& !textField2.getText().isEmpty()
						&& !textField3.getText().isEmpty()
						&& !textField4.getText().isEmpty()
						&& !textField5.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = textField2.getText();
					String value3 = textField3.getText();
					String value4 = textField4.getText();
					String value5 = textField5.getText();
					String value6 = (String) newComboBox1.getSelectedItem();
					String value7 = (String) newComboBox2.getSelectedItem();
					if (isInteger(value1)&&isInteger(value2)&&isInteger(value3)&&isInteger(value4)&&isInteger(value5)){
					String onevalue = "'" + value1+"/"+value2+"/"+value3+"at"+value4+value5 + "'" + "," + "'" + value6
							+ "'" + "," + "'" + value7 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("01");
					textField2.setText("01");
					textField3.setText("13");
					textField4.setText("00");
					textField5.setText("00");
					}
					else{
						textField1.setText("01");
						textField2.setText("01");
						textField3.setText("13");
						textField4.setText("00");
						textField5.setText("00");
						JOptionPane.showMessageDialog(null,
								"One of the value entered is not an integer",
								"Invalid Value", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					textField1.setText("01");
					textField2.setText("01");
					textField3.setText("13");
					textField4.setText("00");
					textField5.setText("00");
					JOptionPane.showMessageDialog(null,
							"Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();
	}

	protected void genus() {
		setBounds(100, 100, 450, 500);
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

		JLabel newLabel1 = new JLabel("G_NAME:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("CAT:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton btnInsert = new JButton("Enter Following Values to Add DNA:");
		btnInsert.setBounds(10, 10, 375, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value1 = textField1.getText();
				String value2 = textField2.getText();
				String onevalue = "'" + value1 + "'" + "," + "'" + value2 + "'";
				rs = jdbc.InsertData(tablename, onevalue);
				System.out.println(value1 + " and " + value2 + " is added to "
						+ tablename);
				createScrollTable();
				textField1.setText("");
				textField2.setText("");
			}
		});
		panel2.add(btnInsert);

	}

	private void dna() {
		setBounds(100, 100, 450, 430);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("D NUCLEOTIDE SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		// JLabel lblNewLabel = new JLabel("D SEQUENCE LENGTH:");
		// lblNewLabel.setBounds(10, 125, 200, 20);
		// panel2.add(lblNewLabel);

		JButton btnInsert = new JButton("Enter Following Values to Add DNA:");
		btnInsert.setBounds(10, 10, 375, 30);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value1 = textField1.getText();
				int value1length = value1.length();
				String value2 = Integer.toString(value1length);
				String onevalue = "'" + value1 + "'" + "," + "'" + value2 + "'";
				rs = jdbc.InsertData(tablename, onevalue);
				System.out.println(value1 + " and " + value2 + " is added to "
						+ tablename);
				createScrollTable();
				textField1.setText("");
			}
		});
		panel2.add(btnInsert);

	}

	protected void contained_coding_region() {
		// 3 LABLEL + 3 BOX
		panel2.removeAll();
		setBounds(100, 100, 450, 600);
		panel2.setBounds(20, 250, 400, 290);
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("DNA", "D_NUCLEOTIDE_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("PROTEIN", "AA_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox2 = new JComboBox(valuelist1);
		newComboBox2.setBounds(10, 225, 375, 30);
		panel2.add(newComboBox2);

		// textField3 = new JTextField();
		// textField3.setBounds(10, 225, 375, 30);
		// panel2.add(textField3);
		// textField3.setColumns(10);

		JLabel newlabel1 = new JLabel("CR NUCLEOTIDE SEQUENCE:");
		newlabel1.setBounds(10, 50, 200, 20);
		panel2.add(newlabel1);

		JLabel newlabel2 = new JLabel("D NUCLEOTIDE SEQUENCE");
		newlabel2.setBounds(10, 125, 200, 20);
		panel2.add(newlabel2);

		JLabel newlabel3 = new JLabel("AA SEQUENCE:");
		newlabel3.setBounds(10, 200, 200, 20);
		panel2.add(newlabel3);

		JButton insertButton = new JButton(
				"Enter Following Values to Add Contained Coding region");
		insertButton.setBounds(10, 10, 375, 30);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = (String) newComboBox1.getSelectedItem();
					String value3 = (String) newComboBox2.getSelectedItem();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Please enter a CR NUCLEOTIDE SEQUENCE",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();
	}

	protected void contains() {
		// TODO Auto-generated method stub
		panel2.removeAll();
		setBounds(100, 100, 450, 600);
		panel2.setBounds(20, 250, 400, 290);
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(10, 150, 375, 30);
		panel2.add(textField2);
		textField2.setColumns(10);

		textField3 = new JTextField();
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);
		textField3.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("SIXTEENS SEQUENCE:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("PROTEIN RRNA STRUCTURE:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		JLabel newlabel3 = new JLabel("SPECIE CAT:");
		newlabel3.setBounds(10, 200, 200, 20);
		panel2.add(newlabel3);

		JButton insertButton = new JButton(
				"Enter Following Values to Add Contains:");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()
						&& !textField2.getText().isEmpty()
						&& !textField3.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = textField2.getText();
					String value3 = textField3.getText();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();

	}

	protected void interacting_stimuli() {
		setBounds(100, 100, 450, 500);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		JLabel lblDnucleotidesequence = new JLabel("NAME OF STIMULI:");
		lblDnucleotidesequence.setBounds(10, 50, 200, 20);
		panel2.add(lblDnucleotidesequence);

		JLabel lblNewLabel = new JLabel("AA SEQUENCE:");
		lblNewLabel.setBounds(10, 125, 200, 20);
		panel2.add(lblNewLabel);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("REGULATORY_PROTEINS", "AA_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		JButton insertButton = new JButton(
				"Enter Following Values to Add Interacting Stimuli");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = (String) newComboBox1.getSelectedItem();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + " is added to "
							+ tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();

	}

	protected void large_ribosomal_subunit() {
		setBounds(100, 100, 450, 430);
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
		// 2label+2box
		setBounds(100, 100, 450, 500);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("CONTAINS", "SIXTEENS_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		JLabel newLabel1 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to mRNA");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField1.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = (String) newComboBox1.getSelectedItem();
					int value1length = value1.length();
					String value3 = Integer.toString(value1length);
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + " is added to "
							+ tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);

	}

	protected void produces() {
		setBounds(100, 100, 450, 500);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		// textField1 = new JTextField();
		// textField1.setBounds(10, 75, 375, 30);
		// panel2.add(textField1);
		// textField1.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("PROTEIN", "AA_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 75, 375, 30);
		panel2.add(newComboBox1);

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

		JButton insertButton = new JButton(
				"Enter Following Values to Add to Produces");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField2.getText().isEmpty()) {
					String value1 = (String) newComboBox1.getSelectedItem();
					String value2 = textField2.getText();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + " is added to "
							+ tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();

	}

	protected void protein() {
		// 2label+2box
		setBounds(100, 100, 450, 500);
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

		JLabel newLabel1 = new JLabel("AA SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("FUNCTION:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to Protein");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()
						&& !textField2.getText().isEmpty()) {
					String value1 = textField1.getText();
					int value1length = value1.length();
					String value2 = Integer.toString(value1length);
					String value3 = textField2.getText();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + " is added to "
							+ tablename);
					createScrollTable();
					textField1.setText("");
					textField2.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);

	}

	protected void regulatory_proteins() {
		setBounds(100, 100, 450, 660);
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

		JLabel newLabel1 = new JLabel("AA SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("D NUCLEOTIDE SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("CONTAINED_CODING_REGION",
				"CR_DN_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 225, 375, 30);
		panel2.add(newComboBox1);

		// textField3 = new JTextField();
		// textField3.setColumns(10);
		// textField3.setBounds(10, 225, 375, 30);
		// panel2.add(textField3);

		textField4 = new JTextField();
		textField4.setColumns(10);
		textField4.setBounds(10, 300, 375, 30);
		panel2.add(textField4);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to Regulatory Proteins");
		insertButton.setBounds(10, 10, 375, 30);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()
						&& !textField2.getText().isEmpty()
						&& !textField4.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = textField2.getText();
					String value3 = (String) newComboBox1.getSelectedItem();
					String value4 = textField4.getText();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'" + "," + "'"
							+ value4 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("");
					textField2.setText("");
					textField4.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing a value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);

		label1 = new JLabel("CR DN SEQUENCE:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		label2 = new JLabel("REG NAME:");
		label2.setBounds(10, 275, 200, 20);
		panel2.add(label2);

	}

	protected void rna_polymerase() {
		// 3 LABLEL + 3 BOX
		panel2.removeAll();
		setBounds(100, 100, 450, 600);
		panel2.setBounds(20, 250, 400, 290);
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		final JComboBox newComboBox1 = new JComboBox(pmname);
		newComboBox1.setBounds(10, 150, 375, 30);

		panel2.add(newComboBox1);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		// valueRS = null;
		// valueRS = jdbc.GetColumnValues("PROTEIN","AA_SEQUENCE");
		// try {
		// convertTablenames(valueRS);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// convertValueToString(rsList);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("DNA", "D_NUCLEOTIDE_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox2 = new JComboBox(valuelist1);
		newComboBox2.setBounds(10, 225, 375, 30);
		panel2.add(newComboBox2);

		// textField3 = new JTextField();
		// textField3.setBounds(10, 225, 375, 30);
		// panel2.add(textField3);
		// textField3.setColumns(10);

		JLabel newlabel1 = new JLabel("AA SEQUENCE");
		newlabel1.setBounds(10, 50, 200, 20);
		panel2.add(newlabel1);

		JLabel newlabel2 = new JLabel("PM NAME");
		newlabel2.setBounds(10, 125, 200, 20);
		panel2.add(newlabel2);

		JLabel newlabel3 = new JLabel("D NUCLEOTIDE SEQUENCE:");
		newlabel3.setBounds(10, 200, 200, 20);
		panel2.add(newlabel3);

		JButton insertButton = new JButton(
				"Enter Following Values to Add RNA Polymerase");
		insertButton.setBounds(10, 10, 375, 30);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField1.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = (String) newComboBox1.getSelectedItem();
					String value3 = (String) newComboBox2.getSelectedItem();
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Please enter a CR NUCLEOTIDE SEQUENCE",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel2.add(insertButton);
		panel2.updateUI();

	}

	protected void rrna() {
		// 2label+2box
		setBounds(100, 100, 450, 500);
		panel2.setBounds(20, 250, 400, 190);
		panel2.removeAll();
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		// textField2 = new JTextField();
		// textField2.setBounds(10, 150, 375, 30);
		// panel2.add(textField2);
		// textField2.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("CONTAINS", "SIXTEENS_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		JLabel newLabel1 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to rRNA");
		insertButton.setBounds(10, 10, 375, 30);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()) {
					String value1 = textField1.getText();
					int value1length = value1.length();
					String value2 = (String) newComboBox1.getSelectedItem();
					String value3 = Integer.toString(value1length);
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + " is added to "
							+ tablename);
					createScrollTable();
					textField1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Missing Value",
							"No Value", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel2.add(insertButton);

	}

	protected void small_ribosomal_subunit() {
		setBounds(100, 100, 450, 430);
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
		panel2.removeAll();
		setBounds(100, 100, 450, 600);
		panel2.setBounds(20, 250, 400, 290);
		textField1 = new JTextField();
		textField1.setBounds(10, 75, 375, 30);
		panel2.add(textField1);
		textField1.setColumns(10);

		valueRS = null;
		valueRS = jdbc.GetColumnValues("CONTAINS", "SIXTEENS_SEQUENCE");
		try {
			convertTablenames(valueRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		convertValueToString(rsList);
		final JComboBox newComboBox1 = new JComboBox(valuelist1);
		newComboBox1.setBounds(10, 150, 375, 30);
		panel2.add(newComboBox1);

		JLabel newLabel1 = new JLabel("R NUCLEOTIDE SEQUENCE:");
		newLabel1.setBounds(10, 50, 200, 20);
		panel2.add(newLabel1);

		JLabel newLabel2 = new JLabel("SIXTEENS SEQUENCE:");
		newLabel2.setBounds(10, 125, 200, 20);
		panel2.add(newLabel2);

		JButton insertButton = new JButton(
				"Enter Following Values to Add to TRNA");
		insertButton.setBounds(10, 10, 375, 30);
		panel2.add(insertButton);

		textField3 = new JTextField();
		textField3.setColumns(10);
		textField3.setBounds(10, 225, 375, 30);
		panel2.add(textField3);

		// textField4 = new JTextField();
		// textField4.setColumns(10);
		// textField4.setBounds(10, 300, 375, 30);
		// panel2.add(textField4);

		// textField5 = new JTextField();
		// textField5.setColumns(10);
		// textField5.setBounds(10, 375, 375, 30);
		// panel2.add(textField5);

		label1 = new JLabel("ANTI CONDON:");
		label1.setBounds(10, 200, 200, 20);
		panel2.add(label1);

		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// tablename = "CONTAINED_CODING_REGION";
				if (!textField1.getText().isEmpty()
						&& !textField3.getText().isEmpty()) {
					String value1 = textField1.getText();
					String value2 = (String) newComboBox1.getSelectedItem();
					String value3 = textField3.getText();
					int value1length = value1.length();
					String value4 = Integer.toString(value1length);
					String onevalue = "'" + value1 + "'" + "," + "'" + value2
							+ "'" + "," + "'" + value3 + "'" + "," + "'"
							+ value4 + "'";
					rs = jdbc.InsertData(tablename, onevalue);
					System.out.println(value1 + "," + value2 + "," + value3
							+ " is added to " + tablename);
					createScrollTable();
					textField1.setText("");
					textField3.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"One of the value is missing", "No Value",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// label2 = new JLabel("ANTI CONDON:");
		// label2.setBounds(10, 275, 200, 20);
		// panel2.add(label2);

		// label3 = new JLabel("R SEQUENCE LENGTH:");
		// label3.setBounds(10, 350, 200, 20);
		// panel2.add(label3);
		// panel2.updateUI();

	}

	private DefaultTableModel buildTable(ResultSet rs2) throws SQLException {
		ResultSetMetaData metaData = rs2.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		firstColumnName = metaData.getColumnName(1);

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

	private void convertTablenames(ResultSet resultset) throws SQLException {
		ResultSetMetaData metaData = resultset.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (resultset.next()) {
			for (int i = 1; i <= columnCount; i++) {
				String value = resultset.getString(i);
				rsList.add(value);
			}

		}

	}

	private void convertToString(ArrayList rsList2) {
		// TODO Auto-generated method stub
		tableList = new String[rsList2.size()];
		tableList = (String[]) rsList2.toArray(tableList);

	}

	private void convertValueToString(ArrayList rsList2) {
		// TODO Auto-generated method stub
		valuelist1 = new String[rsList2.size()];
		valuelist1 = (String[]) rsList2.toArray(valuelist1);
	}

	// private void convertColumntoList(ResultSet valueRS2) {
	// rsList = new ArrayList();
	// while (valueRS2.next()){
	// for (int i=1 ; )
	// }
	//
	//
	// }

	private void noaddtable() {
		// TODO Auto-generated method stub
		panel2.removeAll();
		panel2.setBounds(20, 393, 400, 50);
		setBounds(100, 100, 450, 500);
		scrollpane.setBounds(10, 41, 414, 350);
		
		JButton btnDropThisView = new JButton("Delete This Table");
		btnDropThisView.setBounds(91, 6, 165, 29);
		btnDropThisView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					jdbc.DropView(tablename);
					comboBox.removeItem(tablename);
					comboBox.setSelectedIndex(0);
					tablename = (String) comboBox.getSelectedItem();
					createScrollTable();
					trna();
			}
	});
		
		panel2.add(btnDropThisView);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(414, 302, -397, -53);
		contentPane.add(panel);
		JLabel noaddlabel = new JLabel(
				"This table does not allow adding or deleting values");
		noaddlabel.setBounds(57, 432, 340, 40);
		contentPane.add(noaddlabel);

	}
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
