/*
 * HmiController.c
 *
 *  Created on: 14.06.2020
 *      Author: Anna Kaiser
 */

#include "../HmiController.h"


void writeToCsv(enum BladeAngle currentBladeAngle, enum Power currentPower, enum RotorOrientation currentRotorOrientation, int WindAngle, double WindSpeed){
	//This function writes currentBladeAnlge, currentRotorOrientation, currentPower, WindAngle and WindSpeed to a file called sensorData.csv
	//in order to communicate with the GUI.
	//In sensorData.csv appear the following values:
	//BladeAngle: 0= Zero, 1=Thirty, 2= Ninety
	//RotorOrientation: 0=North, 1=East, 2=South, 3=West
	//Power: 0=None, 1=Little, 2=Full
	//WindAngle: corresponding integer-Value
	//WindSpeed: corresponding double-Value

		char path[] = "sensorData.csv";
		FILE *csvTarget = fopen(path, "w");

		if (csvTarget == NULL){
			printf("%s konnte nicht zum Schreiben geöffnet werden.\n", path);
		}

		fprintf(csvTarget,"BladeAngle; RotorOrientation; Power; WindAngle; WindSpeed\n");
		fprintf(csvTarget,"%d; %d; %d; %d; %f;",currentBladeAngle,currentRotorOrientation,currentPower,WindAngle,WindSpeed);
		printf("BladeAngle; RotorOrientation; Power; WindAngle; WindSpeed\n");
		printf("%d; %d; %d; %d; %f;\n",currentBladeAngle,currentRotorOrientation,currentPower,WindAngle,WindSpeed);
		fclose(csvTarget);

}

enum OnOffStatus readFromCsv(){
	//This function reads from a csv file called startStop.csv. This File is created and updated by the GUI.
	//The currentStatus changes according to the content to "on" or "off"
	//Possible content:
	//0: The user wants to switch off regulation of windturbine.
	//1: The user wants to switch on regulation of windturbine.
	char path[] = "startStop.csv";
	FILE *csvTarget = fopen(path, "r");

	if (csvTarget == NULL){
				printf("%s konnte nicht zum Lesen geöffnet werden.\n", path);
			}

	enum OnOffStatus currentStatus;
	int status;
	fscanf(csvTarget, "%d", &status);

	if (status == 1){
		currentStatus = On;
	}
	else {
		currentStatus = Off;
	}
	fclose(csvTarget);
	return currentStatus;
}


char* getPathFromUser(char path[]){
	//This function reads the content of the csv-file "pathFromUser.csv". The file contains a path, where the user wants to save
	//the measured Data to.

		FILE *csvTarget = fopen(path, "r");
		char *pathFromUser=(char*)malloc(200*sizeof(char));
		fscanf(csvTarget,"%s",pathFromUser);
		fclose(csvTarget);
		return pathFromUser;

}

bool saveData(char path[]){
	//if the user wants to save the measurement data, this function will return 1. Otherwise 0.

		FILE *csvTarget = fopen(path, "r");

			if (csvTarget == NULL){	//the file just exists if the user wants to save the measurement data.
				fclose(csvTarget);
						return 0;
					}
			else{
				fclose(csvTarget);
				return 1;
			}
}

void writeMeasurementDataToCsv(int windAngle, double windSpeed, char path[]){
	//this function writes current windAngle and windSpeed to a csv file which name and location was
	//defined by the user in the GUI

	char* pathFromUser = getPathFromUser(path);	//get the path to the csv file, that the user chose to store the Data
	FILE *csvTarget = fopen(pathFromUser, "a");

	if (csvTarget == NULL){
		printf("%s konnte nicht zum Schreiben geöffnet werden.\n", pathFromUser);
	}

	char time[25];
	getTimeStamp(time);


	printf("%s:	 %d°; %f m/s\n", time, windAngle, windSpeed);
	fprintf(csvTarget,"%s:	 %d°; %f m/s\n", time, windAngle, windSpeed);
	fclose(csvTarget);
}


void getTimeStamp(char currentTime[25]){
	//this function creates a timestamp in this form: Wed Jun 24 13:10:31 2020

		time_t t;
		time(&t);
		char *s = ctime(&t);

		for(int i = 0; i < 24; i++){
			currentTime[i] = s[i];
		}
		currentTime[24] = 0;
}

void writeHeadline(char path[]){
	//this function writes adds a Headline to the csv File for saving measurements

	char* pathFromUser = getPathFromUser(path);	//get the path to the csv file, that the user chose to store the Data
		FILE *csvTarget = fopen(pathFromUser, "w");

		if (csvTarget == NULL){
			printf("%s konnte nicht zum Schreiben geöffnet werden.\n", pathFromUser);
		}
		fprintf(csvTarget,"TimeStamp:\t\t\tWindangle; Windspeed\n");
		fclose(csvTarget);

}
