package br.com.vitormarcal.thymelafpdfexample.pdf;

import br.com.vitormarcal.thymelafpdfexample.DadoDeclaracao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.Base64;

public class BuilderPDFTest {


    @Test
    void dadoUmArquivoHtml_quandoBuilderEhExecutado_entaoRetornaBase64() {
        // given
        final Context context = new Context();
        final String nomeAquivo = "declaracao-nada-consta";
        final String srcImage = "https://picsum.photos/id/237/200/300";
        final DadoDeclaracao declaracao = new DadoDeclaracao("dummy", "dummy", LocalDate.now());

        context.setVariable("dado", declaracao);
        context.setVariable("image", srcImage);

        // when
        final String pdf = new BuilderPDF()
                .nomeArquivo(nomeAquivo)
                .context(context)
                .buildTemplateString();
        // then
        Assertions.assertNotNull(pdf);
        System.out.println("########");
        System.out.println(pdf);
        System.out.println("########");
    }

    @Test
    void dadoUmArquivoHtml_quandoBuilderEhExecutado_entaoRetornaByteArray() {
        // given
        final var context = new Context();
        final var cpfEsperado = "000.000.00-00";
        final var nomeEsperado = "Jon Snow";
        final var srcImageEsperado = "https://picsum.photos/id/237/200/300";
        final var nomeAquivo = "declaracao-nada-consta";
        final var declaracao = new DadoDeclaracao(nomeEsperado, cpfEsperado, LocalDate.now());

        context.setVariable("dado", declaracao);
        context.setVariable("image", srcImageEsperado);

        // when
        final var html = new BuilderPDF()
                .nomeArquivo(nomeAquivo)
                .context(context)
                .buildHtml();

        // then
        Assertions.assertNotNull(html);
        Assertions.assertTrue(html.contains(srcImageEsperado));
        Assertions.assertTrue(html.contains(nomeEsperado));
        Assertions.assertTrue(html.contains(cpfEsperado));
    }


}
