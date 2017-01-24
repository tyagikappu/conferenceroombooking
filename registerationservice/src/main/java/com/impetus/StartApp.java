package com.impetus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.impetus.reservation.domain.UserRoomBookingInfo;
import com.impetus.reservation.process.ReservationProcess;
import com.impetus.reservation.sender.BookingRequestSender;


@SpringBootApplication
@RabbitListener(queues = "room_book")
@EnableScheduling
public class StartApp {
	
	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    final static String queueName = "room_book";
    
    
    @Autowired
    ReservationProcess reservationProcess;
    
    
   /* @Bean
	public Sender mySender() {
		return new Sender();
	}*/

	@Bean
	public Queue fooQueue() {
		return new Queue(queueName);
	}

	@RabbitHandler
	public void process(@Payload Map<String, Object> bookingMap) {
		System.out.println("***Listining Room Booking Request******"+bookingMap);
		reservationProcess.bookRoom(bookingMap);
	}


	public static void main(String[] args) {
		System.out.println("*****STARTING SPRING BOOT********");
		SpringApplication.run(StartApp.class, args);
		try{
		createRequiredTables(); // One Time activity
		}
		catch(Exception e)
		{
			
		}
		
		
		
	}
	
	public static void createRequiredTables() throws Exception
	{
		createUserTable();
		createRoomTable();
		createUserRoomMappingTable();
	}
	
	public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    
    public static void createUserTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
       

        String CreateQuery = "CREATE TABLE USERS(ID int primary key, FIRST_NAME varchar(255), LAST_NAME varchar(255), EMAIL varchar(255))";
        
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void createRoomTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
       

        String CreateQuery = "CREATE TABLE ROOMS(ID int primary key, ROOM_NAME varchar(255))";
        
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
    
    public static void createUserRoomMappingTable() throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
       

        String CreateQuery = "CREATE TABLE USER_ROOM_MAPPING(BOOKING_ID int primary key, USER_ID INT, ROOM_ID, BOOKING_START DATETIME , BOOKING_END DATETIME )";
        
        try {
            connection.setAutoCommit(false);
           
            createPreparedStatement = connection.prepareStatement(CreateQuery);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
           
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

}
