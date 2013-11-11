import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public static void main(String[] args) {

		
		
		JFrame f = new JFrame();
		f.setLayout(new BorderLayout());
		Buttons buttons = new Buttons();
		buttons.pack();
		buttons.setVisible(true);
//		f.getContentPane().add(buttons);
//		f.setSize(200,200);
//		f.setVisible(true);

	}
}

