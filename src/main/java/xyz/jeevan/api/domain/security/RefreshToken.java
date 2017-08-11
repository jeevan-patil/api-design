package xyz.jeevan.api.domain.security;

import java.io.Serializable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document(collection = "refresh_tokens")
public class RefreshToken implements Serializable {

  private final String tokenId;
  private final OAuth2RefreshToken oAuth2RefreshToken;
  private final OAuth2Authentication authentication;
  @Indexed
  private String id;

  public RefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
    this.oAuth2RefreshToken = oAuth2RefreshToken;
    this.authentication = authentication;
    this.tokenId = oAuth2RefreshToken.getValue();
  }

  public String getTokenId() {
    return tokenId;
  }

  public OAuth2RefreshToken getoAuth2RefreshToken() {
    return oAuth2RefreshToken;
  }

  public OAuth2Authentication getAuthentication() {
    return authentication;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}