/*
 * WindTurbine.c
 *
 *  Created on: 15.06.2020
 *  Author: Anna Kaiser
 */

#include "../WindTurbine.h"


enum BladeAngle turnBlade(double WindSpeed){
	//this function returns the current BladeAngle depending on the WindSpeed

	enum BladeAngle nextAngle;
	if (WindSpeed <= 4.0 || WindSpeed>=25.0){
		nextAngle = Ninety;
	}
	else if(WindSpeed > 4.0 && WindSpeed <= 12.0){
		nextAngle = Zero;
	}
	else{
		nextAngle = Thirty;
	}
	return nextAngle;
}

enum RotorOrientation turnRotor(int WindAngle){
	//this function returns the current RotorOrientation depending on the WindAngle

	enum RotorOrientation nextOrientation;
	if (WindAngle >= 315 || WindAngle < 45){
		nextOrientation = North;
	}
	else if (WindAngle >= 45 && WindAngle < 135){
		nextOrientation = East;
	}
	else if (WindAngle >= 135 && WindAngle < 315){
		nextOrientation = South;
	}
	else{
		nextOrientation = West;
	}
	return nextOrientation;
}

enum Power get_Power(enum BladeAngle currentAngle){
	//this function returns the current Powerstatus depending on the BladeAngle
	enum Power nextPower;
	switch(currentAngle){
	case Zero: nextPower = Little; break;
	case Thirty: nextPower = Full; break;
	case Ninety: nextPower = None; break;
	}
	return nextPower;
}
