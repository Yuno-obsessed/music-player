package commands

type AudioCommands struct {
	*UploadSongCommand
}

func NewAudioCommands(
	uploadSong *UploadSongCommand,
) *AudioCommands {
	return &AudioCommands{
		uploadSong,
	}
}
