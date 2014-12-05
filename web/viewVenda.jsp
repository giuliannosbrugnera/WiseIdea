<%@page import="java.util.ArrayList"%>
<%@page import="model.Produto"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Wise Idea | Vender produtos</title>

        <!-- Bootstrap core CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/offcanvas.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link rel="stylesheet" type="text/css" href="js/datatables/media/css/jquery.dataTables.css">

        <link rel="stylesheet" type="text/css" href="css/styles.css">

        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/ie10-viewport-bug-workaround.js"></script>
        <script src="js/offcanvas.js"></script>
        <script src="js/script.js"></script>
        <script src="js/jquery.maskedinput.min.js"></script>
        <script src="js/ie-emulation-modes-warning.js"></script>

        <!-- DataTables -->
        <script type="text/javascript" charset="utf8" src="js/datatables/media/js/jquery.dataTables.js"></script>
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
                        <li><a href="index.html">In�cio</a></li>
                        <li><a href="http://localhost:8084/WiseIdea/BuscaLote?acao=listar">Lotes</a></li>
                        <li><a href="index.html#sec2">Produtos</a></li>
                        <li class="active"><a href="index.html#sec3">Vender produtos</a></li>
                        <li><a href="index.html#sec4">Clientes</a></li>
                        <li id="section5"><a href="#">Configurar data limite</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </div><!-- /.navbar -->

        <div class="container">
            <div class="row row-offcanvas row-offcanvas-right">
                <div class="col-xs-12 col-sm-9">

                    <ol class="breadcrumb">
                        <li><a href="index.html">In�cio</a></li>
                        <li><a href="index.html#sec3">Vender produtos</a></li>
                        <li class="active">Resultado vender produto</li>
                    </ol>

                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Abrir navega��o</button>
                    </p>

                    <div id="menu" class="panel panel-default" style="margin-bottom: 70px;">
                        <div class="panel-heading">
                            <h4>Produtos</h4>
                        </div>
                        <div class="panel-body">
                            <%
                                ArrayList<Produto> produtos = new ArrayList<Produto>();
                                produtos = (ArrayList<Produto>) request.getAttribute("listProduto");

                                if ( produtos.isEmpty() ) {
                            %>
                            <h4> Resultados n�o encontrados. Tente novamente.</h4>
                            <% }
                            else { %> 
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover dataTable" id="table_produtos">
                                    <thead>
                                        <tr>
                                            <th>C�digo produto</th>
                                            <th>Nome do produto</th>
                                            <th>Valor</th>
                                            <th>Categoria</th>
                                            <th>Vender</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for ( Produto produto : produtos ) {%>
                                        <tr class="gradeA">
                                            <td><%=produto.getId().toString()%></td>
                                            <td><%=produto.getNome()%></td>
                                            <td>R$ <%=produto.getValor()%>0</td>
                                            <td><%=produto.getCategoria()%></td>
                                            <td>
                                                <a href="http://localhost:8084/WiseIdea/VendaProduto?productID=<%=produto.getId().toString()%>&acao=buscar&url=<%=(String) request.getAttribute("url")%>"
                                                   <button type="button" class="btn btn-success">
                                                        <span class="glyphicon glyphicon-ok"></span> Vender
                                                    </button>
                                                </a>
                                            </td>
                                        </tr>
                                        <%} %>
                                    </tbody>
                                </table>
                            </div>
                            <%  }%>
                        </div>
                    </div>
                </div>

                <!-- Modal configura��o do limite de dias -->
                <div class="modal fade" id="modalconfiguracao" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Configurar data limite</h4>
                            </div>
                            <div class="modal-body" id="info">
                                <h4>Neste espa�o voc� pode alterar o limite de dias para vender os produtos do estoque</h4>
                                <p>Atual limite de dias para vender um produto: <code id="limitedias"></code></p>
                                <p>Apenas <strong>n�meros</strong> s�o permitidos.</p>
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

                <!-- Modal atualiza��o do limite de dias com sucesso -->
                <div class="modal fade" id="modalsucesso" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">
                                    <span style="color: #3c763d;">
                                        <span class="glyphicon glyphicon-ok-circle"></span> Altera��o efetuada com sucesso
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
                            <span class="glyphicon glyphicon-home"></span> In�cio
                        </a>
                        <a href="http://localhost:8084/WiseIdea/BuscaLote?acao=listar" id="lotes" class="list-group-item">
                            <span class="glyphicon glyphicon-list"></span> Listar todos os lotes
                        </a>
                        <a href="index.html#sec2" id="buscarprodutos" class="list-group-item">
                            <span class="glyphicon glyphicon-search"></span> Pesquisar produtos
                        </a>
                        <a href="index.html#sec3" id="venderprodutos" class="list-group-item active">
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
