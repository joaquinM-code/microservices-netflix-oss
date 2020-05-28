package com.ecatom.oauthservice.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenAdditionalInfo tokenAdditionalInfo;

    public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenAdditionalInfo tokenAdditionalInfo) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenAdditionalInfo = tokenAdditionalInfo;
    }
    //Configures the access permissions to the token routes
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // /oauth/token
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("frontEndApp")//username for the app
                .secret(passwordEncoder.encode("12345"))//required app password
                .scopes("read", "write")//What can the app do
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);

        //this method configure the client access, many clients can by added by adding .and() after every configuration
        //Is like a 2FA, the client app must have the credentials and the users must provide their own
        //This allow us to revoke and authorize third party clients easily
        //https://stackoverflow.com/questions/31524474/spring-boot-configure-clientdetailsserviceconfigurer-in-authorizationserverconfi
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //Adding the additional info to the token
        //chains the default token accessTokenConverter() with the personalized on tokenAdditionalInfo()
        //This info also will be sent in the returned JSON
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenAdditionalInfo, accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager)//Registers the authenticationManager that verifies the user credentials
                //.accessTokenConverter(accessTokenConverter())//Converts the user info to a token and adds the secret
                .tokenEnhancer(tokenEnhancerChain) // Adds the custom token
                .tokenStore(tokenStore()); //Stores the token
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    //This bean creates the token and adds the signature to it
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("move_this_key_to_config_server");
        return jwtAccessTokenConverter;
    }
}
