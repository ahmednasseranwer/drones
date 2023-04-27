package com.musala.task.drone.fixture;

import com.musala.task.drone.model.MedicationItemRequest;

import java.util.ArrayList;
import java.util.List;

public class MedicationItemFixture {

    public static  List<MedicationItemRequest> medicationItemRequestModelList(){
        MedicationItemRequest medicationItemRequest = new MedicationItemRequest();
        medicationItemRequest.setCode("DDD");
        medicationItemRequest.setName("NAME");
        medicationItemRequest.setWeight(324);
        List<MedicationItemRequest> medicationItemRequestList = new ArrayList<>();
        medicationItemRequestList.add(medicationItemRequest);
        return medicationItemRequestList;
    }
}
