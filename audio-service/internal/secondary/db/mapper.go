package db

import (
	"audio-service/internal/domain"
	"github.com/google/uuid"
)

func MapAudioToAudioModel(audio domain.Audio) Audio {
	return Audio{
		audio.AudioID.UUID.String(),
		audio.Title,
		audio.Bucket,
	}
}

func MapAudioModelToAudio(audio Audio) domain.Audio {
	id, _ := uuid.Parse(audio.AudioID)
	return domain.Audio{
		AudioID: domain.NewAudioID(id),
		Title:   audio.AudioName,
		Bucket:  audio.Bucket,
	}
}
