// Criacao de controllers
appPokemon.controller("timeController", function($scope, $http) {

	$scope.times = [];

	$scope.selected = [];
	$scope.habilidades = [];
	$scope.pokemons = [];

	$scope.exist = function(pokemon) {
		return $scope.selected.indexOf(pokemon) > -1;
	}
	$scope.toggleSelection = function(pokemon) {
		var idx = $scope.selected.indexOf(pokemon);
		if (idx > -1) {
			$scope.selected.splice(idx, 1);
		} else {
			$scope.selected.push(pokemon);
		}
	};

	listarTimes = function() {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/times'
		}).then(function(response) {
			$scope.times = response.data;
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};
	listarPokemons = function() {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/pokemons/'
		// url : 'http://pokeapi.co/api/v2/pokemon/'
		}).then(function(response) {
			$scope.pokemons = response.data;
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
	};

	listarHabilidades = function() {
		$http({
			method : 'GET',
			// url : 'http://pokeapi.co/api/v2/move/'
			url : 'http://localhost:8080/habilidades/'
		}).then(function(response) {
			$scope.habilidades = response.data;
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};

	listarTreinadores = function() {
		$http({
			method : 'GET',
			url : 'http://localhost:8080/treinadores/'
		}).then(function(response) {
			$scope.treinadores = response.data;
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};
	$scope.salvarTime = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/times',
			data : $scope.time
		}).then(function(response) {
			console.log(response.data);
			$scope.times.push(response.data);
			alert('Time escalado com sucesso');
			$scope.time = {};
			$scope.selected = [];
			listarTimes();

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};

	$scope.excluirTime = function(time) {
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/times/' + time.id

		}).then(function(response) {
			listarTimes();
			alert('Time excluído com sucesso!');

		}, function(response) {

			console.log(response.data);
			console.log(response.status);
		});

	};

	$scope.alterarTime = function(time) {
		$scope.time = angular.copy(time);
		console.log(time.treinador.nome);
	};
	$scope.cancelarAlteracaoTime = function(time) {
		$scope.time = {};
	};
	listarPokemons();
	listarHabilidades();
	listarTimes();
	listarTreinadores();

});
