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
		printResult(-1, boardJSON.player); //prints information about current player
<<<<<<< HEAD
		$('.field').off('click');
=======
		
>>>>>>> e5a72101205ed549013bdc372000f584d61f398f
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
		printResult(-1, 1); //prints information about actual player
<<<<<<< HEAD
		$('.field').off('click');
=======
>>>>>>> e5a72101205ed549013bdc372000f584d61f398f
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
