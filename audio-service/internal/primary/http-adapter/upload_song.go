package http_adapter

import (
	"audio-service/internal/application"
	"audio-service/internal/application/commands"
	"github.com/gofiber/fiber/v2"
	"github.com/google/uuid"
	"strconv"
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
	qualityForm := c.FormValue("quality")
	quality, err := strconv.Atoi(qualityForm)
	if err != nil {
		return err
	}
	idForm := c.FormValue("id")
	id, err := uuid.Parse(idForm)
	if err != nil {
		return err
	}

	file, err := formFile.Open()
	if err != nil {
		return err
	}
	defer file.Close()

	objectName := title + formFile.Filename[strings.Index(formFile.Filename, "."):]

	dto := commands.UploadSongCommandDTO{
		Id:      id,
		Quality: int16(quality),
		Title:   objectName,
		File:    file,
		Size:    formFile.Size,
	}

	response, err := h.Commands.UploadSongCommand.Handle(dto)
	if err != nil {
		return err
	}

	return c.Status(fiber.StatusOK).JSON(response)
}
