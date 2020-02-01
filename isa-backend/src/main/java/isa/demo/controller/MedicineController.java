package isa.demo.controller;

import isa.demo.dto.request.MedicineDTORequest;
import isa.demo.dto.response.MedicineDTOResponse;
import isa.demo.model.Medicine;
import isa.demo.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/medicine")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @RequestMapping(value = "/addMedicine",consumes = "application/json")
    public ResponseEntity<?> addMedicine(@RequestBody MedicineDTORequest medicineDTO){
        Medicine medicine = medicineService.findOneByName(medicineDTO.getName());
        if(medicine != null) {
            return new ResponseEntity<>(new MedicineDTOResponse(medicine), HttpStatus.BAD_REQUEST);
        }
        medicine = medicineService.save(medicineDTO);
        return new ResponseEntity<>(new MedicineDTOResponse(medicine), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllMedicine() {
        List<Medicine> medicines = medicineService.findAll();
        List<MedicineDTOResponse> medicineDTOS = new ArrayList<>();
        for(Medicine m : medicines){
            medicineDTOS.add(new MedicineDTOResponse(m));
        }
        return new ResponseEntity<>(medicineDTOS,HttpStatus.OK);
    }
}
