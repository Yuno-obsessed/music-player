package persistence

import (
	"github.com/google/uuid"
	"user-service/internal/domain"
)

type UoW interface {
	Commit() error
	Rollback() error
	Begin() (interface{}, error)
}
type UoWManager interface {
	GetUoW() UoW
}

type UserRepo interface {
	Create(user domain.User, transaction interface{}) error
	GetByID(id uuid.UUID, transaction interface{}) (*domain.User, error)
}

type Logger interface {
	Info(args ...string)
	Error(err error)
}
