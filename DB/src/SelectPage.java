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
	private JTable jtable;
	String tablename;
	JScrollPane pane;
	String selectedValue;
	private JTextField textField1;
	private JTextField textField2;
	private JPanel panel2;
	private int sval;
	String[] tableList = { "DNA", "Coding_region", "Contains",
			"Interacting_Stimuli", "Large_Ribosomal_Subunit", "mRNA",
			"Produces", "Protein", "Regulatory_Proteins", "RNA", "rRNA",
			"Small_Ribosomal_Subunit", "tRNA", };
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;

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
		setBounds(100, 100, 450, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);

		panel2 = new JPanel();
		panel2.setBounds(20, 250, 400, 190);
		contentPane.add(panel2);
		panel2.setLayout(null);

		pane = new JScrollPane(jtable);
		pane.setBounds(10, 41, 414, 164);
		contentPane.add(pane);

		final JComboBox comboBox = new JComboBox(tableList);
		tablename = (String) comboBox.getSelectedItem();
		createScrollTable();
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tablename = (String) comboBox.getSelectedItem();
				for (int i = 0; i < tableList.length ; i++) {
					if(tablename == tableList[i]){
						sval = i;
						break;
					}
					else
						sval = 0;
				}
				switch (sval) {
				case 0:
					dna();
					break;
				case 1:
					coding_region();
					break;
				case 2:
					contains();
					break;
				case 3:
					interacting_stimuli();
					break;
				case 4:
					large_ribosomal_subunit();
					break;
				case 5:
					mrna();
					break;
				case 6:
					produces();
					break;
				case 7:
					protein();
					break;
				case 8:
					regulatory_proteins();
					break;
				case 9:
					rna();
					break;
				case 10:
					rrna();
					break;
				case 11:
					small_ribosomal_subunit();
					break;
				case 12:
					trna();
					break;
			}
				createScrollTable();
			}

		});

		comboBox.setBounds(57, 10, 367, 27);
		contentPane.add(comboBox);

		JButton btnSelect = new JButton("Delete Selected");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String where = textField2.getText();
				rs = jdbc.SelectData(tablename, where);
				try {
					if (!rs.isBeforeFirst()) {
						jtable = new JTable();

					}
					jtable = new JTable(buildTable(rs));

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
				// JOptionPane optionpane = new JOptionPane();
				// optionpane.showMessageDialog(null, new JScrollPane(jtable));
				// optionpane.setMe
				// JButton jButton = new getButton(optionPane, "Delete");
				// optionpane.setOptions(new Object [] {jButton});

			}
		});

		btnSelect.setBounds(89, 205, 257, 30);
		contentPane.add(btnSelect);

		JPanel panel = new JPanel();
		panel.setBounds(414, 302, -397, -53);
		contentPane.add(panel);
		
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add DNA");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);

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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add DNA:");
		btnNewButton.setBounds(10, 10, 375, 30);
		panel2.add(btnNewButton);


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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add Coding region");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add Contains:");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add Interacting Stimuli");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add Large Ribosomal Subunit");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to mRNA");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to Produces");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to mRNA");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to Regulatory Proteins");
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

	protected void rna() {
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to rRNA");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add Small Ribosomal Subunit");
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
		
		JButton btnNewButton = new JButton("Enter Following Values to Add to Regulatory Proteins");
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
		jtable.setRowSelectionAllowed(true);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel jtableSelectedModel = jtable
				.getSelectionModel();
		jtableSelectedModel
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectedindex = jtable.getSelectedRow();
						System.out.println((String) jtable.getModel()
								.getValueAt(selectedindex, 0));
					}

				});
		pane.setViewportView(jtable);
		
	}
}
