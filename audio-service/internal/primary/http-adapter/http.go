package http_adapter

import (
	"context"
	"fmt"
	"go.uber.org/fx"
)

var Module = fx.Options(
	fx.Provide(
		NewEngine,
		NewUploadHandler,
		NewGetSongHandler,
		NewHandlers,
		NewMiddleware,
		NewFiberGroup,
		fx.Annotate(
			NewAudioRouter,
			fx.As(new(Route)),
		),
	),
	fx.Invoke(Start),
)

func Start(
	lifecycle fx.Lifecycle,
	route Route,
	engine Engine,
) {
	route.Setup()

	lifecycle.Append(
		fx.Hook{
			OnStart: func(context.Context) error {
				go func() {
					defer func() {
						if r := recover(); r != nil {
							fmt.Printf("Recovered when boot rest server, r %s", r)
						}
					}()
					err := engine.Run()
					if err != nil {
						panic(err)
					}
				}()
				return nil
			},
			OnStop: func(context.Context) error {
				return engine.Shutdown()
			},
		},
	)
}
