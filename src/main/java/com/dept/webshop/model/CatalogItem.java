package com.dept.webshop.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Size;

public class CatalogItem {

  private Long id;

  @Size(max = 100)
  private String name;

  @Size(max = 2000)
  private String description;

  private double price;

  private List<URI> images = new ArrayList<>();

  private List<Categories> categories = new ArrayList<>();

  public CatalogItem() {
  }

  public CatalogItem(Long id, @Size(max = 100) String name,
          @Size(max = 2000) String description, double price, List<URI> images,
          List<Categories> categories) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.images = images;
    this.categories = categories;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    this.images = new ArrayList<>(images);
  }

  public List<Categories> getCategories() {
    return categories;
  }

  public void setCategories(List<Categories> categories) {
    this.categories = new ArrayList<>(categories);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CatalogItem)) {
      return false;
    }
    CatalogItem that = (CatalogItem) o;
    return Double.compare(that.getPrice(), getPrice()) == 0 &&
            Objects.equals(getId(), that.getId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getDescription(), that.getDescription()) &&
            Objects.equals(getImages(), that.getImages()) &&
            Objects.equals(getCategories(), that.getCategories());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDescription(), getPrice(), getImages(), getCategories());
  }

  @Override
  public String toString() {
    return "CatalogItem{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", images=" + images +
            ", categories=" + categories +
            '}';
  }

}
