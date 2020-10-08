package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.services.VetService;
import java.util.Set;

@Service
public class VetServiceMap extends  AbstractMapService<Vet,Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        //ako je hashet specialities veca od nule
        if(object.getSpecialities().size() > 0){
            //iteriraj hashsetom
            object.getSpecialities().forEach(speciality -> {
                //za svaki objekt provjeri da li je null, i ako je
                if(speciality.getId() == null){
                    //spremi ga u abstraktnu mapu speciality
                    Speciality savedSpeciality = specialityService.save(speciality);
                    //objektu iz hashseta dodjeli ID koji se dodjeli abstraktnom mapom
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
