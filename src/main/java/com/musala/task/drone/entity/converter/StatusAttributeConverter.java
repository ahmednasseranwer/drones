package com.musala.task.drone.entity.converter;


import com.musala.task.drone.model.DroneModel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.musala.task.drone.model.DroneModel.StateEnum.*;


@Converter
public class StatusAttributeConverter implements AttributeConverter<DroneModel.StateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DroneModel.StateEnum statusEnum) {
        if (statusEnum == null)
            return null;
        switch (statusEnum) {
            case IDLE:
                return 1;
            case LOADING:
                return 2;
            case LOADED:
                return 3;
            case DELIVERING:
                return 4;
            case DELIVERED:
                return 5;
            case RETURNING:
                return 6;
            default:
                throw new IllegalArgumentException(statusEnum + " not supported.");
        }
    }

    @Override
    public DroneModel.StateEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return IDLE;
            case 2:
                return LOADING;
            case 3:
                return LOADED;
            case 4:
                return DELIVERING;
            case 5:
                return DELIVERED;
            case 6:
                return RETURNING;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}