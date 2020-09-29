package guru.springframework.sfgpetclinic.services.map;
import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        //vrati hashset iz Hashmap-a
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save (T object){
        if(object != null){
            if(object.getId() == null){
                object.setId(getNextid());
            }

            map.put(object.getId(), object);
        } else{
            throw new RuntimeException("Object cannot be null!");
        }

        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete (T object){
        //iteriraj kroz setove u mapi, obriši ako je element u mapi jednak objektu
        map.entrySet().removeIf(entry->entry.getValue().equals(object));
    }

    // dodjeljuje novi id svakom novom objektu koji se ubaci u hash
    private Long getNextid(){

        Long nextId = null;
        try{
            nextId = Collections.max(map.keySet()) + 1;
        }catch (NoSuchElementException e){
            nextId = 1L;
        }
        return nextId;
    }
}
