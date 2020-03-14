package se.iuh.e2portal.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenericTemplate<T,T2> {
     T save(T t);
     T findById(T2 id);
     List<T> findAll();
     boolean existsById(T2 id);
     long count();
     void deleteById(T2 id);
     void delete(T object);

}
