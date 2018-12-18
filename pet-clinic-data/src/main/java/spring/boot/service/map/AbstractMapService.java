package spring.boot.service.map;

import spring.boot.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null && object.getId() == null) {
            object.setId(getNextId());
            map.put(object.getId(), object);
        } else
            throw new RuntimeException("Object cannot be null. This error is from save method in AbstractMapService");

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    //20181218 - Update Id as auto increment
    private Long getNextId() {

        Long nextId = null;
        try {
            if (map != null && map.keySet() != null) nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {//This class is coming earlier than BaseEntity, so getId() may not be accessable.
            nextId = Long.valueOf(1);//nextId=1L;
        }
        return nextId;
    }
}
