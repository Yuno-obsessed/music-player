package queries

import (
	"user-service/internal/application/persistence"
)

type GetUserQuery struct {
	persistence.UserRepo
	persistence.UoWManager
	persistence.Logger
}

func NewGetUserQuery(
	repo persistence.UserRepo,
	manager persistence.UoWManager,
	logger persistence.Logger,
) *GetUserQuery {
	return &GetUserQuery{
		repo,
		manager,
		logger,
	}
}

func (q *GetUserQuery) Handle(query GetUserRequest) (GetUserResponse, error) {

	uow := q.UoWManager.GetUoW()
	tx, err := uow.Begin()
	if err != nil {
		return GetUserResponse{}, err
	}

	user, err := q.UserRepo.GetByID(query.ID, tx)
	if err != nil {
		return GetUserResponse{}, err
	}

	roles := make([]RoleQueryDTO, 0)
	for _, roleReq := range user.Roles {
		role := RoleQueryDTO{
			RoleType: roleReq.Type,
		}
		roles = append(roles, role)
	}

	if err := uow.Commit(); err != nil {
		return GetUserResponse{}, err
	}
	defer func() {
		if err != nil {
			q.Logger.Error(err)
		} else {
			q.Logger.Info("Committed successfully")
		}
	}()

	return GetUserResponse{
		Username: user.Username,
		Password: user.Password,
		Roles:    roles,
	}, nil
}
