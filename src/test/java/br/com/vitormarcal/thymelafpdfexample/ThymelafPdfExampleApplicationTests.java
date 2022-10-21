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
//        String imageBase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAh1BMVEX///8AAABTU1NOTk729vb7+/vu7u7z8/Po6Oj19fXi4uKzs7NLS0u4uLjU1NSvr6/j4+N1dXVdXV3a2to/Pz+Pj4+oqKi/v7/T09NqamozMzPKysoJCQmTk5MeHh5+fn6cnJw2NjYqKioUFBRkZGSGhoYbGxt4eHgmJiahoaEQEBA9PT1FRUX2l9uWAAAKLUlEQVR4nO2d53bqMAyACZCEQplhbyh00L7/891yC20kS7bDiGxOvp/FcKTasTWdUqmgoKCgoKCgoKCgoKCgoKCAJIw7rVYnDqXluA+15uorOPHZHdal5bk1zV6AqAyr0kLdkOES6/efwaMs19k7qd+RhrRsN2HP6vdNL5IW72qivk7BIDi0pCW8kppevyMjaRmvom5W0G8Vo62NhsFYWs7LebZSMAhiaUEvRbuLpnmWlvRCElsFg2AoLetlqGt03l18DBdvhIXj5bE4wlqUZ+ePWjv82UpS0ktBU7icpT9sTZGKHm42LahBBa3DsAw/X8hIeQ1wI+2rbkQFDNgKiHglxkVYhS6Hd8c+PCqa1BC4FXm3TIdgjdJjvtJjevnKdz2TtPRtekwzPWb5lK+AVwNOgxozCCxT3w79g3mRlkrA9fDMFQ7nNo8YWMqeuYlRWvYuN2qQHuVZVOoCDckTxV2qadkfcpWWXmx2GmDVJHmKdwPAadGhx4TpMd6dFmABMi58Oz3m3bcTH1htG3oMCBZ7Z7VB95A024DR5p/ljbwnwm6LDmCEbxtNqbQC8m+UpywEnkUwNf1eVK8nSZ2zcCVAUYw+2iqrUMFgrfutpL3rv57+E5XBzJW0Iw4mAptl9oI+5RPC0YeSG+i6ERBQoonPo1Ms42lWxp/tuV+JVnjoz2/NuC/kiRoRPvRWi8V+R0SEuWDimtTvSM+BWofro/q1zQVfyhPrzAxz2jcNX9vJbzmW2bVXeo1+GL/YFy9YifCOSUMf9vwj+MdGfBatstz0ttg2fzFwIfPYuVTBsZWCmvhBbtQ+9RK+00s05quMEEwsNkeqXZ18PcYr1H4J4oBfqVhov7xw0SdqjU573d3XQf37JFdlGNZk5d7rgt0JlaXdO1t84/0cf+ZEKLk6VI7GcptfXjM09jNtaUd4BTsxiSXl9K/oxvb0KmBbxxGnEaZ8g7JmKDpiVL8DzbEjAZAMGkJzjTJaoTlgjA/kQwYNYS0KGUftmYfkjr2GT2C3pJcgXMhuJHXsNYTSM+4DiDizEYJcsdcQbJVcoBgM0m7MuWGvIXCb1swgUHz8cgd5s2Ov4SI9jsu5PYEA3F0kzoq9hiBvyppkfY813D+8ho+/Sr3caeLZ6hVqOJ80OEsEmGR+nBYtrN5JyR2dfoCRZMaHdOnEb2miphviMQthIHlN/ijsxRG12upKEgbyheexgUeQ4WIL4zwnFlhelVXa8px9KZ9TLjz0gQW9p5oqL8Hrb0AxmlCfq95FYhqQFzjcwvKTRQoHzMcDw++KRTGGpLgkx82wSbfSHumldQjxyheLRJlTRylWSUX7+f68kqMh7tQQq+LIpKAF28lHe7gnOlKlplDJ4t8NoRxihgz3lQiVpYabvBSUagl7I6WZ7tuj2XjUGBgybhnQBSXvCZk6+kht+dHQ0MRuCdFOlQ9qg/OhgWUZGbug+fPxTFlKQcV4DnbEhhfS1U6/fBhT+XIZbiVDzdT26E6UydGlSLS5brnifqXQhxWFNVz7p/KFkC866gq6TPgqGk11FjOLqW/U6Iz+TjLxiwtMtE8L5T92oc8br/GedBjIVu2hWqatfr9TTo0vYnbqze7z9H+RwvvXbiheL42cBEMrDDbv1tzAMKrV6zUHSktQm0jwaRqPnlonKmP1oP3RuKWjenA3UoFakF9oHI+M9F0OIl4J3N0tOmGgaSNfbGgEbo4f5i/AM3ErXhRrBBZlWWwccDedu1FXoQM+hhbyou4gRwqcNEANLdbc0/LRNUT3Y/imocW1M9Gr1xpa2JDIUnd/p4GOgEVmDxo1HjTLQjvTwgiDvsjX/SW8FphAOpi/AP8lrhT8akAxDOODGMHxjlTD6kC3Xb6ZxiOH2Y3eST2o98C0+8PRBx9uNUNhUENQEwfTnplJrCWzUbs5SzoOWOY4RKgNYxA5qolyIlab3c9fg35bFg/UVHEYV3OGK2P/swZjxsoVhMFUuH0UFxxs2UM8ZIqJ3v/mfcyUc6wlV6vSkffJzCKn4De9n5VIl5/8/N8k77NRwtSv5PbRUWoO0qxCU6rceBDdj0iVZq/GhY1dsA1TQdVWzkonSn9e10CcuGF3ibKeg1h0P6S2yPmkmfzMZH00IcsxszMX8ya5tNl8OZ2+Kw2EV/AuZgNZ1CTeBrn4qvI+i3uBy/ryQ3sU3BLmIrH7E2dT0VBK/O38LxrNxsdObXWWW6dxlrqgpDTS/UeWf2fNSClilDNuqsZ5OTM9rrSQNwFWwLLFxSxSdVFHLK9v6Z2saM4Kxdcm1NFpKulN8ZcNpEjlpxKqGEyNSKI7U0Szqk+GwqfvCYR7YUOp9aLkh4VzFgG9e9LSnoyfSvqtiozaJfmr8B8ndmCcSFQn/QR9YxeMbNBR8xiMkb/fJB5WVGO0v2A8A7gEmZ8Ek+jEmxUiFI3o8ysLdDFwoTpg27uRCriww5IrBACB5/ldJM7KhX3AXCFAFZhAd5E4Kxf2AbOnuc99wGAO2TzGp8cagueQqxqDV2XdReKsXNjpzGXbwE1wtFWQN/Yagigp5/1ZtUPni72GMAPJxESBZ+ZGWtVewyrwHGjHAVp2blxYn+GOIegmkrYd9LIcyCmWMmkIm0+pJ3FtHCFABg1RKYBanoGqIRy4jPZIlvvakLuF25twg7ELZVTRQknETBd8zgHVfgfbtHVaw+6mA75TzIQyVmzSQUmdVpqniZqprY3SHr62a53zjagm222vu+sTWR3x6v5IG6fpM0vV+hppeZs0MfVJ0g5gdWOroHQLisV1wLT3YNsMLm2w4U0xg4p2l2pIlzJaXXZ9zXXX0k5FaJtgo7cb06Xz8jPIdOUTMIalaaGKnxPW19OwXYf6/Jz0LloqbRSZpt11o7HuEgkmzrRssGUpvEGUG0pX/mR8tqATpS6MreCq0nnTiXQF5hGU5F6CuGCCNyHePwibOK/9ojHacwQ9hWXshyNjTls0Eo1WlXOn86TtwvQdgQuR2C2hiuY3HkedeseJTuczcIqIbeEJFr85cXd8FuAiJYNhsHpUrrbpQkDDM5PfA36utAGWGRBqYPq7gFnGvE7HXUBc5uHfeMwmocGh6NlWY/cuWXCiuBGat+bx3wf8+O90fvz3coMePfYGFxCHc8XctGWTFp45LcB25N1pAbZJJrT9QG88Zq4yBvPsndUGI7pkhg8GAdb5yncDgPjkG49hlsW3jQbnVtTuHXR7EvPqbpdBiYcNyvLFKOC2lpDxSnCsExTyjnCU0I1yimwoAeFp8xxNHCnNveLh64sgUqPl/WCwJ/4+9+0w/MEy86SsYI+wbkL07rT/xbIJ8cXHbeaHeGOloX+H/R81m55f+SzZNVi8t9pvBb8XquHt40ufl+gJbfHPzs+DEDHmCxY8i6/xtMkrIubS1T43pakcjZW2v6cgTTRa/drb/be2fMnkXQjjZDxOYqk3GhQUFBQUFBQUFBQUFBQUFDwm/wDIYH+2LuA7UgAAAABJRU5ErkJggg==";

        String imageBase64 = "data:image/png;base64," + gerarImagemBase64();

        DadoDeclaracao dadoDeclaracao = new DadoDeclaracao("fulano", "03490113101", LocalDate.now());
        contexto.setVariable("dado", dadoDeclaracao);
        contexto.setVariable("image", imageBase64);

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
