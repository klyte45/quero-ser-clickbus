package com.klyte.places.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klyte.places.dto.PlaceDTO;
import com.klyte.places.services.PlaceService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.klyte.places.util.TestUtils.createPlaceDTO;
import static com.klyte.places.util.TestUtils.createRandomPlaceDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
    private PlaceService placeService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Mockito.when(placeService.createPlace(any())).then((x) -> createPlaceDTO(x.getArgument(0)));
        Mockito.when(placeService.getPlace(any())).then((x) -> createPlaceDTO(x.getArgument(0), "TESTE", "Teste", "Teste"));
        Mockito.when(placeService.listPlaces()).thenReturn(Arrays.asList(new PlaceDTO(), new PlaceDTO()));
        Mockito.when(placeService.updatePlace(any(), any())).then((x) -> createPlaceDTO(x.getArgument(1)));
        Mockito.doNothing().when(placeService).deletePlace(any());
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
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true).toLowerCase();
        mockMvc.perform(get(baseUrl + "/" + uriTest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("urlSlug", is(uriTest)));
    }

    @Test
    public void testDelete() throws Exception {
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true);
        mockMvc.perform(delete(baseUrl + "/" + uriTest))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPut() throws Exception {
        String uriTest = RandomStringUtils.random((int) (1 + Math.random() * 8), true, true).toLowerCase();
        PlaceDTO randomInDTO = createRandomPlaceDTO();
        ensureOutValues(mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/" + uriTest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(randomInDTO)))
                .andExpect(status().isOk()), randomInDTO);
    }

    @Test
    public void testCreate() throws Exception {
        PlaceDTO randomInDTO = createRandomPlaceDTO();
        ensureOutValues(mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(randomInDTO)))
                .andExpect(status().isCreated()), randomInDTO);
    }

    private static ResultActions ensureOutValues(ResultActions result, PlaceDTO randomInDTO) throws Exception {
        return result.andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.urlSlug", is(randomInDTO.getUrlSlug())))
                .andExpect(jsonPath("$.name", is(randomInDTO.getName())))
                .andExpect(jsonPath("$.city", is(randomInDTO.getCity())))
                .andExpect(jsonPath("$.state", is(randomInDTO.getState())));
    }


}
