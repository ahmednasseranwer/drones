package com.musala.task.drone.entity.converter;

import com.musala.task.drone.model.DroneModel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.musala.task.drone.model.DroneModel.ModelEnum.*;

@Converter
public class ModelAttributeConverter implements AttributeConverter<DroneModel.ModelEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DroneModel.ModelEnum modelEnum) {
        if (modelEnum == null)
            return null;

        switch (modelEnum) {
            case LIGHTWEIGHT:
                return 1;
            case MIDDLEWEIGHT:
                return 2;
            case CRUISERWEIGHT:
                return 3;
            case HEAVYWEIGHT:
                return 4;
            default:
                throw new IllegalArgumentException(modelEnum + " not supported.");
        }
    }

    @Override
    public DroneModel.ModelEnum convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return LIGHTWEIGHT;
            case 2:
                return MIDDLEWEIGHT;
            case 3:
                return CRUISERWEIGHT;
            case 4:
                return HEAVYWEIGHT;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}