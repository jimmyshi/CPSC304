import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class SelectPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnReturn;

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
		textField_1.setBounds(47, 41, 266, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnReturn = new JButton("Return");
		btnReturn.setBounds(323, 40, 89, 23);
		contentPane.add(btnReturn);
		
		JLabel lblValues = new JLabel("Where:");
		lblValues.setBounds(10, 43, 46, 14);
		contentPane.add(lblValues);
		
		JLabel lblTable = new JLabel("Table:");
		lblTable.setBounds(10, 15, 46, 14);
		contentPane.add(lblTable);
		
		JButton btnInsert = new JButton("Select");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInsert.setBounds(323, 11, 89, 23);
		contentPane.add(btnInsert);
	}
}
