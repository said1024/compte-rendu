Application de Gestion d'Utilisateurs

Cette application Spring Boot permet la gestion des utilisateurs avec authentification JWT.
Elle utilise une base de données H2 en mémoire et propose des fonctionnalités de création de compte,
connexion et recherche d'utilisateurs.

Prérequis :
Java 17 ou supérieur
Maven
Spring boot 3.3.5
Un IDE (IntelliJ IDEA, VS Code...)

Configuration :
L'application utilise une base de données H2 en mémoire
La Console H2 est accessible via l'URL :
URL : http://localhost:8080/h2-console

Installation :
-Décompressez le fichier fourni
-Ouvrez le projet dans votre IDE
-Installez les dépendances Maven
-Lancez l'application

Documentation API (Swagger) :
Une documentation interactive de l'API est disponible via Swagger UI :
URL : http://localhost:8080/swagger-ui/index.html
Cette interface vous permet de :
-Visualiser tous les endpoints disponibles
-Tester les endpoints directement depuis le navigateur
-Tester l'authentification JWT

Points d'accès (Endpoints) :
1. Création d'un compte
URL : POST /user/register
Corps de la requête :
{
    "firstName": "fname",
    "lastName": "lname",
    "email": "mail@example.com",
    "password": "password"
}

2. Connexion
URL : POST /user/login
Corps de la requête :

{
    "email": "mail@example.com",
    "password": "password"
}

3. Recherche d'un utilisateur
URL : GET /user/get-user?email=mail@example.comm
Header requis : Authorization: Bearer Token

