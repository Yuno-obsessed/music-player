package http_adapter

import "github.com/gofiber/fiber/v2"

type Handlers []fiber.Handler

func NewHandlers() Handlers {
	return Handlers{}
}
