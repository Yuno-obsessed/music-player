package http_adapter

import "github.com/gofiber/fiber/v2"

type Routes struct {
	FiberGroup
	upload *UploadHandler
	get    *GetSongHandler
}

type Route interface {
	Setup()
}

type Handler interface {
	Handle(ctx *fiber.Ctx) error
}

func (r Routes) Setup() {
	r.Add(fiber.MethodPost, "/upload", r.upload.Handle)
	r.Add(fiber.MethodGet, "/:id", r.get.Handle)
}

func NewAudioRouter(
	group FiberGroup,
	upload *UploadHandler,
	get *GetSongHandler,
) *Routes {
	return &Routes{FiberGroup: group, upload: upload, get: get}
}
