package services;
import guru.springframework.sfgpetclinic.model.Pet;
import java.util.Set;

public interface PetService {
    Pet findbyId(long id);
    Pet save(Pet save);
    Set<Pet> findAll();
}
