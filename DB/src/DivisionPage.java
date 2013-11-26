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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JLabel;


public class DivisionPage extends JFrame {

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
					DivisionPage frame = new DivisionPage();
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
	public DivisionPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 110);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		input = new JTextField();
		input.setBounds(10, 18, 414, 20);
		contentPane.add(input);
		input.setColumns(10);
		
		JLabel lblValues = new JLabel("Enter D Nucleotide Sequence:");
		lblValues.setBounds(10, 0, 179, 14);
		contentPane.add(lblValues);
		
		JButton btnDelete = new JButton("Find");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inputq = input.getText();
				
				Pattern p = Pattern.compile("[A-Z]*");
				Matcher m = p.matcher(inputq);
				boolean b = m.matches();
				
				if(!b)
				{
					JOptionPane.showMessageDialog(null, "Your input value must be capitalized letters.","Wrong Input Detected", JOptionPane.ERROR_MESSAGE);
				}else{
		
				rs = jdbc.DivisionQuery(input.getText());
				try {
					if(!rs.isBeforeFirst())
					{
						if(inputq.isEmpty())
						{
							//JOptionPane.showMessageDialog(null, "No 16s Sequence entered");
							JOptionPane.showMessageDialog(null, "No D Nucleotide Sequence entered", "Error Detected", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "The entered D Nucleotide Sequence does not exisit", "16s sequence not found", JOptionPane.ERROR_MESSAGE);
						}
						
					}
					else
					{
						jtable = new JTable(buildTable(rs));
						//JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
						JOptionPane.showMessageDialog(null, new JScrollPane(jtable), "Resutls", JOptionPane.PLAIN_MESSAGE);
					}
				}
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}}
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
