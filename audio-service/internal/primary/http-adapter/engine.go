package http_adapter

import (
	"github.com/gofiber/fiber/v2"
)

type Engine struct {
	app *fiber.App
}

func NewEngine() Engine {
	cfg := fiber.Config{
		Prefork:      true,
		ErrorHandler: NewErrorMiddleware,
	}
	app := fiber.New(cfg)
	return Engine{app: app}
}
func (e *Engine) Run() error {
	return e.app.Listen(":8082")
}
func (e *Engine) Shutdown() error {
	return e.app.Shutdown()
}
