package com.currency.microservices.apigateway;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
	{
		//return builder.routes().build(); here , if we return this we are not defining any custom routes and going with standard
		/*
		Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get")   // if this path "/get" is encountered
																		.filters(f -> f.addRequestHeader("authCode", "123")
																						.addRequestParameter("urlParam", "456")) // add a filter i.e. add a request header
																		.uri("http://httpbin.org:80"); // and redirect to this URL ; this URL return the request as it being sent to it.
		return builder.routes()
					  .route(routeFunction)
					  .build();
		*/
		
		// now lets customize routes for the serivces registered with the naming server
		// 1. from config disable the auto discovery.
		
		return builder.routes()
					  .route(p -> p.path("/currency-exchange/**") // if path URL starts with currency-exchange
					  // now we want to redirect it using the Eureka naming server and load balancer
					   .uri("lb://currency-exchange")) // talk to Eureka , find service with name currency-exchange and ld:load balance in the instances returned
					  .route(p -> p.path("/currency-conversion/**")
							  		.uri("lb://currency-converter"))
					  .route(p -> p.path("/currency-conversion-feign/**")
							  		.uri("lb://currency-converter"))
					  .route(p -> p.path("/v2/currency-conversion/**")
							  		.filters(f -> f.rewritePath("/v2/currency-conversion/(?<segment>.*)", // pick the path /v2/currency-conversion/....
							  									"/currency-conversion-feign/${segment}")) // rewrite it with /currency-conversion-feign 
							  		.uri("lb://currency-converter")
							  		)	
					   .build();
						
	}
}
