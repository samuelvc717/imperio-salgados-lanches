package com.example.imperio_salgadosApi.domain;

import com.example.imperio_salgadosApi.domain.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository rep;
    public List<ProductDTO> getProducts(){
        return rep.findAll().stream().map(ProductDTO::create).collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductsById(Long id) {

        return rep.findById(id).map(ProductDTO::create);
    }

    public List<ProductDTO> getProductsByTipo(String tipo) {

        return rep.findByTipo(tipo).stream().map(ProductDTO::create).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByCategory(String category) {
        return rep.findByCategory(category).stream().map(ProductDTO::create).collect(Collectors.toList());
    }

    public void delete(Long id) {

        rep.deleteById(id);


    }

    public ProductDTO insert(Product product) {
        Assert.isNull(product.getId(), "Nao foi Possivel inserir o registro");
        return ProductDTO.create(rep.save(product));
    }

    public ProductDTO update(Product product, Long id) {
        Assert.notNull(id, "Não foi possivel atualizar o registro");

        // Busca o carro no anco de dados
        Optional<Product> optional = rep.findById(id);
        if (optional.isPresent()){
            Product db = optional.get();
            // Copiar as propiedades
            db.setNome(product.getNome());
            db.setTipo(product.getTipo());
            db.setCategory(product.getCategory());
            System.out.println("Product id " + db.getId());

            // Atualizar o produto

                rep.save(db);
            return ProductDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }

    }













}
