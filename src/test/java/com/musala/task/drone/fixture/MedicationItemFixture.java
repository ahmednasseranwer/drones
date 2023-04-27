package com.musala.task.drone.fixture;

import com.musala.task.drone.model.MedicationItemRequest;

public class MedicationItemFixture {

    public static  MedicationItemRequest medicationItemRequestModel(){
        MedicationItemRequest medicationItemRequest = new MedicationItemRequest();
        medicationItemRequest.setCode("DDD");
        medicationItemRequest.setName("NAME");
        medicationItemRequest.setWeight(324);
        return medicationItemRequest;
    }
}
