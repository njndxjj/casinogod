package com.casinogod.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;
import javapns.notification.transmission.PushQueue;

import org.apache.log4j.Logger;
import org.json.JSONException;

import com.casinogod.action.AuthorityInterceptor;

/**
 * A command-line test facility for the Push Notification Service.
 * <p>Example:  <code>java -cp "[required libraries]" javapns.test.NotificationTest keystore.p12 mypass 2ed202ac08ea9033665e853a3dc8bc4c5e78f7a6cf8d55910df230567037dcc4</code></p>
 * 
 * <p>By default, this test uses the sandbox service.  To switch, add "production" as a fourth parameter:</p>
 * <p>Example:  <code>java -cp "[required libraries]" javapns.test.NotificationTest keystore.p12 mypass 2ed202ac08ea9033665e853a3dc8bc4c5e78f7a6cf8d55910df230567037dcc4 production</code></p>
 * 
 * <p>Also by default, this test pushes a simple alert.  To send a complex payload, add "complex" as a fifth parameter:</p>
 * <p>Example:  <code>java -cp "[required libraries]" javapns.test.NotificationTest keystore.p12 mypass 2ed202ac08ea9033665e853a3dc8bc4c5e78f7a6cf8d55910df230567037dcc4 production complex</code></p>
 * 
 * <p>To send a simple payload to a large number of fake devices, add "threads" as a fifth parameter, the number of fake devices to construct, and the number of threads to use:</p>
 * <p>Example:  <code>java -cp "[required libraries]" javapns.test.NotificationTest keystore.p12 mypass 2ed202ac08ea9033665e853a3dc8bc4c5e78f7a6cf8d55910df230567037dcc4 sandbox threads 1000 5</code></p>
 * 
 * @author Sylvain Pedneault
 */
public class PushNotificationThread extends TestFoundation {

	/**
	 * Execute this class from the command line to run tests.
	 * 
	 * @param args
	 */
	
	private static Logger log = Logger.getLogger(PushNotificationThread.class); 
	
	public static void sendPush(String keystore, String password, boolean production, List<String> tokens, 
			boolean simulation,int threads,String text) {

		/* Initialize Log4j to print logs to console */
		configureBasicLogging();
		
		verifyKeystore(keystore, password, production);

		/* Push an alert */
	    pushSimplePayloadUsingThreads(keystore, password, production, tokens, simulation, threads);
//		pushSimplePayloadUsingQueue(keystore, password, production, tokens, simulation, threads,text);
		
	}


	public PushNotificationThread() {
	}


	/**
	 * Push a test notification to a device, given command-line parameters.
	 * 
	 * @param args
	 * @throws KeystoreException 
	 * @throws CommunicationException 
	 */
	private static void pushNotification(String[] args) throws CommunicationException, KeystoreException {
		String keystore = args[0];
		String password = args[1];
		String token=args[2];
		boolean production = args.length >= 4 ? args[3].equalsIgnoreCase("production") : false;
		boolean simulation = args.length >= 4 ? args[3].equalsIgnoreCase("simulation") : false;
		boolean complex = args.length >= 5 ? args[4].equalsIgnoreCase("complex") : false;
		boolean threads = args.length >= 5 ? args[4].equalsIgnoreCase("threads") : false;
//		int threadDevices = args.length >= 6 ? Integer.parseInt(args[5]) : 100;
		int threadThreads = args.length >= 6 ? Integer.parseInt(args[5]) : 10;
		boolean simple = !complex && !threads;

		verifyKeystore(keystore, password, production);

		if (simple) {			
			/* Push a test alert */
			List<PushedNotification> notifications = Push.test(keystore, password, production, token);
			printPushedNotifications(notifications);

		} else if (complex) {
			/* Push a more complex payload */
			List<PushedNotification> notifications = Push.payload(createComplexPayload(), keystore, password, production, token);
			printPushedNotifications(notifications);

		} else if (threads) {
			/* Push a Hello World! alert repetitively using NotificationThreads */
			
		}
	}


	/**
	 * Create a complex payload for test purposes.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Payload createComplexPayload() {
		PushNotificationPayload complexPayload = PushNotificationPayload.complex();
		try {
			// You can use addBody to add simple message, but we'll use
			// a more complex alert message so let's comment it
			complexPayload.addCustomAlertBody("My alert message");
			complexPayload.addCustomAlertActionLocKey("Open App");
			complexPayload.addCustomAlertLocKey("PokerKing Message: %@");
			ArrayList parameters = new ArrayList();
			parameters.add("Congratulations,\"test\" get the total bonus of 21");		
			complexPayload.addCustomAlertLocArgs(parameters);
			complexPayload.addBadge(45);
			complexPayload.addSound("default");
			complexPayload.addCustomDictionary("acme", "foo");
			complexPayload.addCustomDictionary("acme2", 42);
//			ArrayList values = new ArrayList();
//			values.add("value1");
//			values.add(2);
//			complexPayload.addCustomDictionary("acme3", values);
		} catch (JSONException e) {
			System.out.println("Error creating complex payload:");
			e.printStackTrace();
		}
		return complexPayload;
	}
	
	protected  static void pushSimplePayloadUsingQueue(String keystore, String password, boolean production, List<String> tokens, 
			final boolean simulation,final int threads,String text) {
		try {
			
			System.out.println("send push from gameserver queue");

			InputStream ks = new BufferedInputStream(new FileInputStream(keystore));
			
			PushQueue queue = Push.queue(ks, password, production, threads);
//			
			queue.start();
//						
			PushNotificationPayload payload = PushNotificationPayload.complex();
			
			payload.addBadge(20);
		
			payload.addAlert(text);
		
			for(int i=0;i<tokens.size();i++)
    			queue.add(payload, tokens.get(i));
	
			Thread.sleep(100);
			
			System.out.println("Starting all threads...");
			long timestamp1 = System.currentTimeMillis();

			List<PushedNotification> pushedNotifications = queue.getPushedNotifications();
			printPushedNotifications("push message:", pushedNotifications);
			
            long timestamp2 = System.currentTimeMillis();
			
			System.out.println("All threads finished in " + (timestamp2 - timestamp1) + " milliseconds");
			
			Thread.sleep(500);
			
			queue.clearPushedNotifications();
			
			pushedNotifications = queue.getPushedNotifications();
			printPushedNotifications("AFTER CLEAR:", pushedNotifications);
			
			try {
				
				List<Device> devices = Push.feedback(keystore, password, production);

				for (Device device : devices) {
					System.out.println("Inactive device: " + device.getToken());
				}
			} catch (CommunicationException e) {
				e.printStackTrace();
			} catch (KeystoreException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected  static void pushSimplePayloadUsingThreads(String keystore, String password, boolean production, List<String> tokens, final boolean simulation,final int threads) {
		try {
				
			log.info("Creating PushNotificationManager and AppleNotificationServer");
			
			final AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, password, production);
			System.out.println("Creating payload (simulation mode)");
			final Payload payload = PushNotificationPayload.alert("Hello World!");
//			final Payload payload=PushNotificationThread.createComplexPayload();
//			System.out.println("Generating " + devices + " fake devices");
			final List<Device> deviceList = new ArrayList<Device>(tokens.size());
			for (int i = 0; i < tokens.size(); i++) {
				String tokenToUse = tokens.get(i);
				deviceList.add(new BasicDevice(tokenToUse));
			}			
			
			new Thread(){
				
				public void run()
				{
					log.info("Creating " + threads + " notification threads");					
					NotificationThreads work = new NotificationThreads(server, simulation ? payload.asSimulationOnly() : payload, deviceList, threads);
					//work.setMaxNotificationsPerConnection(10000);
					System.out.println("Linking notification work debugging listener");
					work.setListener(DEBUGGING_PROGRESS_LISTENER);
					System.out.println("Starting all threads...");
					long timestamp1 = System.currentTimeMillis();
					work.start();
					log.info("All threads started, waiting for them...");
					try {
						work.waitForAllThreads();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long timestamp2 = System.currentTimeMillis();
					System.out.println("All threads finished in " + (timestamp2 - timestamp1) + " milliseconds");
					printPushedNotifications(work.getPushedNotifications());
				}
			}.start();
			
			Thread.sleep(10000);
		
			try {
				
				List<Device> devices = Push.feedback(keystore, password, production);

				for (Device device : devices) {
					log.info("Inactive device: " + device.getToken());
				}
			} catch (CommunicationException e) {
				e.printStackTrace();
			} catch (KeystoreException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * A NotificationProgressListener you can use to debug NotificationThreads.
	 */
	public static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {

		public void eventThreadStarted(NotificationThread notificationThread) {
			System.out.println("   [EVENT]: thread #" + notificationThread.getThreadNumber() + " started with " + notificationThread.getDevices().size() + " devices beginning at message id #" + notificationThread.getFirstMessageIdentifier());
		}


		public void eventThreadFinished(NotificationThread thread) {
			System.out.println("   [EVENT]: thread #" + thread.getThreadNumber() + " finished: pushed messages #" + thread.getFirstMessageIdentifier() + " to " + thread.getLastMessageIdentifier() + " toward " + thread.getDevices().size() + " devices");
		}


		public void eventConnectionRestarted(NotificationThread thread) {
			System.out.println("   [EVENT]: connection restarted in thread #" + thread.getThreadNumber() + " because it reached " + thread.getMaxNotificationsPerConnection() + " notifications per connection");
		}


		public void eventAllThreadsStarted(NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads started: " + notificationThreads.getThreads().size());
		}


		public void eventAllThreadsFinished(NotificationThreads notificationThreads) {
			System.out.println("   [EVENT]: all threads finished: " + notificationThreads.getThreads().size());
		}


		public void eventCriticalException(NotificationThread notificationThread, Exception exception) {
			System.out.println("   [EVENT]: critical exception occurred: " + exception);
		}
	};
	

	/**
	 * Print to the console a comprehensive report of all pushed notifications and results.
	 * @param notifications a raw list of pushed notifications
	 */
	public static void printPushedNotifications(List<PushedNotification> notifications) {
		List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
		List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
		int failed = failedNotifications.size();
		int successful = successfulNotifications.size();

		if (successful > 0 && failed == 0) {
			printPushedNotifications("All notifications pushed successfully (" + successfulNotifications.size() + "):", successfulNotifications);
		} else if (successful == 0 && failed > 0) {
			printPushedNotifications("All notifications failed (" + failedNotifications.size() + "):", failedNotifications);
		} else if (successful == 0 && failed == 0) {
			System.out.println("No notifications could be sent, probably because of a critical error");
		} else {
			printPushedNotifications("Some notifications failed (" + failedNotifications.size() + "):", failedNotifications);
			printPushedNotifications("Others succeeded (" + successfulNotifications.size() + "):", successfulNotifications);
		}
	}


	/**
	 * Print to the console a list of pushed notifications.
	 * @param description a title for this list of notifications
	 * @param notifications a list of pushed notifications to print
	 */
	public static void printPushedNotifications(String description, List<PushedNotification> notifications) {
		System.out.println(description);
		for (PushedNotification notification : notifications) {
			try {
				System.out.println("  " + notification.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
