var met = new Array();
var co = new Array();
var cf = new Array();
var nombre;
// metodologias
function cargarMetodologias(){ 
	var token = localStorage.getItem("Token");
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/api/metodologias?token="+token,
			contentType: "application/json;charset=UTF-8",
			dataType: "json",
			async: false
      })
			.done(function(data) {
				var tabla=$("#Metodologias");
				var longitud = data.length; 
				for (i=0;i<longitud;i++){
          met[i]= data[i].nombre
          var filaNueva = "<tr>"
					filaNueva += "<td>"+data[i].nombre+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"aplicarMetodologia("+i+")\" > Aplicar </button></td>"
          //filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"mostrarFiltro("+data[i].id+")\" > Mostrar </button></td>"
					//filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"mostrarOrden("+data[i].id+")\"> Mostrar </button></td></tr>"
					tabla.append(filaNueva);
				}
			})
			.fail(function() {
			alert( "Hubo un error" );
			});
}

function aplicarMetodologia(pos){
  nombre = met[pos];
  localStorage.setItem("NOMBREAPLICAR", nombre);
  window.open('AplicarMetodologia.html','popup','width=400,height=650')
}

function aplicar(){
  var token = localStorage.getItem("Token");
  var nombreB= localStorage.getItem("NOMBREAPLICAR");
 // nombreB = nombreB.replace(/ /g,"");
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/api/metodologias/aplicar?nombre="+nombreB+"&token="+token,
    contentType: "application/json;charset=UTF-8",
    async: false
    })
    .done(function(data) {
      $("#resultado").val(data);
    })
    .fail(function() {
      alert( "Hubo un error" );
    });
 
}

//--------------------------------------------------------------------------------------------------------------------------
// condiciones de Orden
function cargarDetalleOrden(){
  var token = localStorage.getItem("Token");
  var idCF= localStorage.getItem("IDCF");
  $.ajax({
            method: "GET",
             url: "http://localhost:8080/api/condicionesorden?token="+token,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false
             })
          .done(function(data) {
            var tabla=$("#Filtro");
            var longitud= data.length;

            for (var i=0; i<longitud; i++) {
                if (data[i].id == idCF ){
                      var filaNueva = "<tr> <td>"+ data[i].inicioIntervalo + "</td><td>"+ data[i].finalIntervalo + "</td><td> "+ data[i].nombreIndicador + "</td><td>"+ data[i].periodo+"</td><td>"+data[i].importancia+ "</td></tr>" 
                //alert(data[i].nombre);
                      tabla.append(filaNueva);
                }
            }
          })
          .fail(function() {
            alert( "error" );
          })
          .always(function() {
            //alert( "complete" );
          });
}
function cargarCondicionesOrden() { 
    var token = localStorage.getItem("Token");
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/api/condicionesorden?token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        var tabla=$("#CondOrden");
        var longitud = data.length; 
        for (i=0;i<longitud;i++){
          co[i]= data[i].nombreCondicion
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicionOrden("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionOrden("+i+")\" > Eliminar </button></td></tr>"

          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}

function detalleCondicionOrden(id){
   localStorage.setItem("IDCO", id);
   window.open('DetalleOrden.html','popup','width=1000,height=1200')
}

function eliminarCondicionOrden(id) { 
    location.reload();
    var token = localStorage.getItem("Token");
    var nombre =  co[id];
    $.ajax({
      method: "DELETE",
      url: "http://localhost:8080/api/condicionesorden?nombre="+nombre+"&token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        $("#CondOrden td").remove(); 
        var tabla=$("#CondOrden");
        var longitud = data.length; 
        for (i=0;i<longitud;i++){
          co[i] = data[i].nombreCondicion;
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicion("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionOrden("+i+")\" > Eliminar </button></td></tr>"
          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}

function agregarCondicionOrden() { 
    location.reload();
    var token = localStorage.getItem("Token");
    var nombre = $("#nombre").val();
    var tipo = $("#tipo").val();
    var iI = $("#iActividad").val();
    var fI = $("#fActividad").val();
    var periodo = $("#periodo").val();
    var importancia = $("#importancia").val();
    var nombreIndicador = $("#nIndicador").val();
    $.ajax({
      method:"POST",
      url: "http://localhost:8080/api/condicionesorden?tipo="+tipo+"&nombre="+nombre+"&inicioIntervalo="+iI+"&finIntervalo="+fI+"&nombreIndicador="+nombreIndicador+"&periodo="+periodo+"&importancia="+importancia+"&token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        $("#CondOrden td").remove(); 
        var tabla=$("#CondOrden");
        var longitud = data.length; 
        for (i=0;i<longitud+1;i++){
          co[i] = data[i].nombre;
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicion("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionOrden("+i+")\" > Eliminar </button></td></tr>"

          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}
//---------------------------------------------------------------------------------------------------------------------------
// Condiciones de filtro
function cargarDetalleFiltro(){
  var token = localStorage.getItem("Token");
  var idCF= localStorage.getItem("IDCF");
  $.ajax({
            method: "GET",
             url: "http://localhost:8080/api/condicionesfiltro?token="+token,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false
             })
          .done(function(data) {
            var tabla=$("#Filtro");
            var longitud= data.length;

            for (var i=0; i<longitud; i++) {
                if (data[i].id == idCF ){
                      var filaNueva = "<tr> <td>"+ data[i].inicioIntervalo + "</td><td>"+ data[i].finalIntervalo + "</td><td> "+ data[i].nombreIndicador + "</td><td>"+ data[i].periodo+"</td><td>"+data[i].comparador+ "</td></tr>" 
                //alert(data[i].nombre);
                      tabla.append(filaNueva);
                }
            }
          })
          .fail(function() {
            alert( "error" );
          })
          .always(function() {
            //alert( "complete" );
          });
}
function cargarCondicionesFiltro() { 
    var token = localStorage.getItem("Token");
    $.ajax({
      method: "GET",
      url: "http://localhost:8080/api/condicionesfiltro?token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        var tabla=$("#CondFiltro");
        var longitud = data.length; 
        for (i=0;i<longitud;i++){
          cf[i]= data[i].nombreCondicion
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicion("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionFiltro("+i+")\" > Eliminar </button></td></tr>"

          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}

function detalleCondicion(id){
   localStorage.setItem("IDCF", id);
   window.open('DetalleFiltro.html','popup','width=1000,height=1200')
}

function eliminarCondicionFiltro(id) { 
    location.reload();
    var token = localStorage.getItem("Token");
    var nombre =  cf[id];
    $.ajax({
      method: "DELETE",
      url: "http://localhost:8080/api/condicionesfiltro?nombre="+nombre+"&token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        $("#CondOrden td").remove(); 
        var tabla=$("#CondOrden");
        var longitud = data.length; 
        for (i=0;i<longitud;i++){
          co[i] = data[i].nombreCondicion;
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicion("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionFiltro("+i+")\" > Eliminar </button></td></tr>"
          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}

function agregarCondicionFiltro() { 
    location.reload();
    var token = localStorage.getItem("Token");
    var nombre = $("#nombre").val();
    var tipo = $("#tipo").val();
    var iI = $("#iActividad").val();
    var fI = $("#fActividad").val();
    var periodo = $("#periodo").val();
    var comparador = $("#comparador").val();
    var nombreIndicador = $("#nIndicador").val();
    $.ajax({
      method:"POST",
      url: "http://localhost:8080/api/condicionesfiltro?tipo="+tipo+"&nombre="+nombre+"&inicioIntervalo="+iI+"&finIntervalo="+fI+"&nombreIndicador="+nombreIndicador+"&periodo="+periodo+"&comparador="+comparador+"&token="+token,
      contentType: "application/json;charset=UTF-8",
      dataType: "json",
      async: false
      })
      .done(function(data) {
        $("#CondFiltro td").remove(); 
        var tabla=$("#CondFiltro");
        var longitud = data.length; 
        for (i=0;i<longitud+1;i++){
          cf[i] = data[i].nombre;
          var filaNueva = "<tr>"
          filaNueva += "<td>"+data[i].nombreCondicion+"</td>"
          filaNueva += "<td>"+data[i].tipo+"</td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-info\" onclick=\"detalleCondicion("+data[i].id+")\" > Ver Detalle </button></td>"
          filaNueva += "<td><button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarCondicionFiltro("+i+")\" > Eliminar </button></td></tr>"

          tabla.append(filaNueva);
        }
      })
      .fail(function() {
      alert( "Hubo un error" );
      });
}