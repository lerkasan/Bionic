import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkExercise {
	
	private NetworkExercise() {
	}

	public static void main(String[] args) throws Exception {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			URL ukrstat = new URL("http://ukrstat.gov.ua/operativ/operativ2016/fin/rp/selo/selo_1_2016.zip"); 
			URLConnection conn = ukrstat.openConnection();
			in = conn.getInputStream(); 
			out = new FileOutputStream("C:\\Job\\27xl.zip");
			int input;
			while ((input = in.read()) != -1) {
				out.write(input);
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) { 
			e.printStackTrace();
		} 
        finally { 
        	if (in != null) {
        		in.close();
        	}
        	if (out != null) {
        		out.close();
        	}
        }
		System.out.println("Done");
	}

}
