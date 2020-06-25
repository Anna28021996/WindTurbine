/*
 * Created on:	19.06.2020
 * Author: 		Johannes Buchberger
 *
 * This class calls the methods in class "WindkraftController" to update the data into the GUI in background.
 *
*/

package application;

import java.io.FileNotFoundException;

import javafx.application.Platform;

public class Refresh extends Thread {

	// attributs
	public WindkraftanlageController controller;


	// constructor
	public Refresh(WindkraftanlageController controller) {
		this.controller = controller;
	}



	public void run() {

		// Condition to update the GUI continuously (startstop = 1)
		while (controller.startstop.equals("1")) {

			// function to update the GUI out of the Thread
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {

						// call "scan()" in class "WindkraftData" to extract the data out of "sensorData.csv"
						controller.windkraftdata.scan();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					// call methods in  class "WindkraftanlageController" to update the GUI
					controller.BladeAngle();
					controller.RotorOrientation();
					controller.Power();
					controller.WindAngle();
					controller.WindSpeed();
					controller.labelPower();
					controller.progressWindSpeed();
				}
			});

			try {
				// wait for 0.5s
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
