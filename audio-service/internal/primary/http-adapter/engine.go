package http_adapter

import "github.com/gofiber/fiber/v2"

type Engine struct {
	app *fiber.App
}

func NewEngine() Engine {
	cfg := fiber.Config{
		Prefork: true,
	}
	return Engine{app: fiber.New(cfg)}
}
func (e *Engine) Run() error {
	return e.app.Listen(":8080")
}
func (e *Engine) Shutdown() error {
	return e.app.Shutdown()
}
