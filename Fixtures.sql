USE `gestion-formation`;

-- gf_user

INSERT INTO `gestion-formation`.`gf_user`
(`name`,`password`,`email`,`role`)
VALUES("serkox", "mdp", "serkox@gmail.com", "ADMIN");

INSERT INTO `gestion-formation`.`gf_user`
(`name`,`password`,`email`,`role`)
VALUES("pe", "mdp", "pe@gmail.com", "VISITOR");

INSERT INTO `gestion-formation`.`gf_user`
(`name`,`password`,`email`,`role`)
VALUES("val", "mdp", "val@gmail.com", "FORMER");


-- gf_skill

INSERT INTO `gestion-formation`.`gf_skill`
(`name`,`weight`,`id_trainer`)
VALUES("Réglementation", 3, 1);

INSERT INTO `gestion-formation`.`gf_skill`
(`name`,`weight`,`id_trainer`)
VALUES("Météorologie", 2, 1);

INSERT INTO `gestion-formation`.`gf_skill`
(`name`,`weight`,`id_trainer`)
VALUES("Communications", 1, 1);

INSERT INTO `gestion-formation`.`gf_skill`
(`name`,`weight`,`id_trainer`)
VALUES("Navigation", 2, 1);

INSERT INTO `gestion-formation`.`gf_skill`
(`name`,`weight`,`id_trainer`)
VALUES("Principees du vol", 2, 1);


-- prerequisite_of_skill

INSERT INTO `gestion-formation`.`prerequisite_of_skill`
(`id_skill`,`id_skill_prerequisite`)
VALUES(4,1);

INSERT INTO `gestion-formation`.`prerequisite_of_skill`
(`id_skill`,`id_skill_prerequisite`)
VALUES(4,2);

INSERT INTO `gestion-formation`.`prerequisite_of_skill`
(`id_skill`,`id_skill_prerequisite`)
VALUES(4,3);


-- gf_course

INSERT INTO `gestion-formation`.`gf_course`
(`created_by`, `name`)
VALUES(3, "Parcours PPL");


-- gf_training

INSERT INTO `gestion-formation`.`gf_training`
(`name`)
VALUES("Principes du vol 01");

INSERT INTO `gestion-formation`.`gf_training`
(`name`)
VALUES("Météorologie 01");


-- course_has_training

INSERT INTO `gestion-formation`.`course_has_training`
(`id_training`,`id_course`)
VALUES(1,1);

INSERT INTO `gestion-formation`.`course_has_training`
(`id_training`,`id_course`)
VALUES(2,1);







-- QUESTIONS REGLEMENTATIONS

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Sur une carte VAC la TODA est la :",1,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("longueur de piste utilisable par un avion pour la phase de roulement au décollage",0,1);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("longueur de piste utilisable par un avion lors du décollage",1,1);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("distance de piste parcourue par un avion du lâcher des freins jusqu'à ce que les roues quittent le sol",0,1);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("distance de piste parcourue par un avion du lâcher des freins jusqu'au passage des 15 m",0,1);




INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Le sigle OACI signifie :",1,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("Organisation de l'Aviation Civile Internationale",1,2);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("Organisation Internationale des Aéronefs Civils",0,2);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("Office de l'information aéronautique internationale",0,2);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("Office aéronautique de contrôle international",0,2);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"En vol au cap magnétique 175 vous subissez un vent venant de la gauche correspondant à une dérive de 5°. Le QNH est de 1013 hPa. Le premier niveau de vol utilisable est le niveau :",1,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("40",0,3);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("35",0,3);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("45",1,3);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("30",0,3);

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Une zone règlementée est un espace aérien :",1,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("contrôlé",0,4);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("dont la pénétration est toujours autorisée après contact radio",0,4);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("dont l'accès est interdit en VFR dans tous les cas",0,4);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("dont la pénétration est subordonnée à des conditions spécifiées",1,4);

-- QUESTIONS METEOROLOGIES

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"Lorsque de la brume est signalée par les services météorologiques, vous considérez que la visibilité est :",2,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("d'environ 10km",0,5);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("peu affectée par ce phénomène",0,5);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("inférieur à 1km",0,5);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("comprise entre 1km et 5km",1,5);




INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"En altitude, les vents :",2,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("ralentissent et tournent vers la droite",0,6);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("ralentissent et tournent vers la gauche",0,6);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("accélèrent et tournent vers la gauche",0,6);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("accélèrent et tournent vers la droite",1,6);



INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(3,"Lors du passage d'un front froid :",2,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la température baisse, la pression monte",0,7);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la température monte, la pression baisse",0,7);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la température et la pression montent",0,7);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la température et la pression baissent",1,7);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(3,"Les ascendances thermiques se produisent par :",2,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("vent fort",0,8);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("magie",0,8);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("forte humidité",0,8);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("réchauffement du sol",1,8);

-- QUESTIONS NAVIGATIONS

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"Sur une carte aéronautique au 1/500 000, la détermination de la distance entre deux points n'ayant ni la même longitude ni la même latitude :",4,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("est impossible avec ce type de projection",0,9);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("se fait en reportant cette distance sur le méridien le plus proche",1,9);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("est impossible sans calculs complexes",0,9);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("se fait en reportant l'écart carte sur le parallèle le plus proche",0,9);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"L'ADF est utilisable",4,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("seulement de jour et sans orage",0,10);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("seulement de nuit par toutes les conditions météorologiques",0,10);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("de jour comme de nuit",1,10);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("seulement de jour et par toutes les conditions météorologiques",0,10);

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(3,"Vous décollez d'un aérodrome situé à une altitude de 364 ft où le QFE est de 1010 hPa. En montée, passant 3500 ft QNH, vous croisez un avion en transit au FL 45. Dans les conditions d'atmosphère standard, au moment du croisement, la marge entre les deux aéronefs est :",4,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("1280 ft",1,11);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("1084 ft",0,11);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("1000 ft",0,11);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("636 ft",0,11);

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Les coordonnées du Pôle Nord sont :",4,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("90°N et 00°W",1,12);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("90°N et 18°W",0,12);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("00°N et 00°W",0,12);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("00°N et 18°W",0,12);



-- QUESTIONS PRINCIPES DU VOL

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"La portance et la traînée sont définies par rapport à :",5,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la direction du vent relatif",1,13);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("au plan de symétrie de l'aile",0,13);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("à un plan horizontal",0,13);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la corde de référence du profil d'aile",0,13);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"Le facteur de charge est le rapport :",5,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("de la traînée sur la portance",0,14);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("du poids sur la portance",0,14);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("de la portance sur le poids",1,14);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("de la traînée sur le poids",0,14);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Une action sur la commande de tangage, modifie directement :",5,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("l'angle d'incidence sur la profondeur",1,15);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("le calage de l'aile",0,15);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("la puissance du moteur",0,15);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("l'inclinaison",0,15);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Une des conditions pour provoquer une autorotation est :",5,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("une faible incidence",0,16);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("une forte incidence",1,16);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("un virage engagé",0,16);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("une forte vitesse",0,16);


-- QUESTIONS COMMUNICATIONS

INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(2,"En vol, en cas d'urgence, le pilote envoie un message radio commençant par :",3,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("urgence",0,17);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("alerte",0,17);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("mayday",0,17);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("pan",1,17);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"L'expression WILCO :",3,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("désigne la lettre W dans le code d'épellation",0,18);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("est un ordre de dégagement du circuit de piste",0,18);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("signifie : Bien reçu",0,18);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("signifie : Votre message a été compris et sera exécuté",1,18);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"Vous êtes aux commandes d'un aéronef immatriculé F-ADES, votre indicatif abrégé est :",3,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("F-AD",0,19);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("DES",0,19);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("F-ES",1,19);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("ES",0,19);


INSERT INTO `gestion-formation`.`gf_question`
(`difficulty`,`contents`,`id_skill`,`id_trainer`)
VALUES(1,"L'expression conventionnelle pour dire oui est :",3,3);

INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("roger",0,20);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("affirme",1,20);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("bien compris",0,20);
INSERT INTO `gestion-formation`.`gf_proposal`
(`denominate`,`state`,`id_question`)
VALUES("bien pris",0,20);