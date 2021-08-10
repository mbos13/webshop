package com.dept.webshop.repository;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.model.Categories;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CatalogItemRepositoryTest {

  @Autowired
  private CatalogItemRepository catalogItemRepository;

  private List<CatalogItem> catalogItems = new ArrayList<>();

  private void prepareDate() {
    catalogItems
            .add(new CatalogItem(null, "Cube road bicycle 2021", "New generation of cube road bicycles.", 2488.99, null,
                    Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI)));

    catalogItems.add(new CatalogItem(null, "Giant trace bicycle 2021", "New generation of Giant bikes.", 2488.99, null,
            Arrays.asList(Categories.BICIKLI, Categories.MTB)));

    catalogItems
            .add(new CatalogItem(null, "Bicycle Tool Set", "Bicycle Tool Set for Tyre, Chain, Shimano Component Repair",
                    248.90, null,
                    Collections.singletonList(Categories.OPREMA)));

    catalogItems.add(new CatalogItem(null, "Bicycle Tools", "Bicycle Tool kit", 248.90, null,
            Collections.singletonList(Categories.OPREMA)));
  }

  private void removeData() {
    List<CatalogItem> allCatalogItems = catalogItemRepository.getAll();
    for (CatalogItem item : allCatalogItems) {
      catalogItemRepository.delete(item.getId());
    }
  }

  @Test
  void When_SavingNewItem_Expect_ItemInList() {
    CatalogItem catalogItem = new CatalogItem(null, "Cube road bicycle 2019", "Cube road bicycle.", 2488.99, null,
            Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI));
    CatalogItem savedItem = catalogItemRepository.save(catalogItem);
    assertTrue(catalogItemRepository.getAll().contains(savedItem));
  }

  @Test
  void When_GetById_Expect_ReturnSavedItem() {
    CatalogItem catalogItem = new CatalogItem(null, "Cube road bicycle 2018", "Cube road bicycle.", 2488.99, null,
            Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI));
    catalogItem.setId(catalogItemRepository.save(catalogItem).getId());
    assertEquals(catalogItemRepository.getById(catalogItem.getId()), catalogItem);
  }

  @Test
  void When_DeleteNotExistingItem_Expect_IndexOutOfBoundsException() {
    CatalogItem catalogItem = new CatalogItem(null, "Cube road bicycle 2017", "Cube road bicycle.", 2488.99, null,
            Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI));
    catalogItem.setId(catalogItemRepository.save(catalogItem).getId());
    assertThrows(IndexOutOfBoundsException.class, () -> catalogItemRepository
            .delete(catalogItem.getId() + 1));
  }

  @Test
  void When_DeleteExistingItem_Expect_ItemNotInList() {
    prepareDate();
    CatalogItem savedItem = catalogItemRepository.save(catalogItems.get(0));
    assertFalse(catalogItemRepository.getAll().contains(catalogItemRepository.delete(savedItem.getId())));
  }

  @Test
  void When_GetAll_Expect_AllItems() {
    List<CatalogItem> allCatalogItems = catalogItemRepository.getAll();
    for (CatalogItem item : allCatalogItems) {
      catalogItemRepository.delete(item.getId());
    }
    assertEquals(0, catalogItemRepository.getAll().size());
  }

  @Test
  void When_FindByCategory_Expect_ItemsWithCategory() {
    prepareDate();
    for (CatalogItem item : catalogItems) {
      catalogItemRepository.save(item);
    }
    List<CatalogItem> items = catalogItemRepository.getAll();
    List<CatalogItem> categoryItems = catalogItemRepository.findByCategory(Categories.CESTOVNI);

    List<CatalogItem> filteredItems = new ArrayList<>();
    for (CatalogItem item : items) {
      if (item.getCategories() != null && item.getCategories().contains(Categories.CESTOVNI)) {
        filteredItems.add(item);
      }
    }

    assertTrue(categoryItems.containsAll(filteredItems));
  }

  @Test
  void When_FindByCategoriesAnd_Expect_ItemsWithAllCategories() {
    removeData();
    prepareDate();
    for (CatalogItem item : catalogItems) {
      catalogItemRepository.save(item);
    }
    List<CatalogItem> items = catalogItemRepository.getAll();
    List<CatalogItem> categoryItems =
            catalogItemRepository.findByCategoriesAnd(Arrays.asList(Categories.CESTOVNI, Categories.BICIKLI));

    List<CatalogItem> filteredItems = new ArrayList<>();
    for (CatalogItem item : items) {
      if (item.getCategories() != null && item.getCategories().containsAll(Arrays.asList(Categories.CESTOVNI, Categories.BICIKLI))) {
        filteredItems.add(item);
      }
    }

    assertTrue(categoryItems.containsAll(filteredItems));
  }

  @Test
  void When_FindByCategoriesOr_Expect_ItemsWithAtLeastOneCategory() {
    for (CatalogItem item : catalogItems) {
      catalogItemRepository.save(item);
    }
    List<CatalogItem> items = catalogItemRepository.getAll();
    List<CatalogItem> categoryItems =
            catalogItemRepository.findByCategoriesOr(Arrays.asList(Categories.CESTOVNI, Categories.OPREMA));

    List<CatalogItem> filteredItems = new ArrayList<>();
    for (CatalogItem item : items) {
      if (item.getCategories() != null && item.getCategories().containsAll(Arrays.asList(Categories.CESTOVNI, Categories.OPREMA))) {
        filteredItems.add(item);
      }
    }

    assertTrue(categoryItems.containsAll(filteredItems));
  }

  @Test
  void When_FindByName_Expect_ItemsWithExactStringInName() {
    CatalogItem catalogItem1 = new CatalogItem(null, "Bosh drill", "", 2488.99, null,
            null);
    CatalogItem catalogItem2 = new CatalogItem(null, "Drill for wood", "", 2488.99, null,
            null);
    CatalogItem catalogItem3 = new CatalogItem(null, "Mandrill monkey", "", 2488.99, null,
            null);

    catalogItemRepository.save(catalogItem1);
    catalogItemRepository.save(catalogItem2);
    catalogItemRepository.save(catalogItem3);

    List<CatalogItem> items = catalogItemRepository.findByName("drill");
    assertTrue(items.containsAll(Arrays.asList(catalogItem1, catalogItem2)));
    assertFalse(items.contains(catalogItem3));
  }

  @Test
  void When_FindByNameOrDesc_Expect_ItemsWithQueryStringInNameOrDescription() {
    CatalogItem catalogItem1 = new CatalogItem(null, "Bosh", "drilling toy", 2488.99, null,
            null);
    CatalogItem catalogItem2 = new CatalogItem(null, "Drill for wood", "", 2488.99, null,
            null);
    CatalogItem catalogItem3 = new CatalogItem(null, "Mandrill monkey", "", 2488.99, null,
            null);

    catalogItemRepository.save(catalogItem1);
    catalogItemRepository.save(catalogItem2);
    catalogItemRepository.save(catalogItem3);

    List<CatalogItem> items = catalogItemRepository.findByNameOrDesc("drill");
    assertTrue(items.containsAll(Arrays.asList(catalogItem1, catalogItem2, catalogItem3)));
  }

}
