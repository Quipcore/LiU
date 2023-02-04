package labb5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Denna klass modellerar en ordlista (Dictionary). En ordlista associerar
 * termer med betydelser. En term kan mappas till flera betydelser. B�de term
 * och betydelse representeras med klassen Word.
 */
public class Dictionary {
	
	Map<Word,Set<Word>> Dictionary = new HashMap<Word, Set<Word>>();
	
	/**
	 * L�gger till termen t till ordlistan med betydelsen m. Om termen redan �r
	 * tillagd med angiven betydelse h�nder ingenting.
	 */
	public void add(Word t, Word m) {
		
		if(Dictionary.containsKey(t)) {
			Dictionary.get(t).add(m);
		}
		else {
			Dictionary.put(t,new HashSet<Word>());
			Dictionary.get(t).add(m);
		}
	}
	/**
	 * Bekv�mare s�tt att anropa add f�r 2 str�ngar �n add(Word, Word).
	 */
	public void add(String t, String m) { //TODO Implement word check.
		Word T = new Word(t);
		Word M = new Word(m);
		
		add(T,M);
	}

	/**
	 * Returnerar en icke-null m�ngd med ordlistans alla termer.
	 */
	public Set<Word> terms() {
		return Dictionary.keySet();
	}

	/**
	 * Sl�r upp och returnerar en m�ngd av betydelser till t, eller null om t inte
	 * finns i ordlistan.
	 */
	public Set<Word> lookup(Word t) {
		return Dictionary.get(t);
	}

	/**
	 * Skapar och returnerar en ny ordlista p� det motsatta spr�ket, dvs, alla
	 * betydelser blir termer och alla termer blir betydelser. T.ex. en
	 * svensk-engelsk ordlista blir efter invertering engelsk-svensk.
	 */
	public Dictionary inverse() {
		
		Dictionary InverseDic = new Dictionary();		
		for(Word w : terms()) {
			for(Word o : lookup(w)) {
				InverseDic.add(o,w);
			}
		}

		return InverseDic;
	}
	
	//--------------------------------------------------------------------
	//L�ser in orden fr�n den givna str�mmen och l�gger dessa i ordlistan.
	public void load(InputStream is) throws IOException {
		//Kanske borde �verskriva nuvarande dictionary?		
		String inString = new String(is.readAllBytes(), StandardCharsets.ISO_8859_1);
		
		String key = "";
		String value = "";
		boolean Where_to_write = true;
		for(int i = 0; i < inString.length(); i++) {
			if(inString.charAt(i) == ':') {
				Where_to_write = false;
			}
			else if(inString.charAt(i) == '\n') {
				Where_to_write = true;
				add(key, value);
				key = "";
				value = "";
			}
			else {
				if(Where_to_write) {
					key += inString.charAt(i);
				}else {
					value += inString.charAt(i);
				}
			}			
		}
	}
	
	//--------------------------------------------------------------------
	//Lagrar ordlistan p� den givna str�mmen.
	public void save(OutputStream os) throws IOException {
		
		String str;
		for(Word w : terms()) {
			for(Word o : lookup(w)) {
				str = w.toString() +":"+ o.toString() + "\n";
				os.write(str.getBytes());
			}
		}
	}
	
	//--------------------------------------------------------------------
	
	public void print() {
		for(Word w : terms()) {
			System.out.println(w.toString());
			for(Word o : lookup(w)) {
				System.out.println("     "+o.toString());
			}
		}
	}	
	
	public int size() {
		return terms().size();
	}
}