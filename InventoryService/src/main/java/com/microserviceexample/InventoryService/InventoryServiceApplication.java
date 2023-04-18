package com.microserviceexample.InventoryService;

import com.microserviceexample.InventoryService.model.Inventory;
import com.microserviceexample.InventoryService.repository.InventoryRepository;
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
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory1 = Inventory.builder()
					.productCode("iphone4s")
					.quantity(250)
					.build();

			Inventory inventory2 = Inventory.builder()
					.productCode("iphone5")
					.quantity(0)
					.build();

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
