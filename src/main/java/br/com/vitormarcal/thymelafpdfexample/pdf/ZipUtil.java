package br.com.vitormarcal.thymelafpdfexample.pdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

public class ZipUtil {

    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        GZIPOutputStream gzip;
        ByteArrayOutputStream retorno = new ByteArrayOutputStream();

        gzip = getGzipOutputStream(retorno);
        escreverEmArquivo(str, gzip);
        closeZipOutputStream(gzip);

        return coverterByteArrayParaString(retorno);
    }

    private static String coverterByteArrayParaString(ByteArrayOutputStream out) {
        return out.toString(StandardCharsets.ISO_8859_1);
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

    private static GZIPOutputStream getGzipOutputStream(ByteArrayOutputStream out) {
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao zipar string:", e);
        }
        return gzip;
    }

}
