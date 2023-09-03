<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html >
   
    <center>
        
        <head>
            <jsp:include page="/Views/Shared/title.jsp" />
            <title>Login</title>
            <link href="wwwroot/css/diseñocss.css" rel="stylesheet" type="text/css"/>
        </head>
        
        <body>
            <jsp:include page="/Views/Shared/headerBody.jsp" />
             <body ng-app="mainModule" ng-controller="mainController">
               <div class="container">
                <div class="row">

                    <div class="col s6 offset-s3 #e0f2f1 teal lighten-5" id="panell">
                      <h5 id="title">Inicio Sesion</h5>
                      
                      <form action="Usuario?accion=login" method="post">
                          <input type="hidden" name="accion" 
                           value="<%=request.getAttribute("accion")%>" id="txtHidden">

                          <div class="input-field" style="width: 150px; height: 150px;" id="username">
                      <i class="material-icons prefix">account_circle</i>
                        <input  type="text" id="txtLogin" name ="login" required
                                   class="validate" maxlength="30">
                            <label for="txtLogin">Correo</label>
                    </div>
                    <div class="input-field" style="width: 150px; height: 150px; id"password">
                    <i class="material-icons prefix">enhanced_encryption</i> 
                      <input type="password" id="txtPassword" name ="password" required 
                                   class="validate" minlength="5">
                            <label for="txtPassword">Contraseña</label>
                  </div>

                 <div class="row">
                        <div class="col 112 s12">
                            <button type="submit" class="waves-effect waves-light btn btn">
                                <i class="material-icons right">save</i>Login
                            </button>
                        </div>
                    </div>
                    <%
                        if(request.getAttribute("error") != null)
                        {
                    %>
                    <div class="row">
                        <div class="col 112 s12">
                            <span style="color:red;font-weight: bold;">
                               <%=request.getAttribute("error") %> 
                            </span>
                        </div>
                    </div>
                    <% } %>

                  </form>

                  </div>
                </div>

         </div>
     </body>
            
            <jsp:include page="/Views/Shared/footerBody.jsp" />
        </body>
    </center>
</html>