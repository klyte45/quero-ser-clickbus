package com.klyte.places.entity;

import com.klyte.places.entities.PlaceEntity;
import com.klyte.places.repository.PlaceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.klyte.places.util.TestUtils.createRandomPlaceEntity;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PlaceEntityFieldsTest {

    @Autowired
    private PlaceRepository repository;

    @Test
    public void testPlaceLifecycle() {
        PlaceEntity entity = createRandomPlaceEntity();
        String slug = entity.getUrlSlug();
        entity = repository.saveAndFlush(entity);
        assertNotNull(entity.getId());
        assertNotNull(entity.getCreationDate());
        assertNotNull(entity.getUpdateDate());
        Date oldUpdateDate = entity.getUpdateDate();
        entity.setCity("AAAAA A");
        entity = repository.saveAndFlush(entity);
        assertNotEquals(oldUpdateDate.getTime(), entity.getUpdateDate().getTime());
        assertEquals(entity, repository.findByUrlSlug(slug));
        repository.deleteByUrlSlug(slug);
        assertNull(repository.findByUrlSlug(slug));
    }
}
