CREATE DATABASE `gestion-formation` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE Utilisateur(
   Id_Utilisateur INT AUTO_INCREMENT,
   nom VARCHAR(50) ,
   email VARCHAR(50) ,
   motdepasse VARCHAR(50) ,
   PRIMARY KEY(Id_Utilisateur)
);

CREATE TABLE Formateur(
   Id_Formateur INT AUTO_INCREMENT,
   groupe VARCHAR(50)  NOT NULL,
   Id_Utilisateur INT NOT NULL,
   PRIMARY KEY(Id_Formateur),
   FOREIGN KEY(Id_Utilisateur) REFERENCES Utilisateur(Id_Utilisateur)
);

CREATE TABLE Graphe(
   Id_Graphe INT AUTO_INCREMENT,
   PRIMARY KEY(Id_Graphe)
);

CREATE TABLE Questionnaire(
   Id_Questionnaire INT AUTO_INCREMENT,
   Id_Formateur INT NOT NULL,
   PRIMARY KEY(Id_Questionnaire),
   FOREIGN KEY(Id_Formateur) REFERENCES Formateur(Id_Formateur)
);

CREATE TABLE Question(
   Id_Question INT AUTO_INCREMENT,
   niveau INT NOT NULL,
   difficulte INT NOT NULL,
   domaine VARCHAR(50)  NOT NULL,
   PRIMARY KEY(Id_Question)
);

CREATE TABLE Competence(
   Id_Competence INT AUTO_INCREMENT,
   nom VARCHAR(50)  NOT NULL,
   Id_Formateur INT NOT NULL,
   PRIMARY KEY(Id_Competence),
   FOREIGN KEY(Id_Formateur) REFERENCES Formateur(Id_Formateur)
);

CREATE TABLE Formation(
   Id_Formation INT AUTO_INCREMENT,
   PRIMARY KEY(Id_Formation)
);

CREATE TABLE Parcours(
   Id_Parcours INT AUTO_INCREMENT,
   PRIMARY KEY(Id_Parcours)
);

CREATE TABLE Apprenant(
   Id_Apprenant INT AUTO_INCREMENT,
   Id_Parcours INT NOT NULL,
   Id_Utilisateur INT NOT NULL,
   PRIMARY KEY(Id_Apprenant),
   FOREIGN KEY(Id_Parcours) REFERENCES Parcours(Id_Parcours),
   FOREIGN KEY(Id_Utilisateur) REFERENCES Utilisateur(Id_Utilisateur)
);

CREATE TABLE Apprenant_avoir_graphe(
   Id_Apprenant INT,
   Id_Graphe INT,
   PRIMARY KEY(Id_Apprenant, Id_Graphe),
   FOREIGN KEY(Id_Apprenant) REFERENCES Apprenant(Id_Apprenant),
   FOREIGN KEY(Id_Graphe) REFERENCES Graphe(Id_Graphe)
);

CREATE TABLE Questionnaire_posseder_question(
   Id_Questionnaire INT,
   Id_Question INT,
   PRIMARY KEY(Id_Questionnaire, Id_Question),
   FOREIGN KEY(Id_Questionnaire) REFERENCES Questionnaire(Id_Questionnaire),
   FOREIGN KEY(Id_Question) REFERENCES Question(Id_Question)
);

CREATE TABLE Formateur_gérer_question(
   Id_Formateur INT,
   Id_Question INT,
   PRIMARY KEY(Id_Formateur, Id_Question),
   FOREIGN KEY(Id_Formateur) REFERENCES Formateur(Id_Formateur),
   FOREIGN KEY(Id_Question) REFERENCES Question(Id_Question)
);

CREATE TABLE Formateur_modifier_graphe(
   Id_Formateur INT,
   Id_Graphe INT,
   commentaire VARCHAR(50) ,
   PRIMARY KEY(Id_Formateur, Id_Graphe),
   FOREIGN KEY(Id_Formateur) REFERENCES Formateur(Id_Formateur),
   FOREIGN KEY(Id_Graphe) REFERENCES Graphe(Id_Graphe)
);

CREATE TABLE Version_de_graphe(
   Id_Graphe INT,
   Id_Graphe_1 INT,
   PRIMARY KEY(Id_Graphe, Id_Graphe_1),
   FOREIGN KEY(Id_Graphe) REFERENCES Graphe(Id_Graphe),
   FOREIGN KEY(Id_Graphe_1) REFERENCES Graphe(Id_Graphe)
);

CREATE TABLE Parcours_avoir_formation(
   Id_Formation INT,
   Id_Parcours INT,
   PRIMARY KEY(Id_Formation, Id_Parcours),
   FOREIGN KEY(Id_Formation) REFERENCES Formation(Id_Formation),
   FOREIGN KEY(Id_Parcours) REFERENCES Parcours(Id_Parcours)
);
