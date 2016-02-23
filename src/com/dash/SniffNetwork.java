package com.dash;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import android.os.AsyncTask;

public class SniffNetwork extends AsyncTask<String, Void, Void>{

	protected Void doInBackground(String... params) {
		// Find the server using UDP broadcast
		try {
			// Open a random port to send the package
			DatagramSocket c = new DatagramSocket();
			c.setBroadcast(true);

			byte[] sendData = "DISCOVER_FUIFSERVER_REQUEST".getBytes();

			// Try the 255.255.255.255 first
			try {
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName("255.255.255.255"), 8888);
				c.send(sendPacket);
				System.out.println(">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
			} catch (Exception e) {
				System.out.println("Failed: " + e.getMessage());
			}

			System.out.println(">>> Done looping over all network interfaces. Now waiting for a reply!");

			// Wait for a response
			byte[] recvBuf = new byte[15000];
			DatagramPacket receivePacket = new DatagramPacket(recvBuf,recvBuf.length);
			c.receive(receivePacket);

			// We have a response
			System.out.println(">>> Broadcast response from server: "+ receivePacket.getAddress().getHostAddress());

			// Check if the message is correct
			String message = new String(receivePacket.getData()).trim();
			if (message.equals("DISCOVER_FUIFSERVER_RESPONSE")) {
				// DO SOMETHING WITH THE SERVER'S IP (for example, store it in
				// your controller)
				// Controller_Base.setServerIp(receivePacket.getAddress());
				System.out.println("Sucessfully Done!!");
			}

			// Close the port!
			c.close();
		} catch (IOException ex) {
			System.out.println("Failed: " + ex.getMessage());
		}
		return null;
	}

}
