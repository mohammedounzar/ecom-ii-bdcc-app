package ma.mohammedounzar.customerservice;

import ma.mohammedounzar.customerservice.entities.Customer;
import ma.mohammedounzar.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Mohammed", "mohammed@gmail.com"));
            customerRepository.save(new Customer(null, "Hamza", "hamza@gmail.com"));
            customerRepository.save(new Customer(null, "Ismail", "ismail@gmail.com"));

            customerRepository.findAll().forEach(c -> {
                System.out.println("=======================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("=======================");
            });
        };
    }


}
