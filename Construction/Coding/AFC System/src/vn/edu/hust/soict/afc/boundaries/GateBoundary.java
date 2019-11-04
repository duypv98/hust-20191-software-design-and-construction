/**
 * 
 */
package vn.edu.hust.soict.afc.boundaries;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Professor
 *
 */
public class GateBoundary extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113549238245180885L;
	private BufferedImage gateOpenImage = null;
	private BufferedImage gateCloseImage = null;
	private JLabel displayedPicture = null;
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			displayedPicture.setIcon(new ImageIcon(gateOpenImage));
		}
	};
	
	/**
	 * 
	 */
	public GateBoundary() {
		try {
			gateOpenImage = ImageIO.read(new File("img/opengate.jpg"));
			gateCloseImage = ImageIO.read(new File("img/closegate.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setLayout(null);
		this.setBounds(445, 150, 407, 270);
		
		displayedPicture = new JLabel(new ImageIcon(gateCloseImage));
		displayedPicture.setSize(406, 350);
		
		add(displayedPicture);
	}
	
	/**
	 * 
	 */
	public void open() {
		timer.scheduleAtFixedRate(task, 0, 1000);
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				timer.cancel();
				task.cancel();
				timer = new Timer();
				task = new TimerTask() {
					
					@Override
					public void run() {
						displayedPicture.setIcon(new ImageIcon(gateOpenImage));
					}
				};
				displayedPicture.setIcon(new ImageIcon(gateCloseImage));
			}
		}, 3000);
	}
}
