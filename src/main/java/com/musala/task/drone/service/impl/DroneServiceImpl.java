package com.musala.task.drone.service.impl;

import com.musala.task.drone.entity.*;
import com.musala.task.drone.model.DroneBattery;
import com.musala.task.drone.model.DroneModel;
import com.musala.task.drone.model.MedicationItemRequest;
import com.musala.task.drone.exception.custom.InvalidDroneBatteryException;
import com.musala.task.drone.exception.custom.InvalidDroneWeightCarryException;
import com.musala.task.drone.exception.custom.NotFoundException;
import com.musala.task.drone.mapper.DroneMapper;
import com.musala.task.drone.repositories.DroneRepository;
import com.musala.task.drone.service.DroneService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    DroneRepository droneRepository;

    private DroneMapper droneMapper = Mappers.getMapper(DroneMapper.class);

    @Override
    public Long register(DroneModel droneModel) {
        Drone drone = droneMapper.toDroneEntity(droneModel);
        return droneRepository.save(drone).getId();
    }

    @Override
    public DroneBattery getBatteryByDroneId(Long droneId) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(NotFoundException::new);
        return droneMapper.toDroneBattery(drone);
    }

    @Override
    public List<DroneModel> findIdleDrones() {
        return droneMapper.toDronesModel(droneRepository.findAllByState(DroneModel.StateEnum.IDLE));
    }

    @Override
    public void addMedicationItems(Long droneId, List<MedicationItemRequest> medicationItemRequestList) {
        Drone drone = droneRepository.findById(droneId).orElseThrow(NotFoundException::new);
        int cumulativeWeightSum = medicationItemRequestList.stream().mapToInt(MedicationItemRequest::getWeight).sum();
        validateDrone(drone, cumulativeWeightSum);
        updateDroneCarryWeightAndState(drone, cumulativeWeightSum);
        addMedicationItemsToDrone(drone, medicationItemRequestList);
        droneRepository.save(drone);
    }

    @Override
    public List<MedicationItemRequest> getMedicationItems(Long droneId) {
        List<Medication> medicationList = droneRepository.findById(droneId).orElseThrow(NotFoundException::new).getMedicationList();
        return droneMapper.toMedicationItemsModel(medicationList);
    }

    private void addMedicationItemsToDrone(Drone drone, List<MedicationItemRequest> medicationItemRequestList) {
        if (drone.getMedicationList() == null) {
            drone.setMedicationList(new ArrayList<>());
        }
        List<Medication> medicationList = droneMapper.toMedicationItems(medicationItemRequestList);
        medicationList.forEach(medication -> medication.setDrone(drone));
        drone.getMedicationList().addAll(medicationList);

    }

    private void updateDroneCarryWeightAndState(Drone drone, int cumulativeWeightSum) {
        drone.setWeightLimit(drone.getWeightLimit() - cumulativeWeightSum);
        drone.setState(DroneModel.StateEnum.LOADING);
    }

    private void validateDrone(Drone drone, Integer medicationItemsWeightSum) {
        validateDroneBattery(drone.getBattery());
        validateDroneCanCarryMedicationItemsWeight(drone.getWeightLimit(), medicationItemsWeightSum);
    }

    private void validateDroneCanCarryMedicationItemsWeight(Integer droneWeightLimit, Integer medicationItemsWeightSum) {
        if (medicationItemsWeightSum > droneWeightLimit) {
            throw new InvalidDroneWeightCarryException();
        }
    }

    private void validateDroneBattery(Integer battery) {
        if (battery < 25) {
            throw new InvalidDroneBatteryException();
        }
    }

}
