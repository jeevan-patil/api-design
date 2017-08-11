package xyz.jeevan.api.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;
import xyz.jeevan.api.domain.security.AccessToken;
import xyz.jeevan.api.domain.security.RefreshToken;
import xyz.jeevan.api.repository.AccessTokenRepository;
import xyz.jeevan.api.repository.RefreshTokenRepository;

@Service("tokenStore")
public class TokenStoreService implements TokenStore {

  private final AuthenticationKeyGenerator authenticationKeyGenerator =
      new DefaultAuthenticationKeyGenerator();

  @Autowired
  private AccessTokenRepository accessTokenRepository;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Override
  public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
    return readAuthentication(token.getValue());
  }

  @Override
  public OAuth2Authentication readAuthentication(String token) {
    AccessToken accessToken = accessTokenRepository.findByTokenId(token);
    return null == token ? null : accessToken.getAuthentication();
  }

  @Override
  public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
    AccessToken accessToken = new AccessToken(token,
        authentication, authenticationKeyGenerator.extractKey(authentication));
    final String tokenId = accessToken.getTokenId();
    OAuth2AccessToken existingToken = readAccessToken(tokenId);

    if (existingToken == null) {
      accessTokenRepository.save(accessToken);
    }
  }

  @Override
  public OAuth2AccessToken readAccessToken(String tokenValue) {
    AccessToken accessToken = accessTokenRepository.findByTokenId(tokenValue);
    if (null == accessToken) {
      return null;
    }
    return accessToken.getoAuth2AccessToken();
  }

  @Override
  public void removeAccessToken(OAuth2AccessToken token) {
    AccessToken accessToken = accessTokenRepository.findByTokenId(token.getValue());
    accessTokenRepository.delete(accessToken);
  }

  @Override
  public void storeRefreshToken(OAuth2RefreshToken refreshToken,
      OAuth2Authentication authentication) {
    refreshTokenRepository.save(new RefreshToken(refreshToken, authentication));
  }

  @Override
  public OAuth2RefreshToken readRefreshToken(String tokenValue) {
    RefreshToken refreshToken = refreshTokenRepository.findByTokenId(tokenValue);
    return refreshToken.getoAuth2RefreshToken();
  }

  @Override
  public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
    RefreshToken refreshToken = refreshTokenRepository.findByTokenId(token.getValue());
    return refreshToken.getAuthentication();
  }

  @Override
  public void removeRefreshToken(OAuth2RefreshToken token) {
    RefreshToken refreshToken = refreshTokenRepository.findByTokenId(token.getValue());
    refreshTokenRepository.delete(refreshToken);
  }

  @Override
  public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
    AccessToken accessToken = accessTokenRepository.findByRefreshToken(refreshToken.getValue());
    accessTokenRepository.delete(accessToken);
  }

  @Override
  public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
    String authenticationId = authenticationKeyGenerator.extractKey(authentication);
    if (null == authenticationId) {
      return null;
    }

    AccessToken accessToken = accessTokenRepository.findByAuthenticationId(authenticationId);
    return accessToken == null ? null : accessToken.getoAuth2AccessToken();
  }

  @Override
  public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId,
      String userName) {
    List<AccessToken> accessTokens = accessTokenRepository
        .findByClientIdAndUserName(clientId, userName);
    return extractAccessTokens(accessTokens);
  }

  @Override
  public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
    List<AccessToken> accessTokens = accessTokenRepository.findByClientId(clientId);
    return extractAccessTokens(accessTokens);
  }

  private Collection<OAuth2AccessToken> extractAccessTokens(List<AccessToken> tokens) {
    List<OAuth2AccessToken> accessTokens = new ArrayList<>();
    tokens.stream().forEach(token -> {
      accessTokens.add(token.getoAuth2AccessToken());
    });
    return accessTokens;
  }

}
