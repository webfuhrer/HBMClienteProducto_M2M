package tienda.hibernate;

import java.util.List;
import java.util.Scanner;

public class EntradaSalida {

	public static int mostrarMenu() {
		System.out.println("1-Insertar cliente\r\n"
				+ "2-Insertar producto\r\n"
				+ "3-Comprar producto(relacionar el cliente con el producto que compró\r\n"
				
				+ "4-Buscar cliente por nombre(y listar sus productos)\r\n"
				+ "5-SALIR");
		Scanner sc=new Scanner(System.in);
		int opcion=sc.nextInt();
		return opcion;
	}

	public static Cliente pedirCliente() {
		Scanner sc=new Scanner(System.in);
		Cliente c=new Cliente();
		System.out.println("Introduzca el nombre");
		String nombre=sc.nextLine();
		System.out.println("Introduzca el CIF");
		String cif=sc.nextLine();
		c.setNombre(nombre);
		c.setCIF(cif);
		return c;
	}

	public static Producto pedirProducto() {
		Scanner sc=new Scanner(System.in);
		System.out.println("Introduzca el nombre del producto");
		String nombre_producto=sc.nextLine();
		System.out.println("Introduzca el precio del producto");
		float precio_producto=sc.nextFloat();
		Producto p=new Producto(nombre_producto, precio_producto);
		return p;
	}

	public static int mostrarClientes(List<Cliente> clientes) {
		// Muestro clientes y espero a que el usuario elija uno
		for(Cliente c: clientes)
		{
			System.out.println(c.getId()+"-"+c.getNombre());
		}
		System.out.println("Escriba el número del cliente");
		Scanner sc=new Scanner(System.in);
		int id_cliente=sc.nextInt();
		return id_cliente;
	}

	public static String mostrarProductos(List<Producto> productos_all) {
		// Se muestran los productos y se lee del teclado los productos elegidos
		//un String tipo a,b por ejemplo 1,6
		for(Producto p: productos_all)
		{
			System.out.println(p.getId()+"-"+p.getNombre()+"("+p.getPrecio()+" €)");
		}
		System.out.println("Introduzca los productos separados por comas");
		Scanner sc=new Scanner(System.in);
		String productos=sc.nextLine();
		return productos;
	}

	public static String pedirNombreBuscado() {
		System.out.println("Introduzca el nombre del cliente buscado");
		Scanner sc=new Scanner(System.in);
		String nombre=sc.nextLine();
		return nombre;
	}

	public static void mostrarCliente(Cliente cliente_buscado) {
		System.out.println("NOMBRE: "+cliente_buscado.getNombre());
		System.out.println("CIF: "+cliente_buscado.getCIF());
		System.out.println("ID: "+cliente_buscado.getId());
		List<Producto> lista_productos=cliente_buscado.getProductos();
		for(Producto p: lista_productos)
		{
			System.out.println("ID: "+p.getId());
			System.out.println("NOMBRE PRODUCTO: "+p.getNombre());
			System.out.println("PRECIO PRODUCTO: "+p.getPrecio());
		}
		
	}

}
