function displayServerMsg(msg){
	$("#srvrmsg").html('<font color="red" >'+msg+'</font>');
	$("#srvrmsg").attr("style", "display:");
}
function generate() {
	if ($('#searchUN').val() == null || $('#searchUN').val() == '')
		$("#msg").attr("style", "display:;");
	else {
		$("#msg").attr("style", "display:none;");
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var obj = JSON.parse(xhttp.responseText);
				if(obj.errMsg != null && obj.errMsg != ''){
					displayServerMsg(obj.errMsg);
				}
				else{
					$('#resume').attr("style", "display:");
					$("#srvrmsg").attr("style", "display:none");
					$('#forks').html(obj.forks);
					$('#githubprofile').html("<a target='_blank' href='"+obj.name+"'>Profile in GitHub</a>");
					$('#email').html(obj.email);
					$('#followers').html(obj.followers);
					var str = "<div class='row'>";
					for(var l  in obj.languages){
						str += "<div class='col-md-1'></div>";
						str += "<div class='col-md-1'>"+obj.languages[l].name+":";
						str += ""+obj.languages[l].percentage+"%</div>";
						str += "<div class='col-md-1'></div>";
					}
					str += "</div>";
					str += "<div class='row'>";
					str += "<div clas='col-md-12'>=============================</div>";
					str += "</div>";
					$("#languages").html(str);
					str = "";
					for(var r in obj.repositories){
						str += "<div class='row'>";
						str += "<div class='col-md-4'>Name:"+obj.repositories[r].name+"</div>";
						str += "<div class='col-md-1'></div>";
						str += "<div class='col-md-1'>Year:"+obj.repositories[r].year+"</div>";
						str += "<div class='col-md-1'></div>";
						str += "<div class='col-md-1'>Forks:"+obj.repositories[r].forks+"</div>";						
						str += "</div>";
						str += "<div class='row'>";
						str += "<div clas='col-md-12'>Url:<a target='_blank' href='"+obj.repositories[r].url+"'>click to see it in github</a></div>";						
						str += "</div>";
						str += "<div class='row'>";
						str += "<div clas='col-md-12'>Description</div>";
						str += "</div>";
						str += "<div class='row'>";
						str += "<div clas='col-md-12'>"+obj.repositories[r].description+"</div>";
						str += "</div>";
						str += "<div class='row'>";
						str += "<div class='col-md-12'>***********************</div>";
						str += "</div>";
					}
					$("#repositories").html(str);
				}
			}
			else{
				if(xhttp.responseText != null && xhttp.responseText != ''){
					var obj = JSON.parse(xhttp.responseText);
					if(obj.errMsg != null && obj.errMsg != ''){
						displayServerMsg(obj.errMsg);
					}
				}
			}
		};
		xhttp.open("GET", '/resume/' + $('#searchUN').val(), true);
		xhttp.send();
	}
}