package dto

type AudioQuery struct {
	Title string `json:"title"`
	Url   string `json:"url"`
}

type UploadSongDTO struct {
	Title string `json:"title"`
}
