package connections;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import be.kuleuven.cs.som.annotate.*;
import java.util.*;
import java.util.regex.*;
public class Location {

	/**
	 * @invar  Each location can have its coordinate as coordinate.
	 *       | canHaveAsCoordinates(this.getCoordinates())
	 * @invar  The address of each location must be a valid address for any
	 *         location.
	 *       | isValidAddress(getAddress())
	 */
//******************************************************************************************//
/**
 * Initialize this new location with given coordinate and given address.
 * 
 * @param  coordinates
 *         The coordinate for this new location.
 * @param  address
 *         The address for this new location.
 * @pre    This new location can have the given coordinate as its coordinate.
 *       | canHaveAsCoordinates(coordinates)
 * @post   The coordinate of this new location is equal to the given
 *         coordinate.
 *       | new.getCoordinates() == coordinates 
 * @post   If the given address is a valid address for any location,
 *         the address of this new location is equal to the given
 *         address. Otherwise, the address of this new location is equal
 *         to "Empty".
 *       | if (isValidAddress(address))
 *       |   then new.getAddress() == address
 *       |   else new.getAddress() == "Null"
 */

public Location(double[] coordinates, String address) {
	assert this.canHaveAsCoordinates(coordinates);
	this.coordinates = coordinates.clone();
		setAddress(address);
}
/**
 * Variable registering the state of the object
 */
private boolean isTerminated = false;
/**
 * Variable registering the coordinate of this location.
 */
private double[] coordinates;
/**
 * Variable registering the address of this location.
 */
private String address;
/**
 * A HashSet registering the adjoining roads for a given location
 */
Collection<Road>adjoiningRoads = new HashSet<Road>();

/**
 * A method to return the value of the isTerminated variable;
 */

@Basic @Raw
public boolean isTerminated() {
	return this.isTerminated;
}

/**
 * A method to terminate a Location object
 * 
 * @post The location is terminated
 * 		 All roads having this location are also terminated
 * 		|for each road in adjoiningRoads
 * 		|road.terminate()
 */

public void terminate() {
	if(this.isTerminated() == false) {
		Collection<Road>cloneRoads = new HashSet<Road>();
		cloneRoads.addAll(this.adjoiningRoads);
		for(Road road:cloneRoads) {
			road.terminate();
		}
	this.adjoiningRoads.clear();
	this.isTerminated = true;
	}
}
//***********************************************************************************************//
// COORDINATES
/**	
 * Return the coordinate of this location.
 */
@Basic @Raw @Immutable
public double[] getCoordinates() {
	double[] returncoordinate = new double[]{coordinates[0],coordinates[1]};
	return returncoordinate;
}

/**
 * Check whether this location can have the given coordinate as its coordinate.
 *  
 * @param  coordinates
 *         The coordinate to check.
 * @return False if the coordinates are indefinite or if they have length < or > 2
 *       | if(coordinates.length !=2) | coordinates[0] | coordinates[1] > infinity or lesser than infinity
 *       |result == false
 * @return True otherwise
 * 		 | else result == true
*/
@Raw
public boolean canHaveAsCoordinates(double[] coordinates) {
	if(coordinates.length == 2) {
		if(coordinates[0]< Double.POSITIVE_INFINITY && coordinates[0] >Double.NEGATIVE_INFINITY) {
			if(coordinates[1]< Double.POSITIVE_INFINITY && coordinates[1] >Double.NEGATIVE_INFINITY) {
				return true;
			}
		}
	}
	return false;
}

//*************************************************************************************//
// ADDRESS

/**
 * Return the address of this location.
 */
@Basic @Raw
public String getAddress() {
	return this.address;
}

/**
 * Check whether the given address is a valid address for
 * any location.
 *  
 * @param  address
 *         The address to check.
 * @return True if the address is longer than 2, is not null and contains only letters,digits,spaces and commas
 *       | if(address !=null && adress.length >2){
 *       | for(each character in address){
 *       |if(character.isDigit() | character.isLetter() | character == ',' | character == ' ')}
 *       |result == true
 *       Otherwise return false
 *       |result == false
*/

	public static boolean isValidAddress(String address) {
		if (address==null)
			return false;
		if( address != null ) {
		if (! Character.isUpperCase(address.charAt(0)))
			return false;
		if (address.length() <2 )
			return false;
		for (int index=1; index < address.length(); index++)
			if ( (! Character.isLetter(address.charAt(index))) &&
				 (! Character.isDigit(address.charAt(index))) &&
				 (address.charAt(index) != ' ') &&
				 (address.charAt(index) != ',') )
				return false;}
		return true;
	}
	
/**
 * Set the address of this location to the given address.
 * 
 * @param  address
 *         The new address for this location.
 * @post   If the given address is a valid address for any location,
 *         the address of this new location is equal to the given
 *         address.
 *       | if (isValidAddress(address))
 *       |   then new.getAddress() == address
 *       If the address is not valid, then set the address to null
 *       |else
 *       |new.getAddress() == "Null"
 */
	
@Raw
public void setAddress(String address) {
	if (isValidAddress(address))
		this.address = address;
	else 
		this.address = "Null";
}

//*****************************************************************************************************//
//ADJOINING ROADS

/**	
 * Return the coordinate of this location.
 */
public Collection<Road> getAllAdjoiningRoads(){ 
return new ArrayList<Road>(this.adjoiningRoads); 
		  }
	  

/**
* 
* @param road
* 		  The road to be added to the list of adjoining roads 
* @pre   The road to be added is verified to be an adjoining road for this location and is not terminated
* 		  |if(this.isAdjoiningRoad(road) && (!road.isTerminated())
* @post  The road is added to the list of adjoining roads for the given location
* 		  |new.getAdjoiningRoads() = getAdjoiningRoads() + 1
*/

	public void addAdjoiningRoad(Road road) {
		if(this.isAdjoiningRoad(road) && (!road.isTerminated())) {
			adjoiningRoads.add(road);
		}
	}
	
	/**
	 * 
	 * @param road 
	 * 		  The road to be checked
	 * @return True if the road is indeeed an adjoining road and is not terminated
	 * 		  |if(this.isAdjoiningRoad(road) && road.isTerminated() == false)
	 * 		  |result == true
	 * 		   False otherwise
	 * 		  | else
	 * 		  |result == false
	 */	
	public boolean canHaveAsAdjoiningRoad(Road road) {
		if(this.isAdjoiningRoad(road) && road.isTerminated() == false) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param road
	 * 		  The road to be checked
	 * @return
	 * 		  True if the road is indeed an adjoining road
	 * 		  |if(this == road.getEndpoints()[0]||this == road.getEndpoints()[1])
	 * 		  |result ==true

	 * @throws IllegalArgumentException
	 * 		   If the road is not an adjoining road
	 */

	
	public boolean isAdjoiningRoad(Road road) throws IllegalArgumentException {
		if(this == road.getEndpoints()[0]||this == road.getEndpoints()[1]) {
			return true;
		}
		throw new IllegalArgumentException();
	}
}
