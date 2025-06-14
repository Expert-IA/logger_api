ALTER TABLE user_tracking
ADD COLUMN user_id VARCHAR(255);

ALTER TABLE user_tracking
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES users(id);
