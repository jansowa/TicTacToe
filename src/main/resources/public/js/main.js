function fieldClick(singleField){
	//TODO
	alert(singleField);
}

function restart(){
	//TODO
	alert("Restart board");
}

function printBoard(dataJSON){
	for(var i=0; i<9; i++){
		$('.field:nth-child('+(i+1)+')').text(dataJSON.fields[i]);
	}
}

$(document).ready(()=>{
	$('.field').on('click', event => {
		fieldClick($(event.currentTarget).attr('id'));
	});

	$('.restart').on('click', event => {
		restart();
	})
})
