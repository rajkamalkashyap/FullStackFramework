var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope', '$timeout', '$window','$http', function($scope,$timeout, $window,$http) {
    $scope.firstName = "John";
    $scope.lastName = "Doe";
	$timeout(function(){
		$(document).ready(function(){
			$scope.table = $('#tabletoolsAll').DataTable();
		});
	});
	$scope.update_game_information = function(game_list){
		console.log(game_list);
		game_list = JSON.parse(game_list);
		console.log(game_list.GAME);
		game_list = JSON.parse(JSON.stringify(game_list.GAME));
		console.log("Redsfas");
		var data = [];
		for (var i = 0; i < game_list.length; i++) {
			console.log("Redsfas");
			var row  = [];
			row.push('');
			row.push(game_list[i].title);
			row.push(game_list[i].platform);
			row.push(game_list[i].score);
			if(game_list[i].genre != undefined){
				row.push(game_list[i].genre);
			}else{
				row.push(' ');
			}
			row.push(game_list[i].editors_choice);
			row.push(game_list[i].release_year);
			row.push(game_list[i].url);
			data.push(row)
		}
		console.log("Redsfas");
		$scope.table.rows.add(data).draw();
		$scope.table.on('order.dt search.dt', function() {
            $scope.table.column(0, {
                    search: 'applied',
                    order: 'applied'
            }).nodes().each(function(cell, i) {
                    cell.innerHTML = i + 1;
            });
        }).draw();
	};
	$http({
		method : "POST",
		url : "/genepg/fetch/game/details",
		data : JSON.stringify({}),
		dataType: 'json'
	}).then(function onSucces(response){
		//console.log(JSON.stringify(response.data));
		$scope.game_list = response.data;
		console.log($scope.game_list);
		$scope.update_game_information($scope.game_list.data);
	},function onError(response){
		console.log(JSON.stringify(response));
	});
	$scope.logout = function(){
		$http({
			method : "GET",
			url : "/genepg/submitLogout",
			data : JSON.stringify({}),
			dataType: 'json'
		}).then(function onSucces(response){
			document.location.replace("/genepg/views/html/index.html")
		},function onError(response){
			document.location.replace("/genepg/views/html/index.html")
		});
	}
}]);
