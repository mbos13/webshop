package com.dept.webshop.service.impl;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.model.Categories;
import com.dept.webshop.repository.CatalogItemRepository;
import com.dept.webshop.service.CatalogItemService;
import com.dept.webshop.web.dto.CatalogItemDto;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CatalogItemServiceImpl implements CatalogItemService {

  private static final Logger                LOGGER = LoggerFactory.getLogger(CatalogItem.class);
  private final        CatalogItemRepository catalogItemRepository;
  private final        ModelMapper           modelMapper;

  public CatalogItemServiceImpl(CatalogItemRepository catalogItemRepository,
          ModelMapper modelMapper) {
    this.catalogItemRepository = catalogItemRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public List<CatalogItem> findAll() {
    LOGGER.info("Retrieving all Catalog Items.");
    return catalogItemRepository.getAll();
  }

  @Override
  public CatalogItem getById(int id) {
    LOGGER.info("Getting Catalog item with id = " + id);
    return catalogItemRepository.getById((long) id);
  }

  @Override
  public CatalogItem create(CatalogItemDto catalogItemDto) {
    CatalogItem catalogItem = modelMapper.map(catalogItemDto, CatalogItem.class);
    LOGGER.info("Saving " + catalogItem.toString());
    return catalogItemRepository.save(catalogItem);
  }

  @Override
  public CatalogItem update(CatalogItem catalogItem) {
    LOGGER.info("Updating " + catalogItem.toString());
    return catalogItemRepository.save(catalogItem);
  }

  @Override
  public CatalogItem delete(int id) {
    LOGGER.info("Deleting Catalog item with id = " + id);
    return catalogItemRepository.delete((long) id);
  }

  @Override
  public List<CatalogItem> findByCategory(String categoryQuery) {
    Categories category = Categories.valueOf(categoryQuery);
    return catalogItemRepository.findByCategory(category);
  }

  @Override
  public List<CatalogItem> findByCategoriesAnd(List<String> categoryList) {
    List<Categories> categories = categoryList.stream()
            .map(Categories::valueOf)
            .collect(Collectors.toList());
    return catalogItemRepository.findByCategoriesAnd(categories);
  }

  @Override
  public List<CatalogItem> findByCategoriesOr(List<String> categoryList) {
    List<Categories> categories = categoryList.stream()
            .map(Categories::valueOf)
            .collect(Collectors.toList());
    return catalogItemRepository.findByCategoriesOr(categories);
  }

  @Override
  public List<CatalogItem> findByName(String query) {
    return catalogItemRepository.findByName(query);
  }

  @Override
  public List<CatalogItem> findByNameOrDesc(String query) {
    return catalogItemRepository.findByNameOrDesc(query);
  }

}
