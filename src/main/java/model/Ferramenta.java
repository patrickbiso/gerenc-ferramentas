package com.example.gerencferramentas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ferramentas")
public class Ferramenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String categoria;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(length = 100)
    private String fornecedor;

    private Integer garantiaMeses;

    private Boolean ativo = true;


    public Ferramenta() {}

    public Ferramenta(String codigo, String nome, String categoria, String descricao,
                      Integer quantidade, BigDecimal preco, String fornecedor,
                      Integer garantiaMeses, Boolean ativo) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.garantiaMeses = garantiaMeses;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(Integer garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
