<%-- 
    Document   : login
    Created on : 16 févr. 2022, 14:32:28
    Author     : valen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signin.css"/>

        <!-- Vue.JS -->
        <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>

        <style>

        </style>
        <title>Skill Reader </title>
    </head>
    <body class="text-center">
        <div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
            <c:if test="${param.error}">
                <div class="alert alert-danger" role="alert">
                    Identifiant ou mot de passe incorrect.
                </div>
            </c:if>
            <c:if test="${param.success}">
                <div class="alert alert-success" role="alert">
                    Votre compte a bien été créee. Vous pouvez dès à présent vous connecter.
                </div>
            </c:if>




            <main class="form-signin">
                <form action="login" method="POST">
                    <img class="mb-4" width="72" height="72" src="${pageContext.request.contextPath}/img/hexagone.png" />
                    <h1 class="h3 mb-3 fw-normal">Connexion</h1>

                    <div class="form-floating" style="margin-top: 5px;">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Nom" />
                        <label for="name">Nom : </label>
                    </div>
                    <div class="form-floating" style="margin-top: 5px;">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" />
                        <label for="password">Mot de passe : </label>
                    </div>

                    <div class="checkbox mb-3" style="margin-top: 5px;">
                        <label>
                            <input type="checkbox" value="remember-me" /> Se souvenir de moi
                        </label>
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" style="margin-top: 15px;" type="submit">Connexion</button>
                    <button class="w-100 btn btn-lg btn-primary" style="margin-top: 5px;" onclick="location.href = '/register.xhtml'">S'enregistrer</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2021–2022</p>
                </form>
            </main>
        </div>

        <script>
            var app = new Vue({
                el: '#app',
                data: {
                    message: null,
                    display: "block"
                }
            });

            var app2 = new Vue({
                el: '#app2',
                data: {
                    message: null,
                    display: "block"
                }
            });

            var test = new URL(location.href).searchParams.get('error');
            var test2 = new URL(location.href).searchParams.get('error');

            switch (test) {
                case '1':
                    app.message = "Votre compte a bien été créee. Vous pouvez dès à présent vous connecter.";
                    break;
                default:
                    app.display = "none";
            }

            switch (test2) {
                case 'true':
                    app2.message = "Identifiant ou mot de passe incorrect.";
                    break;
                default:
                    app2.display = "none";
            }
        </script>
    </body>
</html>
