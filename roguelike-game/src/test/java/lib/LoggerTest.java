package test.lib;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import lib.Logger;

class LoggerTest {
    private ByteArrayOutputStream byteArrayOutputStream;

    @Test
    public void testLogWithLevel() throws IOException {
        redirectSystemOut();

        Logger.log("FATAL", "some things never change");

        byteArrayOutputStream.flush();

        assertEquals(getRedirectedContent(), "[FATAL] -- some things never change\n");
    }

    @Test
    public void testLogWithoutLevel() throws IOException {
      redirectSystemOut();

      Logger.log("some things do change");

      byteArrayOutputStream.flush();

      assertEquals(getRedirectedContent(), "[INFO] -- some things do change\n");
    }

    private void redirectSystemOut() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    private String getRedirectedContent() {
        return new String(byteArrayOutputStream.toByteArray());
    }
}
