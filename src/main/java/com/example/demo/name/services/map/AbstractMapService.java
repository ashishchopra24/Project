package com.example.demo.name.services.map;

import com.example.demo.name.model.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;






public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {

    protected Map<Long,T> map=new HashMap<>();

    Set<T> findAll()
    {
        return new HashSet<>(map.values());
    }

    T findById(ID id)
    {
        return map.get(id);
    }

    T save(T object)
    {
        if(object!=null)
        {
            if(object.getId()==null)
                object.setId(getnextId());

            map.put(object.getId(), object);
        }
        else throw new RuntimeException("Object is null");

        return object;
    }

    void deleteById(ID id)
    {
        map.remove(id);
    }

    void delete(T object)
    {
        map.entrySet().removeIf(entry->entry.getValue().equals(object));
    }

    private Long getnextId()
    {
        Long nextid=null;
        try {
            nextid=Collections.max(map.keySet())+1;
        }
        catch(NoSuchElementException e)
        {
            nextid=1L;
        }
        return nextid;
    }

}

