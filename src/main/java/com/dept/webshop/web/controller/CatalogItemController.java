package com.dept.webshop.web.controller;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.service.CatalogItemService;
import com.dept.webshop.web.dto.CatalogItemDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/webshop")
public class CatalogItemController {

  private final CatalogItemService catalogItemService;

  @Autowired
  public CatalogItemController(CatalogItemService catalogItemService) {
    this.catalogItemService = catalogItemService;
  }

  @GetMapping
  public ResponseEntity<List<CatalogItem>> getAll() {
    List<CatalogItem> catalogItems = catalogItemService.findAll();
    return ResponseEntity.ok(catalogItems);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CatalogItem> getById(@PathVariable Long id) {
    return ResponseEntity.ok(catalogItemService.getById(id));
  }

  @PostMapping
  public ResponseEntity<CatalogItem> create(@RequestBody CatalogItemDto catalogItemDto) {
    return ResponseEntity.ok(catalogItemService.create(catalogItemDto));
  }

  @PutMapping
  public ResponseEntity<CatalogItem> update(@RequestBody  CatalogItem catalogItem) {
    return ResponseEntity.ok(catalogItemService.update(catalogItem));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CatalogItem> delete(@PathVariable("id") Long id) {
    return ResponseEntity.ok(catalogItemService.delete(id));
  }

  @GetMapping("/searchByName/{query}")
  public ResponseEntity<List<CatalogItem>> searchByName(@PathVariable("query") String query) {
    return ResponseEntity.ok(catalogItemService.findByName(query));
  }

  @GetMapping("/searchByNameOrDesc/{query}")
  public ResponseEntity<List<CatalogItem>> searchByNameOrDesc(@PathVariable("query") String query) {
    return ResponseEntity.ok(catalogItemService.findByNameOrDesc(query));
  }

  @GetMapping("/searchByCategoriesAnd")
  public ResponseEntity<List<CatalogItem>> searchByCategoriesAnd(@RequestBody List<String> categories) {
    return ResponseEntity.ok(catalogItemService.findByCategoriesAnd(categories));
  }

  @GetMapping("/searchByCategoriesOr")
  public ResponseEntity<List<CatalogItem>> searchByCategoriesOr(@RequestBody List<String> categories) {
    return ResponseEntity.ok(catalogItemService.findByCategoriesOr(categories));
  }
}
