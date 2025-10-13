package com.swift.console.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testCriarProdutoVazio() {
        Product product = new Product();
        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getNome());
        assertNull(product.getCategoriaId());
    }

    @Test
    void testCriarProdutoComParametros() {
        Product product = new Product(1, "Notebook", 5, 2500.00f, null, 10, "img.jpg", "Notebook Dell", null);
        assertEquals(1, product.getId());
        assertEquals("Notebook", product.getNome());
        assertEquals(5, product.getCategoriaId());
        assertEquals(2500.00f, product.getPreco(), 0.01);
        assertEquals(10, product.getEstoque());
    }

    @Test
    void testSettersAndGetters() {
        Product product = new Product();
        product.setId(20);
        product.setNome("Mouse");
        product.setCategoriaId(3);
        product.setPreco(50.00f);
        product.setPromocao(1);
        product.setEstoque(100);
        product.setImagem("mouse.jpg");
        product.setDescricao("Mouse sem fio");
        product.setDesconto(10);
        
        assertEquals(20, product.getId());
        assertEquals("Mouse", product.getNome());
        assertEquals(3, product.getCategoriaId());
        assertEquals(50.00f, product.getPreco(), 0.01);
        assertEquals(1, product.getPromocao());
        assertEquals(100, product.getEstoque());
        assertEquals("mouse.jpg", product.getImagem());
        assertEquals("Mouse sem fio", product.getDescricao());
        assertEquals(10, product.getDesconto());
    }

    @Test
    void testPrecoComDesconto() {
        Product product = new Product();
        product.setPreco(100.00f);
        product.setDesconto(20); // 20% de desconto
        
        // Preço final = 100 - (100 * 0.20) = 80
        float precoFinal = product.getPreco() * (1 - product.getDesconto() / 100.0f);
        assertEquals(80.00f, precoFinal, 0.01);
    }

    @Test
    void testProdutoEmPromocao() {
        Product product = new Product();
        product.setPromocao(1);
        assertEquals(1, product.getPromocao());
    }

    @Test
    void testToString() {
        Product product = new Product(1, "Teclado", 3, 150.00f, null, 50, "teclado.jpg", "Teclado mecânico", null);
        String result = product.toString();
        assertTrue(result.contains("Teclado"));
        assertTrue(result.contains("150.0"));
    }
}

