package storage

import (
	"github.com/ilyakaznacheev/cleanenv"
	"github.com/minio/minio-go/v7"
	"github.com/minio/minio-go/v7/pkg/credentials"
)

type Config struct {
	Host      string `env:"minio.host"`
	Port      string `env:"minio.port"`
	AccessKey string `env:"minio.access_key"`
	SecretKey string `env:"minio.secret_key"`
}

func NewConfig() Config {
	var config Config

	err := cleanenv.ReadEnv(&config)
	if err != nil {
		panic(err)
	}
	return config
}

func (conf *Config) GetClient() *minio.Client {
	minioClient, err := minio.New(conf.Host+conf.Port, &minio.Options{
		Creds:  credentials.NewStaticV4(conf.AccessKey, conf.SecretKey, ""),
		Secure: false,
	})
	if err != nil {
		panic(err)
	}

	return minioClient
}
