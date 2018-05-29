package tools.commons;

import java.util.UUID;

public class ShortUuid {
	/*
	 * 返回一个8位的不重复的字符串
	 * */  	  
		public static String generateShortUuid() {
			 String[] chs = new String[] { "a", "b", "c", "d", "e", "f",  
	            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",  
	            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",  
	            "6", "7", "8", "9", "1", "0", "2", "3", "4", "5", "6", "7", "8",  
	            "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1",  
	            "2", "3", "4", "5" };    
		    StringBuffer shortBuffer = new StringBuffer();  
		    String uuid = UUID.randomUUID().toString().replace("-", "");  
		    for (int i = 0; i < 8; i++) {  
		        String str = uuid.substring(i * 4, i * 4 + 4);  
		        int x = Integer.parseInt(str, 16);  
		        shortBuffer.append(chs[x % 0x3E]);  
		    }  
		    return shortBuffer.toString();		  
		}  
}
