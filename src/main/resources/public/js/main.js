function singleMove(singleField){
	$.post("/singleMove", {field: singleField})
		.done(function(boardStateJSON){
			printBoard(boardStateJSON.board);
			printResult(boardStateJSON.state, boardStateJSON.board.player);
		});
}

function drawSign(sign, field){
	let $jqueryField = $('.field:nth-child('+(field+1)+')');
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

function printBoard(boardJSON){
	for(var y=0; y<3; y++){
		for(var x=0; x<3; x++){
			drawSign(boardJSON.fields[y][x], 3*y+x);
		}
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

	const $gameState = $('.gameState');
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
}

function loadGame(name){
	$.get("/loadGame", {gameName: name})
	.done(function(boardJSON){
		printBoard(boardJSON);
		printResult(-1, boardJSON.player);
	});
}

$(document).ready(()=>{

	$('.loadGame').click( () => {
		$('.loadInput').slideToggle();
		$('.loadButton').slideToggle();
	});

	$(".loadButton").click( () => {
		var gameName = $('.loadInput').val();
		loadGame(gameName);
	})

	$('.saveGame').click( () => {
		$('.saveInput').slideToggle();
		$('.saveButton').slideToggle();
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
