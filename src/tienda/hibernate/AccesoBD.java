package tienda.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;


public class AccesoBD {
	static StandardServiceRegistry s=null;
	static SessionFactory sf=null;
	static Session sesion=null;
	public static void inicializarConexion()
	{
		if (sesion==null)
		{
			s=new StandardServiceRegistryBuilder().configure("Luismi.xml").build();
			sf=new MetadataSources(s).buildMetadata().buildSessionFactory();
			sesion=sf.openSession();
		}
	}

	public static List<Producto> devolverListaProductos(String id_productos) {
		// id_productos tiene la pinta a,b,c 
		//donde a b y c son id de producto
		/*select * from personas where id=3
		select * from personas where id=6
		select * from personas where id=8
		select * from personas where id in (3,6,8)	*/
		inicializarConexion();
		String[] ids=id_productos.split(",");
		List<Integer> id_numericos=new ArrayList();
		for (String s: ids)
		{
			//"3"
			id_numericos.add(Integer.valueOf(s));
		}
		Criterion c=Restrictions.in("id", id_numericos);
		Criteria criteria=sesion.createCriteria(Producto.class);
		criteria.add(c);
		List<Producto> productos=criteria.list();
		return productos;
	}

	public static Cliente recuperarClientePorId(int id_cliente) {
		//METODO 1
		/*Query q=sesion.createQuery("FROM Cliente WHERE id="+id_cliente);
		Cliente c=(Cliente) q.getSingleResult();*/
		//METODO 2.
		inicializarConexion();
		SimpleExpression s=Restrictions.eq("id",id_cliente);
		Criteria criteria=sesion.createCriteria(Cliente.class);
		criteria.add(s);
		Cliente c=(Cliente) criteria.uniqueResult();
		return c;	
	}

	public static void actualizarCliente(Cliente c) {
		inicializarConexion();
		Transaction t=sesion.beginTransaction();
		sesion.update(c);
		t.commit();
		
	}

	public static void guardarCliente(Cliente cliente) {
		inicializarConexion();
		Transaction t=sesion.beginTransaction();
		sesion.save(cliente);
		t.commit();
		
	}

	public static void guardarProducto(Producto producto) {
		inicializarConexion();
		Transaction t=sesion.beginTransaction();
		sesion.save(producto);
		t.commit();
		
	}

	public static List<Cliente> devolverClientes() {
		inicializarConexion();
		Query consulta=sesion.createQuery("FROM Cliente");
		//¡¡ Cliente es el nombre de la entidad. Me da igual como se llame la tabla!!
		List<Cliente> clientes=consulta.getResultList();
		return clientes;
	}

	public static List<Producto> devolverProductos() {
		inicializarConexion();
		Query consulta=sesion.createQuery("FROM Producto");
		//¡¡ Producto es el nombre de la entidad. Me da igual como se llame la tabla!!
		List<Producto> productos=consulta.getResultList();
		return productos;
	}

	public static Cliente recuperarClientePorNombre(String nombre_buscado) {
		inicializarConexion();
		Query consulta=sesion.createQuery("FROM Cliente WHERE nombre='"+nombre_buscado+"'");
		Cliente c=(Cliente)consulta.getSingleResult();
		return c;
	}

}
