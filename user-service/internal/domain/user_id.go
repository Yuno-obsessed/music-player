package domain

import "github.com/google/uuid"

type UserID struct {
	ID uuid.UUID
}

func NewUserID(id uuid.UUID) UserID {
	return UserID{ID: id}
}
