/*
 * Created on:	19.06.2020
 * Author: 		Johannes Buchberger
 *
 * This class implements the GUI elements and sets the onAction-methods for the Buttons "Start" and "Stop".
 *
 * Integrated Methods:
 * 		- buttonStart(): Set "startstop" = "1" and print in "startStop.csv"; Call class "Refresh"
 * 		- buttonStop(): Set "startstop" = "0" and print in "startStop.csv"; reset GUI; delete "path.csv"
 * 		- BladeAngle(): Set the correct value for the BladeAngle (0°,30°,90°)
 * 		- WindAngle(): Set the correct value for the WindAngle (0° - 359°)
 * 		- RotorOrientation(): Set the correct value for the RotorOrientation (N,O,S,W)
 * 		- WindSpeed(): Set the correct value for the WindSpeed (0m/s - 30m/s)
 * 		- progressWindSpeed(): implement the progressbar to visualize the value of WindSpeed
 * 		- Power(): Set the correct Color to visualize the generated Power (red, yellow, green)
 * 		- labelPower(): Set the term for the generated Power (keine Leistung, unter Nennleistung, Nennleistung)
 * 		- save(): Open dialog-window to choose the location for saving the data and write the path into "path.csv"
 *
*/

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class WindkraftanlageController {

	//attributs
	WindkraftData windkraftdata = new WindkraftData();
	String startstop;



	//methods to update the GUI
	@FXML
	public void buttonStart() throws IOException, InterruptedException {
		startstop = "1";

		PrintWriter datei = new PrintWriter("startStop.csv");
		datei.printf(startstop);
		datei.close();

		Refresh r = new Refresh(this);
		r.start();

	}

	@FXML
	public void buttonStop() throws IOException {
		startstop = "0";

		PrintWriter datei = new PrintWriter("startStop.csv");
		datei.printf(startstop);
		datei.close();

		File pathCsv = new File("path.csv");
		pathCsv.delete();

		labelBladeAngle.setText("");
		labelWindAngle.setText("");
		labelRotorOrientation.setText("");
		labelWindSpeed.setText("");
		progressWindSpeed.setProgress(0.0);
		circlePower.setFill(Color.WHITE);
		labelPower.setText("");
		saveBox.setSelected(false);
	}

	@FXML
	Label labelBladeAngle;

	public void BladeAngle() {
		if (windkraftdata.BladeAngle.equals("0")) {
			labelBladeAngle.setText("0°");
		} else if (windkraftdata.BladeAngle.equals("1")) {
			labelBladeAngle.setText("30°");
		} else if (windkraftdata.BladeAngle.equals("2")) {
			labelBladeAngle.setText("90°");
		}

	}

	@FXML
	Label labelWindAngle;

	public void WindAngle() {
		labelWindAngle.setText(windkraftdata.WindAngle + " °");
	}

	@FXML
	Label labelRotorOrientation;

	public void RotorOrientation() {
		if (windkraftdata.RotorOrientation.equals(" 0")) {
			labelRotorOrientation.setText("N");
		} else if (windkraftdata.RotorOrientation.equals(" 1")) {
			labelRotorOrientation.setText("O");
		} else if (windkraftdata.RotorOrientation.equals(" 2")) {
			labelRotorOrientation.setText("S");
		} else if (windkraftdata.RotorOrientation.equals(" 3")) {
			labelRotorOrientation.setText("W");
		}
	}

	@FXML
	Label labelWindSpeed;

	public void WindSpeed() {
		double f = Double.valueOf(windkraftdata.WindSpeed);
		labelWindSpeed.setText(f + " m/s");

	}

	@FXML
	ProgressBar progressWindSpeed;

	public void progressWindSpeed() {
		double ws = Double.valueOf(windkraftdata.WindSpeed);
		progressWindSpeed.setProgress(ws / 30.0);
	}

	@FXML
	Circle circlePower;

	public void Power() {
		if (windkraftdata.Power.equals(" 1")) {
			circlePower.setFill(Color.YELLOW);
		} else if (windkraftdata.Power.equals(" 2")) {
			circlePower.setFill(Color.LIMEGREEN);
		} else if (windkraftdata.Power.equals(" 0")) {
			circlePower.setFill(Color.RED);
		}
	}

	@FXML
	Label labelPower;

	public void labelPower() {
		if (windkraftdata.Power.equals(" 1")) {
			labelPower.setText("unter\nNennleistung");
		} else if (windkraftdata.Power.equals(" 2")) {
			labelPower.setText("Nennleistung");
		} else if (windkraftdata.Power.equals(" 0")) {
			labelPower.setText("keine\nLeistung");
		}
	}

	@FXML
	CheckBox saveBox;
	public void save() throws FileNotFoundException {

		// If saveBox is selected, save Path in csv
		if (saveBox.isSelected() == true) {

			// Open dialog-window
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showDialog(null,"Speichern");

			// If button "Speichern" is pressed, write in "path.csv"
			if (result == JFileChooser.APPROVE_OPTION) {
				File path = chooser.getSelectedFile();
				PrintWriter writePath  = new PrintWriter("path.csv");
				writePath.print(path);
				writePath.close();

			// If button "Abbrechen" is pressed, set saveBox to "false"
			} else if (result == JFileChooser.CANCEL_OPTION) {
				saveBox.setSelected(false);
			}


		// If saveBox is not selected, delete "path.csv"
		} else if (saveBox.isSelected() == false) {
			File pathCsv = new File("path.csv");
			pathCsv.delete();
		}

	}


}
