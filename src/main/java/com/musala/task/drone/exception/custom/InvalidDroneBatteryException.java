package com.musala.task.drone.exception.custom;


import static com.musala.task.drone.exception.ErrorCodes.INVALID_BATTERY_25;

public class InvalidDroneBatteryException extends ProblemRunTimeException{

    public InvalidDroneBatteryException() {
        super(INVALID_BATTERY_25);
    }
}
