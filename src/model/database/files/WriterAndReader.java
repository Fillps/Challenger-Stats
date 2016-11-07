package model.database.files;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WriterAndReader {
	
	
	public static <T> T read(String local){
		T t = null; 
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			t = readObject(ois);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
	    return (T)in.readObject();
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


	public static <T> T hackedRead(String local){
		T t = null;
		try {
			FileInputStream in = new FileInputStream(local);
			HackedObjectInputStream ois = new HackedObjectInputStream(in);
			t = readObject(ois);
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}

/**
 * http://stackoverflow.com/questions/2358886/how-can-i-deserialize-the-object-if-it-was-moved-to-another-package-or-renamed
 * problemas ao tentar ler uma classe que foi serializada depois de ser movida de diretorio
 */


class HackedObjectInputStream extends ObjectInputStream {

	public HackedObjectInputStream(InputStream in) throws IOException {
		super(in);
	}

	@Override
	protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
		ObjectStreamClass resultClassDescriptor = super.readClassDescriptor();

		if (resultClassDescriptor.getName().equals("database.data_structure.TrieExtended"))
			resultClassDescriptor = ObjectStreamClass.lookup(model.database.data_structure.TrieExtended.class);

		return resultClassDescriptor;
	}
}