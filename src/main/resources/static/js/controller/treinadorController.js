// Criacao de controllers
appPokemon.controller("treinadorController", function($scope, $http) {

	$scope.treinadores = [];

	listarTreinadores = function() {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/treinadores'
		}).then(function(response) {
			$scope.treinadores = response.data;

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};

	$scope.salvarTreinador = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/treinadores',
			data : $scope.treinador
		}).then(function(response) {
			$scope.treinadores.push(response.data);

			$scope.treinador = {};
			listarTreinadores();
			alert('Treinador cadastrado com sucesso!');

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};
	$scope.excluirTreinador = function(treinador) {
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/treinadores/' + treinador.id

		}).then(function(response) {
			listarTreinadores();
			alert('Treinador excluido com sucesso!');

		}, function(response) {

			console.log(response.data);
			console.log(response.status);
		});

	};
	$scope.alterarTreinador = function(treinador) {
		$scope.treinador = angular.copy(treinador);

	};
	$scope.cancelarAlteracaoTreinador = function(treinador) {
		$scope.treinador = {};
	};
	listarTreinadores();
});
