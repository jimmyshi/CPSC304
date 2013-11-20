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
import javax.swing.JCheckBox;


public class CreateViewPage extends JFrame {

	private JPanel contentPane;
	private JTextField Name;
	private JTextField From;
	private JTextField Select;
	private JLabel lblWhere;
	private JTextField Where;
	private JDBC jdbc = new JDBC();
	private ResultSet rs;
	private JTable jtable;
	private JCheckBox duplicate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateViewPage frame = new CreateViewPage();
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
	public CreateViewPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Name = new JTextField();
		Name.setBounds(59, 12, 243, 20);
		contentPane.add(Name);
		Name.setColumns(10);
		
		From = new JTextField();
		From.setBounds(59, 81, 365, 20);
		contentPane.add(From);
		From.setColumns(10);
		
		JLabel lblValues = new JLabel("From:");
		lblValues.setBounds(10, 84, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Name:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		JButton btnCreateViewPage = new JButton("Create View");
		btnCreateViewPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = Name.getText();
				String from = From.getText();
				String select = Select.getText();
				String where = Where.getText();
				
				rs = jdbc.ViewData(duplicate.isSelected(),name, select, from, where);
				
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
				// TODO Auto-generated method stub
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
		});
		btnCreateViewPage.setBounds(310, 11, 114, 23);
		contentPane.add(btnCreateViewPage);
		
		JLabel lblSelect = new JLabel("Select:");
		lblSelect.setBounds(10, 54, 46, 14);
		contentPane.add(lblSelect);
		
		Select = new JTextField();
		Select.setBounds(59, 49, 365, 20);
		contentPane.add(Select);
		Select.setColumns(10);
		
		lblWhere = new JLabel("Where:");
		lblWhere.setBounds(10, 110, 46, 14);
		contentPane.add(lblWhere);
		
		Where = new JTextField();
		Where.setBounds(59, 113, 365, 20);
		contentPane.add(Where);
		Where.setColumns(10);
		
		duplicate = new JCheckBox("Exclude Duplicates");
		duplicate.setBounds(6, 146, 320, 23);
		contentPane.add(duplicate);
	}
}
