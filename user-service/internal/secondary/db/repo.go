package db

import (
	"context"
	"github.com/google/uuid"
	"github.com/jackc/pgx/v4"
	"user-service/internal/domain"
)

type UserRepo struct {
	Conn Connection
}

func NewUserRepo(conn Connection) *UserRepo {
	return &UserRepo{
		Conn: conn,
	}
}

func (es *UserRepo) Create(user domain.User, transaction interface{}) error {
	tx := transaction.(pgx.Tx)
	model := MapUserToUserModel(user)

	createUser := `INSERT INTO public.user (id, username, password) 
				VALUES ($1, $2, $3);`
	_, err := tx.Exec(context.Background(),
		createUser, model.UserID, model.Username, model.Password,
	)
	if err != nil {
		return err
	}

	getRole := `SELECT * FROM public.role r 
				WHERE r.id IN ($1);`
	rows, err := tx.Query(context.Background(), getRole, user.GetRoles())
	if err != nil {
		return err
	}
	defer rows.Close()

	var roles []Role
	for rows.Next() {
		var role Role
		err := rows.Scan(&role.RoleID, &role.RoleType)
		if err != nil {
			return err
		}
		roles = append(roles, role)
	}

	createRole := `INSERT INTO public.user_role (user_id, role_id)
				VALUES ($1, $2);`
	if len(user.Roles) == 0 {
		_, err = tx.Exec(context.Background(),
			createRole, model.UserID, "1",
		)
	} else {
		for _, role := range roles {
			_, err = tx.Exec(context.Background(),
				createRole, user.UserID, role.RoleID)
		}
	}
	if err != nil {
		return err
	}

	return nil
}

func (es *UserRepo) GetByID(id uuid.UUID, transaction interface{}) (*domain.User, error) {

	tx := transaction.(pgx.Tx)
	var user domain.User

	query := `SELECT u.id, u.username, u.password, 
					array_agg(r.id), array_agg(r.type)
				FROM public.user_role ur
				LEFT JOIN public.user u
				ON ur.user_id = u.id
				LEFT JOIN public.role r
				ON ur.role_id = r.id
				WHERE user_id = $1
				GROUP BY u.id, u.username, u.password;`

	var roleIDs []int32
	var roleTypes []string
	err := tx.QueryRow(context.Background(), query, id).Scan(&user.UserID, &user.Username, &user.Password, &roleIDs, &roleTypes)
	if err != nil {
		return nil, err
	}

	for i, roleID := range roleIDs {
		role := domain.Role{
			ID:   roleID,
			Type: roleTypes[i],
		}
		user.Roles = append(user.Roles, role)
	}

	return &user, nil
}
