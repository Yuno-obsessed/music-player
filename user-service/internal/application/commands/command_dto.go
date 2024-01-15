package commands

type CreateUserRequest struct {
	Username string        `json:"username"`
	Password string        `json:"password"`
	Roles    []RoleRequest `json:"roles"`
}

type RoleRequest struct {
	Id int32 `json:"id"`
}

type CreateUserResponse struct {
	ID string `json:"id"`
}
