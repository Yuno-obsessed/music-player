package logger

import (
	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
)

type Logger struct {
	*zap.SugaredLogger
}

func (l *Logger) Debug(args ...string) {
	l.Debugln(args)
}

func (l *Logger) Error(err error) {
	l.Errorln(err)
}

func NewLogger() *Logger {
	var zapConfig zap.Config
	zapConfig = zap.NewDevelopmentConfig()
	encoderConfig := zap.NewDevelopmentEncoderConfig()
	zapConfig.EncoderConfig = encoderConfig
	zapConfig.EncoderConfig.EncodeLevel = zapcore.CapitalLevelEncoder
	zapConfig.EncoderConfig.EncodeCaller = nil
	zapConfig.EncoderConfig.EncodeTime = zapcore.ISO8601TimeEncoder
	zapConfig.OutputPaths = []string{"stdout"}
	zapConfig.ErrorOutputPaths = []string{"stderr"}
	zapConfig.Level.SetLevel(zapcore.DebugLevel)
	zapLogger, _ := zapConfig.Build()
	logger := newSugaredLogger(zapLogger)
	return logger
}

func newSugaredLogger(logger *zap.Logger) *Logger {
	return &Logger{
		SugaredLogger: logger.Sugar(),
	}
}
