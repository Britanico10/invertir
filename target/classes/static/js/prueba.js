function login(){ 
	user=$("#usuario").val()
	pass=$("#contrasena").val()
	if (user == "" || pass == ""){
		alert("Faltan datos");
	}
	else {
		$.getJSON("http://localhost:8080/api/iniciarsesion", "nombre="+user+"&password="+pass, success(data,status,xhr) {
           console.log(data);
        });