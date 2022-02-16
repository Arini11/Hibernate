import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class Pantalla extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JLabel etiqueta = new JLabel("");
    JLabel label = new JLabel("Introdueix una ciutat: ");
    JTextField ciutat = new JTextField(15);
    JButton boto = new JButton("Consultar");
    
    static Session session;
    	
    public void iniciar() {
    	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
          
        SessionFactory factory = meta.getSessionFactoryBuilder().build();  
        session = factory.openSession();  
        
        getContentPane().setLayout(new GridLayout(10, 1)); //Contenidor per a botons 
        //Contenidor per a botons
        JPanel panellBotons = new JPanel(new FlowLayout());
        panellBotons.add(label);
        panellBotons.add(ciutat);
        panellBotons.add(boto);

        getContentPane().add(panellBotons);
        getContentPane().add(etiqueta);
        setVisible(true);
        pack(); //Prémer el botó 
        boto.addActionListener(this);
        
        
    }
    //Acció al prémer el botó 
    public void actionPerformed(ActionEvent e) {
        //Es prem el botó 
        if (e.getSource() == boto) {
            etiqueta.setText("Departament a consultar: " +
                ciutat.getText());
            String ciu;
            try {
                ciu = ciutat.getText();
                //Mostra les dades del departament
                VisualitzarDep(ciu);
            } catch (java.lang.NumberFormatException ex) {
                etiqueta.setText("DEPARTAMENT ERRONI");
            }
        }

    }

    void VisualitzarDep(String ciu) {
        //Pintar les dades del departament


        List<Departament> departs = session.createQuery(
            "from Departament as de " +
            "where de.loc = :ciu").setString("ciu", ciu).list();
        if (departs != null) {
        	String departaments = "Departaments: \n";
        	for(int i=0;i<departs.size();i++) {
        		departaments += "\t "+departs.get(i).getDnombre()+"\n";
        		
        		//Pintar les dades del departament 
                JTextArea area = new JTextArea();
                JScrollPane scroll = new JScrollPane(area);
                //Es prepara l'àrea per a escriure els empleats
                area.setBounds(new Rectangle(20, i==0 ? 70 : 70+(210*i), 250, 200));
                scroll.setBounds(new Rectangle(20, i==0 ? 70 : 70+(210*i), 250, 200));
                area.setEditable(false);
                getContentPane().add(scroll, null);
                getContentPane().setLayout(null);
                area.setForeground(Color.BLUE);
                
        		VisualitzarEmp(departs.get(i).getDept_no(), area);
        	}
            etiqueta.setText(departaments);
            
        } else
            etiqueta.setText("NO EXISTEIX EL DEPARTAMENT...");
    }

    //Localitzar les dades del departament 
    void VisualitzarEmp(int dep, JTextArea area) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
          
        SessionFactory sessio = meta.getSessionFactoryBuilder().build(); 
        Session session = sessio.openSession();

        Query q = session.createQuery("from Empleat e where e.dept_no = :ndep");
        q.setInteger("ndep", dep);
        Empleat emple = new Empleat();
        List < Empleat > llista = q.list();

        area.append("Nombre d'empleats: " + llista.size() + "\n");
        area.append("--------------------------");
        area.append("\n COGNOM\tOFICI");
        area.append("\n========================");
        Iterator < Empleat > iter = llista.iterator();
        while (iter.hasNext()) {
            emple = (Empleat) iter.next();
            String cad = "\n" + emple.getApellido() + "\t" + emple.getOficio();
            area.append(cad);
        }
    }
}