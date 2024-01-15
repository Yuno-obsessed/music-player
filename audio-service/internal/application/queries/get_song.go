package queries

import (
	"audio-service/internal/application/dto"
	"audio-service/internal/application/persistence"
)

type GetSongCommand struct {
	persistence.AudioRepo
	persistence.Storage
	persistence.UoWManager
	persistence.Logger
}

func NewGetSongCommand(
	repo persistence.AudioRepo,
	storage persistence.Storage,
	manager persistence.UoWManager,
	logger persistence.Logger,
) *GetSongCommand {
	return &GetSongCommand{
		AudioRepo:  repo,
		Storage:    storage,
		UoWManager: manager,
		Logger:     logger,
	}
}

func (q *GetSongCommand) Handle(query GetSongQueryDTO) (dto.AudioQuery, error) {

	uow := q.UoWManager.GetUoW()
	tx, err := uow.Begin()
	if err != nil {
		return dto.AudioQuery{}, err
	}
	audio, err := q.AudioRepo.GetByID(query.ID, tx)
	if err != nil {
		return dto.AudioQuery{}, err
	}
	q.Logger.Debug("Audio with id: " + audio.AudioID.UUID.String() + " successfully saved.")

	if err := uow.Commit(); err != nil {
		return dto.AudioQuery{}, err
	}

	url, err := q.Storage.GetPresignedURL(audio.Title, audio.Bucket)
	if err != nil {
		return dto.AudioQuery{}, err
	}
	q.Logger.Debug("Audio url extracted: " + url)

	return dto.AudioQuery{
		Title: audio.Title,
		Url:   url,
	}, nil
}
