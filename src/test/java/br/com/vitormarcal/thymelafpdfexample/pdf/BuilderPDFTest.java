package br.com.vitormarcal.thymelafpdfexample.pdf;

import br.com.vitormarcal.thymelafpdfexample.DadoDeclaracao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

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


}
