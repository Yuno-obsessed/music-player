package queries

type AudioQueries struct {
	*GetSongCommand
}

func NewAudioQueries(
	getSong *GetSongCommand,
) *AudioQueries {
	return &AudioQueries{
		getSong,
	}
}
