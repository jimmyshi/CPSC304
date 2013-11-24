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


public class IdentifyPage extends JFrame {

	private JPanel contentPane;
	private JTextField table;
	private JTextField input;
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
					IdentifyPage frame = new IdentifyPage();
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
	public IdentifyPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		input = new JTextField();
		input.setBounds(99, 12, 325, 20);
		contentPane.add(input);
		input.setColumns(10);
		
		JLabel lblValues = new JLabel("16s Sequence:");
		lblValues.setBounds(10, 15, 93, 14);
		contentPane.add(lblValues);
		
		JButton btnDelete = new JButton("Find");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputq = input.getText();
				rs = jdbc.JoinData(false, "G_name", "Genus G, Contains C",
						"C.sixteens_sequence = " + inputq + " AND " + "C.speciecat = G.cat");
				try {
					if(!rs.isBeforeFirst())
					{
						jtable = new JTable();
					}
					else
					{
						jtable = new JTable(buildTable(rs));
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
			}
		});
		btnDelete.setBounds(10, 43, 92, 20);
		contentPane.add(btnDelete);
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
