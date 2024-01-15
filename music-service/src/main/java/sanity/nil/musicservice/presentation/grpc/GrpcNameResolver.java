package sanity.nil.musicservice.presentation.grpc;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class GrpcNameResolver extends NameResolver {

    private final URI uri;

    public GrpcNameResolver(URI uri) {
        this.uri = uri;
    }

    @Override
    public String getServiceAuthority() {
        return uri.getAuthority();
    }

    @Override
    public void start(Listener listener) {
        List<EquivalentAddressGroup> servers = Arrays.stream(uri.getAuthority()
                        .split(";"))
                .map(ipPortPair -> {
                    String[] ipPort = ipPortPair.split(",");
                    String ip = ipPort[0];
                    int port = Integer.parseInt(ipPort[1]);
                    return new EquivalentAddressGroup(Collections.singletonList(new InetSocketAddress(ip, port)));
                }).collect(Collectors.toList());
        listener.onAddresses(servers, Attributes.EMPTY);
    }

    @Override
    public void shutdown() {

    }

}
