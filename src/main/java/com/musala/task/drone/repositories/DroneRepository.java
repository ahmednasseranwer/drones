package com.musala.task.drone.repositories;

import com.musala.task.drone.entity.Drone;
import com.musala.task.drone.entity.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Long> {
    List<Drone> findAllByState(DroneModel.StateEnum state);
}
