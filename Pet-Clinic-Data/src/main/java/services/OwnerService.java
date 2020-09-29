package services;
import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.context.annotation.ComponentScan;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
