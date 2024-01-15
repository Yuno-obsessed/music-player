package di

import (
	"go.uber.org/fx"
	"user-service/internal/application"
	"user-service/internal/application/persistence"
	"user-service/internal/secondary/db"
	"user-service/internal/secondary/logger"
)

var Module = fx.Provide(
	fx.Annotate(
		db.NewUserRepo,
		fx.As(new(persistence.UserRepo)),
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
	application.NewUserServices,
)
