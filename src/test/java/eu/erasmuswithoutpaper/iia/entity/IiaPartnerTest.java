
package eu.erasmuswithoutpaper.iia.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IiaPartnerTest {
    
    EntityManager em;
    EntityTransaction tx;
    
    @Before
    public void setUp() {
        this.em = Persistence.createEntityManagerFactory("connector-test").createEntityManager();
        this.tx = this.em.getTransaction();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testPersistPartner() {
        IiaPartner iiaPartner = new IiaPartner("IiaId1", "InstId1", "OrgUnitId1");
        
        this.tx.begin();
        this.em.persist(iiaPartner);
        this.tx.commit();
        this.em.clear();
        
        IiaPartner result = em.find(IiaPartner.class, iiaPartner.getId());
        Assert.assertNotNull(result);
    }
}
