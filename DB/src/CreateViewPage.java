import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class CreateViewPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblWhere;
	private JTextField textField_3;

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
		setBounds(100, 100, 450, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(59, 12, 249, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(59, 81, 371, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblValues = new JLabel("From:");
		lblValues.setBounds(10, 84, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Name:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		JButton btnCreateViewPage = new JButton("Create View");
		btnCreateViewPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreateViewPage.setBounds(320, 11, 110, 23);
		contentPane.add(btnCreateViewPage);
		
		JLabel lblSelect = new JLabel("Select:");
		lblSelect.setBounds(10, 54, 46, 14);
		contentPane.add(lblSelect);
		
		textField_2 = new JTextField();
		textField_2.setBounds(59, 49, 371, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblWhere = new JLabel("Where:");
		lblWhere.setBounds(10, 110, 46, 14);
		contentPane.add(lblWhere);
		
		textField_3 = new JTextField();
		textField_3.setBounds(59, 113, 365, 97);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
