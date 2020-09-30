package com.codegym.task.task21.task2101;

/* 
1) The IP address and subnet mask are given. You need to calculate the network address: implement the getNetAddress method.
Use the bitwise AND operator.

Example:
IP address: 11000000 10101000 00000001 00000010 (192.168.1.2)
Subnet mask: 11111111 11111111 11111110 00000000 (255.255.254.0)
Network address: 11000000 10101000 00000000 00000000 (192.168.0.0)

2) Implement the print method, which will display the data in binary on the console.
If the IP address is 192.168.1.2, then "11000000 10101000 00000001 00000010" should be displayed
3) The main method is not tested

*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

	public static byte[] getNetAddress(byte[] ip, byte[] mask) {
	    byte[] netID = new byte[mask.length];
	    int id = 0;
	    int ipID = 0;
	    int masked = 0;
	    for(int i = 0; i<mask.length; i++){
	        id = mask[i] & 0xFF;
	        System.out.println("Id is: " + id);
	        if(id == 255){
	            netID[i] = ip[i];
	        }
	        if(id != 255){
	           ipID = ip[i] & 0xFF;
	           System.out.println("IPId is: " + ipID);
	           masked = ipID&id;
	           netID[i] = (byte)masked;
	           break;
	        }
	    }
        return netID;
    }

	public static void print(byte[] bytes) {
	   StringBuilder sb  = new StringBuilder();
	   for(byte b : bytes){
	       String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
	       sb.append(s1 + " ");
	   }
		System.out.println(sb.toString().trim());
		}
}
