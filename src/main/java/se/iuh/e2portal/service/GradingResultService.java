package se.iuh.e2portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.GradingResult;
import se.iuh.e2portal.model.GradingResultPK;
import se.iuh.e2portal.model.ModuleClass;
import se.iuh.e2portal.repository.GradingResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradingResultService {

    @Autowired
    private GradingResultRepository gradingResultRepository;

    public Iterable<GradingResult> findByModuleClass(String id) {
        return gradingResultRepository.findByModuleClassModuleClassId(id);
    }

    private GradingResult merge(GradingResult gradingResult) {
        GradingResultPK key = new GradingResultPK();
        key.setModuleClass(gradingResult.getModuleClassId());
        key.setStudent(gradingResult.getStudentId());
        Optional<GradingResult> old = gradingResultRepository.findById(key);
        if (old.isPresent()) {
            old.get().setQuiz1(gradingResult.getQuiz1() != null ? gradingResult.getQuiz1() : old.get().getQuiz1());
            old.get().setQuiz2(gradingResult.getQuiz2() != null ? gradingResult.getQuiz2() : old.get().getQuiz2());
            old.get().setQuiz3(gradingResult.getQuiz3() != null ? gradingResult.getQuiz3() : old.get().getQuiz3());
            old.get().setQuiz4(gradingResult.getQuiz4() != null ? gradingResult.getQuiz4() : old.get().getQuiz4());
            old.get().setQuiz5(gradingResult.getQuiz5() != null ? gradingResult.getQuiz5() : old.get().getQuiz5());

            old.get().setPracticeScore1(gradingResult.getPracticeScore1() != null ? gradingResult.getPracticeScore1()
                    : old.get().getPracticeScore1());
            old.get().setPracticeScore2(gradingResult.getPracticeScore2() != null ? gradingResult.getPracticeScore2()
                    : old.get().getPracticeScore2());
            old.get().setPracticeScore3(gradingResult.getPracticeScore3() != null ? gradingResult.getPracticeScore3()
                    : old.get().getPracticeScore3());
            old.get().setPracticeScore1(gradingResult.getPracticeScore4() != null ? gradingResult.getPracticeScore4()
                    : old.get().getPracticeScore4());
            old.get().setPracticeScore1(gradingResult.getPracticeScore5() != null ? gradingResult.getPracticeScore5()
                    : old.get().getPracticeScore5());

            old.get().setMidScore(
                    gradingResult.getMidScore() != null ? gradingResult.getMidScore() : old.get().getMidScore());
            old.get().setEndScore(
                    gradingResult.getEndScore() != null ? gradingResult.getEndScore() : old.get().getEndScore());
            old.get().setAverageScore(gradingResult.getAverageScore() != null ? gradingResult.getAverageScore()
                    : old.get().getAverageScore());
        }
        return gradingResultRepository.save(old.get());
    }

    public <S extends GradingResult> Iterable<S> saveAll(Iterable<S> entities) {
        return gradingResultRepository.saveAll(entities);
    }

    public void mergeAll(List<GradingResult> gradingResult) {
        for (GradingResult result : gradingResult) {
            GradingResultPK key = new GradingResultPK();
            key.setModuleClass(result.getModuleClassId());
            key.setStudent(result.getStudentId());
            if (gradingResultRepository.existsById(key))
                merge(result);
            else{
                save(result);
            }

        }

    }

    private void save(GradingResult gradingResult) {
        gradingResultRepository.save(gradingResult);
    }

    public boolean existsById(GradingResultPK gradingResultPK) {
        return false;
    }

    public List<GradingResult> findAll() {
        return (List<GradingResult>) gradingResultRepository.findAll();
    }

    public Iterable<GradingResult> findAllById(Iterable<GradingResultPK> iterable) {
        return null;
    }

    public long count() {
        return gradingResultRepository.count();
    }

    public void delete(GradingResult object) {
        gradingResultRepository.delete(object);
    }

    public void deleteAll(Iterable<? extends GradingResult> iterable) {

    }

    public void deleteAll() {

    }

    public Optional<GradingResult> findById(GradingResultPK id) {
        return gradingResultRepository.findById(id);
    }
}
