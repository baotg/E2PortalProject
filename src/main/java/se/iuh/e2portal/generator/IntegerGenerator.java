package se.iuh.e2portal.generator;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.stream.Stream;

public class IntegerGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String query = "select e.id from " + object.getClass().getName() +" e";
        Stream<Integer> ids = session.createQuery(query, Integer.class).stream();
        int max = ids.map(o -> o.intValue()).mapToInt(Integer::intValue).max().orElse(0);
        return (max + 1);
    }
}
