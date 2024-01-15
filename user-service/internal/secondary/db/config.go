package db

import (
	"fmt"
	"github.com/ilyakaznacheev/cleanenv"
)

type Config struct {
	Host     string `env:"db.host"`
	Port     int    `env:"db.port"`
	Database string `env:"db.database"`
	User     string `env:"db.user"`
	Password string `env:"db.password"`
}

func NewConfig() Config {
	var config Config

	err := cleanenv.ReadEnv(&config)
	if err != nil {
		panic(err)
	}
	return config
}

func (conf *Config) GetDSN() string {
	return fmt.Sprintf("postgres://%s:%s@%s:%d/%s",
		conf.User, conf.Password, conf.Host, conf.Port, conf.Database,
	)
}
