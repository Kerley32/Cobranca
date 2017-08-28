// Criacao de controllers
appPokemon.controller("habilidadeController", function($scope, $http) {

	$scope.habilidades = [];

	listarHabilidades = function() {
		$http({
			method : 'GET',
			url : 'http://pokeapi.co/api/v2/move/'
		}).then(function(response) {
			$scope.habilidades = response.data;

		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});

	};

	listarHabilidades();
});
