package com.dept.webshop.web.dto;

import com.dept.webshop.model.Categories;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.Size;

public class CatalogItemDto {

  @Size(max = 100)
  private String name;

  @Size(max = 2000)
  private String description;

  private double price;

  private List<URI> images = new LinkedList<>();

  private List<Categories> categories = new LinkedList<>();

  public CatalogItemDto() {
  }

  public CatalogItemDto(@Size(max = 100) String name,
          @Size(max = 2000) String description, double price, List<URI> images,
          List<Categories> categories) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.images = images;
    this.categories = categories;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public List<URI> getImages() {
    return images;
  }

  public void setImages(List<URI> images) {
    this.images = images;
  }

  public List<Categories> getCategories() {
    return categories;
  }

  public void setCategories(List<Categories> categories) {
    this.categories = categories;
  }

}
