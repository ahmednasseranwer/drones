package com.musala.task.drone.exception;


import com.musala.task.drone.model.ErrorApiResponse;

public class ErrorCodes {

    private ErrorCodes() {
        throw new IllegalStateException("ErrorCodes class");
    }
    public static final ErrorApiResponse INVALID_BATTERY_25 = new ErrorApiResponse(1001, "drone should be above or equal 25%");
    public static final ErrorApiResponse INVALID_DRONE_WEIGHT_CAN_CARRY = new ErrorApiResponse(1002, "medication items more weight that it can carry");

}
