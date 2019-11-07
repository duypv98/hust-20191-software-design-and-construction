package vn.edu.hust.soict.afc;

import java.awt.EventQueue;

import vn.edu.hust.soict.afc.controllers.MainController;

public class AFCApplication {

	public static void main(String[] args) {
		final MainController mainController = new MainController();
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainController.mainFrame.setVisible(true);
			}
		});
	}

}
