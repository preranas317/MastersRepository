package ttt.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board1")
public class Board1 {

	
	@Override
	public String toString() {
		return "Board1 [board_id=" + board_id + ", sarray="
				+ Arrays.toString(sarray) + ", value=" + value + "]";
	}

	//static int cnt=0;
    @Id
    @GeneratedValue
    private Integer board_id;
    

    public Integer getBoard_id() {
		return board_id;
	}
    
    
    
	public Board1() {
		super();
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
		this.sarray[i][j]="___";		
			}
		}
	//	this.board_id = ++cnt;
	}



	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}
	public String[][] getSarray() {
		return this.sarray;
	}
	public void setSarray(String[][] sarray) {
		this.sarray = sarray;
	}
	
	public String getValue(int i, int j) {
		return this.sarray[i][j];
	}
	public void setValue(int i,int j,String value) {
		this.sarray[i][j]=value;
	}
	
	String[][] sarray = new String[3][3];
    //private Integer i;
    private String value;
  //  private Integer j;


	public void setArray(int i, int j, String value) {
		this.sarray[i][j]=value;
		
	}
   
  
      

   
   
}