package nl.androidappfactory.springrestclientexamples.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.api.domain.User;
import nl.androidappfactory.api.domain.UserData;

@Slf4j
@Service
public class ApiServiceImpl implements ApiService {

	RestTemplate restTemplate;

	// @Value("${api.uri}")
	String apiUri;

	public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.uri}") String apiUri) {
		this.restTemplate = restTemplate;
		this.apiUri = apiUri;
	}

	@Override
	public List<User> getUsers(Integer limit) {

		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(apiUri)
				.queryParam("limit", limit);

		UserData userData = restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);

		log.debug("after getForObject: " + userData);
		return userData.getData();
	}

}
