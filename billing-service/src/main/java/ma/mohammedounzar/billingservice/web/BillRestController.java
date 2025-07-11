package ma.mohammedounzar.billingservice.web;

import ma.mohammedounzar.billingservice.entities.Bill;
import ma.mohammedounzar.billingservice.feign.CustomerRestClient;
import ma.mohammedounzar.billingservice.feign.ProductRestClient;
import ma.mohammedounzar.billingservice.repository.BillRepository;
import ma.mohammedounzar.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ProductRestClient productRestClient;
    @Autowired
    private CustomerRestClient customerRestClient;

    @GetMapping(path ="/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }
}
