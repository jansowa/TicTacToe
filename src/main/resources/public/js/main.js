function singleMove(singleField){
	$.post("/singleMove", {field: singleField})
		.done(function(boardStateJSON){
			printBoard(boardStateJSON.board);
			printResult(boardStateJSON.state, boardStateJSON.board.player);
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
		url: "/restartBoard"
	}).then(function(boardJSON) {
		printBoard(boardJSON);
		$('.gameState').text("Player O move:")
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));
		})
	});
}

function getBoard(){
	$.ajax({
		url: "/getBoard"
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
	$.ajax({
		url: "/singlePlayer"
	}).then(function(boardJSON){
		printBoard(boardJSON);
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));

		});
	});
}

function multiPlayer(){
	$.ajax({
		url: "/multiPlayer"
	}).then(function(boardJSON){
		printBoard(boardJSON);
		printResult(-1, 0);
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));
		})
	})
}

function printResult(result, player){

	$gameState = $('.gameState');
	if(result==0){
		$gameState.text("Player O wins!");
	}
	else if(result==1){
		$gameState.text("Player X wins!");
	}
	else if(result==2){
		$gameState.text("Draw game");
	}
	else{ //if result==-1
		if(player==0){
			$gameState.text("Player O move:");
		}
		else{
			$gameState.text("Player X move:");
		}
	}

	if(result!=-1){
		$('.field').off('click');
	}
}

function saveGame(name){
	$.post("/saveGame", {gameName: name});
	//TODO info game saved
}

function loadGame(name){
	$.get("/loadGame", {gameName: name});
	//TODO print loaded board
}

$(document).ready(()=>{
	$('.restart').click( () => {
		restart();
	})

	$('.loadGame').click( () => {
		$('.loadInput').toggle();
		$('.loadButton').toggle();
	});

	$(".loadButton").click( () => {
		var gameName = $('.loadInput').val();
		loadGame(gameName);
	})

	$('.saveGame').click( () => {
		$('.saveInput').toggle();
		$('.saveButton').toggle();
	});

	$(".saveButton").click( event => {
		var gameName = $('.saveInput').val();
		saveGame(gameName);
	})

	$('.singlePlayer').click( () => {
		singlePlayer();
	})

	$('.multiPlayer').click( () => {
		multiPlayer();
	})

	multiPlayer();
})
