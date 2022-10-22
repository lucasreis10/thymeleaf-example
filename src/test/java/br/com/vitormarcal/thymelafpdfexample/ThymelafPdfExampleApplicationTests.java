package br.com.vitormarcal.thymelafpdfexample;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Base64Utils;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;


@SpringBootTest
class ThymelafPdfExampleApplicationTests {

    @Test
    void contextLoads() {
        HtmlConverter componente = new HtmlConverter();
        Context contexto = new Context();

        String imageBase64 = "data:image/png;base64," + gerarImagemBase64();

        DadoDeclaracao dadoDeclaracao = new DadoDeclaracao("fulano", "03490113101", LocalDate.now());
        contexto.setVariable("dado", dadoDeclaracao);
        contexto.setVariable("image", "https://teste-teste-image-pub.s3.us-east-1.amazonaws.com/bradesco.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXNhLWVhc3QtMSJHMEUCIQDEi4cIeQ5yQx9zCk%2BRNhVSAQnvT2WodBIIsUup%2FrgJpAIgD4P39bpS%2F%2BSR4psP0862%2FoKQUlprT5E%2BBpYUUjhEdEUq%2BwIIfBACGgw2NDQ3MTE2MzA0ODciDB5Gm%2F5ryPm4IIjtsSrYArK3DTnXIhragDsKEQGKv3aiHexFJ6rKmJhclY9hsWx%2BgWQYAvPnyOp3rfadMAs6985LgbqqMYDKe%2FF%2BqVYtlVEu1dNSKNAfEIuhdxLDaWwgNIMlnITLbdShTZWjWn4GzRdnsh4OW9A%2F0rYC7l5qrtyjyt9Oebp1ZK97sc5TT9UZqColIXBgHKOw46u%2BJYLe%2Fj%2FmNY3g7iNaO8oo57Wo5mOv%2BS56sNDn1dTTSZLsqIdhZD5lUdO38JaGlD0z%2Fhq0mslItm9UhzzU3IGDFz9M3baaSYfTtFxeuq%2Fx8J4BXMF95tlO0Z%2FyDR%2B3%2FFk9%2F1G9nAQSa47Z8yhq2Ak6IN3OFGND%2BdPZAryc4TKcoXv2PTLbESmmP7j9AZ%2BwC1yen9MaJppp4K%2BSILZY8QS0GnrHYr04fquozYHYqcpMQeW%2FoQBDRo4Q7PByqyAtDe9KgK%2B44dDLBznXmekCMOzSy5oGOrMCQinNbU5WaAZdESR%2BsR4dKWQp5dv7sR022TlodFmiI%2BbSPvoj0%2BQECmQJXbhMlGIV84wG6iekwP0YJCbzA5kEtX%2FZq6pMYStKX9seJ9Q%2FS4OlPmKN%2FeVVvmu%2FFHuLWOos0tOBfv5rpXLLwXC4u5FSZZjjuqdX0jQ0HoyNFWRJpmFafRdmv5jMVNBgNZNrnomhQaqM2RxjYRo7VGWH1TEu%2B%2BMYNdxW62%2BJ4PTw8lPW9yFWU6Pk2eKa%2B665V6yhjBHHvuvA4ZkixwhJ%2FB3hAN5%2BpvU1P%2BQPfOe%2BrSJIK6rL9YjMoiyn5fQSTw9WuyWpV%2BWS1tLzvEdAWQPQQGRjUsdW1NE6EYAjPOvImFtTzPMVEJOlH5rX%2FXynCRWCMBC3BCyiprubeDL31AKmnbYK2CzRmXVxLg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20221021T201105Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43199&X-Amz-Credential=ASIAZMG6OY2LZ6S5BVMG%2F20221021%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=a922e87c6500d083b5ffe2e9f89308d70807babfd2d73dd4dceb32f82a2d488f");

        byte[] template = componente.gerarPdfDoHtml("declaracao-nada-consta", contexto);
        byte[] base64 = base64(template);

        Assertions.assertNotNull(template);
        Assertions.assertNotNull(base64);
        System.out.println(new String(base64));
    }

    private String gerarImagemBase64() {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL("https://teste-teste-image-pub.s3.us-east-1.amazonaws.com/bradesco.png?response-content-disposition=inline&X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKP%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXNhLWVhc3QtMSJHMEUCIQDEi4cIeQ5yQx9zCk%2BRNhVSAQnvT2WodBIIsUup%2FrgJpAIgD4P39bpS%2F%2BSR4psP0862%2FoKQUlprT5E%2BBpYUUjhEdEUq%2BwIIfBACGgw2NDQ3MTE2MzA0ODciDB5Gm%2F5ryPm4IIjtsSrYArK3DTnXIhragDsKEQGKv3aiHexFJ6rKmJhclY9hsWx%2BgWQYAvPnyOp3rfadMAs6985LgbqqMYDKe%2FF%2BqVYtlVEu1dNSKNAfEIuhdxLDaWwgNIMlnITLbdShTZWjWn4GzRdnsh4OW9A%2F0rYC7l5qrtyjyt9Oebp1ZK97sc5TT9UZqColIXBgHKOw46u%2BJYLe%2Fj%2FmNY3g7iNaO8oo57Wo5mOv%2BS56sNDn1dTTSZLsqIdhZD5lUdO38JaGlD0z%2Fhq0mslItm9UhzzU3IGDFz9M3baaSYfTtFxeuq%2Fx8J4BXMF95tlO0Z%2FyDR%2B3%2FFk9%2F1G9nAQSa47Z8yhq2Ak6IN3OFGND%2BdPZAryc4TKcoXv2PTLbESmmP7j9AZ%2BwC1yen9MaJppp4K%2BSILZY8QS0GnrHYr04fquozYHYqcpMQeW%2FoQBDRo4Q7PByqyAtDe9KgK%2B44dDLBznXmekCMOzSy5oGOrMCQinNbU5WaAZdESR%2BsR4dKWQp5dv7sR022TlodFmiI%2BbSPvoj0%2BQECmQJXbhMlGIV84wG6iekwP0YJCbzA5kEtX%2FZq6pMYStKX9seJ9Q%2FS4OlPmKN%2FeVVvmu%2FFHuLWOos0tOBfv5rpXLLwXC4u5FSZZjjuqdX0jQ0HoyNFWRJpmFafRdmv5jMVNBgNZNrnomhQaqM2RxjYRo7VGWH1TEu%2B%2BMYNdxW62%2BJ4PTw8lPW9yFWU6Pk2eKa%2B665V6yhjBHHvuvA4ZkixwhJ%2FB3hAN5%2BpvU1P%2BQPfOe%2BrSJIK6rL9YjMoiyn5fQSTw9WuyWpV%2BWS1tLzvEdAWQPQQGRjUsdW1NE6EYAjPOvImFtTzPMVEJOlH5rX%2FXynCRWCMBC3BCyiprubeDL31AKmnbYK2CzRmXVxLg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20221021T201105Z&X-Amz-SignedHeaders=host&X-Amz-Expires=43199&X-Amz-Credential=ASIAZMG6OY2LZ6S5BVMG%2F20221021%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=a922e87c6500d083b5ffe2e9f89308d70807babfd2d73dd4dceb32f82a2d488f");
            is = url.openStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        byte[] bytes = new byte[0];
        try {
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(base64(bytes));
    }

    public byte[] base64(byte [] template) {
        byte[] encoded = Base64Utils.encode(template);
        return encoded;
    }


}
