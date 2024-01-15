package sanity.nil.musicservice.presentation.grpc;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import io.micrometer.common.lang.Nullable;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.util.Objects;

@Configuration
public class GrpcNameResolverProvider extends NameResolverProvider {

    private static final String SCHEME = "sanity";

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 10;
    }

    @Nullable
    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        if (!Objects.equals(SCHEME, targetUri.getScheme()))
            return null;
        return new GrpcNameResolver(targetUri);
    }

    @Override
    public String getDefaultScheme() {
        return SCHEME;
    }
}
