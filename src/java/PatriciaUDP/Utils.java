package PatriciaUDP;

public class Utils {
    
	public static byte[] getDPBytes(String smac) {
		byte[] macBytes = getMacBytes(smac);
		byte[] bytes = new byte[6 + 16 * macBytes.length];
		for (int i = 0; i < 6; i++) {
			bytes[i] = (byte) 0xff;
		}
		for (int i = 6; i < bytes.length; i += macBytes.length) {
			System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
		}

		return bytes; 
        
	}
    
		private static byte[] getMacBytes(String smac) throws IllegalArgumentException {
			byte[] bytes = new byte[6];
			String[] hex = smac.split("(\\:|\\-)");
			if (hex.length != 6) {
				throw new IllegalArgumentException("Invalid MAC address.");
			}
			try {
				for (int i = 0; i < 6; i++) {
					bytes[i] = (byte) Integer.parseInt(hex[i], 16);
				}
			}
			catch (NumberFormatException e) {
				throw new IllegalArgumentException("Invalid hex digit in MAC address.");
			}
			return bytes;
		}

	public static int Fibonacci(int n){
		if (n == 1 || n == 2)
			return 1;

		return Fibonacci(1, 1, n, 2); 
	}

	public static int Fibonacci(int n1, int n2, int n3, int c){
		if (c < n3)
			return Fibonacci(n2, n1+n2, n3, 1+c);

		return n1+n2;
	}	
}
