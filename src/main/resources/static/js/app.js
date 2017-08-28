//Criacao do modulo principal da aplicacao
var appPokemon = angular.module("appPokemon", [ 'ngRoute' ]);

appPokemon.config(function($routeProvider) {
	$routeProvider.when('/time', {
		templateUrl : 'view/CadastroTime.html',
		controller : 'timeController'
	}).when('/treinadores', {
		templateUrl : 'view/CadastroTreinador.html',
		controller : 'treinadorController'
	}).when('/pokemon', {
		templateUrl : 'view/CadastroPokemon.html',
		controller : 'pokemonController'
	}).when('/habilidade', {
		templateUrl : 'view/CadastroHabilidade.html',
		controller : 'habilidadeController'
	}).when('/pesquisaTimes', {
		templateUrl : 'view/PesquisaTimes.html',
	}).when('/login', {
		templateUrl : 'view/Login.html',
		controller : 'loginController'
	})
});
