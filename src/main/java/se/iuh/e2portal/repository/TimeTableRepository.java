package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.TimeTable;

@RepositoryRestResource(itemResourceRel = "timetable",collectionResourceRel = "timetable", path = "timetable")
public interface TimeTableRepository extends CrudRepository<TimeTable, Long> {

}
