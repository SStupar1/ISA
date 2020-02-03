package isa.demo.service;

import isa.demo.dto.request.ClinicDTORequest;
import isa.demo.model.Clinic;
import isa.demo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicService {


    @Autowired
    ClinicRepository clinicRepository;

    @Cacheable("clinics")
    public List<Clinic> findAll() {
        return clinicRepository.findAll();
    }
    @Cacheable("clinics")
    public Clinic findOneById(Long id){
        return clinicRepository.findOneById(id);
    }
    @Cacheable("clinics")
    public Clinic findOneByName(String name){return clinicRepository.findOneByName(name); }
    public Clinic save(ClinicDTORequest c) {
        Clinic c1 = new Clinic();
        c1.setAddress(c.getAddress());
        c1.setDescription(c.getDescription());
        c1.setName(c.getName());

        return clinicRepository.save(c1);
    }
    public int updateClinic(String name,String address,String description,long id) { return clinicRepository.updateClinic(name,address,description,id); }

}
