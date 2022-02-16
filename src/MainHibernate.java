import java.math.BigDecimal;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.boot.Metadata;  
import org.hibernate.boot.MetadataSources;  
import org.hibernate.boot.registry.StandardServiceRegistry;  
import org.hibernate.boot.registry.StandardServiceRegistryBuilder; 
  
public class MainHibernate {  
	
	static Session session;
  
    public static void main( String[] args )  {  
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
          
        SessionFactory factory = meta.getSessionFactoryBuilder().build();  
        session = factory.openSession();  
          
        //deptMarqueting();
        insertEmpleat();
       
         /*
            e1.setId(1);    
            e1.setApellido("MAS");    
            e1.setOficio("EMPLEADO");
            e1.setDir(7839);
            e1.setFecha_alt("2002-09-26");
            e1.setSalario(999);
            e1.setComision(0);
            e1.setDept_no(10);
         
       session.save(e1);  
       t.commit();  
       System.out.println("successfully saved");    
        factory.close();  
        session.close();    
        */ 
        
		session.close();

    }  
    
    
    public static void deptMarqueting () {
		
		Transaction tx = session.beginTransaction();
		
		System.out.println("Inserir una filera a la taula DEPARTAMENTS.");
		
		Departament dep = new Departament();
		dep.setDept_no((byte) 60);
		dep.setDnombre("MARKETING");
		dep.setLoc("REUS");
		
		session.save(dep);
		tx.commit();
		
		
	}
    
    public static void insertEmpleat () {
		
		Transaction tx = session.beginTransaction();
		
		System.out.println("Inserir un EMPLEAT a DEPARTAMENTS 10.");
		
		BigDecimal salario = new BigDecimal(1500);//Inicialitzo el salari
		BigDecimal comision = new BigDecimal(10);//Inicialitzo la comissió
		
		Empleat em = new Empleat();//Crear objecte empleados
		em.setEmp_no((short) 4455); //nombre empleat 4455
		em.setApellido("Shakira");
		em.setDir("7499");//el director és el nombre d'empleat 7499
		em.setOficio("VENDEDOR");
		em.setSalario(salario);
		em.setComision(comision);
		
		em.setDept_no((byte)10);
		
		//Data d'alta
		java.util.Date avui = new java.util.Date();
		java.util.Date data = new java.sql.Date(avui.getTime());
		em.setFecha_alt(data.toString());
		
		session.save(em);
		tx.commit();
		
	}


}  