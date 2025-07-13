package ma.mohammedounzar.billingservice;

import lombok.extern.slf4j.Slf4j;
import ma.mohammedounzar.billingservice.entities.Bill;
import ma.mohammedounzar.billingservice.entities.ProductItem;
import ma.mohammedounzar.billingservice.feign.CustomerRestClient;
import ma.mohammedounzar.billingservice.feign.ProductRestClient;
import ma.mohammedounzar.billingservice.model.Customer;
import ma.mohammedounzar.billingservice.model.Product;
import ma.mohammedounzar.billingservice.repository.BillRepository;
import ma.mohammedounzar.billingservice.repository.ProductItemRepository;
import org.apache.juli.logging.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.*;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(BillRepository billRepository,
//                                        ProductItemRepository productItemRepository,
//                                        CustomerRestClient customerRestClient,
//                                        ProductRestClient productRestClient) {
//        return args -> {
//            Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
//            Collection<Product> products = productRestClient.getAllProducts().getContent();
//
//            customers.forEach(customer -> {
//                Bill bill = new Bill();
//                bill.setBillingDate(new Date());
//                bill.setCustomerId(customer.getId());
//                bill = billRepository.save(bill);
//
//                List<ProductItem> items = new ArrayList<>();
//                for (Product product : products) {
//                    ProductItem productItem = new ProductItem();
//                    productItem.setProductId(product.getId());
//                    productItem.setBill(bill);
//                    productItem.setQuantity(1 + new Random().nextInt(10));
//                    productItem.setUnitPrice(product.getPrice());
//                    productItemRepository.save(productItem);
//                    items.add(productItem);
//                }
//                bill.setProductItems(items);
//                System.out.println(bill.getProductItems());
//            });
//        };
//    }
}
