package com.example.imperio_salgadosApi.api;

import com.example.imperio_salgadosApi.domain.Product;
import com.example.imperio_salgadosApi.domain.ProductService;
import com.example.imperio_salgadosApi.domain.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping()
    public ResponseEntity get(){

        return ResponseEntity.ok(service.getProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){

        Optional<ProductDTO>  product = service.getProductsById(id);
        return product.isPresent() ?
                ResponseEntity.ok(product.get()):
                ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getProductsByTipo(@PathVariable("tipo") String tipo){

        List<ProductDTO> product =service.getProductsByTipo(tipo);

        return product.isEmpty()?
                ResponseEntity.noContent().build():
                ResponseEntity.ok(product);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getProductsByCategory(@PathVariable("category") String category){

        List<ProductDTO> product = service.getProductsByCategory(category);

        return product.isEmpty()?
                ResponseEntity.noContent().build():
                ResponseEntity.ok(product);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Product product){

        ProductDTO p = service.insert(product);
        URI location = getUri(p.getId());
        return  ResponseEntity.created(location).build();

    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);

        ProductDTO p = service.update(product, id);
        return  p != null ?
                ResponseEntity.ok(p):
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.ok().build();

    }
}
