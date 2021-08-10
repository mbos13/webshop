package com.dept.webshop.service;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.web.dto.CatalogItemDto;
import java.util.List;

public interface CatalogItemService {

  List<CatalogItem> findAll();

  CatalogItem getById(Long id);

  CatalogItem create(CatalogItemDto catalogItemDto);

  CatalogItem update(CatalogItem catalogItem);

  CatalogItem delete(Long id);

  List<CatalogItem> findByCategory(String categoryQuery);

  /**
   *
   * @param categoryList contains categories which we are searching
   * @return list of CatalogItems which have all the categories from categorySet
   */
  List<CatalogItem> findByCategoriesAnd(List<String> categoryList);

  /**
   *
   * @param categoryList contains categories which we are searching
   * @return list of CatalogItems where field catalogItem.categories
   * contains at least one category from @param categorySet
   */
  List<CatalogItem> findByCategoriesOr(List<String> categoryList);

  /**
   *
   * @param query
   * @return list of CatalogItems where @param query is substring of
   * catalogItem.name
   */
  List<CatalogItem> findByName(String query);

  /**
   *
   * @param query
   * @return list of CatalogItems where @param query is substring of
   * catalogItem.name or catalogItem.description
   */
  List<CatalogItem> findByNameOrDesc(String query);
}
