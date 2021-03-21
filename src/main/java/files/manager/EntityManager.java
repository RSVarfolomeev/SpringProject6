package files.manager;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@Qualifier("EntityManager")
public class EntityManager {

    public javax.persistence.EntityManager createEntityManager(){
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
        javax.persistence.EntityManager em = factory.createEntityManager();
        return em;
    }
}
