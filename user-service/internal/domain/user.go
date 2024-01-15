package domain

import (
	"fmt"
	"github.com/google/uuid"
	"strings"
)

type User struct {
	UserID   UserID
	Username string
	Password string
	Roles    []Role
}

func (User) Create(username, password string, roles []Role) User {
	return User{
		UserID:   NewUserID(uuid.New()),
		Username: username,
		Password: password,
		Roles:    roles,
	}
}

func (u User) GetRoles() string {
	var roles []string
	for _, role := range u.Roles {
		roles = append(roles, role.Type)
	}
	userRoles := strings.Join(roles, ",")
	fmt.Println(userRoles)
	return userRoles
}
