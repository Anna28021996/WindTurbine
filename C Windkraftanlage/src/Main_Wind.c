/*
 ============================================================================
 Name        : WindTurbine.c
 Author      : Anna Kaiser
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <windows.h>
#include "WindTurbine.h"
#include "WindSensors.h"
#include "HmiController.h"
#include <string.h>


int main(void) {

	// Eclipse-Konsolen BUG: Ausgabepuffer stdout/stderr auf 0 setzen
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);

	//Initialize variables
	int windAngle=0;
	double windSpeed=12.5;
	enum OnOffStatus currentStatus = Off;
	char path[] = "path.csv";



	while (1){
		currentStatus = readFromCsv();	//update currentStatus

		if (currentStatus == On){	//if User has switched on the regulation of the windturbine in the GUI

			//get new WindAngle and windSpeed
			windAngle= get_WindAngle(windAngle);
			windSpeed  = get_WindSpeed(windSpeed);

			//Update currentBladeAngle, currentRotorOrientation, currentPower
			enum BladeAngle currentBladeAngle = turnBlade(windSpeed);
			enum RotorOrientation currentRotorOrientation = turnRotor(windAngle);
			enum Power currentPower = get_Power(currentBladeAngle);

			writeToCsv(currentBladeAngle, currentPower, currentRotorOrientation, windAngle, windSpeed);		//write to csv to update GUI

			if (saveData(path) == 1){		//if user wants to save measurements in csv file
				char pathFromUser[200];
				getPathFromUser(path, pathFromUser);
				if(access(pathFromUser, F_OK ) == -1){		//if the user chooses a csvFile that does not exist yet, a Headline is added to the file
					writeHeadline(pathFromUser);
				}
				writeMeasurementDataToCsv(windAngle, windSpeed, pathFromUser);	//write measured Data to the chosen csv file
			}


			Sleep(2000);
		}
	}



}
