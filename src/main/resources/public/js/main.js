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
		$jqueryField.append("<div class='circleOuter'><div class='circleInner'></div></div>");
	}
	else if(sign==1){
		$jqueryField.append("<div class='cross1'></div><div class='cross2'></div>");
	}
	else{
		$jqueryField.text("");
	}
}

function restart(){
	$.ajax({
		url: "/restartBoard"
	}).done(function(boardJSON) {
		printBoard(boardJSON);
		printResult(-1, boardJSON.player); //prints information about current player

		$('.field').off('click');
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));
		})
	});
}

function getBoard(){
	$.ajax({
		url: "/getBoard"
	}).done(function(boardJSON) {
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
	}).done(function(boardJSON){
		printBoard(boardJSON);
		printResult(-1, 1); //prints information about actual player
		$('.field').off('click');
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));

		});
	});
}

function multiPlayer(){
	$.ajax({
		url: "/multiPlayer"
	}).done(function(boardJSON){
		printBoard(boardJSON);
		printResult(-1, 0);
		$('.field').off('click');
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));
		})
	})
}

function printResult(result, player){
	//argument result: 0 O wins, 1 X wins, 2 draw game, -1 game in progress
	//argument "player" represents currentPlayer (important when game in progress)

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
	$.post("/saveGame", {gameName: name})
	.done(function(){
		alert("Game "+name+" saved.");
	});
}

function loadGame(name){
	$.get("/loadGame", {gameName: name})
	.done(function(boardJSON){
		printBoard(boardJSON);
		printResult(-1, boardJSON.player);
	});
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
