package net.mnp.spring.dao;

import net.mnp.spring.model.Token;

import java.util.Date;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
public interface TokenRepository {

    public void createNewToken(Token token);

    public void updateToken(String series, String tokenValue, Date lastUsed);

    public Token getTokenForSeries(String seriesId);

    public void removeUserTokens(final String username);
}
