USE `gestion-formation`;

-- gf_user

INSERT INTO `gestion-formation`.`gf_user` (`name`,`password`,`email`,`role`) VALUES("serkox", "mdp", "serkox@gmail.com", "ADMIN");
INSERT INTO `gestion-formation`.`gf_user` (`name`,`password`,`email`,`role`) VALUES("pe", "mdp", "pe@gmail.com", "VISITOR");
INSERT INTO `gestion-formation`.`gf_user` (`name`,`password`,`email`,`role`) VALUES("val", "mdp", "val@gmail.com", "FORMER");


-- gf_subdomain

INSERT INTO `gestion-formation`.`gf_subdomain` (`name`) VALUES("Réglementation");
INSERT INTO `gestion-formation`.`gf_subdomain` (`name`) VALUES("Météorologie");
INSERT INTO `gestion-formation`.`gf_subdomain` (`name`) VALUES("Communications");
INSERT INTO `gestion-formation`.`gf_subdomain` (`name`) VALUES("Navigation");
INSERT INTO `gestion-formation`.`gf_subdomain` (`name`) VALUES("Principes du vol");


-- gf_skill
	-- METEOROLOGIE
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('L\'atmosphère - la pression - la température - l\'humidité', '1', '1', '2'); -- 1
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Les nuages - les précipitations associées', '1', '1', '2'); -- 2 
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Le vent - les masses d\'air', '2', '1', '2'); -- 3
		
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('2', '1');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('3', '1');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('3', '2');

	-- REGLEMENTATION
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Le cadre réglementaire', '2', '1', '1'); -- 4
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('L\'aérodrome', '1', '1', '1'); -- 5
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Les cartes d\'approche à vue (VAC) - SERA, les règles de l\'air - Les signaux au sol & lumineux', '2', '1', '1'); -- 6

	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('5', '4');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('6', '5');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('6', '4');

	-- NAVIGATION
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('La Terre, les Cartes, le Temps', '1', '1', '4'); -- 7
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Méthodes de navigation - l\'estime', '2', '1', '4'); -- 8
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Méthodes de navigation : le cheminement, l\'erreur systématique & la radionavigation', '2', '1', '4'); -- 9

	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('8', '7');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('9', '7');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('9', '8');


	-- COMMUNICATIONS
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Généralités sur les communications', '2', '1', '3'); -- 10

	-- PRINCIPES DU VOL
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('L\'atmosphère - Flux & écoulements - La portance & la traînée', '2', '1', '5'); -- 11
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Les axes et angles associés - Les différentes phases du vol - Le virage', '2', '1', '5'); -- 12
INSERT INTO `gestion-formation`.`gf_skill` (`name`, `weight`, `id_trainer`, `id_subdomain`) VALUES ('Le décrochage - La vrille - Stabilité - Domaine de vol', '2', '1', '5'); -- 13

	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('12', '11');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('13', '12');
	INSERT INTO `gestion-formation`.`prerequisite_of_skill` (`id_skill`, `id_skill_prerequisite`) VALUES ('13', '11');


-- user_has_skill

INSERT INTO `gestion-formation`.`user_has_skill` (`id_user`, `id_skill`, `score`, `malus`, `successive_error`) VALUES ('2', '1', '0', '0', '0');
INSERT INTO `gestion-formation`.`user_has_skill` (`id_user`, `id_skill`, `score`, `malus`, `successive_error`) VALUES ('2', '4', '0', '0', '0');
INSERT INTO `gestion-formation`.`user_has_skill` (`id_user`, `id_skill`, `score`, `malus`, `successive_error`) VALUES ('2', '7', '0', '0', '0');


-- gf_course

INSERT INTO `gestion-formation`.`gf_course` (`created_by`,`name`,`id_subdomain`) VALUES (3, "Parcours Réglementation", 1);
INSERT INTO `gestion-formation`.`gf_course` (`created_by`,`name`,`id_subdomain`) VALUES (3, "Parcours Météorologie", 2);
INSERT INTO `gestion-formation`.`gf_course` (`created_by`,`name`,`id_subdomain`) VALUES (3, "Parcours Communications", 3);
INSERT INTO `gestion-formation`.`gf_course` (`created_by`,`name`,`id_subdomain`) VALUES (3, "Parcours Navigation", 4);
INSERT INTO `gestion-formation`.`gf_course` (`created_by`,`name`,`id_subdomain`) VALUES (3, "Principes du vol", 5);




-- gf_training

INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Météorologie 1');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Météorologie 2');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Météorologie 3');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Réglementation 1');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Réglementation 2');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Réglementation 3');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Navigation 1');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Navigation 2');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Navigation 3');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Communications');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Principes du vol 1');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Principes du vol 2');
INSERT INTO `gestion-formation`.`gf_training` (`name`) VALUES ('Principes du vol 3');



-- course_has_training

INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('1', '2');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('2', '2');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('3', '2');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('4', '1');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('5', '1');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('6', '1');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('7', '4');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('8', '4');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('9', '4');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('10', '3');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('11', '5');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('12', '5');
INSERT INTO `gestion-formation`.`course_has_training` (`id_training`, `id_course`) VALUES ('13', '5');


-- QUESTIONS METEOROLOGIE

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Dans les basses couches de l\'atmosphère, on peut considérer que la décroissance moyenne de pression avec l\'altitude est de :', '1', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'En atmosphère type, la température à 5000 ft est :', '1', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'Le Soleil échauffe la Terre par rayonnement, la Terre échauffe les basses couches de l\'atmosphère principalement par :\n', '1', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'La quantité maximale de vapeur d\'eau que peut contenir une masse d\'air :\n', '1', '3');

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'Une particule est dite stable si :', '2', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'Un nuage d\'atmosphère stable :\n', '2', '3');

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'La force du vent est d\'autant plus grande que :', '3', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'Pour schématiser la circulation du vent en altitude, on peut dire qu\'elle est :\n', '3', '3');

	-- REPONSES
    INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('6.5 hPa par 28 ft', '0', '1');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('1 hPa par 1000 ft', '0', '1');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('1 hPa par 100 m', '0', '1');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('1 hPa par 28 ft', '1', '1');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('+12.5°C', '0', '2');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('+5°C', '1', '2');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('+10°C', '0', '2');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('+15°C', '0', '2');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('convection', '0', '3');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('isothermie', '0', '3');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('inversion de température', '0', '3');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('conduction', '1', '3');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est une constance', '0', '4');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('diminue quand la température augmente', '0', '4');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('ne dépend pas de la température', '0', '4');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('augmente quand la température augmente', '1', '4');
    
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('au cours d\'un soulèvement elle se refroidit moins vite que l\'air ambiant', '0', '5');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('sa température est positive', '0', '5');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('sa température est négative', '0', '5');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('au cours d\'un soulèvement elle se refroidit plus vite que l\'air ambiant', '1', '5');
    INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est caractérisé par une grande extension verticale', '0', '6');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est caractérisé par des contours nets, et peut présenter de nombreuses protubérances', '0', '6');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est de forme aplatie avec des contours imprécis', '1', '6');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('ne se rencontre qu\'à des altitudes élevées', '0', '6');

	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la cote des lignes isobares est basse', '0', '7');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la cote des lignes isobares est haute', '0', '7');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('les lignes isobares sont serrées', '1', '7');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('les lignes isobares sont espacées', '0', '7');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('dirigée des hautes pressions vers les basses pressions', '0', '8');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('dirigées des basses pressions vers les hautes pressions', '0', '8');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('perpendiculaire aux lignes isobares', '0', '8');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('parallèle aux lignes isobares', '1', '8');



-- QUESTIONS REGLEMENTATIONS

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'L\'âge minimal pour voler en solo (dans le cadre FCL) est :\n', '4', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Un pilote détenteur d\'une qualification monopilote monomoteur à piston (SEP) effectue une prorogation le 15/05/10. La validité inscrite précédemment était le 31/05/10. La qualification de classe est alors valide jusqu\'au :', '4', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'La couleur des feux délimitant les aires de parking est :', '5', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'Vous décollez en piste 11 pour un tour de piste à main gauche. La route de la branche vent traversier est :\n', '5', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'La tour émet une série d\'éclats blancs à l\'attention d\'un avion dans la circulation d\'aérodrome, cela signifie :\n', '6', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Une piste numérotée 25 a pour orientation magnétique :\n', '6', '3');

    -- REPONSES
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('18 ans', '0', '9');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('16 ans', '1', '9');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('15 ans', '0', '9');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('17 ans', '0', '9');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('15/05/2012', '0', '10');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('31/05/2012', '1', '10');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('31/05/2011', '0', '10');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('15/05/2011', '0', '10');
    
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('blanche', '0', '11');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('rouge', '0', '11');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('bleue', '1', '11');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('verte', '0', '11');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('200°', '0', '12');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('290°', '0', '12');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('110°', '0', '12');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('020°', '1', '12');
    
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('atterrissez et rejoignez le parking', '1', '13');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('interdiction d\'atterrir', '0', '13');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('autorisé à atterrir', '0', '13');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('cédez le passage à un autre aéronef, et restez dans le circuit', '0', '13');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('245', '0', '14');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('259', '0', '14');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('254', '1', '14');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('256', '0', '14');


-- QUESTIONS NAVIGATIONS

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Sur une carte Lambert (par exemple la carte O.A.C.I. au 1 : 500 000), les méridiens sont représentés par :\n', '7', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Le 3 septembre, l\'heure légale à Paris est : UTC + 2 h. Il est 8 h 30. Vous en déduisez que l\'heure UTC est :\n', '7', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('2', 'La route vraie est 117°. La déclinaison magnétique est de 28°W. la route magnétique est :\n', '8', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Le cap vrai (Cv) est l\'orientation de l\'axe longitudinal de l\'avion par rapport :\n', '8', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Les balises VOR fonctionnent dans la gamme de fréquence :\n', '9', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Après centrage de l\'aiguille à l\'aide de l\'OBS, avec indicateur FROM, un VOR indique :\n', '9', '3');


    -- REPONSES
    
    INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('des droites parallèles', '0', '15');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('des cercles', '0', '15');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('des cercles concentriques', '0', '15');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('des droites', '1', '15');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('7h30', '0', '16');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('9h30', '0', '16');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('10h30', '0', '16');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('6h30', '1', '16');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('145°', '1', '17');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('089°', '0', '17');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('028°', '0', '17');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('117°', '0', '17');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('à la route vraie', '0', '18');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('au nord vrai', '1', '18');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('au nord magnétique', '0', '18');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('à la route magnétique', '0', '18');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('VHF', '1', '19');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('MF', '0', '19');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('UHF', '0', '19');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('HF', '0', '19');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('le cap à tenir pour rejoindre la station', '0', '20');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la dérive', '0', '20');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('le radial sur lequel se trouve l\'avion', '1', '20');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la distance entre l\'avion et la station', '0', '20');

-- QUESTIONS PRINCIPES DU VOL

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'La traînée est la composante de la résultante aérodynamique :\n', '11', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'La finesse est maximale lorsque :\n', '11', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Le facteur de charge est le rapport :\n', '12', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Pour maintenir le palier en virage, la portance doit être :\n', '12', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'La position du centre de gravité :\n', '13', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Si le centre de gravité de votre avion se situe en arrière de la limite arrière de centrage, vous courez le risque :\n', '13', '3');


    -- REPONSES
    
    INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('perpendiculaire au vent relatif', '0', '21');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('parallèle au vent relatif', '1', '21');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('parallèle à la portance', '0', '21');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('parallèle à la corde de référence de l\'aile', '0', '21');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la traînée est minimale', '0', '22');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('le rapport portance sur traînée est minimal', '0', '22');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('le rapport portance sur traînée est maximal', '1', '22');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la portance est maximale', '0', '22');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('de la traînée sur le poids', '0', '23');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('de la portance sur le poids', '1', '23');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('du poids sur la portance', '0', '23');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('de la traînée sur la portance', '0', '23');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('égale au poids', '0', '24');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('plus grande qu\'en vol rectiligne', '1', '24');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('la même qu\'en vol rectiligne', '0', '24');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('moins grande qu\'en vol rectiligne', '0', '24');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est une constante aérodynamique', '0', '25');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('est fixe pour un avion donné', '0', '25');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('dépend de l\'incidence', '0', '25');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('dépend de la répartition des charges (passagers, bagages, essence)', '1', '25');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('d\'être incapable de le manoeuvrer autour de l\'axe de tangage', '1', '26');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('de le trouver trop stable', '0', '26');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('de ne pas disposer de la force physique nécessaire pour braquer la profondeur', '0', '26');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('d\'être incapable de le manoeuvrer autour de l\'axe de roulis', '0', '26');

    
-- QUESTIONS COMMUNICATIONS

INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Dans l\'alphabet aéronautique international la lettre \"A\" s\'énonce :\n', '10', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Dans l\'alphabet aéronautique international la lettre \"Z\" s\'énonce :\n', '10', '3');
INSERT INTO `gestion-formation`.`gf_question` (`difficulty`, `contents`, `id_skill`, `id_trainer`) VALUES ('1', 'Dans l\'alphabet aéronautique international la lettre \"U\" s\'énonce :\n', '10', '3');

    -- REPONSES
    
    INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('ambre', '0', '27');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('arsène', '0', '27');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('alfa', '1', '27');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('alice', '0', '27');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('zulu', '1', '28');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('zigzag', '0', '28');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('zodiac', '0', '28');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('zero', '0', '28');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('univers', '0', '29');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('uranus', '0', '29');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('ursule', '0', '29');
	INSERT INTO `gestion-formation`.`gf_proposal` (`denominate`, `state`, `id_question`) VALUES ('uniform', '1', '29');
