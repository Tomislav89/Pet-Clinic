package guru.springframework.sfgpetclinic.services.map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    Set<T> findAll() {
        //vrati hashset iz Hashmap-a
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save (ID id, T object){
        map.put(id, object);
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete (T object){
        //iteriraj kroz setove u mapi, obriši ako je element u mapi jednak objektu
        map.entrySet().removeIf(entry->entry.getValue().equals(object));
    }
}
