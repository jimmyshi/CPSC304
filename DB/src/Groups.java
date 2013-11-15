import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Groups extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(47, 12, 266, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(47, 41, 365, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblValues = new JLabel("From:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Select:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		JButton btnGroupSelect = new JButton("Group Select");
		btnGroupSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGroupSelect.setBounds(319, 11, 93, 23);
		contentPane.add(btnGroupSelect);
		
		JLabel lblWhere = new JLabel("Where:");
		lblWhere.setBounds(10, 72, 46, 14);
		contentPane.add(lblWhere);
		
		JLabel lblGroupBy = new JLabel("Group:");
		lblGroupBy.setBounds(10, 97, 56, 14);
		contentPane.add(lblGroupBy);
		
		JLabel lblHaving = new JLabel("Having:");
		lblHaving.setBounds(10, 120, 46, 14);
		contentPane.add(lblHaving);
		
		textField_2 = new JTextField();
		textField_2.setBounds(47, 68, 365, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(47, 94, 365, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(47, 117, 365, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	}
}
