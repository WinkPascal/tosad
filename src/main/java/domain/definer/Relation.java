package domain.definer;

public class Relation {
	private String ownerTable;
	private String referencedTable;
	private String ownerColumn;
	
	public Relation(String ownerTable, String ownerColumn, String referencedTable) {
		this.ownerTable = ownerTable;
		this.ownerColumn = ownerColumn;
		this.referencedTable = referencedTable;
	}
	
	public String getOwnerColumn() {
		return ownerColumn;
	}

	public String getOwnerTable() {
		return ownerTable;
	}
	
	public String getReferencedTable() {
		return referencedTable;
	}
	
	public String toString() {
		return ownerTable+"("+ownerColumn+") references "+referencedTable;
	}
}
