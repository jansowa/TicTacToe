function singleMove(singleField){
	$.post("http://localhost:8080/singleMove", {field: singleField})
		.done(function(boardJSON){
			printBoard(boardJSON);
		});
}

function drawSign(sign, field){
	$jqueryField = $('.field:nth-child('+(field+1)+')');
	if(sign==0){
		$jqueryField.text("O");
	}
	else if(sign==1){
		$jqueryField.text("X");
	}
	else{
		$jqueryField.text("");
	}
}

function restart(){
	$.ajax({
		url: "http://localhost:8080/restartBoard"
	}).then(function(boardJSON) {
		printBoard(boardJSON);
	});
}

function getBoard(){
	$.ajax({
		url: "http://localhost:8080/getBoard"
	}).then(function(boardJSON) {
		printBoard(boardJSON);
	});
}

function printBoard(boardJSON){
	for(var i=0; i<9; i++){
		drawSign(boardJSON.fields[i], i);
	}
}

function singlePlayer(){
	//TODO
	alert("Single player");
}

function multiPlayer(){
	//TODO
	alert("Multi player");
}

function printResult(result){
	//TODO
}

function saveGame(){
	//TODO
	alert("Save game");
}

function loadGame(){
	//TODO
	alert("Load game");
}

$(document).ready(()=>{
	$('.field').on('click', event => {
		singleMove($(event.currentTarget).attr('id'));
	});

	$('.restart').on('click', event => {
		restart();
	});

	$('.loadGame').on('click', event => {
		loadGame();
	});

	$('.saveGame').on('click', event => {
		saveGame();
	});

	$('.singlePlayer').on('click', event => {
		singlePlayer();
	});

	$('.multiPlayer').on('click', event => {
		multiPlayer();
	});

	getBoard();
})