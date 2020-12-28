package com.example.imperio_salgadosApi.domain;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity()
@Data


public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome ;
    private String tipo;
    private String descricaoP;
    private String url_foto;
    private String url_video;
    private String price;
    private String imageCategory;
    private String category;




}
