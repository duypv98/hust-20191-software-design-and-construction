package vn.edu.hust.soict.afc;

import java.awt.EventQueue;

import vn.edu.hust.soict.afc.boundaries.MainGUI;

/**
 * AFC Application
 * @author duycv
 * @date Dec 6, 2019
 * @project AFC System
 * @lecturer Nguyen Thi Thu Trang
 * @class 111589
 */
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
