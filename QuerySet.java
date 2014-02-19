import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
public class QuerySet<C,D>{
	public Hashtable table;
	
	public QuerySet(){
		this.table = new Hashtable<C, HashSet<D>>();
	}
	
	public boolean add(C category, D data){
		HashSet categoryset = (HashSet) this.table.get(category);
		if(categoryset==null){
			this.table.put(category, new HashSet<D>());
			categoryset = (HashSet) this.table.get(category);
		}
		return categoryset.add(data);
	}
	
	public D get(C category){
		return (D) table.get(category);
	}
	
	public C[] getKeys(){
		return (C[]) table.keySet().toArray();
	}
	
	public HashSet[] getSets(){
		C[] keys = (C[]) table.keySet().toArray();
		HashSet[] sets = new HashSet[keys.length];
		for(int i = 0; i < keys.length; i++){
			sets[i] = (HashSet) table.get(keys[i]);
		}
		return sets;
	}
	
	public ArrayList getDots(){
		ArrayList dotlist = new ArrayList<Dot>();
		Set[] sets = this.getSets();
		for(Set s : sets){
			dotlist.addAll(s);
		}
		return dotlist;
	}
}