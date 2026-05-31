package pl.kinga.kolekcjegeneryki.answears;

import java.math.BigDecimal;
import java.util.*;

public class ProductCatalog {
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    //#Basic methods
    public void addProduct(Product p) {
        products.add(p);
    }

    public void printAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public List<Product> getProductsReadOnly() {
        return Collections.unmodifiableList(products);
    }

    //#Filter methods
    public List<Product> getByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product p : products) {
            if (p.category().equals(category)) {
                productsByCategory.add(p);
            }
        }
        if (productsByCategory.isEmpty()) {
            System.out.println("Products of this category not found!");
        }
        return productsByCategory;
    }

    public List<Product> getInPriceRange(BigDecimal min, BigDecimal max) {
        List<Product> rangedProducts = new ArrayList<>();
        for (Product p : products) {
            if ((p.price().compareTo(min) >= 0 && (p.price().compareTo(max) <= 0))) {
                rangedProducts.add(p);
            }
        }
        if (rangedProducts.isEmpty()) {
            System.out.println("Products of this price range not found!");
        }
        return rangedProducts;
    }

    //#Remove methods
    public void removeOutOfStock() {
        products.removeIf(p -> !p.inStock());
    }

    public void removeCheaperThan(BigDecimal minPrice) {
        Iterator<Product> it = products.iterator();
        while (it.hasNext()) {
            if ((it.next().price()).compareTo(minPrice) <= 0) {
                it.remove();
            }
        }
    }

    //#Sort methods
    public List<Product> sortByPrice() {
        products.sort(Comparator.comparing(Product::price));
        return products;
    }

    public List<Product> sortByRatingDescending() {
        products.sort(Comparator.comparing(Product::rating).reversed());
        return products;
    }

    public List<Product> sortByNameThenPrice() {
        products.sort(Comparator.comparing(Product::name).thenComparing(Product::price));
        return products;
    }

    //Grouping and statistics methods
    public Map<String, List<Product>> groupByCategory() {
        Map<String, List<Product>> groupedMap = new HashMap<>();

        for (Product p : products) {
            String key = p.category();
            if (!groupedMap.containsKey(key)) {
                List<Product> productList = new ArrayList<>();
                productList.add(p);
                groupedMap.put(key, productList);
            } else {
                List<Product> productList = groupedMap.get(key);
                productList.add(p);
            }
        }
        return groupedMap;
    }

    public Set<String> getUniqueCategories() {
        Set<String> uniqueCategories = new TreeSet<>();
        for (Product p : products) {
            uniqueCategories.add(p.category());
        }
        return uniqueCategories;
    }

    public Product getCheapest() {
        if (products.isEmpty()) {
            return null;
        }
        return Collections.min(products, Comparator.comparing(Product::price));
    }

    public Product getMostExpensive() {
        if (products.isEmpty()) {
            return null;
        }
        return Collections.max(products, Comparator.comparing(Product::price));
    }
}
