package application;

import java.io.FileNotFoundException;

import javafx.application.Platform;

public class Refresh extends Thread {
	public WindkraftanlageController c;

	public Refresh(WindkraftanlageController c) {
		this.c = c;
	}

	public void run() {

	        while (c.startstop.equals("on")) {

	            Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	        			try {
							c.d.scan();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
	        			c.BladeAngle();
	        			c.RotorOrientation();
	        			c.Power();
	        			c.WindAngle();
	        			c.WindSpeed();
	        			c.labelPower();
	        			c.progressWindSpeed();
	                }
	            });

	            try {
					sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        }

	}

}
