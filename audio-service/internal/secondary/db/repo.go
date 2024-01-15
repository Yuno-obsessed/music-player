package db

import (
	"audio-service/internal/domain"
	"context"
	"github.com/jackc/pgx/v4"
)

type AudioRepo struct {
	Conn Connection
}

func NewAudioRepo(conn Connection) *AudioRepo {
	return &AudioRepo{
		Conn: conn,
	}
}

func (es *AudioRepo) Create(audio domain.Audio, transaction interface{}) error {
	tx := transaction.(pgx.Tx)
	model := MapAudioToAudioModel(audio)
	query := `INSERT INTO public.audio (audio_id, audio_name, bucket) 
				VALUES ($1, $2, $3);`
	_, err := tx.Exec(
		context.Background(),
		query, model.AudioID, model.AudioName, model.Bucket,
	)
	if err != nil {
		return err
	}
	return nil
}

func (es *AudioRepo) GetByID(id string, transaction interface{}) (*domain.Audio, error) {
	tx := transaction.(pgx.Tx)
	var model Audio
	query := `SELECT * FROM public.audio WHERE id = $1;`
	err := tx.QueryRow(context.Background(), query, id).Scan(&model)
	if err != nil {
		return nil, err
	}

	audio := MapAudioModelToAudio(model)
	return &audio, nil
}
