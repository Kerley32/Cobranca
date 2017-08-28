/**
 * Created by dan.geabunea on 3/29/2016.
 */
(function() {
	'use strict';

	angular.module('app').controller('BookingsController', BookingsController);

	BookingsController.$inject = [ '$http' ];

	function BookingsController($http) {
		var vm = this;

		vm.bookings = [];
		vm.getAll = getAll;
		vm.getAffordable = getAffordable;
		vm.deleteBooking = deleteBooking;

		init();

		function init() {
			getAll();
		}

		function getAll() {
			var url = "/treinadores/";
			var bookingsPromise = $http.get(url);
			bookingsPromise.then(function(response) {
				vm.bookings = response.data;
			});
		}

		// function getAffordable() {
		// var url = "/treinadores/affordable/" + 100;
		// var bookingsPromise = $http.get(url);
		// bookingsPromise.then(function(response) {
		// vm.bookings = response.data;
		// });
		//		}

		function deleteBooking(id) {
			var url = "/treinadores/" + id;
			$http.post(url).then(function(response) {
				vm.bookings = response.data;
			});
		}
	}
})();