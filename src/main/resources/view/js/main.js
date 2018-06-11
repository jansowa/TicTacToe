function fieldClick(singleField){
	//TODO
	alert(singleField);
}

function restart(){
	//TODO
	alert("Restart board");
}

$(document).ready(()=>{
	$('.field').on('click', event => {
		fieldClick($(event.currentTarget).attr('id'));
	});

	$('.restart').on('click', event => {
		restart();
	})
})