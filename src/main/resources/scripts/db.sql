CREATE TABLE CUSTOM_USER (
	ID serial PRIMARY KEY,
	EMAIL VARCHAR(100) UNIQUE NOT NULL,
	PWD VARCHAR(100) NOT NULL,
	ROLE_NAME VARCHAR(20)
);

-- use https://bcrypt-generator.com/, round 12, password text: 123
INSERT INTO CUSTOM_USER (id, email, pwd, role_name) VALUES (1, 'duyen@gmail.com', '$2a$12$u3Q8r8XvpQddTHV50rbcKe6VhWm1ItR98uYe2nwF4p80qGLlUG.j6', 'user');