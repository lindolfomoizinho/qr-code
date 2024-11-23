package br.com.lindolfomoizinho.qrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class GeneratorService {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final String format = "png";


    public byte[] generateQrCode(String content) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, format, outputStream);
        return outputStream.toByteArray();
    }
}
