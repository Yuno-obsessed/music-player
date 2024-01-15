package main

import (
	http_adapter "audio-service/internal/primary/http-adapter"
	"audio-service/internal/secondary/di"
	"github.com/joho/godotenv"
	"go.uber.org/fx"
)

func main() {

	err := godotenv.Load(".env")
	if err != nil {
		panic(err)
	}

	fx.New(
		di.Module,
		http_adapter.Module,
	).Run()

}
