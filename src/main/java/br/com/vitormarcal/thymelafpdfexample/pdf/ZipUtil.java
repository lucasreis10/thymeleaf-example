package br.com.vitormarcal.thymelafpdfexample.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {

    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();

        GZIPOutputStream gzip = gerarZip(byteArrayOutput);
        escreverEmArquivo(str, gzip);
        closeZipOutputStream(gzip);

        return new String(gerarByteArray(byteArrayOutput));
    }

    private static byte[] gerarByteArray(ByteArrayOutputStream out) {
        return Base64.getEncoder().encode(out.toByteArray());
    }

    private static void closeZipOutputStream(GZIPOutputStream gzip) {
        try {
            gzip.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escreverEmArquivo(String str, GZIPOutputStream gzip) {
        try {
            gzip.write(str.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static GZIPOutputStream gerarZip(ByteArrayOutputStream out) {
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao zipar string:", e);
        }
        return gzip;
    }

}
