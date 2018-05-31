package ch.ethz.brandin.data_service;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ch.ethz.brandin.models.Comparison;
import ch.ethz.brandin.models.Post;
import ch.ethz.brandin.models.User;
import ch.ethz.brandin.models.User.UserObject;

public class DataThread extends Thread {

	private static final String COOKIE = "datr=aqKNWoEqYO2u_nEnkL8yu0-w; mid=WSsY1gALAAF_u4qKwzKSlZm3TW00; mcd=3; shbid=5515; rur=PRN; ig_cb=1; csrftoken=SjXs3J7zQLd7MTfBAueut1W8gj4phEfo; ds_user_id=7473404871; sessionid=IGSC98a61270a319c912ad6d6eabdbdca2da277f8793b1ba43a21a11c58b10257b6f%3AKHeUm4AFWnuedkalb1sG4QhlCkptxH1v%3A%7B%22_auth_user_id%22%3A7473404871%2C%22_auth_user_backend%22%3A%22accounts.backends.CaseInsensitiveModelBackend%22%2C%22_auth_user_hash%22%3A%22%22%2C%22_platform%22%3A4%2C%22_token_ver%22%3A2%2C%22_token%22%3A%227473404871%3A1ETrqJQBeStXVP2nDnkgNvq2Xb9eqr31%3Ab7527c8e51da58d30d2f43b19c9b9c26fd9ca1fe4001782f345ea8aa59c1069f%22%2C%22last_refreshed%22%3A1527509866.4203350544%7D; urlgen=\"{\\\"time\\\": 1527451667\\054 \\\"2a02:168:60fe:0:9941:d34:25aa:2ebb\\\": 13030\\054 \\\"2a02:168:60fe:0:5da2:4de9:3c6a:ac9d\\\": 13030}:1fNH6m:XD2e7c9AUtC1t1_7kqD8cnHypUc\"";
	private static final int MAX_COUNT = 5;

	private final static Logger log = LogManager.getLogger(DataThread.class);

	private Comparison comparison;
	private RestTemplate restTemplate;

	public DataThread(Comparison comparison) {
		super();
		this.comparison = comparison;
		this.restTemplate = new RestTemplate();

	}

	@Override
	public void run() {
		log.debug(String.format("DataThread started for {%s, %s}", comparison.getProfile1(), comparison.getProfile2()));

		try {
			getUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}

		downloadPosts(comparison.getUser1(), comparison.getPostUser1Event(), comparison);
		downloadPosts(comparison.getUser2(), comparison.getPostUser2Event(), comparison);

		log.debug(
				String.format("DataThread finished for {%s, %s}", comparison.getProfile1(), comparison.getProfile2()));
	}

	private void getUsers() throws IOException {
		String html;
		UserObject userObject;

		// get user1
		html = restTemplate.getForObject(Endpoints.getProfileUrl(comparison.getProfile1()), String.class);
		userObject = UserObject.createUserObject(html);
		comparison.setUser1(userObject.getUser());

		// get user2
		html = restTemplate.getForObject(Endpoints.getProfileUrl(comparison.getProfile2()), String.class);
		userObject = UserObject.createUserObject(html);
		comparison.setUser2(userObject.getUser());

		comparison.getUserEvent().activate();
	}

	private void downloadPosts(User user, Event event, Comparison comparison) {
		long userId = user.getId();
		Post.PostObject postObject = null;
		int count = 0;

		do {
			count += 1;

			String endCursor = "";
			if (postObject != null && postObject.getPageInfo() != null) {
				endCursor = postObject.getPageInfo().getEndCursor();
			}

			HttpHeaders headers = new HttpHeaders();
			
			headers.set("Cookie", COOKIE);
			HttpEntity<String> entity = new HttpEntity<String>("", headers);

			String url = Endpoints.getProfileMediaUrl();
			String variables = Endpoints.getMediaVariables(userId, endCursor);

			ResponseEntity<Post.PostObject> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					Post.PostObject.class, variables);
			postObject = response.getBody();
			user.getUnfilteredPosts().addAll(postObject.getPosts());

		} while (postObject != null && postObject.getPageInfo() != null && postObject.getPageInfo().isHasNextPage()
				&& count < MAX_COUNT);
		
		user.filterPosts(comparison.getFilter(), comparison.getNumPosts());
		event.activate();
	}
}
