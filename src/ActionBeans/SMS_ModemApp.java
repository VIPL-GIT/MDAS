/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActionBeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;

/**
 *
 * @author dell
 */
public class SMS_ModemApp implements SerialPortEventListener, CommPortOwnershipListener {

    public static String comPort = "COM11"; // This COM Port must be connected with GSM Modem  
    public CommPortIdentifier portId = null;
    public Enumeration portList;
    public InputStream inputStream = null;
    public OutputStream outputStream = null;
    public SerialPort serialPort;
    String readBufferTrial = "";
    static String smsStatus="";
    public ArrayList<String> arrlist = new ArrayList<String>();

    /**
     * Creates a new instance of GsmSampleCode
     */
    public SMS_ModemApp(String comm) {
        this.comPort = comm;
    }

    public SMS_ModemApp() {

    }
    
    public ArrayList<String> initialize() {
        ArrayList<String> portLists = new ArrayList<String>();
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                String portName = portId.getName();
                // System.out.println("port name = "+portName);
                portLists.add(portName);
                if (portId.getName().equals(comPort)) {
                    System.out.println("Got PortName " + comPort);

//					System.out.println("portLists size = " + portLists.size());
//					for (String ports : portLists) {
//						System.out.println("ports = " + ports);
//					}
                    return portLists;
                }
            }
        }
        return portLists;
    }

    public boolean init() {
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {

            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                //adding port names to the arraylist 
                arrlist.add(portId.getName());
//                System.out.println("port name = " + portId.getName());
                if (portId.getName().equals(comPort)) {
                    System.out.println("Got PortName = " + comPort);
//                    System.out.println("port list length = "+arrlist.size());
//                    int abc=0;
//                    for(String portss : arrlist){
//                        System.out.println("portss = "+abc+" ==> "+portss);
//                        abc++;
//                    }
                    return true;
                }
            }
        }

        return false;
    }

    public void checkStatus() throws InterruptedException {
//        System.out.println("in check status before send status command");
        send("AT\r\n");
        Thread.sleep(5000);
        send("AT+CSQ\r\n");
        Thread.sleep(5000);
//        System.out.println("after send status command");
    }

    public void send(String cmd) {
        try {
            System.out.println("command = " + cmd);
            outputStream.write(cmd.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String phoneNumber, String message) {
        char quotes = '"';
        send("AT+CMGS=" + quotes + phoneNumber + quotes + "\r\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { 
            e.printStackTrace();
        }
        send(message + '\032');
        
        if(smsStatus.contains(">")){
        System.out.println("Message Sent");
//        System.out.println("------>"+smsStatus);
        }
        else{
        System.out.println("Message failed");
        }
//        String response = smsStatus;
        
        return smsStatus;
    }

    public void connect() throws NullPointerException {
        if (portId != null) {
            try {
                portId.addPortOwnershipListener(this);
                serialPort = (SerialPort) portId.open("MobileGateWay", 2000);
                serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
//                System.out.println("end of serial port configurations");
            } catch (PortInUseException | UnsupportedCommOperationException e) {
                e.printStackTrace();
            }

            try {
                inputStream = serialPort.getInputStream();
                outputStream = serialPort.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                serialPort.addEventListener(this);
                serialPort.notifyOnDataAvailable(true);
                serialPort.notifyOnRingIndicator(true);
            } catch (TooManyListenersException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("COM Port not found!!");
        }
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
            case SerialPortEvent.DATA_AVAILABLE:

                byte[] readBuffer = new byte[2048];
                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);

                        if ((readBuffer.toString()).contains("RING")) {
//                            System.out.println("Enter Inside if RING Loop");
                        }
                    }
                    smsStatus = new String(readBuffer);
//                    String response = new String(readBuffer);
//                    String data = response.substring(0, 10);
//                    System.out.println(data);
                } catch (IOException e) {
                }
                break;
        }
    }

    public void outCommand() {
        System.out.print(readBufferTrial);
    }

    public void ownershipChange(int type) {
        switch (type) {
            case CommPortOwnershipListener.PORT_UNOWNED:
//                System.out.println(portId.getName() + ": PORT_UNOWNED");
                break;
            case CommPortOwnershipListener.PORT_OWNED:
//                System.out.println(portId.getName() + ": PORT_OWNED");
                break;
            case CommPortOwnershipListener.PORT_OWNERSHIP_REQUESTED:
//                System.out.println(portId.getName() + ": PORT_INUSED");
                break;
        }
    }

    public void closePort() {
        serialPort.close();
    }

    public static void main(String[] args) {

        SMS_ModemApp gsm = new SMS_ModemApp(comPort);
        if (gsm.init()) {
            try {
//                System.out.println("Initialization Success");
                gsm.connect();
                Thread.sleep(5000);
                gsm.checkStatus();
                gsm.sendMessage("8121447021", "$123456DPIP");
                gsm.closePort();
                // gsm.outCommand();
//                System.out.println("end of the gsm programming");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Can't init this Modem");
        }
    }
}
