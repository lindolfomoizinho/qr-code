package br.com.lindolfomoizinho.qrcode.controller;

import br.com.lindolfomoizinho.qrcode.exceptions.InvalidContentException;
import br.com.lindolfomoizinho.qrcode.service.GeneratorService;
import com.google.zxing.WriterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/qr")
public class GeneratorController {
    private final GeneratorService generatorService;

    public GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody Request request) {
        if (request.content() == null || request.content().trim().isEmpty()) {
            throw new InvalidContentException("Content cannot be null or empty");
        }

        try{
            var qrCode = generatorService.generateQrCode(request.content());
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public record Request(String content){}
}
