package com.swift.console.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void testCriarEnderecoVazio() {
        Endereco endereco = new Endereco();
        assertNotNull(endereco);
        assertNull(endereco.getId());
        assertNull(endereco.getDescricao());
        assertNull(endereco.getCep());
    }

    @Test
    void testCriarEnderecoComParametros() {
        BigDecimal lat = new BigDecimal("-23.550520");
        BigDecimal lon = new BigDecimal("-46.633308");
        Endereco endereco = new Endereco(1, "Av. Paulista, 1000", "01310-100", lat, lon);
        
        assertEquals(1, endereco.getId());
        assertEquals("Av. Paulista, 1000", endereco.getDescricao());
        assertEquals("01310-100", endereco.getCep());
        assertEquals(0, lat.compareTo(endereco.getLatitude()));
        assertEquals(0, lon.compareTo(endereco.getLongitude()));
    }

    @Test
    void testSettersAndGetters() {
        Endereco endereco = new Endereco();
        BigDecimal latitude = new BigDecimal("-23.561414");
        BigDecimal longitude = new BigDecimal("-46.656139");
        
        endereco.setId(10);
        endereco.setDescricao("Rua Augusta, 500");
        endereco.setCep("01305-000");
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);
        
        assertEquals(10, endereco.getId());
        assertEquals("Rua Augusta, 500", endereco.getDescricao());
        assertEquals("01305-000", endereco.getCep());
        assertEquals(0, latitude.compareTo(endereco.getLatitude()));
        assertEquals(0, longitude.compareTo(endereco.getLongitude()));
    }

    @Test
    void testCepComFormatoValido() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        assertEquals("12345-678", endereco.getCep());
    }

    @Test
    void testCoordenadasPositivas() {
        Endereco endereco = new Endereco();
        BigDecimal lat = new BigDecimal("40.712776");
        BigDecimal lon = new BigDecimal("74.005974");
        
        endereco.setLatitude(lat);
        endereco.setLongitude(lon);
        
        assertTrue(endereco.getLatitude().compareTo(BigDecimal.ZERO) > 0);
        assertTrue(endereco.getLongitude().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testToString() {
        BigDecimal lat = new BigDecimal("-23.550520");
        BigDecimal lon = new BigDecimal("-46.633308");
        Endereco endereco = new Endereco(1, "Av. Paulista, 1000", "01310-100", lat, lon);
        
        String result = endereco.toString();
        assertTrue(result.contains("Av. Paulista, 1000"));
        assertTrue(result.contains("01310-100"));
    }
}

