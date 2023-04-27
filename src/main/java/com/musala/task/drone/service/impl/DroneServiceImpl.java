package com.musala.task.drone.service.impl;

import com.musala.task.drone.entity.Drone;
import com.musala.task.drone.entity.DroneBattery;
import com.musala.task.drone.entity.DroneModel;
import com.musala.task.drone.exception.custom.NotFoundException;
import com.musala.task.drone.mapper.DroneMapper;
import com.musala.task.drone.repositories.DroneRepository;
import com.musala.task.drone.service.DroneService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return  droneMapper.toDronesModel(droneRepository.findAllByState(DroneModel.StateEnum.IDLE ));
    }

}
