package Java.gui;

import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame {
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		JButton btn1 = new JButton("ボタン");
		add(btn1);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("クリック");
			}
		});
	}

	public static void main(String[] args) {
		Gui frm = new Gui();
		frm.setVisible(true);
	}
}