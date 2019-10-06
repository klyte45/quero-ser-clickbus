package com.klyte.places.services;

import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.entities.PlaceEntity;
import com.klyte.places.exception.DummyException;
import com.klyte.places.exception.PlaceNotFoundException;
import com.klyte.places.repository.PlaceRepository;
import com.klyte.places.util.ObjectWrapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.klyte.places.util.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceTests {

    @Autowired
    private PlaceService placeService;

    @MockBean
    private PlaceRepository repository;

    @Test
    public void testCreateSuccess() {
        PlaceDTO in = createRandomPlaceDTO();
        ObjectWrapper<PlaceEntity> savedEntity = new ObjectWrapper<>();
        Mockito.when(repository.save(any())).then((x) -> {
            PlaceEntity entity = x.getArgument(0);
            assertDtoEntitySameValue(in, entity);
            savedEntity.setValue(entity);
            return entity;
        });
        Mockito.when(repository.findByUrlSlug(any())).then((x) -> {
            String slug = x.getArgument(0);
            if (slug == null) {
                return null;
            }
            if (savedEntity.getValue() != null && slug.equals(savedEntity.getValue().getUrlSlug())) {
                return savedEntity.getValue();
            }
            return null;
        });
        PlaceDTO out = placeService.createPlace(in);
        assertInOutSameValue(in, out);
    }

    @Test(expected = DummyException.class)
    public void testCreateFailure() {
        PlaceDTO in = createRandomPlaceDTO();
        Mockito.when(repository.save(any())).thenThrow(new DummyException());
        placeService.createPlace(in);
    }


    @Test
    public void testUpdateSuccess() {
        PlaceDTO in = createRandomPlaceDTO();
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        ObjectWrapper<PlaceEntity> savedEntity = new ObjectWrapper<>();
        Mockito.when(repository.save(any())).then((x) -> {
            PlaceEntity entity = x.getArgument(0);
            assertDtoEntitySameValue(in, entity);
            savedEntity.setValue(entity);
            return entity;
        });
        Mockito.when(repository.findByUrlSlug(any())).then((x) -> {
            String slug = x.getArgument(0);
            if (slug == null) {
                return null;
            }
            if (inUrl.equals(slug)) {
                return createPlaceEntity(slug, "adas", "adas", "AA");
            } else if (savedEntity.getValue() != null && slug.equals(savedEntity.getValue().getUrlSlug())) {
                return savedEntity.getValue();
            }
            return null;
        });
        PlaceDTO out = placeService.updatePlace(inUrl, in);
        assertInOutSameValue(in, out);
    }

    @Test(expected = PlaceNotFoundException.class)
    public void testUpdateNotFound() {
        PlaceDTO in = createRandomPlaceDTO();
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        ObjectWrapper<PlaceEntity> savedEntity = new ObjectWrapper<>();
        Mockito.when(repository.save(any())).then((x) -> {
            PlaceEntity entity = x.getArgument(0);
            assertDtoEntitySameValue(in, entity);
            savedEntity.setValue(entity);
            return entity;
        });
        Mockito.when(repository.findByUrlSlug(any())).then((x) -> {
            String slug = x.getArgument(0);
            if (slug == null || inUrl.equals(slug)) {
                return null;
            } else if (savedEntity.getValue() != null && slug.equals(savedEntity.getValue().getUrlSlug())) {
                return savedEntity.getValue();
            }
            return null;
        });
        PlaceDTO out = placeService.updatePlace(inUrl, in);
        assertInOutSameValue(in, out);
    }

    @Test(expected = DummyException.class)
    public void testUpdateFailure() {
        PlaceDTO in = createRandomPlaceDTO();
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        ObjectWrapper<PlaceEntity> savedEntity = new ObjectWrapper<>();
        Mockito.when(repository.findByUrlSlug(any())).thenThrow(new DummyException());
        PlaceDTO out = placeService.updatePlace(inUrl, in);
        assertInOutSameValue(in, out);
    }

    @Test
    public void testGetSuccess() {
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        PlaceEntity outDb = createRandomPlaceEntity();
        Mockito.when(repository.findByUrlSlug(any())).then((x) -> {
            String slug = x.getArgument(0);
            if (slug == null) {
                return null;
            }
            if (inUrl.equals(slug)) {
                return outDb;
            }
            return null;
        });
        PlaceDTO out = placeService.getPlace(inUrl);
        assertDtoEntitySameValue(out, outDb);
    }

    @Test(expected = PlaceNotFoundException.class)
    public void testGetNotFound() {
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        Mockito.when(repository.findByUrlSlug(any())).then((x) -> null);
        placeService.getPlace(inUrl);
    }

    @Test(expected = DummyException.class)
    public void testGetFailure() {
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        Mockito.when(repository.findByUrlSlug(any())).thenThrow(new DummyException());
        placeService.getPlace(inUrl);
    }

    @Test
    public void testDeleteSuccess() {
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        Mockito.doNothing().when(repository).deleteByUrlSlug(any());
        placeService.deletePlace(inUrl);
    }

    @Test(expected = DummyException.class)
    public void testDeleteFailure() {
        String inUrl = RandomStringUtils.random((int) (1 + Math.random() * 12), true, true) + "-";
        Mockito.doThrow(new DummyException()).when(repository).deleteByUrlSlug(any());
        placeService.deletePlace(inUrl);
    }

    @Test
    public void testListSuccess() {
        List<PlaceEntity> outDb = createRandomSizeListEntities();
        Mockito.when(repository.findAll()).then((x) -> {
            return outDb;
        });
        List<PlaceDTO> out = placeService.listPlaces();
        assertListSame(out, outDb);
    }

    @Test(expected = DummyException.class)
    public void testListFailure() {
        Mockito.when(repository.findAll()).thenThrow(new DummyException());
        placeService.listPlaces();
    }

    private static void assertInOutSameValue(PlaceDTO in, PlaceDTO out) {
        assertEquals(in.getCity(), out.getCity());
        assertEquals(in.getState(), out.getState());
        assertEquals(in.getName(), out.getName());
        assertEquals(in.getUrlSlug(), out.getUrlSlug());
    }

    private static void assertDtoEntitySameValue(PlaceDTO dto, PlaceEntity entity) {
        assertEquals(dto.getCity(), entity.getCity());
        assertEquals(dto.getState(), entity.getState());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getUrlSlug(), entity.getUrlSlug());
    }

    private static void assertListSame(List<PlaceDTO> dtoList, List<PlaceEntity> entityList) {
        assertEquals(entityList.size(), dtoList.size());
        for (int i = 0; i < entityList.size(); i++) {
            assertDtoEntitySameValue(dtoList.get(i), entityList.get(i));
        }
    }

    private static List<PlaceEntity> createRandomSizeListEntities() {
        int size = (int) (Math.random() * 8 + 1);
        List<PlaceEntity> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(createRandomPlaceEntity());
        }
        return result;
    }
}
