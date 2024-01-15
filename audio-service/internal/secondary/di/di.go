package di

import (
	"audio-service/internal/application"
	"audio-service/internal/application/persistence"
	"audio-service/internal/secondary/db"
	"audio-service/internal/secondary/logger"
	"audio-service/internal/secondary/storage"
	"go.uber.org/fx"
)

var Module = fx.Provide(
	fx.Annotate(
		db.NewAudioRepo,
		fx.As(new(persistence.AudioRepo)),
	),
	fx.Annotate(
		storage.NewMinioInstance,
		fx.As(new(persistence.Storage)),
	),
	fx.Annotate(
		db.NewUoWManager,
		fx.As(new(persistence.UoWManager)),
	),
	fx.Annotate(
		logger.NewLogger,
		fx.As(new(persistence.Logger)),
	),
	db.NewConfig,
	db.NewConnection,
	storage.NewConfig,
	application.NewAudioServices,
)
