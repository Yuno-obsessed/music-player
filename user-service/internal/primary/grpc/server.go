package grpc

import (
	"fmt"
	"google.golang.org/grpc"
	"google.golang.org/grpc/keepalive"
	"google.golang.org/grpc/reflection"
	"net"
	"time"
	"user-service/protobuf/servicespb"
)

const (
	maxConnectionIdle = 5
	gRPCTimeout       = 15
	maxConnectionAge  = 5
	gRPCTime          = 10
)

type Server struct {
	service servicespb.UserServiceServer
	server  *grpc.Server
}

func NewGrpcServer(service servicespb.UserServiceServer) *Server {
	return &Server{
		service: service,
	}
}

func (g *Server) Run(port string) error {
	s := grpc.NewServer(grpc.KeepaliveParams(keepalive.ServerParameters{
		MaxConnectionIdle: maxConnectionIdle * time.Minute,
		Timeout:           gRPCTimeout * time.Second,
		MaxConnectionAge:  maxConnectionAge * time.Minute,
		Time:              gRPCTime * time.Minute,
	}))
	servicespb.RegisterUserServiceServer(s, g.service)
	lis, err := net.Listen("tcp", fmt.Sprintf(":%s", port))
	if err != nil {
		return err
	}
	reflection.Register(s)
	g.server = s
	err = s.Serve(lis)
	if err != nil {
		return err
	}
	return nil
}
func (g *Server) Down() {
	g.server.GracefulStop()
}
