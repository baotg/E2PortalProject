package se.iuh.e2portal.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

public class LongGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String query = "select e.id from " + object.getClass().getName() +" e";
        Stream<Long> ids = session.createQuery(query, Long.class).stream();
        Long max = ids.map(o -> o.longValue()).mapToLong(Long::longValue).max().orElse(0L);
        return (max + 1);
    }
}
