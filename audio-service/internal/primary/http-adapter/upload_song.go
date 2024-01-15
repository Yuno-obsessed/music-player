package http_adapter

import (
	"audio-service/internal/application"
	"audio-service/internal/application/commands"
	"github.com/gofiber/fiber/v2"
	"github.com/google/uuid"
	"strings"
)

type UploadHandler struct {
	*application.AudioServices
}

func NewUploadHandler(services *application.AudioServices) *UploadHandler {
	return &UploadHandler{services}
}

func (h *UploadHandler) Handle(c *fiber.Ctx) error {
	formFile, err := c.FormFile("file")
	if err != nil {
		return err
	}

	title := c.FormValue("title", uuid.New().String()[:10])
	genre := c.FormValue("genre")

	file, err := formFile.Open()
	if err != nil {
		return c.Status(fiber.StatusBadRequest).JSON(fiber.Map{
			"error": err.Error()},
		)
	}
	defer file.Close()

	objectName := title + formFile.Filename[strings.Index(formFile.Filename, "."):]

	dto := commands.UploadSongCommandDTO{
		Title: objectName,
		File:  file,
		Genre: genre,
		Size:  formFile.Size,
	}

	response, err := h.Commands.UploadSongCommand.Handle(dto)
	if err != nil {
		return c.Status(fiber.StatusInternalServerError).JSON(fiber.Map{
			"error": err.Error()},
		)
	}

	return c.Status(fiber.StatusOK).JSON(response)
}
