/*
 * WindSensors.c
 *
 *  Created on: 14.06.2020
 *  Author: Anna Kaiser
 */

#include "../WindSensors.h"


int get_WindAngle(int currentAngle){
	//returns measured WindAngle in the range 0 to 359 degrees.
	//the maximum change to the previous measurement is +-10 degrees.

	int nextAngle=currentAngle+randomNumber();
	if (nextAngle>359){
		nextAngle=nextAngle-360;
	}
	else if(nextAngle<0){
		nextAngle=nextAngle+360;
	}
	return nextAngle;
}


double get_WindSpeed(double currentSpeed){
	//returns measured WindSpeed in the range 0 to 30 m/s
	//the maximum change to the previous measurement is +-5 m/s.

	double nextSpeed=currentSpeed+randomNumber()*0.5;
	if (nextSpeed<0.0){
		nextSpeed=nextSpeed*(-1);
	}
	if(nextSpeed>30.0){
		nextSpeed=currentSpeed-abs(randomNumber()*0.5);
	}
	return nextSpeed;
}


int randomNumber(){
	//creates random numbers between -10 and 10 except 0

	int random= rand();
	int sign;
	if (random%2 == 0){
		sign = 1;
	}
	else {
		sign = -1;
	}
	return (((random%10)+1)*sign);
}
