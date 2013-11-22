import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;


public class JoinPage extends JFrame {

	private JPanel contentPane;
	private JTextField select;
	private JTextField from;
	private JLabel lblWhere;
	private JTextField where;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private JTable jtable;
	private JCheckBox duplictae;
	String tablename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinPage frame = new JoinPage();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		select = new JTextField();
		select.setBounds(10, 24, 402, 20);
		select.setText("e.g p.p_sequence_length, p2.sixteens_sequence");
		contentPane.add(select);
		select.setColumns(10);
		
		from = new JTextField();
		from.setBounds(10, 71, 402, 20);
		from.setText("e.g Protein p, Produces p2");
		contentPane.add(from);
		from.setColumns(10);
		
		JLabel lblValues = new JLabel("What tables you want the values from?");
		lblValues.setBounds(10, 55, 402, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("What you want to know?");
		lblTable.setBounds(10, 11, 402, 14);
		contentPane.add(lblTable);
		
		String[] tableList ={
				"",
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
		
		JButton btnJoin = new JButton("Find");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectq = select.getText();
				String fromq = from.getText();
				String whereq = where.getText();
				rs = jdbc.JoinData(duplictae.isSelected(),selectq, fromq, whereq);
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
		btnJoin.setBounds(308, 140, 104, 30);
		contentPane.add(btnJoin);
		
		lblWhere = new JLabel("What requirements do you want from the values?");
		lblWhere.setBounds(10, 102, 352, 14);
		contentPane.add(lblWhere);
		
		where = new JTextField();
		where.setBounds(10, 117, 402, 20);
		where.setText("e.g. p.sequence_length = 12");
		contentPane.add(where);
		where.setColumns(10);
		
		duplictae = new JCheckBox("Exclude Duplicates");
		duplictae.setBounds(6, 144, 158, 23);
		contentPane.add(duplictae);
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
