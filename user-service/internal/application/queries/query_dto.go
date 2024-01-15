package queries

import "github.com/google/uuid"

type GetUserResponse struct {
	Username string         `json:"username"`
	Password string         `json:"password"`
	Roles    []RoleQueryDTO `json:"roles"`
}

type RoleQueryDTO struct {
	RoleType string `json:"role_type"`
}

type GetUserRequest struct {
	ID uuid.UUID `json:"id"`
}
