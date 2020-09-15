package services;
import guru.springframework.sfgpetclinic.model.Vet;
import java.util.Set;

public interface VetService {

    Vet findbyId(long id);
    Vet save(Vet save);
    Set<Vet> findAll();
}
