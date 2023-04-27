package com.musala.task.drone.fixture;

import com.musala.task.drone.entity.Drone;
import com.musala.task.drone.entity.Medication;
import com.musala.task.drone.model.DroneModel;

import java.util.ArrayList;
import java.util.List;

public class DroneFixture {

    public static Drone createdDrone(){
        Drone drone = new Drone();
        drone.setState(DroneModel.StateEnum.IDLE);
        drone.setWeightLimit(100);
        drone.setId(11L);
        drone.setBattery(100);
        drone.setModel(DroneModel.ModelEnum.LIGHTWEIGHT);
        drone.setSerialNumber("hbsdfkjsgfjhkgasf");
        Medication medication = new Medication();
        medication.setId(123123);
        medication.setCode("FFF");
        medication.setWeight(2);
        medication.setName("NAME");
        drone.setMedicationList(List.of(medication));
        return drone;
    }

    public static List<Drone> createdDrones(){
      List<Drone> drones = new ArrayList<>();
      drones.add(createdDrone());
      return drones;
    }

    public static Drone createdDroneWithTENBattery(){
      Drone drone =  createdDrone();
      drone.setBattery(10);
      return drone;
    }

    public static Drone createdDroneWithOneWeight(){
        Drone drone =  createdDrone();
        drone.setWeightLimit(1);
        return drone;
    }

    public static DroneModel createdDroneModel(){
        DroneModel droneModel = new DroneModel();
        droneModel.setModel(DroneModel.ModelEnum.LIGHTWEIGHT);
        droneModel.setState(DroneModel.StateEnum.IDLE);
        droneModel.setSerialNumber("asdasfasf");
        droneModel.setWeightLimit(100);
        return droneModel;
    }
}
