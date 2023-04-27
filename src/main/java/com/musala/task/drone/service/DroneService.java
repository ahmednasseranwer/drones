package com.musala.task.drone.service;

import com.musala.task.drone.entity.DroneBattery;
import com.musala.task.drone.entity.DroneModel;

import java.util.List;

public interface DroneService {
    Long register(DroneModel droneModel);

    DroneBattery getBatteryByDroneId(Long droneId);

    List<DroneModel> findIdleDrones();

}
