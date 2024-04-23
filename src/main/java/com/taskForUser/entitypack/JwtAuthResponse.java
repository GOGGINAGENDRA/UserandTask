package com.taskForUser.entitypack;

public class JwtAuthResponse {
private String token;
private String tokenType="Bearer";

public String getToken() {
	return token;
}
public String getTokenType() {
	return tokenType;
}
public JwtAuthResponse(String token) {
	super();
	this.token = token;
}

}
