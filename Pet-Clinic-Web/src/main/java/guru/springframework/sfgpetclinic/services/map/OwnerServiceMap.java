package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import guru.springframework.sfgpetclinic.services.OwnerService;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner,Long> implements OwnerService{

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public Owner save(Owner owner) {
        //ako objekt nije null
        if(owner != null){
            //dohvati hashset Pets i provjeri da nije null, ako nije null onda:
            if (owner.getPets() != null) {
                //iteriraj kroz hashset
                owner.getPets().forEach(pet -> {
                    //dohvati pet iz hasgseta i te getaj type i provjeri da nije null, ako  nije null:
                    if (pet.getPetType() != null){
                        // jos za dodatnu provjeru pogledaj dal je id null, ako je null onda:
                        if(pet.getPetType().getId() == null){
                            //objektom pet, setiraj pet type tako da pozoves (I)petTypeSerivce koji u (C)PetTypeMapService ima implementiranu save metodu
                            // te u save prosljedis pet type
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }

                    //dodatno za provjeru za svaki slučaj
                    if(pet.getId() == null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(owner);

        } else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
