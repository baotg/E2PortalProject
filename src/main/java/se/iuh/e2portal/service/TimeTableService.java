package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.TimeTable;
import se.iuh.e2portal.repository.TimeTableRepository;

import java.util.List;

@Service
public class TimeTableService implements GenericTemplate<TimeTable, Long> {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Override
    public TimeTable save(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public TimeTable findById(Long id) {
        return timeTableRepository.findById(id).get();
    }

    @Override
    public List<TimeTable> findAll() {
        return (List<TimeTable>) timeTableRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return timeTableRepository.existsById(id);
    }

    @Override
    public long count() {
        return timeTableRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        timeTableRepository.deleteById(id);

    }

    @Override
    public void delete(TimeTable object) {
        timeTableRepository.delete(object);
    }
}
