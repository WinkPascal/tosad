package domain.definer;

public class Relation {
	private String ownerTable;
	private String referencedTable;
	
	public Relation(String ownerTable, String referencedTable) {
		this.ownerTable = ownerTable;
		this.referencedTable = referencedTable;
	}

	public String getOwnerTable() {
		return ownerTable;
	}
	
	public String getReferencedTable() {
		return referencedTable;
	}
	
	public String toString() {
		return ownerTable+" references "+referencedTable;
	}
}
