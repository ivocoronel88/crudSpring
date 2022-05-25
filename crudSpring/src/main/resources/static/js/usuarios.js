// Call the dataTables jQuery plugin
$(document).ready(function() {
	cargarUsuarios();
  $('#usuarios').DataTable();
});


async function cargarUsuarios(){
	
		  const request = await fetch('api/usuarios', {
		    method: 'GET',
		    headers: {
		      'Accept': 'application/json',
		      'Content-Type': 'application/json'
		    },
		  
		  });
		  const usuarios = await request.json();
		  let listadoHTML = '';
		  let telefono = usuario.telefono == null ? '-' : usuario.telefono;
		  for(let usuario of usuarios){
			  let botonEliminar = '<a onclick = "eliminarUsuario('+usuario.id+')" href="#" class="btn btn-danger btn-circle btn-lg"><i class="fas fa-trash"></i></a>';

			  listadoHTML +=' <tr><td>'+usuario.id
			  +'</td> <td>'+usuario.nombre+' '+usuario.apellido
			  +'</td><td>'+usuario.email
			  +'</td><td>'+telefono
			  +'</td> <td>'+botonEliminar+'</td></tr>';
		  }
    
   
      
		document.querySelector('#usuarios tbody').outerHTML = listadoHTML;
}


async function eliminarUsuario(id){
			if(!confirm("Desea eliminar este usuario?")){
				return;
			}
			
			const request = await fetch('api/usuario/'+ id, {
				method: 'DELETE',
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				
			});
			location.reload()
				
}

