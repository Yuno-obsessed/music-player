package domain

import "github.com/google/uuid"

type AudioID struct {
	UUID uuid.UUID
}

func NewAudioID(uuid uuid.UUID) AudioID {
	return AudioID{uuid}
}
