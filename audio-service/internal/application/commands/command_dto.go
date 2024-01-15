package commands

import "io"

type UploadSongCommandDTO struct {
	Title string    `json:"title"`
	File  io.Reader `json:"file,omitempty"`
	Genre string    `json:"genre"`
	Size  int64     `json:"size"`
}
