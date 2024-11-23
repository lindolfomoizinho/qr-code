package br.com.lindolfomoizinho.qrcode.controller;

import br.com.lindolfomoizinho.qrcode.service.GeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@ExtendWith(MockitoExtension.class)
class GeneratorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GeneratorService generatorService;

    @InjectMocks
    private GeneratorController generatorController;

    @BeforeEach
    void setUp() {
        // Configura o MockMvc com o controlador e sua configuração
        mockMvc = MockMvcBuilders.standaloneSetup(generatorController).build();
    }

    @Test
    void testGenerateQRCodeSuccess() throws Exception {
        String content = "https://example.com";
        byte[] mockQRCode = "mockQRCode".getBytes();

        when(generatorService.generateQrCode(content)).thenReturn(mockQRCode);

        mockMvc.perform(post("/api/v1/qr/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"" + content + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().bytes(mockQRCode));
    }

    @Test
    void testGenerateQRCodeInternalError() throws Exception {
        String content = "https://example.com";

        when(generatorService.generateQrCode(content)).thenThrow(new IOException("Error generating QR code"));

        mockMvc.perform(post("/api/v1/qr/generate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"" + content + "\"}"))
                .andExpect(status().isInternalServerError());
    }
}
