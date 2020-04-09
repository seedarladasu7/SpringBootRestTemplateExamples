package com.hcl.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {

	//RestTemplate restTemplate = RestTemplateSingleton.getInstance();

	@Autowired
	RestTemplate restTemplate;
	
	static String baseURIForOrderSer = "http://ORDER-SERVICE/demo/orders";

	@GetMapping("")
	public String getUserOrders() {

		return restTemplate.getForObject(baseURIForOrderSer, String.class);
	}

	@GetMapping("/{userId}")
	public String getUserOrdersById(@PathVariable String userId) {

		return restTemplate.getForObject(baseURIForOrderSer + "/" + userId, String.class);

	}

	@GetMapping("/byReqParam")
	public String getUserOrdersByReqParam(@RequestParam String userId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURIForOrderSer + "/byReqParam");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}

		return restTemplate.getForObject(builder.toUriString(), String.class);

	}

	@GetMapping("/postParam")
	public ResponseEntity<String> testPostWithParam(@RequestParam String userId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("userId", userId);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(baseURIForOrderSer + "/byPostReqParam", httpEntity,
				String.class);

		System.out.println(response);
		return response;
	}

	@GetMapping("/byBody")
	public ResponseEntity<String> testReqBody() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("id", 110);
		request.put("des", "test10");

		HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

		ResponseEntity<String> response = restTemplate.postForEntity(baseURIForOrderSer + "/byBody", entity, String.class);

		System.out.println(response);
		return response;

	}

	@GetMapping("/byBody2")
	public ResponseEntity<String> testReqBody2() {

		String uri = "http://localhost:8081/demo/orders/byBody";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		JSONObject request = new JSONObject();
		request.put("id", 1122);
		request.put("des", "usk1");

		HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

		return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	}

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
	/*public static class RestTemplateSingleton {

		private RestTemplateSingleton() {
		}

		private static class RestTemplateSingletonHolder {
			public static final RestTemplate instance = new RestTemplate();
		}

		public static RestTemplate getInstance() {
			return RestTemplateSingletonHolder.instance;
		}
	}*/

}
