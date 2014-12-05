<%@page import="model.Cliente"%>
<%@page import="model.Produto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Lote"%>

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
                        <li><a href="index.html">Início</a></li>
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
                        <li><a href="index.html">Início</a></li>
                        <li><a href="index.html#sec3">Vender produtos</a></li>
                        <li><a href="<%=(String) request.getAttribute("url")%>">Resultado produto venda</a></li>
                        <li class="active">Venda</li>
                    </ol>

                    <p class="pull-right visible-xs">
                        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Abrir navegação</button>
                    </p>

                    <%
                        Produto produto = (Produto) request.getAttribute("produto");
                        ArrayList<Lote> lotes = new ArrayList<Lote>();
                        lotes = (ArrayList<Lote>) request.getAttribute("listLote");
                        boolean thereIsAGoodLot = false;
                        boolean allLotAreBad = true;
                        Integer qtdeLotesBons = 0;
                        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
                        clientes = (ArrayList<Cliente>) request.getAttribute("listCliente");

                        if ( lotes != null ) {
                            for ( Lote lote : lotes ) {
                                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                //get current date time with Date()
                                Date todayDate = new Date();
                                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date newerDate = null;
                                newerDate = formatter.parse(lote.getValidade());
                                int tempoRestante = (int) ((newerDate.getTime() - todayDate.getTime()) / (1000 * 60 * 60 * 24));

                                if ( tempoRestante > 0 ) {
                                    if ( tempoRestante >= produto.getLimiteValidade() ) {
                                        thereIsAGoodLot = true;
                                        qtdeLotesBons += lote.getQtdeProd();
                                    }
                                    else {
                                        allLotAreBad = false;
                                    }
                                }
                            }
                        }

                        if ( !thereIsAGoodLot ) {%>
                    <div class="jumbotron" id="main">
                        <h1 style="color: red;">Atenção</h1>
                        <h3>Todos os lotes do produto <strong><%=produto.getNome()%></strong> estão
                            <%if ( allLotAreBad ) {%> vencidos!</h3> <p>Escolha um outro produto para vender</p>
                            <%}
                            else {%>
                        fora do limite de dias para venda.
                        </h3>
                        <p>
                            Caso ainda queira vender esse produto, <strong>diminua</strong>
                            o limite de dias clicando na aba <strong>Configuração</strong>
                            e tente novamente.
                        </p>
                        <%}%>
                    </div>
                    <%}
                    else {%>

                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h2 id="sec0">Vender produto</h2>
                            Preencha todos os campos para realizar a venda de um produto.<br>
                            Campos com o símbolo <span class="glyphicon glyphicon-asterisk" style="color: red;"></span> são <strong>
                                obrigatórios</strong>.<br>
                            Escolha os lotes manualmente clicando em <kbd>Manual</kbd> ou deixe o sistema escolher clicando em <kbd>Automático</kbd><br>
                            Preencha o <kbd>CPF</kbd> ou <kbd>CNPJ</kbd> do cliente para que suas informações sejam apresentadas.
                        </div>
                    </div>

                    <!-- Está comentado para que a div com as informações do produto se aproxime mais da barra de navegação -->
                    <!-- <div class="col-md-9"> -->
                    <div id="menu" class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title"><b>Informações do produto</b></h3></div>
                        <div class="panel-body">
                            <%if ( produto != null ) {%>
                            <dl class="dl-horizontal">
                                <dt>Código produto</dt><dd><%=produto.getId()%></dd><br>
                                <dt>Nome</dt><dd><%= produto.getNome()%></dd><br>
                                <dt>Categoria</dt><dd><%=produto.getCategoria()%></dd><br>
                                <dt>Valor</dt><dd>R$ <%=produto.getValor()%>0</dd><br>
                                <dt>Quantidade disponível</dt><dd id="disponiveis"><%=qtdeLotesBons%></dd><br>
                                <dt>Quantidade venda <span class="glyphicon glyphicon-asterisk" style="color: red;"></span></dt>
                                <dd>
                                    <div class="form-group" id="formqtdeprod">
                                        <input class="form-control" id="qtdeprod" name="numero">
                                    </div>
                                    <div class="panel panel-danger" id="erroqtdeprod">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Erro</h3>
                                        </div>
                                        <div class="panel-body">
                                            A quantidade do produto deve ser composta apenas por números.
                                        </div>
                                    </div>
                                    <div class="panel panel-danger" id="erroqtdemaior">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Erro</h3>
                                        </div>
                                        <div class="panel-body" id="erroqtdeprodvenda">
                                        </div>
                                    </div>
                                </dd><br>
                                <dt>Escolha dos lotes <span class="glyphicon glyphicon-asterisk" style="color: red;"></span></dt>
                                <dd id="formescolha">
                                    <label class="radio-inline">
                                        <input type="radio" name="escolha" id="automatico" value="automatico" checked>Automático
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="escolha" id="manualmente" value="manual">Manual
                                    </label>
                                </dd>
                            </dl>
                        </div> <!-- Fim do body das informações do produto  -->

                        <!-- Escolha dos lotes provenientes -->
                        <div id="escolhalote">
                            <div class="panel-heading needStyle" id="loteheading"><h3 class="panel-title"><b>Lotes</b></h3></div>
                            <div class="panel-body">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Código lote</th>
                                                <th>Quantidade</th>
                                                <th>Validade</th>
                                                <th>Data Entrada</th>
                                                <th>Data Fabricação</th>
                                                <th>Tempo Restante</th>
                                                <th>Escolher</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for ( Lote lote : lotes ) {
                                                    String mensagem;
                                                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                                    //get current date time with Date()
                                                    Date todayDate = new Date();
                                                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                                    Date newerDate = null;
                                                    newerDate = formatter.parse(lote.getValidade());
                                                    int tempoRestante = (int) ((newerDate.getTime() - todayDate.getTime()) / (1000 * 60 * 60 * 24));
                                                    String string = "danger", disabled = "disabled";

                                                    if ( tempoRestante > 0 ) {
                                                        if ( tempoRestante >= produto.getLimiteValidade() ) {
                                                            mensagem = tempoRestante + " dias";
                                                            string = "success";
                                                            disabled = "";
                                                        }
                                                        else {
                                                            mensagem = "Fora do limite de dias";
                                                        }
                                                    }
                                                    else {
                                                        mensagem = "Vencido";
                                                    }
                                            %>
                                            <tr class="gradeA">
                                                <td><%=lote.getId().toString()%></td>
                                                <td id="td<%=lote.getId().toString()%>"><%=lote.getQtdeProd().toString()%></td>
                                                <td><%=lote.getValidade()%></td>
                                                <td><%=lote.getDataEntrada()%></td>
                                                <td><%=lote.getDataFabricacao()%></td>
                                                <td class="<%=string%>"><%=mensagem%></td>
                                                <td style="text-align: center;">
                                                    <input type="checkbox" name="checkBoxGroup" value="<%=lote.getId().toString()%>" <%=disabled%>>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>
                            </div> <!-- Fim do body da escolha manual dos lotes -->
                        </div>

                        <!-- Dados do cliente -->
                        <div class="panel-heading needStyle"><h3 class="panel-title"><b>Cliente</b></h3></div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">

                                <!-- Escolha do cliente pelo nome -->
                                <dl class="dl-horizontal">
                                    <dt>Escolher cliente <span class="glyphicon glyphicon-asterisk" style="color: red;"></span></dt>
                                    <dd>
                                        <select class="form-control" id="escolhacliente" name="categoria" style="width: 280px;">
                                            <option value="">-- Selecione um cliente pelo nome --</option>
                                            <% for ( Cliente cliente : clientes ) {%>
                                            <option value="<%=cliente.getId().toString()%>"><%=cliente.getNome()%></option>
                                            <%}%>
                                        </select>
                                    </dd>
                                </dl>

                                <!-- Informações do cliente -->
                                <dl class="dl-horizontal" id="infoscliente">
                                    <dt>Nome<dt><dd id="nomeclienteajax"></dd><br>
                                    <dt>CPF ou CNPJ<dt><dd id="docclienteajax"></dd><br>
                                    <dt>Endereço</dt><dd id="enderecoclienteajax"></dd><br>
                                    <dt>Telefone</dt><dd id="telefoneclienteajax"></dd><br>
                                </dl>
                            </form>
                        </div> <!-- Fim do body das informações do cliente -->

                        <div class="panel-footer clearfix">
                            <div class="pull-right">
                                <a class="btn btn-danger" id="cancelar" href="index.html">
                                    <span class="glyphicon glyphicon-remove"></span> Cancelar
                                </a>
                                <!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalescolhaclientes">
                                    Adicionar cliente
                                </button> -->
                                <button class="btn btn-success" id="vender">
                                    <span class="glyphicon glyphicon-ok"></span> Vender
                                </button>
                                <input type="hidden" value="<%=produto.getId()%>" id="productID">
                                <input type="hidden" value="vender" id="acao">
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <%}%>
                </div><!--/span-->

                <!-- Modal venda -->
                <div class="modal fade" id="modalvenda" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" id="closeicon">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    <span style="color: #3c763d;">
                                        <span class="glyphicon glyphicon-ok-circle"></span> Venda efetuada com sucesso
                                    </span>
                                </h4>
                            </div>
                            <!-- <div class="modal-body" id="info"></div> -->
                            <div class="modal-body">
                                <p>Clique em <kbd>Visualizar relatório</kbd> caso queira conferir todos os dados da venda.</p>
                            </div>
                            <div class="modal-footer">
                                <form class="form-inline" role="form" method="post" action="viewRelatorioVenda.jsp">
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="fecharvenda">Fechar</button>
                                    <input type="hidden" id="reportInfo" name="report" value="">
                                    <button type="submit" class="btn btn-success" id="produtobuscar">
                                        <span class="glyphicon glyphicon-eye-open"></span> Visualizar relatório
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

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
                                <button type="button" class="close" data-dismiss="modal" id="closeicon">
                                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                </button>
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

                <!-- Modal lotes não escolhidos -->
                <div class="modal fade" id="modalescolha" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Atenção</h4>
                            </div>
                            <div class="modal-body" id="info">
                                <h4>Nenhum lote foi selecionado</h4>
                                <p>Você fez a escolha de selecionar <strong>manualmente</strong> os lotes, porém <strong>nenhum</strong> lote
                                    foi selecionado.</p>
                                <p>Escolha um lote para finalizar corretamente a venda.</p>
                                <p>Caso queira que o sistema escolha os lotes por você, marque a opção <kbd>Automático</kbd></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal quantidade dos lotes menor que a quantidade escolhida para venda -->
                <div class="modal fade" id="modalqtdemenor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Atenção</h4>
                            </div>
                            <div class="modal-body" id="info">
                                <h4>Quantidade dos lotes escolhidos é insuficiente</h4>
                                <p>Você escolheu manualmente um conjunto de lotes que somados possuem uma quantidade menor do que a quantidade desejada para venda.</p>
                                <p>Adicione mais lotes manualmente ou diminua a quantidade para venda.</p>
                                <p>Caso queira que o sistema escolha os lotes por você, marque a opção <kbd>Automático</kbd></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal fade bs-example-modal-sm" id="lotesdemais" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Atenção</h4>
                            </div>
                            <div class="modal-body" id="info">
                                <h4>Você escolheu lotes demais</h4>
                                <p>Você pode vender essa quantidade de produtos usando menos lotes. Desmarque alguns.</p>
                            </div>
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
