package se.iuh.e2portal.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class GradingResultPK implements Serializable {
    private static final long serialVersionUID = 1L;
    private long student;
    private String moduleClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GradingResultPK)) return false;
        GradingResultPK that = (GradingResultPK) o;
        return student == that.student &&
                moduleClass.equals(that.moduleClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, moduleClass);
    }
}
