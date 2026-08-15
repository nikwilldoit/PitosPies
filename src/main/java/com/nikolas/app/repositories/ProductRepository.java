package com.nikolas.app.repositories;

import com.nikolas.app.models.Pie;
import com.nikolas.app.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Product findProductById(int id);

    List<Product> findAll();
}
