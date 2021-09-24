package com.p1;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller 
public class HelloWebClientController {
	@Autowired 
	private DiscoveryClient discoveryClient; 
	
	@GetMapping("/")
	public String handleRequest(Model model)
	{
		List<ServiceInstance> instances = discoveryClient.getInstances("HelloService");   // you are accessing another project
		if(instances != null && instances.size() > 0) {
			ServiceInstance serviceInstance = instances.get(0);  // The first instance you are assigning it to serviceInstance 
			String url = serviceInstance.getUri().toString();   // defining a string to get Uri of the instance 
			System.out.println(url);		
			url = url + "/hello";  // converting it to mapping /hello
			RestTemplate restTemplate = new RestTemplate();    // It is a class, whose object will access service classes 
			
			// restTemplte allows to access the object of HelloObject class by using url, we get the object with /hello mapping
			HelloObject helloObject = restTemplate.getForObject(url, HelloObject.class);  // getForObject is the url and HelloObject class 
			
			System.out.println(helloObject.getMessage());
			model.addAttribute("msg", helloObject.getMessage());   
			model.addAttribute("time", LocalDateTime.now()); 
		}
		return "hello-page";   // It returns hello-page.html
	}
}