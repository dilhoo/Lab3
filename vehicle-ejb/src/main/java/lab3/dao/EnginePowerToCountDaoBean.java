package lab3.dao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote(EnginePowerToCountDao.class)
public class EnginePowerToCountDaoBean implements EnginePowerToCountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object> get() {
        return entityManager
                .createNamedQuery("enginePowerToCount", Object.class)
                .getResultList();
    }
}