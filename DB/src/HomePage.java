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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(17, 6, 90, 29);
		panel.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(119, 6, 90, 29);
		panel.add(btnDelete);
		
		JButton btnJoin = new JButton("Join");
		btnJoin.setBounds(221, 6, 90, 29);
		panel.add(btnJoin);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(323, 6, 90, 29);
		panel.add(btnSelect);
		
		btnInsert.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                InsertPage myProg = new InsertPage();
                myProg.setSize(450, 400);
                myProg.setLocationRelativeTo(null);
                myProg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                myProg.setVisible(true);
            }
        });
		
		btnDelete.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                DeletePage myProg = new DeletePage();
                myProg.setSize(450, 400);
                myProg.setLocationRelativeTo(null);
                myProg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                myProg.setVisible(true);
            }
        });
		
		btnJoin.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                JoinPage myProg = new JoinPage();
                myProg.setSize(450, 400);
                myProg.setLocationRelativeTo(null);
                myProg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                myProg.setVisible(true);
            }
        });
		
		btnSelect.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextActionPerformed(evt);
            }

            private void NextActionPerformed(ActionEvent evt) {
                SelectPage myProg = new SelectPage();
                myProg.setSize(450, 400);
                myProg.setLocationRelativeTo(null);
                myProg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                myProg.setVisible(true);
            }
        });
		
		
	}
}
