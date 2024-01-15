include config/.env
migrate-new:
	migrate create -ext sql -dir ${db.migration_path} -seq init
migrate-up:
	migrate -path ${db.migration_path} -database ${db.url} -verbose up
migrate-down:
	migrate -path ${db.migration_path} -database ${db.url} -verbose down
migrate-force:
	migrate -path ${db.migration_path} -database ${db.url} -verbose force 1