package net.mnp.spring.dao.impl;

import net.mnp.spring.dao.TokenRepository;
import net.mnp.spring.model.Token;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmitry Natalenko on 27.10.2015.
 */
@Repository
@Transactional
public class TokenRepositoryImpl implements TokenRepository{


    @PersistenceContext
    private EntityManager entityManager;

    public void createNewToken(Token token) {
        entityManager.persist(token);
    }

    public void updateToken(String series, String tokenValue, Date lastUsed) {

        Token existingToken = entityManager.find(Token.class, series);
        existingToken.setTokenValue(tokenValue);
        existingToken.setDate(lastUsed);
        entityManager.merge(existingToken);
    }

    public Token getTokenForSeries(String seriesId) {
        Token token = entityManager.find(Token.class, seriesId);
        return token;
    }

    public void removeUserTokens(final String username) {
        Query query = entityManager.createQuery("SELECT t from Token t WHERE t.username = :username");
        query.setParameter("username", username);
        List<Token> tokens = query.getResultList();
        Token token = tokens.get(0);
        if (token != null) {
            entityManager.remove(token);
        }
    }
}
