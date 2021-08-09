package com.dept.webshop.repository;

import com.dept.webshop.model.CatalogItem;
import com.dept.webshop.model.Categories;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class CatalogItemRepository {

  private List<CatalogItem> catalogItems = new ArrayList<>();

  public CatalogItemRepository() throws URISyntaxException {
    initializeCatalogItems();
  }

  public CatalogItem save(CatalogItem item) {
    if (item.getId() == null) {
      item.setId((long) catalogItems.size());
    }
    catalogItems.add(item);
    return item;
  }

  public CatalogItem getById(Long id) {
    for (CatalogItem item : catalogItems) {
      if (item.getId() == id) {
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
            .filter(item -> item.getCategories().contains(category))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByCategoriesAnd(List<Categories> categories) {
    return catalogItems
            .stream()
            .filter(item -> item.getCategories().containsAll(categories))
            .collect(Collectors.toList());
  }

  public List<CatalogItem> findByCategoriesOr(List<Categories> categories) {
    return catalogItems
            .stream()
            .filter(item -> !Collections.disjoint(item.getCategories(), categories))
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

  void initializeCatalogItems() throws URISyntaxException {
    this.catalogItems.add(new CatalogItem(1L, "CUBE STEREO 150 C:62 RACE 29 OLIVE´N´GREY 2021",
            "Stereo 150 C:62 Race model dizajniran je s ciljem pružanja najkvalitetnijih trail performansi i "
                    + "vrhunske udobnosti. 29er kotači, agilna trail geometrija i uglađen dizajn okvira omogućavaju vam maksimalno zadovoljstvo "
                    + "pri savladavanju staza. C:62 karbonske monocoque cijevi okvira pažljivo su oblikovane TwinMold tehnologijom kako bi "
                    + "omogućile idealnu kombinaciju male težine s vrhunskom čvrstoćom.",
            24399.95, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/37926/cube-stereo-150-c62-race-29-olive-n-grey-2021_5f353f74ec60c.jpg")),
            Arrays.asList(Categories.BICIKLI, Categories.MTB)));

    this.catalogItems.add(new CatalogItem(2L, "GIANT TRANCE X 29 3 CRNA/CRNA KROM",
            "Potpuno novi Giant Trance X 29 namijenjen je onim vozačima koji najteži dio staze smatraju svojim omiljenim dijelom vožnje. "
                    + "S dužim hodom suspenzije i podesivom geometrijom, ovaj aluminijski fully sposoban je savladati svaki izazov.\n"
                    + "Dizajniran je kako bi omogućio da brzo i glatko vozite po najtežim stazama, a dolazi s laganim, izdržljivim i "
                    + "robusnim aluminijskim okvirom te specifičnom podesivom geometrijom za ugodnu i zabavnu vožnju.",
            21999.50, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/41218/giant-trance-x-29-3-crna-crna-krom_61000251a8b39.jpg")),
            Arrays.asList(Categories.BICIKLI, Categories.MTB)));

    this.catalogItems.add(new CatalogItem(3L, "BIANCHI OLTRE XR.4 DISC DURA ACE DI2 5K-CK16/BLACK GLOSSY",
            "Bianchi Oltre XR4 izrađen je sa najnovijim tehnologijama i istraživanjima kako bi pružao najbolju aerodinamiku tijekom vožnje. "
                    + "Aerodinamičan dizajn postigao se sa Computational Fluid Dynamic (CFD)  tehnologijom gdje se optimizirao svaki aspekt okvira. "
                    + "Zahvaljujući Bianchi CV sustavu, kontroli snage, preciznosti i modulaciji disk kočnica, novi Bianchi Oltre XR4 Disc savršen je, "
                    + "brz, aero cestovni bicikl.",
            74999.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/40946/bianchi-oltre-xr4-disc-dura-ace-di2-5k-ck16-black-_60dd60b1bbd24.jpg")),
            Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI)));

    this.catalogItems.add(new CatalogItem(4L, "GIANT REVOLT 2 BOJA BETONA",
            "Šljunak, blato i slikovite rute nude neke od najboljih prilika za slobodu i avanturu. Ovaj lagani aluminijski višenamjenski bicikl "
                    + "savršen je način da se maknete od svega i istražite staze o kojima ste oduvijek razmišljali, ali nikada ih niste vozili.",
            8999.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/41208/giant-revolt-2-boja-betona_60ffd4c15c8d4.jpg")),
            Arrays.asList(Categories.BICIKLI, Categories.CESTOVNI)));

    this.catalogItems.add(new CatalogItem(5L, "BIDON CUBE ICON RED 750ML",
            "Bidon koji s dizajnom prati sve Cube proizvode, te tako čini savršenom opremom za vaš Cube bicikl. "
                    + "Proizveden u Europi, lagan za rukovanje, fleksibilan i izdržljiv bidon, "
                    + "široki čep omogućava jednostavnije punjenje i čišćenje.",
            39.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/4116/bidon-cube-icon-red-750ml-keindl-sport_551b9554ca283.jpg")),
            Arrays.asList(Categories.OPREMA, Categories.BIDONI)));

    this.catalogItems.add(new CatalogItem(6L, "BIDON ELITE JET CLEAR 750ML RED LOGO",
            "Jet kombinira kvalitetu i performanse najcjenjenijih Elite bidona, kao što su mala težina, velik protok tekućine "
                    + "te čep bez okusa i mirisa, a sve to uz integraciju biorazgradivog plastičnog materijala za smanjenje utjecaja na okoliš.",
            49.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/36933/bidon-elite-jet-clear-750ml-red-logo_5efaee3585d96.jpeg")),
            Arrays.asList(Categories.OPREMA, Categories.BIDONI)));

    this.catalogItems.add(new CatalogItem(6L, "BIDON CUBE ICON TRANSPARENT 750 ML",
            "Bidon koji s dizajnom prati sve CUBE proizvode, što ga čini savršenom opremom za vaš CUBE bicikl. Proizveden u Europi, "
                    + "lagan za rukovanje, fleksibilan i izdržljiv bidon, s širokim čepom koji omogućuje jednostavnije punjenje i čišćenje.",
            39.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/4119/bidon-cube-icon-transparent-750-ml_5e60e32e7dea2.jpg")),
            Arrays.asList(Categories.OPREMA, Categories.BIDONI)));

    this.catalogItems.add(new CatalogItem(7L, "LOKOT NA KLJUČ STEEL-O-CHAIN 5805K/75 RED ABUS 72489-3",
            "\n"
                    + "Duljina: 75 cm\n"
                    + "\n"
                    + "Debljina lanca: 5 mm\n"
                    + "\n"
                    + "Težina: 510 g\n"
                    + "\n"
                    + "Stupanj sigurnosti: 5",
            219.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/23891/72489-5805k-75-rd-a-3_5ab8b3ebaf7d3.jpg")),
            Arrays.asList(Categories.OPREMA, Categories.LOKOTI)));

    this.catalogItems.add(new CatalogItem(8L, "LOKOT CATENA 6806K/75 NEON GREEN ABUS 82514-9",
            "kvadratni lanac debljine 6 mm s tekstilnim rukavom koji sprječava oštećenje laka\n"
                    + "lanac je izrađen od posebno kaljenog čelika\n"
                    + "dobra zaštita pri malom riziku od krađe\n"
                    + "težina: 850 g\n"
                    + "promjer: 6 mm\n"
                    + "duljina: 75 cm\n"
                    + "razina zaštite: 6",
            229.00, Arrays.asList(
            new URI("https://keindl-sport.hr/upload/catalog/product/30220/lokot-catena-6806k-75-neon-green-abus-82514-9_5c9b31f608b83.jpg")),
            Arrays.asList(Categories.OPREMA, Categories.LOKOTI)));
  }

}

