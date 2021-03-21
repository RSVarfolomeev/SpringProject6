package files;

import files.Dao.CustomerDao;
import files.Dao.ProductDao;
import files.entity.Product;
import files.services.Service;

public class Application {
    public static void main(String[] args) {
//        ProductDao productDao = new ProductDao();
//        CustomerDao customerDao = new CustomerDao();
        Service service = new Service();
//        System.out.println(productDao.findAll());
//        System.out.println(customerDao.findAll());
//        System.out.println(service.getProductListByCustomerId(2L));
        System.out.println(service.getCustomerListByProductId(1L));
    }
}