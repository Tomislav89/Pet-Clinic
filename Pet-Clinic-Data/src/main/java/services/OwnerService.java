package services;
import guru.springframework.sfgpetclinic.model.Owner;
import java.util.Set;

public interface OwnerService {
    Owner findByLastName(String lastName);
    Owner findbyId(long id);
    Owner save(Owner save);
    Set<Owner> findAll();
}
