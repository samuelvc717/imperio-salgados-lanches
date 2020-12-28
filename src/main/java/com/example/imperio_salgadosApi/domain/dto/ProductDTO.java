package com.example.imperio_salgadosApi.domain.dto;


import com.example.imperio_salgadosApi.domain.Product;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ProductDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String descricao;
    private String url_foto;
    private String url_video;
    private String price;
    private String imageCategory;
    private String category;

    /*public ProductDTO(Product p) {
        this.id = p.getId();
        this.tipo = p.getTipo();
        this.category = p.getCategory();
        this.nome = p.getNome();
    }*/

    public static ProductDTO create(Product p) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, ProductDTO.class);
    }


}
