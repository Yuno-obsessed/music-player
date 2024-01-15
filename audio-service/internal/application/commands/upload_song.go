package commands

import (
	"audio-service/internal/application/dto"
	"audio-service/internal/application/persistence"
	"audio-service/internal/domain"
)

type UploadSongCommand struct {
	persistence.AudioRepo
	persistence.Storage
	persistence.UoWManager
	persistence.Logger
}

func NewUploadSongCommand(
	repo persistence.AudioRepo,
	storage persistence.Storage,
	manager persistence.UoWManager,
	logger persistence.Logger,
) *UploadSongCommand {
	return &UploadSongCommand{
		AudioRepo:  repo,
		Storage:    storage,
		UoWManager: manager,
		Logger:     logger,
	}
}

func (c *UploadSongCommand) Handle(command UploadSongCommandDTO) (dto.UploadSongDTO, error) {

	bucketName := "audio-" + command.Genre
	uow := c.UoWManager.GetUoW()
	tx, err := uow.Begin()
	if err != nil {
		return dto.UploadSongDTO{}, err
	}

	err = c.Storage.UploadFile(bucketName, command.Title, command.File, command.Size)
	if err != nil {
		err := uow.Rollback()
		if err != nil {
			return dto.UploadSongDTO{}, err
		}
		return dto.UploadSongDTO{}, err
	}
	c.Logger.Debug("File: " + command.Title + " uploaded in bucket " + bucketName)

	audio := domain.Audio{}.Create(command.Title, bucketName)

	err = c.AudioRepo.Create(audio, tx)
	if err != nil {
		return dto.UploadSongDTO{}, err
	}
	c.Logger.Debug("Audio with id: " + audio.AudioID.UUID.String() + " saved successfully")

	if err := uow.Commit(); err != nil {
		return dto.UploadSongDTO{}, err
	}

	return dto.UploadSongDTO{Title: command.Title}, nil
}
