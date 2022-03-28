# Documentation projet de gestion de formation "Skill reader"


## Sommaire

1. [Présentation du projet](#présentation-du-projet)
2. [Diagramme de cas d'utilisation](#diagramme-de-cas-dutilisation)
3. [Diagramme de classe](#diagramme-de-classe)
4. [Modèle conceptuel de données](#modèle-conceptuel-de-données)
5. [Evolutivité de la modélisation](#evolutivité-de-la-modélisation)
6. [L'API, les droits d'accès](#lapi-les-droits-daccès)
7. [La génération des questionnaires et de formations adaptées](#la-génération-des-questionnaires-et-de-formations-adaptées)
8. [Structuration front end](#structuration-front-end)
9. [Conclusion](#conclusion)
10. [Démo](#démo)


-----

## Présentation du projet


L’idée de ce projet est de permettre aux élèves pilotes d’approfondir leurs compétences dans certains sous domaines comme la météorologie, la navigation et d’autres. L’application permet d'évaluer les compétences des élèves et de leurs proposer automatiquement un parcours de formation en adéquation avec les compétences acquises et non acquises.

L’application est divisée en deux parties, la gestion de la partie back-end sera réalisée en Java EE développé sous la forme de service REST, la partie front-end est réalisée en ReactJS et communique avec l’API Jakarta. L’API permet de gérer toutes les données relatives à l’application et communique directement avec la base de données.

-----

## Diagramme de cas d'utilisation

![image](./docs/diagrammes/Diagramme%20de%20cas%20d'utilisation.png?raw=true)

-----

## Diagramme de classe

![image](./docs/diagrammes/Diagramme%20de%20classes.png?raw=true)

-----

## Modèle conceptuel de données

![image](./docs/diagrammes/MCD.png?raw=true)

-----

## Evolutivité de la modélisation

Les différents diagrammes font l'œuvre d’une modélisation non exhaustive. Étant un projet en version alpha, celui-ci sera amené à évoluer.

Premièrement, concernant la partie graphe, il peut-être intéressant de gérer la modélisation du côté des compétences uniquement, en revoyant la gestion des versions d’un graphe également.

Concernant la partie questionnaire, le projet actuel génère un unique questionnaire pour proposer une formation, proposer des questionnaires en cours et en fin de formations pourrait permettre d’évaluer les compétences de l’élève pilote au cours de son apprentissage mais aussi de pouvoir le proposer de nouvelles formations il faiblit sur une ou plusieurs de ses compétences. L’idée de proposer d’autre forme de question pourrait voir le jour, pour le moment chaque question possède quatre propositions de réponses avec une seule bonne réponse, nous pouvons très simplement rajouter la possibilité d’avoir plusieurs bonnes réponses, des questions avec des annexes ou même proposer des questions interactives.

Les formations ne sont pas encore implémentées, une fois le questionnaire d'entrée fini, l’utilisateur doit se voir proposer une formation. Cette finition sera apportée au projet par la suite.

-----

## L'API, les droits d'accès

<table>
  <tr>
   <td><strong>Type</strong>
   </td>
   <td><strong>URL</strong>
   </td>
   <td><strong>Description</strong>
   </td>
   <td><strong>Accès</strong>
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/user/read/{count}/{startAt}
   </td>
   <td>Retourne un nombre d’utilisateur à partir de l’index {startAt}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/user/read/{idUser}
   </td>
   <td>Retourne l’utilisateur correspondant à {idUser}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/user/login
   </td>
   <td>Permets la connexion d’un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/user/register
   </td>
   <td>Permets l’inscription d’un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/user/update
   </td>
   <td>Permets la modification d’un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>DELETE
   </td>
   <td>/skill/remove/{idSkill}/idUser
   </td>
   <td>Supprime une compétence correspondant à {idSkill} si l’utilisateur en possède les droits
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/skill/read/{idSkill}
   </td>
   <td>Retourne la compétence correspondante à {idSkill}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/skill/update
   </td>
   <td>Permets la modification d’une compétence
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/skill/add
   </td>
   <td>Permets l’ajout d’une compétence
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/skill/read/all
   </td>
   <td>Retourne toutes les compétences
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>DELETE
   </td>
   <td>/question/remove/{idQuestion}/{idUser}
   </td>
   <td>Permets la suppression d’une question correspondant à {idQuestion} si l’utilisateur en possède les droits
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/question/read/{count}/{startAt}
   </td>
   <td>Retourne un nombre de questions à partir de l’index {startAt}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/question/read/{idQuestion}
   </td>
   <td>Retourne la question correspondant à {idQuestion}
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/question/add
   </td>
   <td>Permets l’ajout d’une question et de ses propositions
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/question/update
   </td>
   <td>Permets la modification d’une question
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/proposal/read/{idQuestion}
   </td>
   <td>Retourne les propositions de la question correspondant à {idQuestion}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/subdomain/add
   </td>
   <td>Permets l’ajout d’un sous-domaine
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>DELETE
   </td>
   <td>/subdomain/remove/{idSubdomain}/{idUser}
   </td>
   <td>Permets la suppression des compétences liée à un sous-domaine
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/subdomain/update
   </td>
   <td>Permets la modification d’un sous-domaine
   </td>
   <td>Formateur
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/subdomain/read/{count}/{startAt}
   </td>
   <td>Retourne un nombre de sous-domaine à partir de l’index {startAt}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/subdomain/read/{idSubdomain}
   </td>
   <td>Retourne un sous-domaine correspondant à {idSubdomain}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/graph/global
   </td>
   <td>Retourne les noeuds du graphe de compétences et ses arcs
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>DELETE
   </td>
   <td>/user/{idUser}/remove/all
   </td>
   <td>Retire toutes les compétences choisis par un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/user/{idUser}/add/all
   </td>
   <td>Ajoute toutes les compétences à un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/user/{idUser}/skill/addBySubdomainId/{idSubdomain}
   </td>
   <td>Ajoute toutes les compétences d’un sous-domaine correspondant à {idSubdomain} à un utilisateur
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>DELETE
   </td>
   <td>/user/{idUser}/skill/remove/{idSkill}
   </td>
   <td>Retire la compétence correspondant à {idSkill} de l’utilisateur {idUser}
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>PUT
   </td>
   <td>/user/{idUser}/skill/add/{idSkill}
   </td>
   <td>Ajoute la compétence correspondant à {idSkill} à l’utilisateur {idUser}
   </td>
   <td>Tout le monde
   </td>
  </tr>
  <tr>
   <td>POST
   </td>
   <td>/questionnary/response/{idUser}/qId/{idQuestion}/rId/{idProposal}
   </td>
   <td>Enregistre la question comme étant répondu par l’utilisateur
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
  <tr>
   <td>GET
   </td>
   <td>/questionnary/getQuestion/{idUser}/{difficulty}
   </td>
   <td>Retourne une question de difficulté {difficulty} en fonction des compétences choisis de l’utilisateur {idUser}
   </td>
   <td>Utilisable uniquement par l’application
   </td>
  </tr>
</table>

-----

## La génération des questionnaires et de formations adaptées

Le but du questionnaire est de pouvoir attribuer à chaque compétence que l’utilisateur aura au préalable sélectionné un score positif ou nul. Pour cela, la génération des questionnaires est entièrement contrôlée par l’API. En effet, celle-ci génère des questions à poser à l’utilisateur en se basant sur les compétences choisies. En appelant la requête permettant d’acquérir une question, l’API retourne une question au hasard appartenant à une ou des compétence(s) de l’utilisateur. De plus, elle ne se limite pas à un choix hasardeux. En effet, elle ne compte que le/les compétence(s) dont le malus est strictement inférieur à 10 et dont l’erreur successive est strictement inférieure à 4 (Le malus étant un entier naturel incrémenté lorsqu’une réponse à une question est fausse. L’erreur successive quant à elle est un entier naturel représentant le nombre de fois où l’utilisateur se trompe à répétition à une compétence). 

Si la réponse à une question est fausse, le questionnaire octroie un malus égal à 4 soustrait par la difficulté de la question posée (4 représentant la difficulté maximale des questions pouvant être posées). Si la réponse est fausse, le questionnaire va incrémenter l’erreur successive correspondant à la compétence examinée de 1. 

Pour chaque bonne réponse à une question d’une compétence de l’utilisateur, la difficulté des questions posées est incrémentée de 0.5 et le score est incrémenté d’un montant égal à la difficulté de la question.

La limite d’un questionnaire est donc fixée sur les erreurs commises par l’utilisateur. Moins il fera d’erreur, plus le questionnaire durera longtemps et deviendra difficile. A contrario, plus il fait d'erreurs, plus le questionnaire sera court. Ainsi, les formations proposées à l’utilisateur à la suite du questionnaire seront proposées dans l’ordre ascendant des résultats de ses compétences.

![image](./docs/diagrammes/Diagramme%20algorithme%20questionnaire.jpg?raw=true)

-----

## Structuration front end

Image structure front end

Les composants constituent les éléments réutilisables de l’application contenant leurs propres logiques, ils sont indépendant l’un de l’autre, sauf pour les sous-composants - les composants utilisables dans un composant - comme le composant “Form” qui est composé de composant “InputFloating”

Les vues constituent les différentes pages de l’application et sont agrégées de multiples composants, les pages incorpore le contexte AuthContext pour identifier l’utilisateur.

Les contextes constituent les états globaux de l’application notamment l’authentification permettant d’identifier l’utilisateur connecté. Les contextes sont utilisés par les Vues.

Les routes accessibles sont décrites dans “App.js”.

![image](./docs/diagrammes/Diagramme%20de%20cas%20d'utilisation.png?raw=true)

-----

## Conclusion

Ce projet nous à permis de travailler en équipe et d’utiliser une méthode adaptée pour le travail d’équipe, l’utilisation de la méthode agile pour la gestion du projet et de git pour le versionning du projet par exemple.

-----

## Démo

https://www.youtube.com/watch?v=Nk5f8VwTSgo
