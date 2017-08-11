package xyz.jeevan.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import xyz.jeevan.api.service.security.TokenStoreService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private static String REALM = "api-security";

  @Value("${app.oauth.client-id}")
  private String CLIENT_ID;

  @Value("${app.oauth.client-secret}")
  private String CLIENT_SECRET;

  @Autowired
  @Qualifier("tokenStore")
  private TokenStoreService tokenStore;

  @Autowired
  private UserApprovalHandler userApprovalHandler;

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(CLIENT_ID)
        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
        .authorities("ROLE_USER")
        .scopes("read", "write", "trust")
        .secret(CLIENT_SECRET)
        .accessTokenValiditySeconds(120)
        .refreshTokenValiditySeconds(600);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
        .authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.realm(REALM + "/client");
  }
}
