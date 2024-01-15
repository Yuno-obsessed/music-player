package db

import "user-service/internal/domain"

func MapUserToUserModel(user domain.User) User {
	return User{
		UserID:   user.UserID.ID,
		Username: user.Username,
		Password: user.Password,
	}
}

//func MapUserToRoleModel(role domain.Role) []Role {
//	var roles []Role
//	for _, role := range user.Roles {
//		roles = append(roles, Role{})
//	}
//}
