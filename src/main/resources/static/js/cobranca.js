$('#confirmacaExclusaoModal').on(
		'show.bs.modal',
		function(event) {
			// pega o botao que disparou o evento
			var button = $(event.relatedTarget);
			// dentro do botao funcao 'data" do Jquery para carrega o valor
			var codigoTitulo = button.data('codigo');
			var descricaoTitulo = button.data('descricao');

			// Mostra um alerta com o codigo
			// alert(codigoTitulo);

			// pega os valores do dialog
			var modal = $(this);
			var form = modal.find('form'); // Encontra o form (dialog ou quem
			// chamar)
			var action = form.data('url-base'); // carrega a string do action do
			// form
			if (!action.endsWith('/')) {// Verificar se a url termina com /
				action += '/'; // Concatenar a url com /
			}
			form.attr('action', action + codigoTitulo); // concatena com codigo

			// altera o span da classe modal-body do dialog
			modal.find('.modal-body span').html(
					'Tem certeza que deseja exluir o título <strong>'
							+ descricaoTitulo + '</strong>?');
		});
$(function() {
	// carrega tooltip nos componentes rel=tooltip
	$('[rel="tooltip"]').tooltip();
	// configura mascara de moeda
	$('.js-currency').maskMoney({
		decimal : ',',
		thousands : '.',
		allowZero : true
	});

	// Atualizacao de status via Javascript
	$('.js-atualizar-status').on(
			'click',
			function(event) {
				// Escreve no console do browser (console.log('Clicou'));
				event.preventDefault();

				// console.log('Clicou!')
				// pegando o botao recebe
				var botaoReceber = $(event.currentTarget);
				var urlReceber = botaoReceber.attr('href');
				// console.log(urlReceber);

				// Comecando funcao Ajax do JQuery
				// Envia requisicao via PUT
				var response = $.ajax({
					url : urlReceber,
					type : 'PUT'
				});

				// Se deu certo
				response.done(function(e) {
					var codigoTitulo = botaoReceber.data('codigo');
					$('[data-role=' + codigoTitulo + ']').html(
							'<span class="label label-success">' + e
									+ '</span>');
					botaoReceber.hide();
				});
				// se deu errado
				response.fail(function(e) {
					console.log(e);
					alert('Erro recebendo cobrança');
				});
			});

});