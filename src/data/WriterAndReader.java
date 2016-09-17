package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WriterAndReader {
	
	public static <T> T read(String local){
		T t = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			t = (T) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> void write(T file, String local){
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(file);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readTxt(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
