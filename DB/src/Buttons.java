import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Buttons extends JFrame {

	public Buttons(){
		

		JButton insert = new JButton("Insert");
		JButton delete = new JButton("Delete");
		JButton compare = new JButton("Compare");

		JButton test = new JButton("Test");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		
		
		insert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Buttons.this, "Insert something in SQL");
			}
		});
		
		delete.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Buttons.this, "Delete something in SQL");
			}
		});
		
		compare.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Buttons.this, "Compare something in SQL");
			}
		});
		//getContentPane().add(panel);
		panel.add(insert, BorderLayout.NORTH);
		panel.add(delete, BorderLayout.CENTER);
		panel.add(compare, BorderLayout.SOUTH);
		getContentPane().add(panel2);
		panel2.add(panel, BorderLayout.NORTH);
		panel2.add(test, BorderLayout.CENTER);
	}
}

