package vn.tinhh.utils.core.functions;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class QRCodeUtils {

    public static String getQRCodeImage(String text, int width, int height) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
            String base64Qrcode = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
           return "data:image/png;base64," + base64Qrcode;

        } catch (Exception e) {
            return null;
        }
    }
}
