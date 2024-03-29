### Initial stage: download modules
FROM golang:1.21.3-alpine as modules
# App dependencies
ADD server/go.mod server/go.sum /server/
# ADD server/go.mod go.sum /server/

# gRPC gen dependencies (replaced in go.mod)
COPY gen/go/api/item_service/go.mod /gen/go/api/item_service/go.mod
COPY gen/go/api/item_service/go.sum /gen/go/api/item_service/go.sum

# download packages
RUN cd /server && go mod download


### Intermediate stage: Build the binary
FROM golang:1.21.3-alpine as builder

# copy all packages
COPY --from=modules /go/pkg /go/pkg

RUN useradd server

RUN mkdir -p /server
COPY server /server

# grpc gen
COPY gen/go/api /gen/go/api

WORKDIR /server

# todo: uncomment
# Run tests
RUN go test ./...

# Build the binary with go build
RUN GOOS=linux GOARCH=amd64 CGO_ENABLED=0 \
    go build -ldflags '-s -w -extldflags "-static"' \
    -o /bin/server ./cmd/lake


### Final stage: Run the binary
# why not scratch? we need bash to connect to the container
FROM busybox:latest

# Sentry needs certificates
COPY --from=builder /etc/ssl/certs /etc/ssl/certs

COPY --from=builder /bin/server /server
ENTRYPOINT ["./server"]

#FROM golang:1.21.3-alpine as modules
#
#ADD go.mod go.sum /server/
#
#RUN cd /server && go mod download
#
#FROM golang:1.21.3-alpine as builder
#
#COPY --from=modules /go/pkg /go/pkg
#
#RUN mkdir -p /server
#COPY . /server
#
#WORKDIR /server
#
##RUN go test ./...
#
#RUN GOOS=linux GOARCH=amd64 CGO_ENABLED=0 \
#    go build -ldflags '-s -w -extldflags "-static"' \
#    -o /bin/server ./main.go
#
#FROM busybox:latest
#
#COPY --from=builder /bin/server /server
#ENTRYPOINT ["./server"]