package queries

type UserQueries struct {
	*GetUserQuery
}

func NewUserQueries(
	query *GetUserQuery,
) *UserQueries {
	return &UserQueries{
		query,
	}
}
