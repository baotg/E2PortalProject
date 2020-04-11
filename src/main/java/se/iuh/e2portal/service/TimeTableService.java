package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.TimeTable;
import se.iuh.e2portal.repository.TimeTableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;


    public TimeTable save(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }



    public <S extends TimeTable> Iterable<S> saveAll(Iterable<S> entities) {
        return timeTableRepository.saveAll(entities);
    }
    public Optional<TimeTable> findById(Long id) {
        return timeTableRepository.findById(id);
    }


    public List<TimeTable> findAll() {
        return (List<TimeTable>) timeTableRepository.findAll();
    }


    public boolean existsById(Long id) {
        return timeTableRepository.existsById(id);
    }


    public long count() {
        return timeTableRepository.count();
    }


    public void deleteById(Long id) {
        timeTableRepository.deleteById(id);

    }


    public void delete(TimeTable object) {
        timeTableRepository.delete(object);
    }
}
