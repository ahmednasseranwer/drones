package com.musala.task.drone.service;

import com.musala.task.drone.exception.custom.InvalidDroneBatteryException;
import com.musala.task.drone.exception.custom.InvalidDroneWeightCarryException;
import com.musala.task.drone.exception.custom.NotFoundException;
import com.musala.task.drone.model.DroneBattery;
import com.musala.task.drone.model.DroneModel;
import com.musala.task.drone.model.MedicationItemRequest;
import com.musala.task.drone.repositories.DroneRepository;
import com.musala.task.drone.service.impl.DroneServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.musala.task.drone.fixture.DroneFixture.*;
import static com.musala.task.drone.fixture.MedicationItemFixture.medicationItemRequestModel;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DroneServiceImplTest {

    @Mock
    DroneRepository droneRepository;


    @InjectMocks
    DroneServiceImpl droneServiceimpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("when the register drone saved successful on Database")
    public void testRegisterDrone_whenDroneSavedSuccessfulOnDatabase(){
        when(droneRepository.save(any())).thenReturn(createdDrone());
        Long droneId = droneServiceimpl.register(createdDroneModel());
        assertEquals(11L,droneId.longValue());
    }

    @Test
    @DisplayName("when the get all idle drone from Database successful")
    public void testFindIdleDrones_whenDroneSavedSuccessfulOnDatabase(){
        when(droneRepository.findAllByState(any())).thenReturn(List.of(createdDrone()));
        List<DroneModel> drones =  droneServiceimpl.findIdleDrones();
        assertEquals(createdDrone().getSerialNumber(),drones.get(0).getSerialNumber());
        assertEquals(createdDrone().getBattery(),drones.get(0).getBattery());
        assertEquals(createdDrone().getWeightLimit(),drones.get(0).getWeightLimit());
        assertEquals(createdDrone().getState(),drones.get(0).getState());
        assertEquals(createdDrone().getModel(),drones.get(0).getModel());
    }

    @Test
    @DisplayName("when the get empty idle drone ")
    public void testFindIdleDrones_whenEmptyIdleDrone(){
        when(droneRepository.findAllByState(any())).thenReturn(new ArrayList<>());
        List<DroneModel> drones =  droneServiceimpl.findIdleDrones();
        assertEquals(true,drones.isEmpty());
    }

    @Test
    @DisplayName("when the get drone battery with given droneId successful")
    public void testGetBatteryByDroneId_whenDroneSavedSuccessfulOnDatabase(){
        when(droneRepository.findById(any())).thenReturn(Optional.of(createdDrone()));
        DroneBattery droneBattery =  droneServiceimpl.getBatteryByDroneId(1L);
        assertEquals(createdDrone().getBattery(),droneBattery.getBattery());
    }

    @Test
    @DisplayName("when the get drone battery with wrong droneId ")
    public void testGetBatteryByDroneId_whenWrongDroneId(){
        when(droneRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> droneServiceimpl.getBatteryByDroneId(1L));
    }

    @Test
    @DisplayName("when the add medication items to wrong drone id")
    public void testAddMedication_whenWrongDroneId(){
        when(droneRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> droneServiceimpl.addMedicationItems(1L,List.of(new MedicationItemRequest())));
    }

    @Test
    @DisplayName("when the add medication items to drone with battery capacity less than 25%")
    public void testAddMedication_whenDroneBatteryLessThan_25(){
        when(droneRepository.findById(any())).thenReturn(Optional.of(createdDroneWithTENBattery()));
        assertThrows(InvalidDroneBatteryException.class, ()-> droneServiceimpl.addMedicationItems(1L,List.of(medicationItemRequestModel())));
    }

    @Test
    @DisplayName("when the add medication items to drone with medication weight greater than drone weight")
    public void testAddMedication_whenDroneWeightLessThanMedicationWeight(){
        when(droneRepository.findById(any())).thenReturn(Optional.of(createdDroneWithOneWeight()));
        assertThrows(InvalidDroneWeightCarryException.class, ()-> droneServiceimpl.addMedicationItems(1L,List.of(medicationItemRequestModel())));
    }

    @Test
    @DisplayName("when the get medication items with wrong drone id ")
    public void tesGetMedication_whenWrongDroneId(){
        when(droneRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> droneServiceimpl.getMedicationItems(1L));
    }

    @Test
    @DisplayName("when the get medication with drone saved Database successful")
    public void testGetMedication_whenDroneSavedSuccessfulOnDatabase(){
        when(droneRepository.findById(any())).thenReturn(Optional.of(createdDrone()));
        List<MedicationItemRequest> medicationItemRequests =  droneServiceimpl.getMedicationItems(1L);
        assertEquals(createdDrone().getMedicationList().get(0).getWeight(),medicationItemRequests.get(0).getWeight());
        assertEquals(createdDrone().getMedicationList().get(0).getCode(),medicationItemRequests.get(0).getCode());
        assertEquals(createdDrone().getMedicationList().get(0).getImage(),medicationItemRequests.get(0).getImage());
    }

}
