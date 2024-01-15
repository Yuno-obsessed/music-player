package commands

import (
	"user-service/internal/application/persistence"
	"user-service/internal/domain"
)

type CreateUserCommand struct {
	persistence.UserRepo
	persistence.UoWManager
}

func NewCreateUserCommand(
	repo persistence.UserRepo,
	manager persistence.UoWManager,
) *CreateUserCommand {
	return &CreateUserCommand{
		repo,
		manager,
	}
}

func (c *CreateUserCommand) Handle(request CreateUserRequest) (CreateUserResponse, error) {

	uow := c.UoWManager.GetUoW()
	tx, err := uow.Begin()

	roles := make([]domain.Role, 0)
	for _, roleReq := range request.Roles {
		role := domain.Role{
			ID: roleReq.Id,
		}
		roles = append(roles, role)
	}
	user := domain.User{}.Create(request.Username, request.Password, roles)

	err = c.UserRepo.Create(user, tx)
	if err != nil {
		return CreateUserResponse{}, err
	}

	if err := uow.Commit(); err != nil {
		return CreateUserResponse{}, err
	}

	return CreateUserResponse{
		ID: user.UserID.ID.String(),
	}, err
}
