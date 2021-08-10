package com.dept.webshop.web.controller;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.service.CatalogItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CatalogItemController.class)
class CatalogItemControllerTest {

  @Autowired
  private MockMvc               mockMvc;
  @Autowired
  private WebApplicationContext webApplicationContext;
  @MockBean
  private CatalogItemService    catalogItemService;
  @Autowired
  private ObjectMapper          objectMapper;

  @Before()
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void When_GetAll_Expect_ArrayList() throws Exception {
    Mockito.when(catalogItemService.findAll()).thenReturn(new ArrayList<>());
    mockMvc.perform(get("/webshop"))
            .andExpect(status().isOk())
            .andExpect(content().string("[]"));
  }

  @Test
  void When_GetById_Expect_ItemWithId() throws Exception {
    CatalogItem catalogItem = new CatalogItem(1L, "cube bajk", "description", 4545, null, null);
    Mockito.when(catalogItemService.getById(1L)).thenReturn(catalogItem);
    mockMvc.perform(get("/webshop/1"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$").value(catalogItem));
  }

  @Test
  void When_Create_Expect_NewItem() throws Exception {
    CatalogItem catalogItem = new CatalogItem(1L, "cube bajk", "description", 4545, null, null);
    Mockito.when(catalogItemService.create(any())).thenReturn(catalogItem);

    mockMvc.perform(post("/webshop")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(catalogItem)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void update() {
  }

  @Test
  void delete() {
  }

  @Test
  void searchByName() {
  }

  @Test
  void searchByNameOrDesc() {
  }

  @Test
  void searchByCategoriesAnd() {
  }

  @Test
  void searchByCategoriesOr() {
  }

}
