package it.de.seve.fate.shorturl.dao;

import de.seven.fate.shorturl.dao.URLEntryDAO;
import de.seven.fate.shorturl.model.URLEntry;
import de.seven.fate.shorturl.model.URLEntryBuilder;
import de.seven.fate.model.util.CollectionUtil;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by Mario on 03.05.2016.
 */
@RunWith(Arquillian.class)
public class URLEntryDAOIT {

    @Inject
    URLEntryDAO sut;

    @Inject
    URLEntryBuilder builder;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction transaction;

    List<URLEntry> models;

    @Before
    public void setUp() throws Exception {

        models = builder.list();

        transactional(() -> sut.saveOrUpdate(models));
    }

    @After
    public void tearDown() throws Exception {

        transactional(sut::removeAll);
    }

    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentUtil.createDeployment();
    }


    @Test
    public void get() throws Exception {

        URLEntry model = CollectionUtil.random(models);

        URLEntry entity = sut.get(model);

        Assert.assertNotNull(entity);
        Assert.assertEquals(model, entity);
    }

    @Test
    public void getEntityByLongUrl() throws Exception {

        URLEntry model = CollectionUtil.random(models);
        String longUrl = model.getLongUrl();

        URLEntry entity = sut.getURLEntityByLongUrl(longUrl);

        Assert.assertNotNull(entity);
        Assert.assertEquals(model, entity);
    }

    @Test
    public void getEntityByShortUrl() throws Exception {

        URLEntry model = CollectionUtil.random(models);
        String shortUrl = model.getShortUrl();

        URLEntry entity = sut.getURLEntityByShortUrl(shortUrl);

        Assert.assertNotNull(entity);
        Assert.assertEquals(model, entity);
    }


    private void transactional(Runnable runnable) throws Exception {
        transaction.begin();
        em.joinTransaction();

        runnable.run();

        transaction.commit();
    }
}