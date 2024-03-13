package com.group5.Restaurant;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
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
		),
		tags = @Tag(
				name = "RESPONSE CODES:",
				description = """
						These are the response codes of each situation
										
						- OK = X8372		\t
						
						- DOESNT_EXIST = R4916	\t
						
						- ALREADY_EXIST = M2845
						
						- NO_DATA_IN_TABLE = P6787
						
						- NULL_OR_INVALID_DATA = H7531
						
						- INTERNAL_SERVER_ERROR = W4629
						
						- CLIENT_RELATED_DOESNT_EXIST = G6294
						
						- PRODUCT_RELATED_DOESNT_EXIST = F3187				\t
						
						- DOESNT_HAVE_CHANGES = D1213"""
		)
)
@SpringBootApplication
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}