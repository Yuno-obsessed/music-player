package application

import (
	"user-service/internal/application/commands"
	"user-service/internal/application/persistence"
	"user-service/internal/application/queries"
)

type UserServices struct {
	Commands *commands.UserCommands
	Queries  *queries.UserQueries
}

func NewUserServices(
	repo persistence.UserRepo,
	manager persistence.UoWManager,
	logger persistence.Logger,
) *UserServices {

	createUser := commands.NewCreateUserCommand(repo, manager)
	getUser := queries.NewGetUserQuery(repo, manager, logger)
	return &UserServices{
		Commands: commands.NewUserCommands(createUser),
		Queries:  queries.NewUserQueries(getUser),
	}
}
