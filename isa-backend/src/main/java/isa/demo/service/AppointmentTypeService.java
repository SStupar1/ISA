package isa.demo.service;

import isa.demo.dto.request.AppointmentTypeDTORequest;
import isa.demo.model.AppointmentType;
import isa.demo.repository.AppointmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    public AppointmentType findOneByName(String name) {return appointmentTypeRepository.findByName(name);}
    public AppointmentType findOneById(Long id){
        return appointmentTypeRepository.findOneById(id);
    }
    public List<AppointmentType> findAll() { return appointmentTypeRepository.findAll();}
    public AppointmentType save(AppointmentTypeDTORequest appointmentTypeDTO) {
        AppointmentType m = new AppointmentType();
        m.setName(appointmentTypeDTO.getName());
        return appointmentTypeRepository.save(m);
    }
}
