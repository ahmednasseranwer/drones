package com.musala.task.drone.service;

import com.musala.task.drone.model.DroneBattery;
import com.musala.task.drone.model.DroneModel;
import com.musala.task.drone.model.MedicationItemRequest;

import java.util.List;

public interface DroneService {
    Long register(DroneModel droneModel);

    DroneBattery getBatteryByDroneId(Long droneId);

    List<DroneModel> findIdleDrones();

    void addMedicationItems(Long droneId, List<MedicationItemRequest> medicationItemRequestList);

    List<MedicationItemRequest> getMedicationItems(Long droneId);
}
