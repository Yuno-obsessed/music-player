CREATE TABLE IF NOT EXISTS [user](
    id UUID PRIMARY KEY,
    username VARCHAR(200) NOT NULL,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS [role](
    id INTEGER PRIMARY KEY GENERATED AS IDENTITY,
    role_type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role(
    user_id UUID,
    role_id INTEGER,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);