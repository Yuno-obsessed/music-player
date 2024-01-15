package http_adapter

import "github.com/gofiber/fiber/v2"

type Middlewares []fiber.Handler

func NewMiddlewares() Middlewares {
	return Middlewares{}
}
