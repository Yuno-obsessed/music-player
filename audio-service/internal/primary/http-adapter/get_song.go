package http_adapter

import (
	"audio-service/internal/application"
	"audio-service/internal/application/queries"
	"github.com/gofiber/fiber/v2"
)

type GetSongHandler struct {
	*application.AudioServices
}

func NewGetSongHandler(services *application.AudioServices) *GetSongHandler {
	return &GetSongHandler{services}
}

func (h *GetSongHandler) Handle(c *fiber.Ctx) error {
	id := c.Params("id")

	query := queries.GetSongQueryDTO{ID: id}
	response, err := h.Queries.GetSongCommand.Handle(query)
	if err != nil {
		return err
	}

	return c.Status(fiber.StatusOK).JSON(response)
}

// server-sent-events to send chunks of data, catch events like pause and get another chunk.
