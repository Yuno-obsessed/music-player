package persistence

import (
	"audio-service/internal/domain"
	"github.com/minio/minio-go/v7"
	"io"
)

type UoW interface {
	Commit() error
	Rollback() error
	Begin() (interface{}, error)
}
type UoWManager interface {
	GetUoW() UoW
}

type AudioRepo interface {
	Create(Audio domain.Audio, transaction interface{}) error
	GetByID(id string, transaction interface{}) (*domain.Audio, error)
}

type Storage interface {
	UploadFile(bucketName string, objectName string, reader io.Reader, size int64) error
	GetPresignedURL(bucketName, objectName string) (string, error)
	GetFile(bucketName, objectName string) (*minio.Object, error)
}

type Logger interface {
	Debug(args ...string)
	Error(err error)
}
