   <!DOCTYPE html>
<html>
  <head>
    <g:layoutHead/>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="images/favicon.ico">

    <link href="${resource(dir: 'stylesheet', file: 'bootstrap-select.css')}" rel="stylesheet">
    <link href="${resource(dir: 'stylesheet', file: 'bootstrap.css')}" rel="stylesheet">
    <link href="${resource(dir: 'stylesheet', file: 'bootstrap-theme.css')}" rel="stylesheet">
    <link href="${resource(dir: 'stylesheet', file: 'style.css')}" rel="stylesheet">
    <!-- link href="${resource(dir: 'stylesheet', file: 'main.css')}" rel="stylesheet" -->
    <link href="${resource(dir: 'stylesheet', file: 'theme.css')}" rel="stylesheet">
    <link href="${resource(dir: 'javascripts', file: 'jquery-ui.min.css')}" rel="stylesheet">

    <script src="${resource(dir: 'javascripts', file: 'ie-emulation-modes-warning.js')}"></script>
    
    <script src="${resource(dir: 'javascripts', file: 'jquery.js')}"></script>
    <script src="${resource(dir: 'javascripts', file: 'jquery-ui.js')}"></script>

    <script src="${resource(dir: 'javascripts', file: 'bootstrap.min.js')}"></script>
    <script src="${resource(dir: 'javascripts', file: 'docs.min.js')}"></script>
    <script src="${resource(dir: 'javascripts', file: 'bootstrap-select.js')}"></script>
    
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${resource(dir: 'js/boostrap', file: 'ie10-viewport-bug-workaround.js')}"></script>


    <style>
      #header {background-color:#ffe0e0;text-align: center;}
      #footer {background-color:#e0e0ff;text-align: center;}
    </style>
    <title><g:layoutTitle default="scm"/></title>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">scm</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="/producto">Productos</a></li>
                <li><a href="/proyectos">Proyectos</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <!--li><a href="#">Separated link</a></li-->
              </ul>
            </li>
            <g:if test="${session["user"] && !session["user"].isEmpty()}">
              <li><a href="/scm/login/logout">Hola ${session["user"]}, Logout</a></li>
            </g:if>
            <g:else>
               <li><a href="/scm/login/index">Login</a></li>
            </g:else>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </head>

<body>
    
    <g:layoutBody/>

</body>
</html>