/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testinghibernate;

import connections.Controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pojos.Students;

/**
 *
 * @author INV
 */
public class TestingHibernate {
    
    private static SessionFactory controlFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Hibernate is a ORM tool, this means the Hibernate functions allow the
        // program to map Java objects to a relational database. (In very simple
        // words it converts an object to information in "table" format that can 
        // be stored in a database). You can also get an object that was mapped 
        // into a table and reconvert it to a normal object again.
        
        // To do this you first need a class (in my case here the Students class)
        // and a table with columns similar to the variables used by the class.
        
        // In my case the Students class has the variables Integer id, 
        // String firstName, String lastName, and String grade; while the table
        // has the id int(10), first_name varchar(50), first_name varchar(50),
        // and 	grade varchar(1) columns.
        
        // To convert the object to a table line the program must know witch 
        // variables should be stored in witch colums (as the machine cant gess
        // this) so we use a .xml file to map (or correlate) each important 
        // object variables and information to the table in the database.
        // For example:
        
            // This line of code was coppied from my Students.hbm.xml file
        //
        //  <property name="firstName" type="string">                   <- Object's varible name with type
        //     <column name="first_name" length="50" not-null="true" /> <- Table's column name that is 
        //  </property>                                                    related to the variable
        //  
       
        
        // Here is a really simple example of how I sent and get objects from the
        // students SQL table in my database.
        
        Students s1 = new Students("André","Matheus","A");
        Students s2 = new Students("Jonathan","Algusto","A");
        Students s3 = new Students("Evelin","Rachel","A");
        Students s4 = new Students("Bob","Marley","B");
        Students s5 = new Students("Draco","Malfoy","E");
        Students s6 = new Students("Peter","Parker","C");
        insert(s1);
        insert(s2);
        insert(s3);
        insert(s4);
        insert(s5);
        insert(s6);
        listStudents();
        
    }
    
    static Session session;
    
    public static void insert(Students stu){
        session = Controller.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(stu);
        tx.commit();
        session.close();
    }    

    public static void listStudents(){
        session = Controller.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List studentsList = session.createQuery("FROM pojos.Students").list();
        for (int i=0; i<studentsList.size();i++){
            Students st = (Students) studentsList.get(i);
            System.out.println("Name: "+st.getFirstName()+" "+st.getLastName()+" Grade: "+st.getGrade());
        }
        tx.commit();    
        session.close();
    }    

}
