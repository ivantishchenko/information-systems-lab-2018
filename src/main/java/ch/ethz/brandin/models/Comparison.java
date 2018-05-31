package ch.ethz.brandin.models;

import ch.ethz.brandin.data_service.EVENTS;
import ch.ethz.brandin.data_service.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import ch.ethz.brandin.data_service.DataThread;
import ch.ethz.brandin.response_models.*;

public class Comparison {
	private int id;
	private int numPosts;
	private String filter;
	private String profile1;
	private String profile2;
	private User user1;
	private User user2;
	private Event userEvent;
	private Event postUser1Event;
	private Event postUser2Event;
	
	private DataThread dataThread;
	
	private ResponseCollection responseCollection;
	
	private List<String> adviceSummary;



	public Comparison(int id, String profile1, String profile2, int numPosts, String filterType) {
		this.id = id;
		this.profile1 = profile1;
		this.profile2 = profile2;
		this.numPosts = numPosts;
		this.filter = filterType;

		userEvent = new Event();
		postUser1Event = new Event();
		postUser2Event = new Event();
		responseCollection = new ResponseCollection(this);
		adviceSummary = new CopyOnWriteArrayList<String>();
	}

	public void init() {
		this.dataThread = new DataThread(this);
		this.dataThread.start();
	}

	public void waitForEvent(EVENTS c) {
		switch (c) {
		case USER_EVENT:
			userEvent.await();
			break;
		case POSTS_USER1_EVENT:
			postUser1Event.await();
			break;
		case POSTS_USER2_EVENT:
			postUser2Event.await();
			break;
		default:
			break;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfile1() {
		return profile1;
	}

	public void setProfile1(String profile1) {
		this.profile1 = profile1;
	}

	public String getProfile2() {
		return profile2;
	}

	public void setProfile2(String profile2) {
		this.profile2 = profile2;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public Event getUserEvent() {
		return userEvent;
	}

	public Event getPostUser1Event() {
		return postUser1Event;
	}

	public Event getPostUser2Event() {
		return postUser2Event;
	}

	public int getNumPosts() {
		return numPosts;
	}

	public void setNumPosts(int numPosts) {
		this.numPosts = numPosts;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public ResponseCollection getResponseCollection() {
		return responseCollection;
	}

	public void setResponseCollection(ResponseCollection responseCollection) {
		this.responseCollection = responseCollection;
	}

	public List<String> getAdviceSummary() {
		return adviceSummary;
	}

	public void setAdviceSummary(List<String> adviceSummary) {
		this.adviceSummary = adviceSummary;
	}
}
