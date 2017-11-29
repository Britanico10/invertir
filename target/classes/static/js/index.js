function cargarEmpresa(){ 
	var token = localStorage.getItem("Token");
  $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/empresas?token="+token,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false
             })
          .done(function(data) {
            var tabla=$("#Empresas");
            var longitud= data.length;

            for (var i=0; i<longitud; i++) {
                var filaNueva = "<tr> <td>"+ data[i].nombre + "</td><td>"+ data[i].inicioActividad + "</td><td> <button type=\"button\" class=\"btn btn-info\" onclick=\"mostrarBalance("+data[i].id+")\" > Mostrar Balance </button></td></tr>"
                tabla.append(filaNueva);
                //alert(data[i].nombre);
            }
          })
          .fail(function() {
            alert( "error" );
          })
          .always(function() {
            //alert( "complete" );
          });
}

function mostrarBalance(idEmpresa){
     localStorage.setItem("ID", idEmpresa);
     window.open('Balance.html','popup','width=1000,height=1200')
} 

function cargarBalances(){ 
  var token = localStorage.getItem("Token");
  var idEmpresa= localStorage.getItem("ID");
  $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/empresas?token="+token,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false
             })
          .done(function(data) {
            var tabla=$("#Balance");
            var longitud= data.length;

            for (var i=0; i<longitud; i++) {
                if (data[i].id == idEmpresa ){
                  var longitudb= data[i].balances.length;
                  for (var j=0; j<longitudb; j++) {
                      var filaNueva = "<tr> <td>"+ data[i].balances[j].periodo + "</td><td>"+ data[i].balances[j].capitalPropio + "</td><td> "+ data[i].balances[j].deuda + "</td><td>"+ data[i].balances[j].ebitda+"</td><td>"+data[i].balances[j].fCashFlow+ "</td><td>"+data[i].balances[j].fds+ "</td><td>"+data[i].balances[j].ingNetoOpCont+ "</td><td>"+data[i].balances[j].ingNetoOpDiscont+ "</td></tr>" 
                //alert(data[i].nombre);
                      tabla.append(filaNueva);
                  }

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

function aplicarIndicador(){
  var nombreAplicar=$("#empresaAplicar").val();
  var periodoAplicar=$("#periodoAplicar").val();
  var confirmacion;
  confirmacion= validarNombre(nombreAplicar);
  if (confirmacion==0){
    alert("La empresa ingresado no existe");
    location.reload();
  } else{
    localStorage.setItem("EAplicar", nombreAplicar);
    localStorage.setItem("PAplicar", periodoAplicar);

    window.open('Aplicarindicador.html','popup','width=600,height=450')
    location.reload();
    }
}

function validarNombre(n){
    var token = localStorage.getItem("Token");
    var nombre=n;
    var confirmado=0;
    var longitud;
    $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/empresas?token="+token,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            async: false
             })
          .done(function(data) {
            var longitud= data.length;

            for (var i=0; i<longitud; i++) {
                if( data[i].nombre==nombre){
                  confirmado=1;
                }
            }
          })
          .fail(function() {
            alert( "error" );
          })
          .always(function() {
            //alert( "complete" );
          });

        return(confirmado);
}        

function aplicarIndicador2(){
  var token = localStorage.getItem("Token");
  var nombreA = localStorage.getItem("EAplicar");
  var periodoA = localStorage.getItem("PAplicar");
    $.ajax({
            method: "GET",
            url: "http://localhost:8080/api/indicadores/aplicar?empresa="+nombreA+"&periodo="+periodoA+"&token="+token,
            contentType: "application/json;charset=UTF-8",
            async: false
        }).done(function(data) {
            $("#resultado").val(data);
        }).fail(function() {
            alert( "Hubo un error" );
        });
}