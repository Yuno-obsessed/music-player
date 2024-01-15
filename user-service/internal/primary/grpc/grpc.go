package grpc

import (
	"context"
	"fmt"
	"go.uber.org/fx"
)

var Module = fx.Options(
	fx.Provide(
		NewGrpcServer,
		NewUserGrpcService,
	),
	fx.Invoke(Start),
)

func Start(lifecycle fx.Lifecycle, server *Server) {
	lifecycle.Append(
		fx.Hook{
			OnStart: func(ctx context.Context) error {
				go func() {
					defer func() {
						if r := recover(); r != nil {
							fmt.Printf("Recovered when boot grpc server, r %s", r)
						}
					}()
					err := server.Run("8081")
					if err != nil {
						panic(err)
					}
				}()
				return nil
			},
			OnStop: func(ctx context.Context) error {
				server.Down()
				return nil
			},
		})
}
