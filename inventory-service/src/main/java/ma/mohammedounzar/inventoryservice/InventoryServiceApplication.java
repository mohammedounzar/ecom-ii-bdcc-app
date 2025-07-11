package ma.mohammedounzar.inventoryservice;

import ma.mohammedounzar.inventoryservice.entities.Product;
import ma.mohammedounzar.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product("p1", "Laptop", 1200.0, 10));
            productRepository.save(new Product("p2", "Smartphone", 800.0, 20));
            productRepository.save(new Product("p3", "Headphones", 150.0, 50));
            productRepository.save(new Product("p4", "Keyboard", 70.0, 40));

            // Affichage de tous les produits enregistrés
            productRepository.findAll().forEach(product -> {
                System.out.println("=======================");
                System.out.println("ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("=======================");
            });
        };
    }


}
