package ma.mohammedounzar.billingservice.controller;

import ma.mohammedounzar.billingservice.entities.Bill;
import ma.mohammedounzar.billingservice.entities.ProductItem;
import ma.mohammedounzar.billingservice.feign.CustomerRestClient;
import ma.mohammedounzar.billingservice.feign.ProductRestClient;
import ma.mohammedounzar.billingservice.model.Customer;
import ma.mohammedounzar.billingservice.model.Product;
import ma.mohammedounzar.billingservice.repository.BillRepository;
import ma.mohammedounzar.billingservice.repository.ProductItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class BillController {

    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillController(BillRepository billRepository,
                          ProductItemRepository productItemRepository,
                          CustomerRestClient customerRestClient,
                          ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @PostMapping("/generate-bills")
    public ResponseEntity<String> generateBills() {
        Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
        Collection<Product> products = productRestClient.getAllProducts().getContent();

        customers.forEach(customer -> {
            Bill bill = new Bill();
            bill.setBillingDate(new Date());
            bill.setCustomerId(customer.getId());
            bill = billRepository.save(bill);

            List<ProductItem> items = new ArrayList<>();
            for (Product product : products) {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setBill(bill);
                productItem.setQuantity(1 + new Random().nextInt(10));
                productItem.setUnitPrice(product.getPrice());
                productItemRepository.save(productItem);
                items.add(productItem);
            }
            bill.setProductItems(items);
        });

        return ResponseEntity.ok("Bills generated for all customers and products. Test");
    }
}

