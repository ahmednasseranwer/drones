package com.musala.task.drone.controller;

import com.musala.task.drone.api.DroneApi;
import com.musala.task.drone.entity.DroneBattery;
import com.musala.task.drone.entity.DroneModel;
import com.musala.task.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class DroneController implements DroneApi {

    @Autowired
    private DroneService droneService;

    /**
     * POST /drone : register drone
     * register drone
     *
     * @param droneModel  (required)
     * @return successful operation (status code 200)
     *         or Bad Request (status code 400)
     */

    @PostMapping(value = "/drone")
    @Override
    public ResponseEntity<Void> _registerDrone(@Valid @RequestBody DroneModel droneModel) {
        Long droneId = droneService.register(droneModel);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("droneId", droneId.toString());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{droneId}/")
                .buildAndExpand(droneId)
                .toUri();

        return  ResponseEntity.created(location).headers(responseHeaders).build();
    }

    /**
     * GET /drone/{droneId}/battery : return drone battery
     * returns all drone battery
     *
     * @param droneId drone id (required)
     * @return successful operation (status code 200)
     *         or Bad Request (status code 400)
     */

    @GetMapping(value = "/drone/{droneId}/battery")
    @Override
    public ResponseEntity<DroneBattery> _checkBattery(@PathVariable("droneId") Long droneId) {
        return new ResponseEntity<>(droneService.getBatteryByDroneId(droneId), HttpStatus.OK);
    }


    /**
     * GET /drone/status/idle : return all idle drones
     * get all drones with idle status
     *
     * @return successful operation (status code 200)
     *         or Bad Request (status code 400)
     */

    @GetMapping(value = "/drone/status/idle")
    @Override
    public ResponseEntity<List<DroneModel>> _idleDrone() {
        return new ResponseEntity<>(droneService.findIdleDrones(), HttpStatus.OK);

    }

}
