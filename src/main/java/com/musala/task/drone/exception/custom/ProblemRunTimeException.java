package com.musala.task.drone.exception.custom;

import com.musala.task.drone.model.ErrorApiResponse;
import lombok.Getter;

@Getter
public class ProblemRunTimeException extends RuntimeException{

    public final ErrorApiResponse errorApiResponse;
    public ProblemRunTimeException(ErrorApiResponse errorApiResponse)
    {
        super(errorApiResponse.getMessage());
        this.errorApiResponse= errorApiResponse;
    }



}
