package vn.edu.hust.soict.afc;

import java.awt.EventQueue;

import vn.edu.hust.soict.afc.boundaries.MainGUI;

public class AFCApplication {

	public static void main(String[] args) {
		final MainGUI mainGUI = new MainGUI();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainGUI.setVisible(true);
			}
		});
	}

}
