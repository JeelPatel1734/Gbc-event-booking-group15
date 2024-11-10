CREATE TABLE room (
                      id SERIAL PRIMARY KEY,
                      room_name VARCHAR(100) NOT NULL,
                      capacity INT NOT NULL,
                      available BOOLEAN DEFAULT true,
                      features TEXT
);
