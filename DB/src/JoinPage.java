import java.awt.EventQueue;

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


public class JoinPage extends JFrame {

	private JPanel contentPane;
	private JTextField select;
	private JTextField from;
	private JLabel lblWhere;
	private JTextField where;
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
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		select = new JTextField();
		select.setBounds(77, 12, 266, 20);
		contentPane.add(select);
		select.setColumns(10);
		
		from = new JTextField();
		from.setBounds(77, 40, 353, 20);
		contentPane.add(from);
		from.setColumns(10);
		
		JLabel lblValues = new JLabel("From:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Select:");
		lblTable.setBounds(10, 15, 55, 14);
		contentPane.add(lblTable);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selectq = select.getText();
				String fromq = from.getText();
				String whereq = where.getText();
				rs = jdbc.JoinData(selectq, fromq, whereq);
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
		btnJoin.setBounds(355, 12, 89, 23);
		contentPane.add(btnJoin);
		
		lblWhere = new JLabel("Where:");
		lblWhere.setBounds(10, 72, 46, 14);
		contentPane.add(lblWhere);
		
		where = new JTextField();
		where.setBounds(77, 72, 353, 120);
		contentPane.add(where);
		where.setColumns(10);
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
