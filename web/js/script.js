$(document).ready(function () {

    $('#produtoform').hide();
    $('#vendaform').hide();
    $('#clienteform').hide();
    $('#erronomeproduto').hide();
    $('#erronumerolote').hide();
    $('#erronomevenda').hide();
    $('#erronomecliente').hide();
    $('#erroqtdeprod').hide();
    $('#erroqtdemaior').hide();
    $('#vender').prop("disabled", true);
    $('#escolhalote').hide();
    $('#infoscliente').hide();
    $('#errocpf').hide();
    $('#errocnpj').hide();
    $('#formcnpj').hide();
    $('#salvarconfig').prop("disabled", true);
    $('#cpf').mask("999.999.999-99");
    $('#cnpj').mask("99.999.999/9999-99");

    if ( location.hash === '#sec1' ) {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#lotes').addClass("active");
        $('#home').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#section1').addClass("active");
        $('#first').removeClass("active");
        $('#section2').removeClass("active");
        $('#section3').removeClass("active");
        $('#produtoform').hide();
        $('#fproduto')[0].reset();
        $('#formnomeproduto').removeClass('has-error has-success');
        $('#formnumerolote').removeClass('has-error has-success');
        $('#main').hide();
        $('#vendaform').hide();
    }

    if ( location.hash === '#sec2' ) {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#buscarprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section2').addClass("active");
        $('#section1').removeClass("active");
        $('#section3').removeClass("active");
        $('#first').removeClass("active");
        $('#main').hide();
        $('#vendaform').hide();
        $('#produtoform').fadeIn();
        $('#currentlocation').html("Pesquisar produtos");
    }

    if ( location.hash === '#sec3' ) {
        $('#venderprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#buscarclientes').removeClass("active");
        $('#section3').addClass("active");
        $('#home').removeClass("active");
        $('#section4').removeClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#clienteform').hide();
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').fadeIn();
        $('#currentlocation').html("Vender produtos");
    }

    if ( location.hash === '#sec4' ) {
        $('#buscarclientes').addClass("active");
        $('#venderprodutos').removeClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section4').addClass("active");
        $('#section3').removeClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#clienteform').fadeIn();
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').hide();
        $('#currentlocation').html("Pesquisar clientes");
    }

    $('#home').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#home').addClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#first').addClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#section3').removeClass("active");
        $('#produtoform').hide();
        $('#fproduto')[0].reset();
        $('#formnomeproduto').removeClass('has-error has-success');
        $('#formnumerolote').removeClass('has-error has-success');
        $('#vendaform').hide();
        $('#main').fadeIn();
        $('#currentlocation').html("");
    });

    $('#lotes').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#lotes').addClass("active");
        $('#home').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#section1').addClass("active");
        $('#first').removeClass("active");
        $('#section2').removeClass("active");
        $('#section3').removeClass("active");
        $('#fproduto')[0].reset();
        $('#formnomeproduto').removeClass('has-error has-success');
        $('#formnumerolote').removeClass('has-error has-success');
    });

    $('#section1').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#lotes').addClass("active");
        $('#home').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#section1').addClass("active");
        $('#first').removeClass("active");
        $('#section2').removeClass("active");
        $('#section3').removeClass("active");
        $('#fproduto')[0].reset();
        $('#formnomeproduto').removeClass('has-error has-success');
        $('#formnumerolote').removeClass('has-error has-success');
    });

    $('#buscarprodutos').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#buscarprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section2').addClass("active");
        $('#section1').removeClass("active");
        $('#section3').removeClass("active");
        $('#first').removeClass("active");
        $('#main').hide();
        $('#vendaform').hide();
        $('#produtoform').fadeIn();
        $('#currentlocation').html("Pesquisar produtos");
    });

    $('#section2').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#buscarprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#venderprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section2').addClass("active");
        $('#section1').removeClass("active");
        $('#section3').removeClass("active");
        $('#first').removeClass("active");
        $('#main').hide();
        $('#vendaform').hide();
        $('#produtoform').fadeIn();
        $('#currentlocation').html("Pesquisar produtos");
    });

    $('#venderprodutos').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#venderprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section3').addClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').fadeIn();
        $('#currentlocation').html("Vender produtos");
    });

    $('#section3').click(function () {
        $('#buscarclientes').removeClass("active");
        $('#section4').removeClass("active");
        $('#clienteform').hide();
        $('#venderprodutos').addClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section3').addClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').fadeIn();
        $('#currentlocation').html("Vender produtos");
    });

    $('#buscarclientes').click(function () {
        $('#buscarclientes').addClass("active");
        $('#venderprodutos').removeClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section4').addClass("active");
        $('#section3').removeClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#clienteform').fadeIn();
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').hide();
        $('#currentlocation').html("Pesquisar clientes");
    });

    $('#section4').click(function () {
        $('#buscarclientes').addClass("active");
        $('#venderprodutos').removeClass("active");
        $('#lotes').removeClass("active");
        $('#buscarprodutos').removeClass("active");
        $('#home').removeClass("active");
        $('#section4').addClass("active");
        $('#section3').removeClass("active");
        $('#section1').removeClass("active");
        $('#section2').removeClass("active");
        $('#first').removeClass("active");
        $('#clienteform').fadeIn();
        $('#main').hide();
        $('#produtoform').hide();
        $('#vendaform').hide();
        $('#currentlocation').html("Pesquisar clientes");
    });

    // Formulário produto
    $('#nomeproduto').on('input', function () {
        this.value = this.value.replace(/\d+/g, '');
        var inputVal = $(this);
        // a expressão regular abaixo permite várias letras (maiúsculas ou minúsculas) 
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // o nome de um prêmio pode ser composto por três palavras no máximo
        var regex = /^([a-zA-Z]*)(([\s]+)?)([a-zA-Z]*)(([\s]*)?)([a-zA-Z]*)$/;

        if ( !regex.test(inputVal.val()) ) {
            $('#formnomeproduto').addClass('has-error').removeClass('has-success');
            $('#erronomeproduto').fadeIn('slow');
            //$('#produtobuscar').prop("disabled", true);
            checkValuesForProductSearch();
        }
        else {
            $('#formnomeproduto').addClass('has-success').removeClass('has-error');
            $('#erronomeproduto').fadeOut('slow');
            //$('#produtobuscar').removeAttr("disabled");
            checkValuesForProductSearch();
        }

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            deleteClass($('#formnomeproduto'));
            //$('#produtobuscar').removeAttr("disabled");
            checkValuesForProductSearch();
        }
    });

    $('#numerolote').on('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        var inputVal = $(this);
        var regex = /^([0-9])+$/;

        if ( !regex.test(inputVal.val()) && !/^$/.test(inputVal.val()) ) {
            $('#formnumerolote').addClass('has-error').removeClass('has-success');
            $('#erronumerolote').fadeIn('slow');
            //$('#produtobuscar').prop("disabled", true);
            checkValuesForProductSearch();
        }
        else {
            $('#formnumerolote').addClass('has-success').removeClass('has-error');
            $('#erronumerolote').fadeOut('slow');
            //$('#produtobuscar').removeAttr("disabled");
            checkValuesForProductSearch();
        }

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            deleteClass($('#formnumerolote'));
            //$('#produtobuscar').removeAttr("disabled");
            checkValuesForProductSearch();
        }
    });

    // Formulário venda
    $('#nomevenda').on('input', function () {
        this.value = this.value.replace(/\d+/g, '');
        var inputVal = $(this);
        // a expressão regular abaixo permite várias letras (maiúsculas ou minúsculas) 
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // o nome de um prêmio pode ser composto por três palavras no máximo
        var regex = /^([a-zA-Z]*)(([\s]+)?)([a-zA-Z]*)(([\s]*)?)([a-zA-Z]*)$/;

        if ( !regex.test(inputVal.val()) ) {
            $('#formnomevenda').addClass('has-error').removeClass('has-success');
            $('#erronomevenda').fadeIn('slow');
            $('#vendabuscar').prop("disabled", true);
        }
        else {
            $('#formnomevenda').addClass('has-success').removeClass('has-error');
            $('#erronomevenda').fadeOut('slow');
            $('#vendabuscar').removeAttr("disabled");
        }

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            deleteClass($('#formnomevenda'));
            $('#vendabuscar').removeAttr("disabled");
        }
        else if ( $('#nomevenda').val().replace(/\s/g, "").length <= 0 ) {
            deleteClass($('#formnomevenda'));
            $('#vendabuscar').prop("disabled", true);
        }
    });

    $('#qtdeprod').on('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        var inputVal = $(this);
        // a expressão regular abaixo permite 4 dígitos como o ano
        var regex = /^([0-9])+$/;
        var qtdeDisponivel = parseInt($('#disponiveis').html());
        var qtdeVenda = parseInt($(this).val());

        if ( !regex.test(inputVal.val()) && !/^$/.test(inputVal.val()) ) {
            $('#formqtdeprod').addClass('has-error').removeClass('has-success');
            $('#erroqtdeprod').fadeIn('slow');
            $('#vender').prop("disabled", true);
        }
        else {
            $('#formqtdeprod').addClass('has-success').removeClass('has-error');
            $('#erroqtdeprod').fadeOut('slow');
            checkValuesForSell();
        }

        if ( qtdeVenda > qtdeDisponivel || qtdeVenda < 1 ) {
            $('#formqtdeprod').addClass('has-error').removeClass('has-success');
            if ( qtdeVenda > qtdeDisponivel ) {
                $('#erroqtdeprodvenda').html("A quantidade para venda nao deve ser maior do que a quantidade disponivel.");
            }
            else {
                $('#erroqtdeprodvenda').html("A quantidade dos produtos para venda deve ser maior que zero");
            }
            $('#erroqtdemaior').fadeIn('slow');
            $('#vender').prop("disabled", true);
        }
        else {
            $('#formqtdeprod').addClass('has-success').removeClass('has-error');
            $('#erroqtdemaior').fadeOut('slow');
            checkValuesForSell();
        }

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            deleteClass($('#formqtdeprod'));
            $('#vender').prop("disabled", true);
        }
    });

    // Formulário cliente
    $('#nomecliente').on('input', function () {
        this.value = this.value.replace(/\d+/g, '');
        var inputVal = $(this);
        // a expressão regular abaixo permite várias letras (maiúsculas ou minúsculas) 
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // depois inúmeros espaços opcionais
        // em seguida permite várias letras (maiúsculas ou minúsculas)
        // o nome de um prêmio pode ser composto por três palavras no máximo
        var regex = /^([a-zA-Z]*)(([\s]+)?)([a-zA-Z]*)(([\s]*)?)([a-zA-Z]*)$/;

        if ( !regex.test(inputVal.val()) ) {
            $('#formnomecliente').addClass('has-error').removeClass('has-success');
            $('#erronomecliente').fadeIn('slow');
            $('#clientebuscar').prop("disabled", true);
        }
        else {
            $('#formnomecliente').addClass('has-success').removeClass('has-error');
            $('#erronomecliente').fadeOut('slow');
            $('#clientebuscar').removeAttr("disabled");
        }

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            deleteClass($('#formnomecliente'));
            $('#clientebuscar').removeAttr("disabled");
        }
        else if ( $('#nomecliente').val().replace(/\s/g, "").length <= 0 ) {
            deleteClass($('#formnomecliente'));
            $('#clientebuscar').prop("disabled", true);
        }
    });

    $('#produtoclear').click(function () {
        $('#formnomeproduto').removeClass('has-error');
        $('#formnomeproduto').removeClass('has-success');
        $('#formnumerolote').removeClass('has-error');
        $('#formnumerolote').removeClass('has-success');
        $('#erronumerolote').fadeOut('slow');
        $('#erronomeproduto').fadeOut('slow');
        $('#produtobuscar').removeAttr("disabled");
    });

    $('#vendaclear').click(function () {
        deleteClass($('#formnomevenda'));
    });

    $('#clienteclear').click(function () {
        deleteClass($('#formnomecliente'));
        $('#erronomecliente').fadeOut('slow');
        $('#clientebuscar').removeAttr("disabled");
    });

    // Chamada Ajax para venda do produto
    $('#vender').click(function () {
        var productID = $('#productID').val();
        var number = $('#qtdeprod').val();
        var escolha = $('input[name=escolha]:checked', '#formescolha').val();
        // var clientID = ($('#cpf').val() != "") ? $('#cpf').val() : $('#cnpj').val();
        var clientID = $('#escolhacliente').val();
        var escolha = $("input:radio[name=escolha]:checked").val();

        // Monta um array dos IDs dos lotes selecionados
        var searchIDs = $('#escolhalote input:checkbox:checked').map(function () {
            return $(this).val();
        }).get();

        console.log("id: %s", clientID);
        console.log(searchIDs);
        console.log("escolha: %s", escolha);

        var i, soma = 0, contador = 0;
        for ( i = 0; i < searchIDs.length; i++ ) {
            console.log("selector: %s", '#' + "td" + searchIDs[i]);
            soma += parseInt($('#' + "td" + searchIDs[i]).html());
            console.log("soma: %s", soma);

            if ( parseInt($('#' + "td" + searchIDs[i]).html()) >= number )
                contador++;
        }

        // Usuário escolheu lotes que, somados, sua quantidade é menor do que a quantidade pretendida para venda.
        if ( soma !== 0 && soma < number && escolha === "manual" ) {
            $('#modalqtdemenor').modal('show');
        }
        else if ( searchIDs.length === 0 && escolha === "manual" ) {
            // Usuário quer fazer a escolha manual dos lotes mas não escolheu nenhum.
            $('#modalescolha').modal('show');
        }
        else if ( contador > 1 && escolha === "manual" ) {
            // Usuário escolheu mais de um lote que possui a quantidade necessária.
            // Ele deveria escolher menos lotes.
            $('#lotesdemais').modal('show');
        }
        else {
            // Venda do produto.
            $.ajax({
                type: "GET",
                url: "VendaProduto",
                dataType: "html",
                data: {'productID': productID, 'acao': "vender", 'numero': number, checkBoxGroup: searchIDs, 'escolha': escolha, 'clientID': clientID},
                success: function ( data, textStatus, jqXHR ) {
                    // $('#info').html(data);
                    $('#reportInfo').val(data);
                    $('#modalvenda').modal('show');
                }
            });
        }
    });

    $('#manualmente').click(function () {
        $('#escolhalote').fadeIn();
    });

    $('#automatico').click(function () {
        $('#escolhalote').fadeOut();
    });

    // Novo limite de dias
    $('#novolimite').on('input', function () {
        this.value = this.value.replace(/[^0-9]/g, '');
        var inputVal = $('#novolimite');

        if ( this.value.length > 3 )
            this.value = this.value.slice(0, 3);

        // campo do formulário em branco
        if ( /^$/.test(inputVal.val()) ) {
            $('#salvarconfig').prop("disabled", true);
        }
        else {
            $('#salvarconfig').removeAttr("disabled");
        }
    });

    // Busca o atual limite de dias
    $('#configurardata, #section5').click(function () {
        $.ajax({
            type: "GET",
            url: "BuscaProduto",
            dataType: "html",
            data: {'acao': "buscarlimite"},
            success: function ( data, textStatus, jqXHR ) {
                $('#limitedias').html(data);
                $('#modalconfiguracao').modal('show');
            }
        });
    });

    // Salva o novo limite de dias
    $('#salvarconfig').click(function () {
        var novolimite = $('#novolimite').val();

        $.ajax({
            type: "GET",
            url: "BuscaProduto",
            dataType: "html",
            data: {'acao': "atualizarlimite", 'novolimite': novolimite},
            success: function ( data, textStatus, jqXHR ) {
                $('#modalconfiguracao').modal('hide');
                $('#flimite')[0].reset();
                $('#salvarconfig').prop("disabled", true);
                $('#infolimite').html(data);
                $('#modalsucesso').modal('show');
            }
        });
    });

    // Força a página a atualizar
    $('#fecharvenda, #closeicon, #fecharupdate').click(function () {
        location.reload();
    });

    // Não permite que a tecla "ENTER" seja pressionada no modal de mudança do limite de dias
    $('#novolimite').bind("keyup keypress", function ( e ) {
        var code = e.keyCode || e.which;
        if ( code === 13 ) {
            e.preventDefault();
            return false;
        }
    });

    // Realiza chamada Ajax para mostrar os dados do cliente selecionado
    $('#escolhacliente').change(function () {
        checkValuesForSell();

        if ( $('#escolhacliente').val() !== "" ) {
            $.ajax({
                type: "GET",
                url: "BuscaCliente",
                dataType: "json",
                data: {'clientID': $('#escolhacliente').val(), 'acao': "buscarunico"},
                success: function ( data, textStatus, jqXHR ) {
                    $('#nomeclienteajax').html(data.nome);
                    $('#docclienteajax').html(data.identificador);
                    $('#enderecoclienteajax').html(data.endereco);
                    $('#telefoneclienteajax').html(data.telefone);
                    $('#infoscliente').fadeIn();
                }
            });
        }
        else {
            $('#infoscliente').fadeOut('slow');
        }
    });

    // Função que remove as classes 'has-error' e 'has-success' de um formulário passado como parâmetro
    function deleteClass( $form ) {
        $form.removeClass('has-success');
        $form.removeClass('has-error');
    }

    // Habilita o botão de venda caso todos os formulários estejam corretamente preenchidos
    function checkValuesForSell() {
        if ( ($('#escolhacliente').val() !== "") && $('#formqtdeprod').hasClass('has-success') ) {
            $('#vender').removeAttr("disabled");
        }
        else {
            $('#vender').prop("disabled", true);
        }
    }

    // Habilita o botão de busca caso todos os formulários estejam corretamente preenchidos
    function checkValuesForProductSearch() {
        if ( $('#formnumerolote').hasClass('has-error') ) {
            $('#produtobuscar').prop("disabled", true);
        }
        else if ( $('#formnomeproduto').hasClass('has-error') ) {
            $('#produtobuscar').prop("disabled", true);
        }
        else if ( $('#nomeproduto').val().replace(/\s/g, "").length <= 0 && !/^$/.test($('#nomeproduto').val()) ) {
            console.log("empty");
            deleteClass($('#formnomeproduto'));
            $('#produtobuscar').prop("disabled", true);
        }
        else {
            $('#produtobuscar').removeAttr("disabled");
        }
    }

    // Funções que geram tabelas com paginação dos resultados
    // Mudando os parâmetros do construtor para exibir as informações em português
    $('#table_lotes').DataTable({
        "language": {
            "lengthMenu": "Exibir _MENU_ registros por página",
            "zeroRecords": "Nenhum registro foi encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "search": "Busca:",
            "paginate": {
                "first": "Primeiro",
                "last": "Último",
                "next": "Próximo",
                "previous": "Anterior"
            },
            "infoFiltered": "(filtrado a partir de _MAX_ entradas)"
        }
    });

    $('#table_produtos').DataTable({
        "language": {
            "lengthMenu": "Exibir _MENU_ registros por página",
            "zeroRecords": "Nenhum registro foi encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "search": "Busca:",
            "paginate": {
                "first": "Primeiro",
                "last": "Último",
                "next": "Próximo",
                "previous": "Anterior"
            },
            "infoFiltered": "(filtrado a partir de _MAX_ entradas)"
        }
    });

    $('#table_clientes').DataTable({
        "language": {
            "lengthMenu": "Exibir _MENU_ registros por página",
            "zeroRecords": "Nenhum registro foi encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "search": "Busca:",
            "paginate": {
                "first": "Primeiro",
                "last": "Último",
                "next": "Próximo",
                "previous": "Anterior"
            },
            "infoFiltered": "(filtrado a partir de _MAX_ entradas)"
        }
    });

    // $('#radioCPF').click(function () {
    //     $('#formcnpj').hide();
    //     $('#formcnpj').removeClass('has-error');
    //     $('#formcnpj').removeClass('has-success');
    //     $('#infoscliente').hide();
    //     $('#cnpj').val('');
    //     $('#vender').prop("disabled", true);
    //     $('#formcpf').fadeIn();
    // });

    // $('#radioCNPJ').click(function () {
    //     $('#formcpf').hide();
    //     $('#formcpf').removeClass('has-error');
    //     $('#formcpf').removeClass('has-success');
    //     $('#infoscliente').hide();
    //     $('#cpf').val('');
    //     $('#vender').prop("disabled", true);
    //     $('#formcnpj').fadeIn();
    // });

    // Pega o CPF da pessoa.
    // Retorna os dados da pessoa com o referido id.
    // $('#cpf').focusout(function () {
    //     var cpf = $(this).val();
    //     console.log("cpf: %s", cpf);

    //     // regex para cpf
    //     var regex = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
    //     // campo em branco para cpf
    //     var emptyField = /^\_{3}\.\_{3}\.\_{3}\-\_{2}$/;

    //     // campo do formulário em branco
    //     if ( /^$/.test(cpf) || emptyField.test(cpf) ) {
    //         $('#formcpf').removeClass('has-error');
    //         $('#formcpf').removeClass('has-success');
    //         $('#infoscliente').fadeOut();
    //         $('#errocpf').fadeOut();
    //         $('#vender').prop("disabled", true);
    //     }
    //     else if ( !regex.test(cpf) ) {
    //         $('#formcpf').addClass('has-error').removeClass('has-success');
    //         $('#mensagemerrocpf').html("Campo CPF somente aceita números e os caracteres especiais <kbd>.</kbd> e <kbd>-</kbd>");
    //         $('#errocpf').fadeIn('slow');
    //         $('#vender').prop("disabled", true);
    //     }
    //     else {
    //         $('#formcpf').addClass('has-success').removeClass('has-error');
    //         $('#errocpf').fadeOut('slow');
    //         checkValuesForSell();

    //         $.ajax({
    //             type: "GET",
    //             url: "BuscaCliente",
    //             dataType: "json",
    //             data: {'clientID': cpf, 'acao': "buscarunico"},
    //             success: function ( data, textStatus, jqXHR ) {
    //                 $('#errocpf').hide();
    //                 $('#nomeclienteajax').html(data.nome);
    //                 $('#enderecoclienteajax').html(data.endereco);
    //                 $('#telefoneclienteajax').html(data.telefone);
    //                 $('#infoscliente').fadeIn();
    //             },
    //             error: function ( response, textStatus, errorThrown ) {
    //                 $('#infoscliente').hide();
    //                 $('#formcpf').addClass('has-error').removeClass('has-success');
    //                 $('#mensagemerrocpf').html("Cliente com CPF/CNPJ ".concat(cpf).concat(" nao existe!"));
    //                 $('#errocpf').fadeIn('slow');
    //                 $('#vender').prop("disabled", true);
    //             }
    //         });
    //     }
    // });

    // Pega o CNPJ da empresa.
    // Retorna os dados da empresa com o referido id.
    // $('#cnpj').focusout(function () {
    //     var cnpj = $(this).val();
    //     console.log("cnpj: %s", cnpj);

    //     // regex para cnpj
    //     var regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/;
    //     var emptyField = /^\_{2}\.\_{3}\.\_{3}\/\_{4}\-\_{2}$/;

    //     // campo do formulário em branco
    //     if ( /^$/.test(cnpj) || emptyField.test(cnpj) ) {
    //         $('#formcnpj').removeClass('has-error');
    //         $('#formcnpj').removeClass('has-success');
    //         $('#infoscliente').fadeOut();
    //         $('#errocnpj').fadeOut();
    //         $('#vender').prop("disabled", true);
    //     }
    //     else if ( !regex.test(cnpj) ) {
    //         $('#formcnpj').addClass('has-error').removeClass('has-success');
    //         $('#mensagemerrocnpj').html("Campo CNPJ somente aceita números e os caracteres especiais <kbd>.</kbd>, <kbd>/</kbd> e <kbd>-</kbd>");
    //         $('#errocnpj').fadeIn('slow');
    //         $('#vender').prop("disabled", true);
    //     }
    //     else {
    //         $('#formcnpj').addClass('has-success').removeClass('has-error');
    //         $('#errocnpj').fadeOut('slow');
    //         checkValuesForSell();

    //         $.ajax({
    //             type: "GET",
    //             url: "BuscaCliente",
    //             dataType: "json",
    //             data: {'clientID': cnpj, 'acao': "buscarunico"},
    //             success: function ( data, textStatus, jqXHR ) {
    //                 $('#errocnpj').hide();
    //                 $('#nomeclienteajax').html(data.nome);
    //                 $('#enderecoclienteajax').html(data.endereco);
    //                 $('#telefoneclienteajax').html(data.telefone);
    //                 $('#infoscliente').fadeIn();
    //             },
    //             error: function ( response, textStatus, errorThrown ) {
    //                 $('#infoscliente').hide();
    //                 $('#formcnpj').addClass('has-error').removeClass('has-success');
    //                 $('#mensagemerrocnpj').html("Cliente com CNPJ ".concat(cnpj).concat(" nao existe!"));
    //                 $('#errocnpj').fadeIn('slow');
    //                 $('#vender').prop("disabled", true);
    //             }
    //         });
    //     }
    // });

});
