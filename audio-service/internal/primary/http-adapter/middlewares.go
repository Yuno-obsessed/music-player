package http_adapter

import (
	"audio-service/internal/secondary/logger"
	"errors"
	"github.com/gofiber/fiber/v2"
	loggerM "github.com/gofiber/fiber/v2/middleware/logger"
)

type Middleware struct {
	fiber.Router
}

func NewMiddleware(engine Engine) Middleware {
	router := engine.app.Use(loggerM.New())

	return Middleware{Router: router}
}

func NewErrorMiddleware(c *fiber.Ctx, err error) error {
	code := fiber.StatusInternalServerError
	var e *fiber.Error
	if errors.As(err, &e) {
		code = e.Code
	}
	log := logger.NewLogger()
	log.Error(err)
	c.Set(fiber.HeaderContentType, fiber.MIMETextPlainCharsetUTF8)
	return c.Status(code).JSON(fiber.Map{"error:": err.Error()})
}
