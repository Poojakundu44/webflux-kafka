package org.example.repo;

import org.example.document.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product ,Long> {
}
