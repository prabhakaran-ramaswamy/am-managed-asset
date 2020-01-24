package org.sample.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import brave.sampler.Sampler;

@SpringBootApplication
@EnableFeignClients("org.sample.capstone.proxy")
@EnableDiscoveryClient
public class ManagedAssetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagedAssetApplication.class, args);
	}

	@Bean
	public HealthIndicator dbHealthIndicator() {
		return new HealthIndicator() {

			@Override
			public Health health() {
				return Health.status(Status.UP).withDetail("hello", "hi").build();
			}
		};
	}

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	
	@RestController
	public class HelloController {
		@GetMapping("/hello")
		public String hello() {
			return "hello";
		}
	}
	
	@Bean 
	public RequestMappingHandlerAdapter objectMapper() {
	   RequestMappingHandlerAdapter requestMappingHandlerAdapter =new RequestMappingHandlerAdapter();
	   MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	   requestMappingHandlerAdapter.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	   return requestMappingHandlerAdapter;
	}
}