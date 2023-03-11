CREATE TABLE  users (
                id bigserial PRIMARY KEY,
                username varchar(255) not null,
                password varchar(255) not null,
                email varchar(255)
);
INSERT INTO users(username,password,email) VALUES
                ('TonyStark','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','TonyStark@email.com'),
                ('MichaelM','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','MichaelM@email.com'),
                ('RedFox','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','RedFox@email.com'),
                ('FredPerry','$2a$12$N70mvj5wOM8Ivf/jMEfWkOCHWZYZGF2ECWLfdx7kAMKqIggukcQRC','FredPerry@email.com');

CREATE TABLE roles (
                id bigserial PRIMARY KEY,
                type varchar(255)
);
INSERT INTO roles(type) VALUES
                ('ROLE_ADMIN'),
                ('ROLE_USER');

CREATE TABLE  users_roles (
            id bigserial PRIMARY KEY REFERENCES users(id),
            user_id bigserial REFERENCES users(id),
            role_id bigserial REFERENCES roles(id)
);
INSERT INTO users_roles(user_id,role_id) VALUES
                (1,1),
                (2,2),
                (3,1),
                (4,1);