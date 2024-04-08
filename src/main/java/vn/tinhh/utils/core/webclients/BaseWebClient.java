package vn.tinhh.utils.core.webclients;

import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import com.netflix.graphql.dgs.client.WebClientGraphQLClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseWebClient {

    protected String baseURL;

    public BaseWebClient(String baseURL) {
        this.baseURL = baseURL;
    }

    private WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                .responseTimeout(Duration.ofMillis(60000))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(60000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(baseURL)
                .exchangeStrategies(ExchangeStrategies.builder().codecs(e -> e.defaultCodecs().maxInMemorySize(1000000000)).build())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    protected WebClientGraphQLClient getWebClientGraphQLClientWithHeader(String headerName, String headerValue) {
        return MonoGraphQLClient.createWithWebClient(getWebClient(), headers -> headers.add(headerName, headerValue));
    }

    protected WebClientGraphQLClient getWebClientGraphQLClient() {
        return MonoGraphQLClient.createWithWebClient(getWebClient());
    }


    protected WebClient.RequestBodySpec post(String uri) {
        WebClient client = getWebClient();
        return client.post()
                .uri(uri);
    }

    protected <T, R> Mono<R> put(String uri, T data, R response) {
        Class<T> tClass = (Class<T>) data.getClass();
        Class<R> RClass = (Class<R>) response.getClass();
        WebClient client = getWebClient();
        return client.post()
                .uri(uri)
                .body(Mono.just(data), data.getClass())
                .retrieve()
                .bodyToMono(RClass);
    }

    protected WebClient.RequestHeadersSpec get(String uri) {
        WebClient client = getWebClient();
        return client.get()
                .uri(uri);
    }

    protected WebClient.RequestHeadersSpec delete(String uri) {
        WebClient client = getWebClient();
        return client.delete()
                .uri(uri);
    }
}
