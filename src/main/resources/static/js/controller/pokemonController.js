// Criacao de controllers
appPokemon.controller("pokemonController", function($scope, $http) {

	$scope.pokemons = [];
	$scope.selected = [];

	$scope.exist = function(pokemon) {

		return $scope.selected.indexOf(pokemon) > -1;
	}
	$scope.toggleSelection = function(pokemon) {

		var idx = $scope.selected.indexOf(pokemon);

		if (idx > -1) {
			console.log('Desmarcou');

			$scope.selected.splice(idx, 1);
		} else {

			console.log('Marcou');

			$scope.selected.push(pokemon);
		}

	};
	listarPokemons = function() {
		$http({
			method : 'GET',
			url : 'http://pokeapi.co/api/v2/pokemon/'
		}).then(function(response) {
			$scope.pokemons = response.data;
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};
	$scope.salvarPokemon = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/pokemons',
			data : $scope.selected
		}).then(function(response) {
			console.log(response.data);
			alert('Pokemons importados com sucesso');
			$scope.selected = [];
			// $scope.pokemons.push(response.data);

		}, function(response) {
			alert('Retorno: ' + response.status + '. Erro na requisição.');

			console.log(response.data);
			console.log(response.status);
		});

	};

	listarPokemons();
});
