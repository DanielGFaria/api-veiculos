package com.tinnova.veiculos.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket veiculoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.tinnova.veiculos"))
			.paths(PathSelectors.regex("/veiculo.*"))
			.build()
			.apiInfo(metainfo());
	}
	
	private ApiInfo metainfo() {		
		ApiInfo apiInfo = new ApiInfo(
				"Veiculo Api Rest",
				"Api Rest de Cadastro de Veiculos",
				"1.0",
				"Terms of Service",
				new Contact("Daniel Galvão faria", "dannfariaa@gmail.com", "github.com/DanielGFaria"),
				"Apache Licente Version 2.0",
				"https://www.apache.org/licesen.html",
				new ArrayList<VendorExtension>()
				);
		return apiInfo;
	}
}
