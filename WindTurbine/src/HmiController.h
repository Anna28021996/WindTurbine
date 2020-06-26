/*
 * HmiController.h
 *
 *  Created on: 14.06.2020
 *  Author: Anna Kaiser
 */

#ifndef HMICONTROLLER_H_
#define HMICONTROLLER_H_
/*
 * HmiController.h
 *
 *  Created on: 14.06.2020
 *      Author: Anna Kaiser
 */
#include "WindTurbine.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>


enum OnOffStatus{On,Off};

void writeToCsv(enum BladeAngle currentBladeAngle, enum Power currentPower, enum RotorOrientation currentRotorOrientation, int WindAngle, double WindSpeed);
enum OnOffStatus readFromCsv();
void getPathFromUser(char path[], char pathFromUser[]);
bool saveData(char path[]);
void writeMeasurementDataToCsv(int windAngle, double windSpeed, char path[]);
void getTimeStamp(char time[25]);
void writeHeadline(char path[]);


#endif /* HMICONTROLLER_H_ */
