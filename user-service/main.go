package main

import (
	"go.uber.org/fx"
	"user-service/internal/primary/grpc"
	"user-service/internal/secondary/di"
)

func main() {

	fx.New(
		di.Module,
		grpc.Module,
	).Run()

}
