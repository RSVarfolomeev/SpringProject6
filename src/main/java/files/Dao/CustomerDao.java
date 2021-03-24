package files.Dao;

import files.entity.Customer;
import files.manager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    @Autowired
    private javax.persistence.EntityManager em = new EntityManager().createEntityManager();

    public void createCustomer(String name){
        Customer customer = new Customer(name);
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public Customer findById(long id) throws NoResultException {
        Customer customer = null;
        Query query = em.createQuery("select p from Customer p where p.id =:id");
        query.setParameter("id",id);

        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Customer no exsist : " + customer);
            customer = new Customer();
        }
        return customer;
    }

    public List<Customer> findAll() throws NoResultException {
        List <Customer> customer = null;
        Query query = em.createQuery("select p from Customer p");
        try {
            customer = query.getResultList();

        }catch (NoResultException e){
            customer = new ArrayList();
        }
        return customer;
    }

    public void deleteById(Long id){
        Customer customer = em.find(Customer.class, id);
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }

    public Customer saveOrUpdate(Customer customer){
        Customer anotherCustomer;
        anotherCustomer = findById(customer.getId());
        if (!anotherCustomer.equals(customer)){
            createCustomer(customer.getName());
            return customer;
        }
        else {
            em.getTransaction().begin();
            Query query = em.createQuery("update Customer p set p.name = :name where p.id =:id");
            query.setParameter("id",customer.getId());
            query.setParameter("name",customer.getName());
            query.executeUpdate();
            em.getTransaction().commit();
        }
        return customer;
    }
}
