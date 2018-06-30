package com.indix.distributedcache;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.stereotype.Component;

@Component
public class RestHttpClient {
	// Default client is jersey
	private final Client client = ClientBuilder.newClient();

	public String getResult(final String requestUri) {
		return client.target(requestUri)
				     .request()
				     .get(String.class)
				     .toString();
	}
}