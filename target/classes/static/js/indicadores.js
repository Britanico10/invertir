
function cargarTabla(data) {
	var tabla=$("#Indicadores");
	var longitud = data.length;
			
	for (i=0;i<longitud;i++){
		var filaNueva = "<tr id=\""+data[i].id+"\">"
		filaNueva += "<td>"+data[i].idIndicador+"</td>"
		filaNueva += "<td>"+data[i].formula+"</td>"
		filaNueva += "<td><button type=\"button\" class=\"btn btn-warning\" onclick=\"mostrarEditar("+data[i].id+")\" > Editar </button></td>"
		filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminar("+data[i].id+")\"> Eliminar </button></td></tr>"
		tabla.append(filaNueva);
	}
}

function mostrarEditar(id){ 
    var fila = $("#"+id);
    var nombre = fila.find("td:eq(0)").html(); 
    var tabla=$("#Indicadores");
	var id= id;
	var numero=70;
    var filaNueva = "<tr id=\""+numero+"\">"
    filaNueva += "<td>"+nombre+"</td>"
    filaNueva += "<td><input type\"text\" id=\"nuevaF\" placeholder=\"Nueva Formula\"</td>"
    filaNueva+= "<td></td>"
	filaNueva += "<td><button type=\"button\" class=\"btn btn-success\" onclick=\"editar("+id+")\" > Aceptar </button></td>"
	tabla.find("tfoot").append(filaNueva);
}


function eliminar(id){
  var token = localStorage.getItem("Token");  
	$.ajax({
		method: "DELETE",
		url: "http://localhost:8080/api/indicadores?id="+id+"&token="+token,
		contentType: "application/json;charset=UTF-8",
		dataType: "json",
		async: false
        })
		.done(function(data) {
			$("#Indicadores td").remove(); 
			//alert("Se elimino el indicador exitosamente");
			cargarTabla(data);
		})
		.fail(function() {
			alert( "Hubo un error" );
		});
}



function cargarIndicadores(){ 
	var token = localStorage.getItem("Token");
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/api/indicadores?token="+token,
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			async: false
            })
			.done(function(data) {
				cargarTabla(data);
				
					
			})
			.fail(function() {
			alert( "Hubo un error" );
			});
        }

function agregarIndicador() {
	var token = localStorage.getItem("Token");
	var nombre = $("#nombre").val();
	var formula = $("#formula").val();
	
	if (nombre == ""  || formula == "") {
		alert("Debe completar el nombre y la formula");
	} else {
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/api/indicadores?nombre="+nombre+"&formula="+formula+"&token="+token,
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			async: false
		})
		.done(function(data) {
			$("#nombre").val("");
			$("#formula").val("");
			$("#Indicadores td").remove(); 
			//alert("Se agrego el indicador exitosamente");
			cargarTabla(data);
		})
		.fail(function() {
			alert( "Hubo un error" );
		});
	}
}

function editar(id){
    var token = localStorage.getItem("Token"); 
    var fila = $("#"+id);
    var nombre = fila.find("td:eq(0)").html(); 
    var formula= $("#nuevaF").val();
    
    $.ajax({
			method: "PUT",
			url: "http://localhost:8080/api/indicadores?nombre="+nombre+"&formula="+formula+"&token="+token,
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			async: false
		})
		.done(function(data) {
			$("#Indicadores td").remove(); 
			//alert("Se edito el indicador exitosamente");
			$("#70").remove();
			cargarTabla(data);
		})
		.fail(function() {
			alert( "Hubo un error" );
		});
	}

       