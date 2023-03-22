package repository.memory;

import Domain.Entity;
import Domain.validators.Validator;
import repository.Repository0;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>>implements Repository0<ID,E> {

    private Validator<E> validator;
    Map<ID,E> entities;
    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }
    @Override
    public E findOne(ID id) {
        if (id==null)
            throw new IllegalArgumentException("Id musn't be null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }


    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("Entity musn't be null");
        validator.validate(entity);
        if(entities.get(entity.getId()) != null) {
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if(id==null)
            throw new IllegalArgumentException("Entity musn't be null");

        E entity = entities.get(id);
        if(entity==null)
            return null;
        return entities.remove(entity.getId());
    }

    @Override
    public E update(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("Entity musn't be null!");
        validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;
    }
}

