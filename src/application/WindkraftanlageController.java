/*
 * Created on:	19.06.2020
 * Author: 		Johannes Buchberger
 *
 * This class implements the GUI elements and sets the onAction-methods for the Buttons "Start" and "Stop".
 *
 * Integrated Methods:
 * 		- buttonStart(): Set "startstop" = "on" and print in "startStop.csv"; Call class "Refresh"
 * 		- buttonStop(): Set "startstop" = "off" and print in "startStop.csv"
 * 		- BladeAngle(): Set the correct value for the BladeAngle (0�,30�,90�)
 * 		- WindAngle(): Set the correct value for the WindAngle (0� - 359�)
 * 		- RotorOrientation(): Set the correct value for the RotorOrientation (N,O,S,W)
 * 		- WindSpeed(): Set the correct value for the WindSpeed (0m/s - 30m/s)
 * 		- progressWindSpeed(): implement the progressbar to visualize the value of WindSpeed
 * 		- Power(): Set the correct Color to visualize the generated Power (red, yellow, green)
 * 		- labelPower(): Set the term for the generated Power (keine Leistung, unter Nennleistung, Nennleistung)
 *
*/

package application;

import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WindkraftanlageController {
	WindkraftData d = new WindkraftData();
	String startstop = "off";

	@FXML
	public void buttonStart() throws IOException, InterruptedException {
		startstop = "on";

		PrintWriter datei = new PrintWriter("startStop.csv");
		datei.printf(startstop);
		datei.close();

		Refresh r = new Refresh(this);
		r.start();

	}

	@FXML
	public void buttonStop() throws IOException {
		startstop = "off";

		PrintWriter datei = new PrintWriter("startStop.csv");
		datei.printf(startstop);
		datei.close();

	}

	@FXML
	Label labelBladeAngle;

	public void BladeAngle() {
		if (d.BladeAngle.equals("0")) {
			labelBladeAngle.setText("0�");
		} else if (d.BladeAngle.equals("1")) {
			labelBladeAngle.setText("30�");
		} else if (d.BladeAngle.equals("2")) {
			labelBladeAngle.setText("90�");
		}

	}

	@FXML
	Label labelWindAngle;

	public void WindAngle() {
		labelWindAngle.setText(d.WindAngle + " �");
	}

	@FXML
	Label labelRotorOrientation;

	public void RotorOrientation() {
		if (d.RotorOrientation.equals(" 0")) {
			labelRotorOrientation.setText("N");
		} else if (d.RotorOrientation.equals(" 1")) {
			labelRotorOrientation.setText("O");
		} else if (d.RotorOrientation.equals(" 2")) {
			labelRotorOrientation.setText("S");
		} else if (d.RotorOrientation.equals(" 3")) {
			labelRotorOrientation.setText("W");
		}
	}

	@FXML
	Label labelWindSpeed;

	public void WindSpeed() {
		double f = Double.valueOf(d.WindSpeed);
		labelWindSpeed.setText(f + " m/s");

	}

	@FXML
	ProgressBar progressWindSpeed;

	public void progressWindSpeed() {
		double ws = Double.valueOf(d.WindSpeed);
		progressWindSpeed.setProgress(ws / 30.0);

	}

	@FXML
	Circle circlePower;

	public void Power() {
		if (d.Power.equals(" 1")) {
			circlePower.setFill(Color.YELLOW);
		} else if (d.Power.equals(" 2")) {
			circlePower.setFill(Color.LIMEGREEN);
		} else if (d.Power.equals(" 0")) {
			circlePower.setFill(Color.RED);
		}
	}

	@FXML
	Label labelPower;

	public void labelPower() {
		if (d.Power.equals(" 1")) {
			labelPower.setText("unter\nNennleistung");
		} else if (d.Power.equals(" 2")) {
			labelPower.setText("Nennleistung");
		} else if (d.Power.equals(" 0")) {
			labelPower.setText("keine\nLeistung");
		}
	}
}
