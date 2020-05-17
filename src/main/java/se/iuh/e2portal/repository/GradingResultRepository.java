package se.iuh.e2portal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.GradingResultPK;

import java.util.List;

@RepositoryRestResource(itemResourceRel = "grading_result",collectionResourceRel = "grading_results", path = "grading_result")
public interface GradingResultRepository extends CrudRepository<GradingResult, GradingResultPK> {

    @Query("select g from GradingResult g where g.student.id = ?1")
    List<GradingResult> findByStudentId(String studentId);

    @Query("select g from GradingResult g where g.student.id = ?1 and g.moduleClass.moduleClassId =?2")
    GradingResult findByStudentIdAndModuleClassId(String studentId, String moduleClassId);

    Iterable<GradingResult> findByModuleClassModuleClassId(String id);
}
