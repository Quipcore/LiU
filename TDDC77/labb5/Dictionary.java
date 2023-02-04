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
 * termer med betydelser. En term kan mappas till flera betydelser. Både term
 * och betydelse representeras med klassen Word.
 */
public class Dictionary {
	
	Map<Word,Set<Word>> Dictionary = new HashMap<Word, Set<Word>>();
	
	/**
	 * Lägger till termen t till ordlistan med betydelsen m. Om termen redan är
	 * tillagd med angiven betydelse händer ingenting.
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
	 * Bekvämare sätt att anropa add för 2 strängar än add(Word, Word).
	 */
	public void add(String t, String m) { //TODO Implement word check.
		Word T = new Word(t);
		Word M = new Word(m);
		
		add(T,M);
	}

	/**
	 * Returnerar en icke-null mängd med ordlistans alla termer.
	 */
	public Set<Word> terms() {
		return Dictionary.keySet();
	}

	/**
	 * Slår upp och returnerar en mängd av betydelser till t, eller null om t inte
	 * finns i ordlistan.
	 */
	public Set<Word> lookup(Word t) {
		return Dictionary.get(t);
	}

	/**
	 * Skapar och returnerar en ny ordlista på det motsatta språket, dvs, alla
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
	//Läser in orden från den givna strömmen och lägger dessa i ordlistan.
	public void load(InputStream is) throws IOException {
		//Kanske borde överskriva nuvarande dictionary?		
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
	//Lagrar ordlistan på den givna strömmen.
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