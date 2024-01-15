package storage

import (
	"context"
	"fmt"
	"github.com/minio/minio-go/v7"
	"io"
	"time"
)

var expiry time.Duration = time.Hour

type MinioInstance struct {
	client *minio.Client
}

func NewMinioInstance(config Config) *MinioInstance {
	return &MinioInstance{client: config.GetClient()}
}

func (m *MinioInstance) UploadFile(bucketName string, objectName string, reader io.Reader, size int64) error {

	exists, err := m.client.BucketExists(context.Background(), bucketName)
	if err != nil {
		return err
	}
	if !exists {
		err := m.client.MakeBucket(context.Background(), bucketName, minio.MakeBucketOptions{Region: "us-east-1", ObjectLocking: false})
		if err != nil {
			return err
		}
	}
	info, err := m.client.PutObject(context.Background(), bucketName, objectName, reader, size, minio.PutObjectOptions{})
	if err != nil {
		return err
	}

	fmt.Println(info.Bucket, info.VersionID, info.Size)
	return nil
}

func (m *MinioInstance) GetPresignedURL(bucketName, objectName string) (string, error) {
	presignedURL, err := m.client.PresignedGetObject(context.Background(), bucketName, objectName, expiry, nil)
	if err != nil {
		return "", err
	}

	return presignedURL.String(), nil
}

func (m *MinioInstance) GetFile(bucketName, objectName string) (*minio.Object, error) {
	audio, err := m.client.GetObject(context.Background(), bucketName, objectName, minio.GetObjectOptions{})
	if err != nil {
		return nil, err
	}

	return audio, nil
}
