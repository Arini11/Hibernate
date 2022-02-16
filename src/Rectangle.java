/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rectangle extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JTextField depar = new JTextField(2);
    JTextField nom = new JTextField();
    JTextField loc = new JTextField();
    JLabel etiqueta = new JLabel("");
    JLabel Lnum = new JLabel("Número de departament:");
    JLabel Lnom = new JLabel("Nom:");
    JLabel Lloc = new JLabel("Localitat: ");
    JButton botAlta = new JButton("Alta");
    JButton botBaixa = new JButton("Baixa");
    JButton botModif = new JButton("Modificació");
    JButton botNet = new JButton("Netejar");
    //Iniciar la pantalla 
    public void iniciar() {
	    JPanel panell = new JPanel(false);
	    JLabel cap = new JLabel("GESTIÓ DE DEPARTAMENTS");
	    panell.setLayout(null);
	    cap.setBounds(new Rectangle(30, 15, 200, 25));
	    panell.add(cap);
	    Lnum.setBounds(new Rectangle(75, 50, 140, 20));
	    depar.setBounds(new Rectangle(220, 50, 80, 20));
	    Lnom.setBounds(new Rectangle(75, 75, 120, 20));
	    nom.setBounds(new Rectangle(150, 75, 175, 20));
	    Lloc.setBounds(new Rectangle(75, 105, 120, 20));
	    loc.setBounds(new Rectangle(150, 105, 250, 20));
	    etiqueta.setBounds(new Rectangle(100, 150, 400, 20));
	    panell.add(etiqueta);
	    panell.add(Lnum);
	    panell.add(depar, null);
	    panell.add(Lnom);
	    panell.add(nom);
	    panell.add(Lloc);
	    panell.add(loc);
	    
	    botAlta.setBounds(new Rectangle(30, 205, 120, 30));
	    botBaixa.setBounds(new Rectangle(150, 205, 120, 30));
	    botModif.setBounds(new Rectangle(270,205, 120, 30));
	    botNet.setBounds(new Rectangle(390, 205, 120, 30));
	    panell.add(botAlta);
	    panell.add(botBaixa);
	    panell.add(botModif);
	    panell.add(botNet);
	    getContentPane().add(panell);
	    pack();
	    setSize(550,300);
	    setVisible(true);
	    // Préner el botó 
	    botAlta.addActionListener(this); 
	    // Prémer el botó 
	    botBaixa.addActionListener(this); 
	    // Prémer el botó 
	    botModif.addActionListener(this); 
	    //Prener el boto 
	    botNet.addActionListener(this);
    }//Fi d'inicar pantalla
    
	    //Acció al prémer els botons 
    public void actionPerformed (ActionEvent e) {
	    int dep;
	    try {
		    dep = Integer.parseInt(depar.getText());
		    if (e.getSource() == botAlta) { // Préner botó Alta
		    	InserirDep(dep, nom.getText(), loc.getText());
		    } else if (e.getSource() == botBaixa) {// Prémer botó Baixa
		        BaixaDep(dep);
		    } else if (e.getSource() == botModif) // Préner boto Modificació
		        ModificarDep(dep, nom.getText(), loc.getText());
		    } else { // Prémer botó Netejar
		        NetejarCamps();
		    }
	    } catch(java.lang.NumberFormatException ex) {
	        etiqueta.setText("DEPARTAMENT ERRONI");
	    } //Fi acció prémer els botons
	    // Inserir un departament 
	    void InserirDept(int num, String nom, String loc) {
		    SessionFactory sessio = SessionFactoryUtil.getSessionFactory(): Session session sessio.openSession();
		    Transaction tx = Session.beginTransaction();
		    Departamentos depart = (Departamentos) session.createQuery "fron Departanentos as de +
		    "Where de.dept No = ?").setInteger(O, nun).uniqueResult();
		    if (depart null) {
		        etiqueta.setText("DEPARTAMENT EXISTENT -
		                NO ES POT DONAR D 'ALTA): tx, rollback():
		    } else {
		        Departamentos d = new Departamentos();
		        if (nom.length() > 15) non = nom.substring(0, 15);
		        d.set Dnombrenon);
		    if (loc.length() > 15) loc - loc.substring(e.15);
		    d.set Loc(loc): d.setDept Noc(byte) num): session.Save(d): tx.commito etiqueta.setText("DEPARTAMENT INSERIT..."):
		        session.close(): //F1 InserirDep
		        //Elininar un departament void Baixabepint num)
		        SessionFactory sessio = SessionFactoryUtil.getSessionFactory(): Session session = sessio.openSession();
		    Transaction tx - session.beginTransaction();
		    Departamentos de new Departamentos(): de = (Departamentos) session.Load(Departamentos.class, (byte) nun);
		    try
		    // Comprovar si n'hi ha empleats Query cons = Session.createQuery("select count(em, emplo) from Empleados as em where en. dep tNo = ?").setInteger(0, num): Long nomEmp (long) cons.uniqueResult(); if (nonEmp ) {//No hi ha empleats
		    session.delete(de); //Elimina l'objecte tx.commit();
		    etiqueta.setText('ELIMINAT..."); } else {//Hi ha empleats etiqueta.setText("NO ES POT ELIMINAR, TË
		        nonem.
		        " EMPLEATS...'); tx.rollback():
		    }
		    catch (ObjectNotFoundException) etiqueta.setText("DEPARTAMENT NO EXISTENT -
		            NO ES POT ELIMINAR "): tx.rollback();
		            session.close(); //F1 BaixaDep
		            //Modificar un departament void ModificarDeplint num, String non, String loc) {
		            SessionFactory sessio - SessionFactoryUtil.getSessionFactory; Session session = sessio.openSession(); Transaction tx = session.beginTransaction(); Departanentos de new Departamentos(); de - (Departamentos) session.Load(Departamentos.class, (byte) nun);
		            try {
		            	if (nom.length() > 15) nom = nom.substring(0, 15);
		            	de.setDnombre(nom);
		            	if (loc.length() > 15) loc = loc.substring(0, 15);
		            	de.setLoc(loc);
		            	session.update(de); //Modifica l'objecte tx.commit();
		            	etiqueta.setText("DEPARTAMENT MODIFICAT...");
		            	}
		            	catch (ObjectNotFoundException t) {
		            	    etiqueta.setText("DEPARTAMENT no EXISTENT -
		            	        NO ES POT MODIFICAR "); tx.rollback();
		            	        session.close();
		            	    } //Fi ModificarDep
		            	    void NetejarCamps() {
		            	        depar.setText("");
		            	        nom.setText("");
		            	        loc.setText("");
		            	        etiqueta.setText("");
		            	    } //Fi NetejarCamps
		            	} //Fi classe Pantalla
		            }
*/