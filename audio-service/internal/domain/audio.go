package domain

import "github.com/google/uuid"

type Audio struct {
	AudioID AudioID
	Title   string
	Bucket  string
}

func (Audio) Create(title string, bucket string) Audio {
	return Audio{
		AudioID: NewAudioID(uuid.New()),
		Title:   title,
		Bucket:  bucket,
	}
}
