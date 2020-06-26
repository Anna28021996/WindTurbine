/*
 * WindTurbine.h
 *
 *  Created on: 14.06.2020
 *      Author: Anna Kaiser
 */

#ifndef WINDTURBINE_H_
#define WINDTURBINE_H_

#include <stdio.h>
#include <stdlib.h>

enum BladeAngle{Zero,Thirty,Ninety};
enum RotorOrientation{North,East,South,West};
enum Power{None, Little, Full};

//Commands for Windturbine
enum RotorOrientation turnRotor(int WindAngle);
enum BladeAngle turnBlade(double WindSpeed);
enum Power get_Power(enum BladeAngle currentAngle);

#endif /* WINDTURBINE_H_ */
