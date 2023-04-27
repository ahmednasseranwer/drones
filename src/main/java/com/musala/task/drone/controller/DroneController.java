package com.musala.task.drone.controller;

import com.musala.task.drone.aop.LogExecutionTime;
import com.musala.task.drone.api.DroneApi;
import com.musala.task.drone.model.DroneBattery;
import com.musala.task.drone.model.DroneModel;
import com.musala.task.drone.model.MedicationItemRequest;
import com.musala.task.drone.service.DroneService;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(path = "/api")
public class DroneController implements DroneApi {

    @Autowired
    private DroneService droneService;

    /**
     * POST /drone : register drone
     * register drone
     *
     * @param droneModel (required)
     * @return successful operation (status code 200)
     * or Bad Request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/drone",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
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

        return ResponseEntity.created(location).headers(responseHeaders).build();
    }

    /**
     * GET /drone/{droneId}/battery : return drone battery
     * returns all drone battery
     *
     * @param droneId drone id (required)
     * @return successful operation (status code 200)
     * or Bad Request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/drone/{droneId}/battery",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<DroneBattery> _checkBattery(@PathVariable("droneId") Long droneId) {
        return new ResponseEntity<>(droneService.getBatteryByDroneId(droneId), HttpStatus.OK);
    }


    /**
     * GET /drone/status/idle : return all idle drones
     * get all drones with idle status
     *
     * @return successful operation (status code 200)
     * or Bad Request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/drone/status/idle",
            produces = { "application/json" })
    @Override
    public ResponseEntity<List<DroneModel>> _idleDrone() {
        return new ResponseEntity<>(droneService.findIdleDrones(), HttpStatus.OK);
    }


    /**
     * POST /drone/{droneId}/medication-items : loading drone with medication items
     * loading drone with medixcation items
     *
     * @param droneId               drone id (required)
     * @param medicationItemRequest (optional)
     * @return successful operation (status code 200)
     * or Bad Request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/drone/{droneId}/medication-items",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<Void> _loadMedicationItems(@PathVariable("droneId") Long droneId, @Valid @RequestBody(required = false) List<MedicationItemRequest> medicationItemRequest) {
        droneService.addMedicationItems(droneId,medicationItemRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * GET /drone/{droneId}/medication-items : get all loaded medication items.
     * get all loaded medication items.
     *
     * @param droneId drone id (required)
     * @return successful operation (status code 200)
     *         or Bad Request (status code 400)
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/drone/{droneId}/medication-items",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<List<MedicationItemRequest>> _getMedicationItemsByDroneSerialNumber(
            @Parameter(name = "droneId", description = "drone id", required = true) @PathVariable("droneId") Long droneId
    ) {
        return new ResponseEntity<>(droneService.getMedicationItems(droneId), HttpStatus.OK);
    }


}
