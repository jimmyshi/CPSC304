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

import javax.swing.JTextField;
import javax.swing.JLabel;


public class Groups extends JFrame {

	private JPanel contentPane;
	private JTextField Select;
	private JTextField From;
	private JTextField Where;
	private JTextField GroupBy;
	private JTextField Having;
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
					Groups frame = new Groups();
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
	public Groups() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Select = new JTextField();
		Select.setBounds(68, 12, 242, 20);
		contentPane.add(Select);
		Select.setColumns(10);
		
		From = new JTextField();
		From.setBounds(68, 44, 365, 20);
		contentPane.add(From);
		From.setColumns(10);
		
		JLabel lblValues = new JLabel("From:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Select:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		JButton btnGroupSelect = new JButton("Group Select");
		btnGroupSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String select = Select.getText();
				String from = From.getText();
				String where = Where.getText();
				String groupby = GroupBy.getText();
				String having = Having.getText();
				
				rs = jdbc.GroupData(select, from, where, groupby, having);
				
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
		btnGroupSelect.setBounds(322, 12, 111, 23);
		contentPane.add(btnGroupSelect);
		
		JLabel lblWhere = new JLabel("Where:");
		lblWhere.setBounds(10, 72, 46, 14);
		contentPane.add(lblWhere);
		
		JLabel lblGroupBy = new JLabel("Group by:");
		lblGroupBy.setBounds(10, 253, 78, 14);
		contentPane.add(lblGroupBy);
		
		JLabel lblHaving = new JLabel("Having:");
		lblHaving.setBounds(10, 279, 56, 14);
		contentPane.add(lblHaving);
		
		Where = new JTextField();
		Where.setBounds(10, 95, 423, 143);
		contentPane.add(Where);
		Where.setColumns(10);
		
		GroupBy = new JTextField();
		GroupBy.setBounds(78, 250, 355, 20);
		contentPane.add(GroupBy);
		GroupBy.setColumns(10);
		
		Having = new JTextField();
		Having.setBounds(10, 293, 423, 105);
		contentPane.add(Having);
		Having.setColumns(10);
	}
}
