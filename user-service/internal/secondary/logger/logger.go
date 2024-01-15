package logger

import (
	"go.uber.org/zap"
	"go.uber.org/zap/zapcore"
)

type Logger struct {
	*zap.SugaredLogger
}

func (l *Logger) Info(args ...string) {
	l.Infoln(args)
}

func (l *Logger) Error(err error) {
	l.Errorln(err)
}

var (
	globalLogger *Logger
	zapLogger    *zap.Logger
)

func NewLogger() *Logger {
	if globalLogger == nil {
		logger := newLogger()
		globalLogger = &logger
	}
	return globalLogger
}

func newLogger() Logger {
	var zapConfig zap.Config
	zapConfig = zap.NewDevelopmentConfig()
	zapConfig.EncoderConfig.EncodeLevel = zapcore.CapitalColorLevelEncoder
	zapLogger, _ = zapConfig.Build()
	logger := newSugaredLogger(zapLogger)
	return *logger
}
func newSugaredLogger(logger *zap.Logger) *Logger {
	return &Logger{
		SugaredLogger: logger.Sugar(),
	}
}
