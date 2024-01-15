FROM golang:1.21.3-alpine as modules

ADD go.mod go.sum /server/

RUN cd /server && go mod download

FROM golang:1.21.3-alpine as builder

COPY --from=modules /go/pkg /go/pkg

RUN mkdir -p /server
COPY . /server

WORKDIR /server

#RUN go test ./...

RUN GOOS=linux GOARCH=amd64 CGO_ENABLED=0 \
    go build -ldflags '-s -w -extldflags "-static"' \
    -o /bin/server ./main.go

FROM busybox:latest

COPY --from=builder /bin/server /server
ENTRYPOINT ["./server"]