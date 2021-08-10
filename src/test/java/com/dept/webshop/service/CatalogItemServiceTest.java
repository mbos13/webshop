package com.dept.webshop.service;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.repository.CatalogItemRepository;
import com.dept.webshop.web.dto.CatalogItemDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatalogItemServiceTest {

  @Autowired
  private CatalogItemService catalogItemService;

  @MockBean
  private CatalogItemRepository catalogItemRepository;

  @Test
  void When_FindAll_Expect_ListOfItems() {
    List<CatalogItem> items = new ArrayList<>();

    items.add(new CatalogItem(1L, "Cube bajk", "opis bicikla", 254.56, null, null));
    items.add(new CatalogItem(2L, "Giant bajk", "opis bicikla", 245.56, null, null));
    items.add(new CatalogItem(2L, "Cube lokot", "opis lokota", 245.56, null, null));

    Mockito.when(catalogItemRepository.getAll()).thenReturn(items);
    var result = catalogItemService.findAll();
    Assertions.assertEquals(items, result);
  }

  @Test
  void When_GetById_Expect_ItemWithId() {
    CatalogItem catalogItem = new CatalogItem(1L,"cube bajk", "opis bajka", 655.54, null, null);
    Mockito.when(catalogItemRepository.getById(1L)).thenReturn(catalogItem);
    var result = catalogItemService.getById(1L);
    Assertions.assertEquals(catalogItem, result);
  }

  @Test
  void When_Create_Expect_CreatedItem() {
    CatalogItemDto catalogItemDto = new CatalogItemDto("cube bajk", "opis bajka", 655.54, null, null);
    CatalogItem catalogItem =
            new CatalogItem(1L, catalogItemDto.getName(), catalogItemDto.getDescription(), catalogItemDto.getPrice(),
                    catalogItemDto.getImages(), catalogItemDto.getCategories());
    Mockito.when(catalogItemRepository.save(any())).thenReturn(catalogItem);
    var result = catalogItemService.create(catalogItemDto);
    Assertions.assertEquals(catalogItem, result);
  }

  @Test
  void When_Update_Expect_UpdatedItem() {
    CatalogItem updatedCatalogItem = new CatalogItem(1L,"cube bajk novi", "opis bajka", 6545.54, null, null);
    Mockito.when(catalogItemRepository.save(updatedCatalogItem)).thenReturn(updatedCatalogItem);

    var result = catalogItemService.update(updatedCatalogItem);
    Assertions.assertEquals(updatedCatalogItem, result);
  }

  @Test
  void delete() {
  }

  @Test
  void findByCategory() {
  }

  @Test
  void findByCategoriesAnd() {
  }

  @Test
  void findByCategoriesOr() {
  }

  @Test
  void findByName() {
  }

  @Test
  void findByNameOrDesc() {
  }

}
