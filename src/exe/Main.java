package exe;

import java.awt.EventQueue;

import frames.JFrameMain;
import frames.LoginPanel;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPanel frame = new LoginPanel();
					frame.setVisible(true);

					//// Testeando sin login
//					 JFrameMain frame = new JFrameMain();
//					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
