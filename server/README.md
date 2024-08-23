<h3> MySQL </h3>

```
CREATE TABLE user (
    `t_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT 0 ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE user_credentials (
    user_t_id BIGINT UNIQUE,
    hashed_password CHAR(60) NOT NULL
)
```
