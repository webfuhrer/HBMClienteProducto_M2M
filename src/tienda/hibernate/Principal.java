package tienda.hibernate;

import java.util.List;

public class Principal {

	public static void main(String[] args) {
		int opcion=EntradaSalida.mostrarMenu();
		
		while(opcion!=5)
		{
			switch(opcion)
			{
			case 1://1-Insertar cliente
				Cliente cliente=EntradaSalida.pedirCliente();
				AccesoBD.guardarCliente(cliente);
				break;
			case 2:
				//2-Insertar producto
				Producto producto=EntradaSalida.pedirProducto();
				AccesoBD.guardarProducto(producto);
				break;
			case 3:
				//Comprar producto(relacionar el cliente con el producto que compró)
				//3.1-Elegir cliente:
				//1-Recupero los clientes
				List<Cliente> clientes=AccesoBD.devolverClientes();
				int id_cliente=EntradaSalida.mostrarClientes(clientes);
				//Elegir productos (introducir separados por comas):
				List<Producto> productos_all=AccesoBD.devolverProductos();
				String id_producto=EntradaSalida.mostrarProductos(productos_all);
				//id_productos: 1,8,10
				//id_cliente
				List<Producto> productos_comprados=AccesoBD.devolverListaProductos(id_producto);
				Cliente cliente_seleccionado=AccesoBD.recuperarClientePorId(id_cliente);
				if(cliente_seleccionado.getProductos()==null)
				{//Se cumple la condicion si el cliente no tiene ningun producto
				cliente_seleccionado.setProductos(productos_comprados);
				}
				else
				{//El cliente ya tiene productos, así que añado los que acaba de comprar
					cliente_seleccionado.getProductos().addAll(productos_comprados);
				}
				
				AccesoBD.actualizarCliente(cliente_seleccionado);
				break;
			case 4:
				//Buscar cliente por nombre(y listar sus productos)
				String nombre_buscado=EntradaSalida.pedirNombreBuscado();
				Cliente cliente_buscado=AccesoBD.recuperarClientePorNombre(nombre_buscado);
				EntradaSalida.mostrarCliente(cliente_buscado);
				break;
		
			
			
			}
			opcion=EntradaSalida.mostrarMenu();
		}
	}

}
