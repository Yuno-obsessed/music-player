package commands

type UserCommands struct {
	*CreateUserCommand
}

func NewUserCommands(
	createUser *CreateUserCommand,
) *UserCommands {
	return &UserCommands{
		createUser,
	}
}
