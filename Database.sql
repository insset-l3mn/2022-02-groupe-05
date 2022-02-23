CREATE DATABASE `gestion-formation` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestion-formation`;
CREATE TABLE gf_user(
   id_user INT AUTO_INCREMENT,
   name VARCHAR(50) ,
   email VARCHAR(50) ,
   password TEXT,
   PRIMARY KEY(id_user)
);

CREATE TABLE gf_former(
   id_former INT AUTO_INCREMENT,
   role VARCHAR(50)  NOT NULL,
   id_user INT NOT NULL,
   PRIMARY KEY(id_former),
   FOREIGN KEY(id_user) REFERENCES gf_user(id_user)
);

CREATE TABLE gf_graph(
   id_graph INT AUTO_INCREMENT,
   PRIMARY KEY(id_graph)
);

CREATE TABLE gf_survey(
   id_survey INT AUTO_INCREMENT,
   type VARCHAR(50)  NOT NULL,
   id_former INT NOT NULL,
   PRIMARY KEY(id_survey),
   FOREIGN KEY(id_former) REFERENCES gf_former(id_former)
);

CREATE TABLE gf_skill(
   id_skill INT AUTO_INCREMENT,
   name VARCHAR(50) ,
   weight INT NOT NULL,
   id_former INT NOT NULL,
   PRIMARY KEY(id_skill),
   FOREIGN KEY(id_former) REFERENCES gf_former(id_former)
);

CREATE TABLE gf_training(
   id_training INT AUTO_INCREMENT,
   name VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_training)
);

CREATE TABLE gf_domain(
   id_domain INT AUTO_INCREMENT,
   denominate VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id_domain)
);

CREATE TABLE gf_question(
   Id_Question INT AUTO_INCREMENT,
   level INT NOT NULL,
   difficulty INT NOT NULL,
   contents VARCHAR(50)  NOT NULL,
   id_skill INT NOT NULL,
   id_domain INT NOT NULL,
   PRIMARY KEY(Id_Question),
   FOREIGN KEY(id_skill) REFERENCES gf_skill(id_skill),
   FOREIGN KEY(id_domain) REFERENCES gf_domain(id_domain)
);

CREATE TABLE gf_course(
   id_course INT AUTO_INCREMENT,
   name VARCHAR(50)  NOT NULL,
   id_former INT NOT NULL,
   id_domain INT NOT NULL,
   PRIMARY KEY(id_course),
   FOREIGN KEY(id_former) REFERENCES gf_former(id_former),
   FOREIGN KEY(id_domain) REFERENCES gf_domain(id_domain)
);

CREATE TABLE gf_proposal(
   id_proposal INT AUTO_INCREMENT,
   denominate VARCHAR(50)  NOT NULL,
   state BOOLEAN NOT NULL,
   Id_Question INT NOT NULL,
   PRIMARY KEY(id_proposal),
   FOREIGN KEY(Id_Question) REFERENCES gf_question(Id_Question)
);

CREATE TABLE gf_learner(
   id_learner INT AUTO_INCREMENT,
   id_course INT NOT NULL,
   id_user INT NOT NULL,
   PRIMARY KEY(id_learner),
   FOREIGN KEY(id_course) REFERENCES gf_course(id_course),
   FOREIGN KEY(id_user) REFERENCES gf_user(id_user)
);

CREATE TABLE learner_has_graph(
   id_learner INT,
   id_graph INT,
   PRIMARY KEY(id_learner, id_graph),
   FOREIGN KEY(id_learner) REFERENCES gf_learner(id_learner),
   FOREIGN KEY(id_graph) REFERENCES gf_graph(id_graph)
);

CREATE TABLE survey_has_question(
   id_survey INT,
   Id_Question INT,
   PRIMARY KEY(id_survey, Id_Question),
   FOREIGN KEY(id_survey) REFERENCES gf_survey(id_survey),
   FOREIGN KEY(Id_Question) REFERENCES gf_question(Id_Question)
);

CREATE TABLE learner_manage_question(
   id_former INT,
   Id_Question INT,
   PRIMARY KEY(id_former, Id_Question),
   FOREIGN KEY(id_former) REFERENCES gf_former(id_former),
   FOREIGN KEY(Id_Question) REFERENCES gf_question(Id_Question)
);

CREATE TABLE trainer_edit_graph(
   id_former INT,
   id_graph INT,
   comment VARCHAR(50) ,
   PRIMARY KEY(id_former, id_graph),
   FOREIGN KEY(id_former) REFERENCES gf_former(id_former),
   FOREIGN KEY(id_graph) REFERENCES gf_graph(id_graph)
);

CREATE TABLE graph_version(
   id_graph INT,
   id_graph_1 INT,
   PRIMARY KEY(id_graph, id_graph_1),
   FOREIGN KEY(id_graph) REFERENCES gf_graph(id_graph),
   FOREIGN KEY(id_graph_1) REFERENCES gf_graph(id_graph)
);

CREATE TABLE course_has_training(
   id_training INT,
   id_course INT,
   PRIMARY KEY(id_training, id_course),
   FOREIGN KEY(id_training) REFERENCES gf_training(id_training),
   FOREIGN KEY(id_course) REFERENCES gf_course(id_course)
);

CREATE TABLE composing_skill(
   id_skill INT,
   id_skill_1 INT,
   PRIMARY KEY(id_skill, id_skill_1),
   FOREIGN KEY(id_skill) REFERENCES gf_skill(id_skill),
   FOREIGN KEY(id_skill_1) REFERENCES gf_skill(id_skill)
);

CREATE TABLE graph_has_skill(
   id_graph INT,
   id_skill INT,
   score INT,
   PRIMARY KEY(id_graph, id_skill),
   FOREIGN KEY(id_graph) REFERENCES gf_graph(id_graph),
   FOREIGN KEY(id_skill) REFERENCES gf_skill(id_skill)
);
