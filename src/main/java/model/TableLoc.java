package model;
import java.io.Serializable;

public class TableLoc implements Serializable{
	private static final long serialVersionUID = 1L;
	private int tableId;
	private String TableName;
	private int maxCapacity;

	public TableLoc() {
		super();
	}

	public int getTableId() {
		return tableId;
	}

	public String getTableName() {
		return TableName;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

}
