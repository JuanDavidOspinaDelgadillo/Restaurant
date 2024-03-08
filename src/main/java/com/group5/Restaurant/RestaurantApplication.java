package com.group5.Restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Grandma's food app by Group 5",
				version = "MVP 1.0",
				description = """
                        Application of Grandma's food Restaurant

                        Contact Information:
                        - Juan David Ospina Delgadillo: juandavid.ospina@globant.com
                        
                        - Jessika Mariana Hernandez Chamucero: jessika.hernandez@globant.com
                        
                        - Jeimy Alejandra Barbosa Avila: jeimy.barbosa@globant.com
                        
                        - Karen Mileidy Murillo Ramirez: karen.murillo@globant.com"""
		)
)
@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}