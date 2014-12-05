<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Wise Idea | Erro</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/offcanvas.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="css/styles.css">

        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/ie10-viewport-bug-workaround.js"></script>
        <script src="js/offcanvas.js"></script>
        <script src="js/script.js"></script>
        <script src="js/jquery.maskedinput.min.js"></script>
        <script src="js/ie-emulation-modes-warning.js"></script>
    </head>

    <body>
        <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">Wise Idea</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li id="first"><a href="index.html">Início</a></li>
                        <li id="section1"><a href="http://localhost:8084/WiseIdea/BuscaLote?acao=listar">Lotes</a></li>
                        <li id="section2"><a href="#sec2">Produtos</a></li>
                        <li id="section3"><a href="#sec3">Vender produtos</a></li>
                        <li id="section4"><a href="#sec4">Clientes</a></li>
                        <li id="section5"><a href="#">Configurar data limite</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div><!-- /.navbar -->

        <div class="container">
            <div class="row row-offcanvas row-offcanvas-right">
                <div class="col-xs-12 col-sm-9">

                    <ol class="breadcrumb">
                        <li><a href="index.html">Início</a></li>
                        <li class="active">Erro</li>
                    </ol>

                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Abrir navegação</button>
                    </p>
                    <div class="jumbotron" id="main">
                        <h1 style="color: red;">Ocorreu algum erro interno!</h1>
                        <h3>Tente novamente.</h3>
                    </div>

                </div><!--/span-->

                <!-- Modal configuração do limite de dias -->
                <div class="modal fade" id="modalconfiguracao" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Configurar data limite</h4>
                            </div>
                            <div class="modal-body" id="info">
                                <h4>Neste espaço você pode alterar o limite de dias para vender os produtos do estoque</h4>
                                <p>Atual limite de dias para vender um produto: <code id="limitedias"></code></p>
                                <p>Apenas <strong>números</strong> são permitidos.</p>
                                <form class="form-horizontal" role="form" id="flimite">
                                    <div class="form-group" id="formnovolimite">
                                        <label for="novolimite" class="col-sm-3 control-label">Novo limite</label>
                                        <div class="col-sm-5">
                                            <input class="form-control" min="0" max="999" id="novolimite" name="novolimite">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">
                                    <span class="glyphicon glyphicon-remove"></span> Cancelar
                                </button>
                                <button type="button" class="btn btn-success" id="salvarconfig">
                                    <span class="glyphicon glyphicon-floppy-disk"></span> Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal atualização do limite de dias com sucesso -->
                <div class="modal fade" id="modalsucesso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">
                                    <span style="color: #3c763d;">
                                        <span class="glyphicon glyphicon-ok-circle"></span> Alteração efetuada com sucesso
                                    </span>
                                </h4>
                            </div>
                            <div class="modal-body" id="infolimite"></div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
                    <div class="list-group">
                        <a href="index.html" id="home" class="list-group-item">
                            <span class="glyphicon glyphicon-home"></span> Início
                        </a>
                        <a href="http://localhost:8084/WiseIdea/BuscaLote?acao=listar" id="lotes" class="list-group-item">
                            <span class="glyphicon glyphicon-list"></span> Listar todos os lotes
                        </a>
                        <a href="index.html#sec2" id="buscarprodutos" class="list-group-item">
                            <span class="glyphicon glyphicon-search"></span> Pesquisar produtos
                        </a>
                        <a href="index.html#sec3" id="venderprodutos" class="list-group-item">
                            <span class="glyphicon glyphicon-usd"></span> Vender produtos</a>
                        <a href="index.html#sec4" id="buscarclientes" class="list-group-item">
                            <span class="glyphicon glyphicon-search"></span> Pesquisar clientes
                        </a>
                        <a href="#" id="configurardata" class="list-group-item">
                            <span class="glyphicon glyphicon-cog"></span> Configurar data limite
                        </a>
                    </div>
                </div><!--/span-->
            </div><!--/row-->

            <hr>

            <footer>
                <p>Projetos e Desenvolvimento de Sistemas &mdash; Interface Humano-Computador</p>
            </footer>

        </div><!--/.container-->

    </body>
</html>
