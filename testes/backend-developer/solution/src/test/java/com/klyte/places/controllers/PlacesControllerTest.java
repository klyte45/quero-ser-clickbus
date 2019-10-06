package com.klyte.places.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klyte.places.PlacesApplication;
import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.services.PlacesService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PlacesApplication.class})
@WebAppConfiguration
public class PlacesControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8
    );
    private static final String baseUrl = "/places";

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @InjectMocks
    private PlacesController controller;

    @MockBean
    private PlacesService placesService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Mockito.when(placesService.createPlace(any())).then((x) -> x.getArgument(0));
        Mockito.when(placesService.getPlace(any())).then((x) -> new PlaceDTO("TESTE", x.getArgument(0), "Teste", "Teste", null, null));
        Mockito.when(placesService.listPlaces()).thenReturn(Arrays.asList(new PlaceDTO(), new PlaceDTO()));
        Mockito.when(placesService.updatePlace(any(), any())).then((x) -> x.getArgument(1));
        Mockito.doNothing().when(placesService).deletePlace(any());
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGet() throws Exception {
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        mockMvc.perform(get(baseUrl + "/" + uriTest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("slug", is(uriTest)));
    }

    @Test
    public void testDelete() throws Exception {
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        mockMvc.perform(delete(baseUrl + "/" + uriTest))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPut() throws Exception {
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String slug = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String name = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String city = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String state = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/" + uriTest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPlaceDTO(slug, name, city, state))))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.slug", is(slug)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.city", is(city)))
                .andExpect(jsonPath("$.state", is(state)));
    }

    @Test
    public void testCreate() throws Exception {
        String slug = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String name = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String city = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        String state = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createPlaceDTO(slug, name, city, state))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.slug", is(slug)))
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.city", is(city)))
                .andExpect(jsonPath("$.state", is(state)));
    }

    private static PlaceDTO createPlaceDTO(String slug, String name, String city, String state) {
        PlaceDTO result = new PlaceDTO();
        result.setUrlSlug(slug);
        result.setState(state);
        result.setCity(city);
        result.setName(name);
        return result;
    }
}
