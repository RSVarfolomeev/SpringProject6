package files.services;

import files.entity.Cart;
import files.entity.Customer;
import files.entity.Product;
import files.manager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    public Customer customer;

    @Autowired
    public Product product;

    @Autowired
    public Cart cart;

    @Autowired
    private javax.persistence.EntityManager em = new EntityManager().createEntityManager();

    public List<Product> getProductListByCustomerId(Long id) {
        List product = new ArrayList<>();
        Query query = em.createQuery("select p from Cart c, Customer cu, Product p where c.customer.id = cu.id and cu.id =:id and p.id = c.product.id", Product.class);
        query.setParameter("id", id);

        try {
            product = query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Product no exsist : " + product);
            product = new ArrayList<>();
        }
        return product;
    }

    public List<Customer> getCustomerListByProductId(Long id) {
        List customer = new ArrayList<>();
        Query query = em.createQuery("select cu from Cart c, Customer cu, Product p where c.customer.id = cu.id and p.id =:id and p.id = c.product.id", Customer.class);
        query.setParameter("id", id);

        try {
            customer = query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Customer no exsist : " + product);
            customer = new ArrayList<>();
        }
        return customer;
    }
}
