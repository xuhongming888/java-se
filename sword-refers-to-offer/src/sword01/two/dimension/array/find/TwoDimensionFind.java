package sword01.two.dimension.array.find;

public class TwoDimensionFind {

    public boolean find(int target, int [][] array) {
		
    	int colNumber = array.length - 1;
    	int rowNumber = array[0].length;
    	
    	while(colNumber >= 0 && rowNumber < array[0].length) {
    		if(array[rowNumber][colNumber] > target) {
    			colNumber--;
    		}else if(array[rowNumber][colNumber] < target) {
    			rowNumber++;
    		}else {
    			return true;
    		}
    	}
    	return false;
    }	
    
    
	
}
