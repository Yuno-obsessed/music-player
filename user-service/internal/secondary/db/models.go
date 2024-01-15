package db

import "github.com/google/uuid"

type User struct {
	UserID   uuid.UUID
	Username string
	Password string
	Roles    []Role
}

type Role struct {
	RoleID   int
	RoleType string
}
