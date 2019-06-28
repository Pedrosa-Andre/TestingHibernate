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
        
        // Hibernate is an ORM tool. This means the Hibernate functions allow the
        // program to map Java objects to a relational database. (In very simple
        // words it converts an object to information in "table" format that can 
        // be stored in a database). You can also get an object that was mapped 
        // into a table and reconvert it to a normal object again.
        
        // To do this you first need a class (in my case here the Students class)
        // and a table with columns similar to the variables used by the class.
        
        // In my case the Students class has the variables Integer id, 
        // String firstName, String lastName, and String grade (see bellow)
        // https://github.com/Pedrosa-Andre/TestingHibernate/blob/master/TestingHibernate/src/pojos/Students.java
        // while the table has the id int(10), first_name varchar(50), 
        // first_name varchar(50), grade varchar(1) columns.
        // 
        
        // To convert the object to a "table line" the program must know witch 
        // variables should be stored in witch colums (as the machine cant gess
        // this) so we use a .xml file to map (or correlate) each important 
        // object variables and information to the table in the database.
        // For example:
        
            // This example line of code was coppied from my Students.hbm.xml file:
            // (https://github.com/Pedrosa-Andre/TestingHibernate/blob/master/TestingHibernate/src/pojos/Students.hbm.xml)
        //*
        //* <property name="firstName" type="string">                   <- Object's variable name with type.
        //*    <column name="first_name" length="50" not-null="true" /> <- Table's column name that is 
        //* </property>                                                    related to the variable.
        //* 
        
        // Then another .xml file carries all the setup necessary to connect the
        // program to the desired database (in my case the hibernate.cfg.xml):
        // https://github.com/Pedrosa-Andre/TestingHibernate/blob/master/TestingHibernate/src/hibernate.cfg.xml
        
        // Here is a really simple example of how I sent and get objects to and 
        // from the 'students' SQL table in my database.
        
        // First I create some objects.
        Students s1 = new Students("André","Matheus","A");
        Students s2 = new Students("Jonathan","Algusto","A");
        Students s3 = new Students("Evelin","Rachel","A");
        Students s4 = new Students("Bob","Marley","B");
        Students s5 = new Students("Draco","Malfoy","E");
        Students s6 = new Students("Peter","Parker","C");
        
        // Then I use an insert method to add them to the table in the database 
        // (see more details below).
        insert(s1);
        insert(s2);
        insert(s3);
        insert(s4);
        insert(s5);
        insert(s6);
        // And here I use a get method to read and get the data in the table in 
        // the database (see more details below).
        listStudents();
        
    }
    
    static Session session;    
    // The Session interface is (as defined in the Javadoc) "the main runtime 
    // interface between a Java application and Hibernate". It does the 
    // communication between the Java application and Hibernate.
    // For example:
    // The save() method tells Hibernate to use a SQL INSERT
    // The update() method tells Hibernate to use a SQL UPDATE
    // The createQuery(query here (in HQL)) method asks the Hibernate to execute
    // a given query (in Hibernate Query Language).

    
    
    public static void insert(Students stu){
        // Start (open) a session.
        session = Controller.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // INSERT the object in the database.
        session.save(stu);
        // COMMIT the insertion.
        tx.commit();
        // Close the session.
        session.close();
    }    

    public static void listStudents(){
        // Start (open) a session.
        session = Controller.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        // Query for all the lines (representing Students objects) and store
        // them in a list.
        List studentsList = session.createQuery("FROM pojos.Students").list();
        // Iterate through the Students list printing each one of their 
        // variable's values to the user.
        for (int i=0; i<studentsList.size();i++){
            Students st = (Students) studentsList.get(i);
            System.out.println("Name: "+st.getFirstName()+" "+st.getLastName()+" Grade: "+st.getGrade());
        }
        // COMMIT the transaction.
        tx.commit();
        // Close the session.
        session.close();
    }    

}
