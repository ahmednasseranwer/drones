package com.musala.task.drone.mapper;

import com.musala.task.drone.entity.*;
import com.musala.task.drone.model.DroneBattery;
import com.musala.task.drone.model.DroneModel;
import com.musala.task.drone.model.MedicationItemRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DroneMapper {

     Drone toDroneEntity(DroneModel droneModel);

     List<DroneModel> toDronesModel(List<Drone> drones);
     DroneBattery toDroneBattery(Drone drone);
    List<Medication> toMedicationItems(List<MedicationItemRequest> medicationItemRequestList);

    List<MedicationItemRequest> toMedicationItemsModel(List<Medication> medicationList);
}
