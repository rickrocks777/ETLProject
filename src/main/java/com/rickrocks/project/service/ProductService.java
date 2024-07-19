package com.rickrocks.project.service;

import com.rickrocks.project.entity.Product;
import com.rickrocks.project.entity.Product2;
import com.rickrocks.project.repository.Product2Repository;
import com.rickrocks.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private Product2Repository repository2;

    private Product2 product2;


    public Product saveProduct(Product product) {
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }
    public List<Product> getProducts() {
        return repository.findAll();
    }
    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Product getProductByName(String name) {
        return repository.findByName(name);
    }
    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Product removed" + id;
    }
    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }
    public List<Product> addToProduct2(List<Product> products) {
        List<Product> product2list = new ArrayList<>();
        for (Product product:products) {
            product2.setName(product.getName());
            product2.setId(product.getId());
            product2.setQuantity(product.getQuantity());
            product2.setPrice(product.getPrice());
        }
        return repository2.saveAll(product2list);
    }
}
