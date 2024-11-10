CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   role VARCHAR(50) NOT NULL, -- E.g., student, staff, faculty
   user_type VARCHAR(50) NOT NULL, -- E.g., admin, regular user, etc.
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
