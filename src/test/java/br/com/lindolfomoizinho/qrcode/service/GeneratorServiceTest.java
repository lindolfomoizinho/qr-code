package br.com.lindolfomoizinho.qrcode.service;

import com.google.zxing.WriterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorServiceTest {
    private GeneratorService generatorService;

    @BeforeEach
    void setUp() {
        generatorService = new GeneratorService();
    }

    @Test
    void testGenerateQrCodeValidContent() {
        String content = "https://example.com";

        assertDoesNotThrow(() -> {
            byte[] qrCode = generatorService.generateQrCode(content);
            assertNotNull(qrCode, "The generated QR code should not be null.");
            assertTrue(qrCode.length > 0, "The generated QR code should not be empty.");
        });
    }

    @Test
    void testGenerateQrCodeEmptyContent() {
        String content = "";

        assertThrows(IllegalArgumentException.class, () -> generatorService.generateQrCode(content),
                "Generating a QR code with empty content should throw an IllegalArgumentException.");
    }

    @Test
    void testGenerateQrCodeNullContent() {
        String content = null;

        assertThrows(NullPointerException.class, () -> generatorService.generateQrCode(content),
                "Generating a QR code with null content should throw a NullPointerException.");
    }
}