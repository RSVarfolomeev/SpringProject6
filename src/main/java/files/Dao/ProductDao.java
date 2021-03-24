package files.Dao;

import files.entity.Product;
import files.manager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ProductDao")
public class ProductDao {

    @Autowired
    private javax.persistence.EntityManager em = new EntityManager().createEntityManager();


    public void createProduct(String name, int price){
        Product product = new Product(name, price);
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public Product findById(long id) throws NoResultException {
        Product product = null;
        Query query = em.createQuery("select p from Product p where p.id =:id");
        query.setParameter("id",id);

        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Product no exsist : " + product);
            product = new Product();
        }
        return product;
    }

    public List<Product> findAll() throws NoResultException {
        List <Product> product = null;
        Query query = em.createQuery("select p from Product p");
        try {
            product = query.getResultList();

        }catch (NoResultException e){
            product = new ArrayList();
        }
        return product;
    }

    public void deleteById(Long id){
        Product product = em.find(Product.class, id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    public Product saveOrUpdate(Product product){
        Product anotherProduct ;
        anotherProduct = findById(product.getId());
        if (!anotherProduct.equals(product)){
            createProduct(product.getTitle(),product.getPrice());
            return product;
        }
        else {
            em.getTransaction().begin();
            Query query = em.createQuery("update Product p set p.title = :title,p.price = :price  where p.id =:id");
            query.setParameter("id",product.getId());
            query.setParameter("title",product.getTitle());
            query.setParameter("price",product.getPrice());
            query.executeUpdate();
            em.getTransaction().commit();
        }
        return product;
    }
}
