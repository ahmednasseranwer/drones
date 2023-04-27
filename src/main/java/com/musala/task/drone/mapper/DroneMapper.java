package com.musala.task.drone.mapper;

import com.musala.task.drone.entity.Drone;
import com.musala.task.drone.entity.DroneBattery;
import com.musala.task.drone.entity.DroneModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DroneMapper {

     Drone toDroneEntity(DroneModel droneModel);

     List<DroneModel> toDronesModel(List<Drone> drones);
     DroneBattery toDroneBattery(Drone drone);

}
