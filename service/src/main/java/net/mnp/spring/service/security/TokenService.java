package net.mnp.spring.service.security;

import net.mnp.spring.dao.TokenRepository;
import net.mnp.spring.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Service
public class TokenService implements PersistentTokenRepository {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        tokenRepository.createNewToken(new Token(token));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        tokenRepository.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Token token = tokenRepository.getTokenForSeries(seriesId);
        if (token == null) {
            return null;
        }
        return new PersistentRememberMeToken(token.getUsername(),
                token.getSeries(), token.getTokenValue(), token.getDate());
    }

    @Override
    public void removeUserTokens(String username) {
        tokenRepository.removeUserTokens(username);
    }
}