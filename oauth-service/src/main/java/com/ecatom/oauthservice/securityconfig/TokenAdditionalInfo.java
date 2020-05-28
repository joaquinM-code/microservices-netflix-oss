package com.ecatom.oauthservice.securityconfig;

import com.ecatom.oauthservice.services.UserServiceInterface;
import com.ecatom.usercommons.model.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenAdditionalInfo implements TokenEnhancer {

    //This class adds additional information to the generated token by fetching the authenticated user

    private final UserServiceInterface userDetails;

    public TokenAdditionalInfo(UserServiceInterface userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String , Object> tokenInfo = new HashMap<>();
        User user = userDetails.findByUserName(oAuth2Authentication.getName());
        tokenInfo.put("fName" , user.getFirstName());
        tokenInfo.put("lName" , user.getLastName());
        tokenInfo.put("email" , user.getEmail());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(tokenInfo);
        return oAuth2AccessToken;
    }
}
