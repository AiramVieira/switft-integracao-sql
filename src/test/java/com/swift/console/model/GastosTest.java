package com.swift.console.model;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class GastosTest {

    @Test
    void testCriarGastoVazio() {
        Gastos gasto = new Gastos();
        assertNotNull(gasto);
        assertNull(gasto.getCdGasto());
        assertNull(gasto.getCdUsuario());
        assertNull(gasto.getCdCategoria());
    }

    @Test
    void testCriarGastoComParametros() {
        Date data = new Date();
        BigDecimal valor = new BigDecimal("150.00");
        Gastos gasto = new Gastos(1, 10, 5, "Supermercado", data, valor, "Compras do mês");
        
        assertEquals(1, gasto.getCdGasto());
        assertEquals(10, gasto.getCdUsuario());
        assertEquals(5, gasto.getCdCategoria());
        assertEquals("Supermercado", gasto.getNmGasto());
        assertEquals(data, gasto.getDtGasto());
        assertEquals(valor, gasto.getVlGasto());
        assertEquals("Compras do mês", gasto.getDsGasto());
    }

    @Test
    void testSettersAndGetters() {
        Gastos gasto = new Gastos();
        Date data = new Date();
        BigDecimal valor = new BigDecimal("200.00");
        
        gasto.setCdGasto(5);
        gasto.setCdUsuario(15);
        gasto.setCdCategoria(8);
        gasto.setNmGasto("Farmácia");
        gasto.setDtGasto(data);
        gasto.setVlGasto(valor);
        gasto.setDsGasto("Remédios");
        
        assertEquals(5, gasto.getCdGasto());
        assertEquals(15, gasto.getCdUsuario());
        assertEquals(8, gasto.getCdCategoria());
        assertEquals("Farmácia", gasto.getNmGasto());
        assertEquals(data, gasto.getDtGasto());
        assertEquals(valor, gasto.getVlGasto());
        assertEquals("Remédios", gasto.getDsGasto());
    }

    @Test
    void testToString() {
        Date data = new Date();
        BigDecimal valor = new BigDecimal("100.50");
        Gastos gasto = new Gastos(1, 10, 5, "Restaurante", data, valor, "Almoço");
        String result = gasto.toString();
        
        assertTrue(result.contains("cdGasto=1"));
        assertTrue(result.contains("nmGasto='Restaurante'"));
        assertTrue(result.contains("vlGasto=100.50"));
    }
}

