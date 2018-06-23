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
	}).then(function(){
		//TODO
		$('.field').click( event => {
			singleMove($(event.currentTarget).attr('id'));
		})
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
	//TODO
	$gameState = $('.gameState');
	if(result==0){
		//alert("O wins!");
		$gameState.text("Player O wins!");
	}
	else if(result==1){
		//alert("X wins!");
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

function saveGame(){
	//TODO
	alert("Save game");
}

function loadGame(){
	//TODO
	alert("Load game");
}

$(document).ready(()=>{
	$('.restart').click( () => {
		restart();
	})

	$('.loadGame').click( () => {
		loadGame();
	})

	$('.saveGame').click( () => {
		saveGame();
	});

	$('.singlePlayer').click( () => {
		singlePlayer();
	})

	$('.multiPlayer').click( () => {
		multiPlayer();
	})

	//getBoard();
	//printResult(-1, 0);
	multiPlayer();
})
