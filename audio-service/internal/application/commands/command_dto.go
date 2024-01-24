package commands

import (
	"github.com/google/uuid"
	"io"
)

type UploadSongCommandDTO struct {
	Id      uuid.UUID `json:"id"`
	Quality int16     `json:"quality"`
	Title   string    `json:"title"`
	File    io.Reader `json:"file,omitempty"`
	Size    int64     `json:"size"`
}
