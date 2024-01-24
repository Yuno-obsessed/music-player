package http_adapter

import "github.com/gofiber/fiber/v2"

type FiberGroup struct {
	fiber.Router
}

func NewFiberGroup(middleware Middleware, handlers Handlers) FiberGroup {
	return FiberGroup{Router: middleware.Group("/api/v1/audio", handlers...)}
}
