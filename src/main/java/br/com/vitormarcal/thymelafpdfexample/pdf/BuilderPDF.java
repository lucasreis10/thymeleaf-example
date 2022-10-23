package br.com.vitormarcal.thymelafpdfexample.pdf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class BuilderPDF {

    private TemplateEngine templateEngine;
    private Context context;
    private String nomeDoArquivo;

    public BuilderPDF() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("relatorios-pdf/");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public BuilderPDF(String caminhoDoArquivo) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix(caminhoDoArquivo);
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public String buildTemplateString() {
        String retorno = null;
        byte[] pdf = gerarPdf();

        if (pdf != null) {
            byte[] pdfEncoded = encode(pdf);
            retorno = new String(pdfEncoded);
        }

        return retorno;
    }

    public byte[] buildTemplateByteArray() {
        byte[] retorno = new byte[0];
        byte[] pdf = gerarPdf();

        if (pdf != null) {
            retorno = pdf;
        }

        return retorno;
    }

    private byte[] gerarPdf() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(getHtml());
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] encode(byte[] template) {
       return Base64.getEncoder().encode(template);
    }

    private String getHtml() {
        return templateEngine.process(nomeDoArquivo, context);
    }

    public BuilderPDF context(Context context) {
        this.context = context;
        return this;
    }

    public BuilderPDF nomeArquivo(String nome) {
        this.nomeDoArquivo = nome;
        return this;
    }


}
