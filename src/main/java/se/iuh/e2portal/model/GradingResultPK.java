package se.iuh.e2portal.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
        return getStudent() == that.getStudent() &&
                getModuleClass().equals(that.getModuleClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getModuleClass());
    }
}
