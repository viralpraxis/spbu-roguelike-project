package test.repositories;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import repositories.Configuration;


class ConfigurationTest {
    @Test
    public void testGet_withExisingKey() {
        Object result = Configuration.get("map.width");

        assertTrue(result instanceof Integer);
    }

    @Test
    public void testGet_withMissingRootKey() {
        Object result = Configuration.get("idk.dpi");

        assertNull(result);
    }

    @Test
    public void testGet_withMissingNestedKey() {
        Object result = Configuration.get("screen.dpi");

        assertNull(result);
    }
}
