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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;


public class Count extends JFrame {

	private JPanel contentPane;
	private JTextField Length;
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
					Count frame = new Count();
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
	public Count() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 109);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Length = new JTextField();
		Length.setBounds(121, 12, 303, 20);
		contentPane.add(Length);
		Length.setColumns(10);
		
		JLabel lblTable = new JLabel("Sequence Length:");
		lblTable.setBounds(10, 15, 112, 14);
		contentPane.add(lblTable);
		
		JButton btnGroupSelect = new JButton("Count");
		btnGroupSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String length = Length.getText();
				Pattern p = Pattern.compile("\\d*");
				Matcher m = p.matcher(length);
				boolean b = m.matches();
				
				if(!b)
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid positive integer","Non-Integer Detected", JOptionPane.ERROR_MESSAGE);
				}else{
				
				rs = jdbc.JoinData(false, "COUNT(*)", "DNA", "d_sequence_length = " + "'" + length + "'");
				
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
				//JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
				JOptionPane.showMessageDialog(null, new JScrollPane(jtable), "Results", JOptionPane.PLAIN_MESSAGE);
			}}

			private TableModel buildTable(ResultSet rs) throws SQLException{
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
				// TODO Auto-generated method stub
			}
		});
		btnGroupSelect.setBounds(10, 43, 88, 23);
		contentPane.add(btnGroupSelect);
	}
}
