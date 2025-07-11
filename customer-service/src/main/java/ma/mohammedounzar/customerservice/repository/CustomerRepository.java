package ma.mohammedounzar.customerservice.repository;

import ma.mohammedounzar.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
