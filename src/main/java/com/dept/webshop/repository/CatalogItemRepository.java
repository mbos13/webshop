package com.dept.webshop.repository;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.model.Categories;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogItemRepository {

  private        List<CatalogItem> catalogItems = new ArrayList<>();
  private static long              counter      = 1L;

  public CatalogItem save(CatalogItem item) {
    if (item.getId() == null) {
      item.setId(counter++);
      catalogItems.add(item);
    } else {
      for (int i = 0, catalogItemsSize = catalogItems.size(); i < catalogItemsSize; i++) {
        CatalogItem catalogItem = catalogItems.get(i);
        if (catalogItem.getId().equals(item.getId())) {
          catalogItems.set(i, item);
          break;
        }
      }
    }
    return item;
  }

  public CatalogItem getById(Long id) {
    for (CatalogItem item : catalogItems) {
      if (item.getId().equals(id)) {
        return item;
      }
    }
    return null;
  }

  public CatalogItem delete(Long id) {
    return catalogItems.remove(catalogItems.indexOf(getById(id)));
  }

  public List<CatalogItem> getAll() {
    return catalogItems;
  }

  public List<CatalogItem> findByCategory(Categories category) {
    return catalogItems
            .stream()
            .filter(item -> item.getCategories() != null && item.getCategories().contains(category))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByCategoriesAnd(List<Categories> categories) {
    return catalogItems
            .stream()
            .filter(item -> item.getCategories() != null && item.getCategories().containsAll(categories))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByCategoriesOr(List<Categories> categories) {
    return catalogItems
            .stream()
            .filter(item -> item.getCategories() != null && !Collections.disjoint(item.getCategories(), categories))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByName(String query) {
    return catalogItems
            .stream()
            .filter(item -> match(item.getName(), query))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByNameOrDesc(String query) {
    return catalogItems
            .stream()
            .filter(item -> (item.getName().toLowerCase().contains(query.toLowerCase())
                    || item.getDescription().toLowerCase().contains(query.toLowerCase())))
            .collect(Collectors.toList());
  }

  boolean match(String name, String query) {
    Pattern pattern = Pattern.compile("\\b" + query.toLowerCase() + "\\b");
    Matcher matcher = pattern.matcher(name.toLowerCase());
    return matcher.find();
  }

}

