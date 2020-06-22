/*
 * Created on:	19.06.2020
 * Author: 		Johannes Buchberger
 *
 * This class defines the type "WindkraftData" and imports the data from "sensorData.csv".
 *
*/

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WindkraftData {

	// Attribute
	public String BladeAngle;
	public String RotorOrientation;
	public String Power;
	public String WindAngle;
	public String WindSpeed;

	// Methoden
	void scan() throws FileNotFoundException {
		File sensorData = new File("sensorData.csv");
		Scanner data = new Scanner(sensorData);
		String line = data.nextLine();
		line = data.nextLine();
		data.close();

		String[] split = line.split(";");

		BladeAngle = split[0];
		RotorOrientation = split[1];
		Power = split[2];
		WindAngle = split[3];
		WindSpeed = split[4];

	}
}
