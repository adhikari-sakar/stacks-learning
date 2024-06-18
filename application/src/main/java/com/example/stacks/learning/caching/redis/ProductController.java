package com.example.stacks.learning.caching.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository repository;

    @PostMapping("/product")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(repository.save(product), HttpStatus.CREATED);
    }


    @GetMapping("/product/{id}")
    @Cacheable(value = "product", key = "#id")
    public Product getProductById(@PathVariable long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/product/{id}")
    @CachePut(cacheNames = "product", key = "#id")
    public Product editProduct(@PathVariable long id, @RequestBody Product product) {
        return repository.findById(id).map(p -> repository.save(product)).orElseThrow();
    }

    @DeleteMapping("/product/{id}")
    @CacheEvict(cacheNames = "product", key = "#id", beforeInvocation = true)
    public void removeProductById(@PathVariable long id) {
        repository.deleteById(id);
    }
}
