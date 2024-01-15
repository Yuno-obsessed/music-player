package grpc

import (
	"context"
	"github.com/google/uuid"
	"user-service/internal/application"
	"user-service/internal/application/commands"
	"user-service/internal/application/queries"
	"user-service/protobuf/servicespb"
)

type UserGrpcService struct {
	cs *application.UserServices
}

func NewUserGrpcService(cs *application.UserServices) servicespb.UserServiceServer {
	return &UserGrpcService{
		cs: cs,
	}
}

func (s *UserGrpcService) CreateUser(_ context.Context, request *servicespb.CreateUserRequest) (*servicespb.CreateUserResponse, error) {

	roles := make([]commands.RoleRequest, 0)
	for _, roleReq := range request.Roles {
		role := commands.RoleRequest{
			Id: roleReq.RoleId,
		}
		roles = append(roles, role)
	}

	c := commands.CreateUserRequest{
		Username: request.Username,
		Password: request.Password,
		Roles:    roles,
	}

	response, err := s.cs.Commands.CreateUserCommand.Handle(c)
	if err != nil {
		return nil, err
	}
	return &servicespb.CreateUserResponse{
		Id: response.ID,
	}, nil
}

func (s *UserGrpcService) GetUser(_ context.Context, request *servicespb.GetUserRequest) (*servicespb.GetUserResponse, error) {

	id, err := uuid.Parse(request.Id)
	if err != nil {
		return nil, err
	}
	c := queries.GetUserRequest{
		ID: id,
	}

	response, err := s.cs.Queries.GetUserQuery.Handle(c)
	if err != nil {
		return nil, err
	}

	var roles []*servicespb.GetRole
	for _, role := range response.Roles {
		role := &servicespb.GetRole{
			Type: role.RoleType,
		}
		roles = append(roles, role)
	}

	return &servicespb.GetUserResponse{
		Username: response.Username,
		Password: response.Password,
		Roles:    roles,
	}, nil
}
