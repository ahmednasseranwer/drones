package com.musala.task.drone.exception.custom;


import static com.musala.task.drone.exception.ErrorCodes.INVALID_DRONE_WEIGHT_CAN_CARRY;

public class InvalidDroneWeightCarryException extends ProblemRunTimeException{

    public InvalidDroneWeightCarryException() {
        super(INVALID_DRONE_WEIGHT_CAN_CARRY);
    }
}
