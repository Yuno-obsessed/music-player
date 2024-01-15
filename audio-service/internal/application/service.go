package application

import (
	"audio-service/internal/application/commands"
	"audio-service/internal/application/persistence"
	"audio-service/internal/application/queries"
)

type AudioServices struct {
	Commands *commands.AudioCommands
	Queries  *queries.AudioQueries
}

func NewAudioServices(
	repo persistence.AudioRepo,
	storage persistence.Storage,
	manager persistence.UoWManager,
) *AudioServices {

	uploadSong := commands.NewUploadSongCommand(repo, storage, manager)
	getSong := queries.NewGetSongCommand(repo, storage, manager)
	return &AudioServices{
		Commands: commands.NewAudioCommands(uploadSong),
		Queries:  queries.NewAudioQueries(getSong),
	}
}
