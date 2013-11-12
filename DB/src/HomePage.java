import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.CardLayout;


public class HomePage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setTitle("Home");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setTitle(String Title){
		
	}
	/**
	 * Create the frame.
	 */
	public HomePage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 488, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(20, 6, 120, 30);
		panel.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(183, 6, 120, 30);
		panel.add(btnDelete);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.setBounds(344, 6, 120, 30);
		panel.add(btnJoin);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(20, 47, 120, 30);
		panel.add(btnSelect);
		
		JButton btnView = new JButton("View");
		btnView.setBounds(183, 47, 120, 30);
		panel.add(btnView);
		
		JButton btnGroup = new JButton("Group");
		btnGroup.setBounds(344, 47, 120, 30);
		panel.add(btnGroup);
		
		btnInsert.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                InsertPage newpage = new InsertPage();
                newpage.setTitle("Insert");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                DeletePage newpage = new DeletePage();
                newpage.setTitle("Delete");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnJoin.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                JoinPage newpage = new JoinPage();
                newpage.setTitle("Join");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnSelect.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                Groups newpage = new Groups();
                newpage.setTitle("Select");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnView.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                CreateViewPage newpage = new CreateViewPage();
                newpage.setTitle("Select");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
		btnGroup.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                Groups newpage = new Groups();
                newpage.setTitle("Group");
                newpage.setSize(450, 400);
                newpage.setLocationRelativeTo(null);
                newpage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                newpage.setVisible(true);
            }
        });
		
	}
}
