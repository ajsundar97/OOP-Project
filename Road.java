package connections;
import java.util.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * @invar The identification of each road must be valid identification for any road
 * 		|isValidIdentification(getIdentification)
 * @invar The length of the road must be a valid length for any road
 * 		|isValidLength(getLength)
 * @invar The endpoints of each road must be valid endpoints for any road
 * 		|canHaveasEndpoints(getEndpoints)
 * @invar  The speedLimit of each speed limit must be a valid speed limit for any
 *         road.
 *       | isValidSpeedLimit(getspeedLimit())
 * @invar  The averageSpeed of each road must be a valid averageSpeed for any
 *         road.
 *       | isValidAverageSpeed(getAverageSpeed())
 ** @invar  The delay of each road must be a valid delay for any
 *         road.
 *       | isValidDelay(getDelay())
 *6
 *  
 * @author Ajay Sundaresan
 * @author Ubaid ur Rehman
 * A class of roads containing identification for each road, end points, length
 * speed, current delay and blocked status
 * 
 *
 */

public abstract class Road {
/**
 * Initialize the road with the given characteristics
 * @param identification
 * 		The new identification for the road
 * @param endpoint1 
 * 		One of the endpoints of the road
 * @param endpoint2
 * 		One of the endpoints of the road
 * @param length
 * 		The length of the road
 * @param speedLimit
 * 		The speed limit of the road
 * @param averageSpeed
 * 		The average speed of vehicles on the road
 * @post The identification of this new road is equal to the given identification
 *		|new.getIdentification = identification
 * @post The endpoints of the new road are equal to the given endpoints
 * 		|new.getEndpoints = endpoints
 * @post The length of this new road is equal to the given length
 *		|new.getLength = length
 * @post The speed limit of this new road is equal to the given speed limit
 *		|new.getSpeedLimit = speedLimit
 * @post The average speed of this new road is equal to the given average speed
 *		|new.getAverageSpeed = averageSpeed
 */	 
public Road(String identification, Location l1, Location l2, int length ,float speedLimit, float averageSpeed){
	this.setIdentification(identification);
	assert this.canHaveAsEndpoints(l1);
	assert this.canHaveAsEndpoints(l2);
	this.endpoint1 = l1;
	this.endpoint2 = l2;
	this.setlength(length);
	this.setSpeedLimit(speedLimit);
	this.setAverageSpeed(averageSpeed);
	listofroads.add(this);
	l1.addAdjoiningRoad(this);
	l2.addAdjoiningRoad(this);
	}

@Basic @Raw
public boolean isTerminated() {
	return this.isTerminated;
}

public void terminate() {
	if(this.isTerminated() == false) {
	this.getEndpoints()[0].adjoiningRoads.remove(this);	
	this.getEndpoints()[1].adjoiningRoads.remove(this);
	idArray.remove(this.getIdentification());
	this.isTerminated = true;
	}
}
//*************************************************************************************//
// VARIABLES

/**
 * Variable registering the identifiation of this Road.
 */
private String identification;
/**
 * ArrayList containing the list of identification numbers in use
 */
static ArrayList<String> idArray = new ArrayList<String>();
/**
 * Variable registering the length of this road.
 */
private int length;
/**
 * Variable registering one of the endpoints of this road.
 */
protected Location endpoint1;
/**
 * Variable registering one of the endpoints of the road
 */
protected Location endpoint2;/**
 * Variable registering the speed limit of this road.
 */
private float speedLimit =19.5F;
public static final float SPEED_OF_LIGHT = 299792458.0F;
/**
 * Variable registering the averageSpeed of this road.
 */
private float averageSpeed;
/**
 * Variables registering the delay of this road.
 */
private float delay1 = 0;
private float delay2 = 0;
/**
 * Variable registering the direction of blockage or delay
 */
boolean direction;
/**
 * Variables registering whether the roads has been blocked or not
 */
boolean isBlocked1;
boolean isBlocked2;

private boolean isTerminated = false;
static Collection<Road>listofroads = new ArrayList<Road>();

// ********************************************************************************//
// IDENTIFICATION

/**
 * Return the identifiation of this Road.
 */
@Basic @Raw
public String getIdentification() {
	return this.identification;
}

/**
 * Check whether the given identifiation is a valid identifiation for
 * any Road.
 *  
 * @param  identifiation
 *         The identifiation to check.
 * @return True if the identification is of acceptable size and format
 * 		   result = true
 * 		| if(isValidFormat(identification) && isValidSize(identification))
 *		  if(!idArray.contains(identification)) 		
 *       
*/
public static boolean isValidIdentification(String identification) {
	// the second character may contain special characters as well, so just check the first??
	if(isValidFormat(identification) && isValidSize(identification)){
		if(!idArray.contains(identification)) {
			return true;
		}
	}
	return false;
}

/**
 * 
 * @param identification
 * 			|The identification to check
 * @return
 * 			True if the identification is of valid size
 * 			result = true
 * 			|if(identification.length() >=getMinLength() | identification.length() <=getMaxLength()) 
 */
public static boolean isValidSize(String identification) {
	if(identification.length() >=getMinLength() | identification.length() <=getMaxLength()) {
		return true;
	}
	return false;
}

/**
 * 
 * @param identification
 * 			The identification to check
 * @return result = true
 * 		   |if the identification is of valid format
 */
public static boolean isValidFormat(String identification) {
	int len = identification.length();
	if(len == 2) {
	char first = identification.charAt(0);
	char second = identification.charAt(1);
	if(Character.isLetter(first)&& Character.isUpperCase(first)) {
		if(Character.isDigit(second)) {		
				return true;
			}
		}
	}
	else if(len ==3) {
		char first = identification.charAt(0);
		char second = identification.charAt(1);
		char third = identification.charAt(2);
		if(Character.isLetter(first)&& Character.isUpperCase(first)) {
			if(Character.isDigit(second) && Character.isDigit(third)) {		
					return true;
				}
			}
		}
	
	return false;}

/**
 * Set the identifiation of this Road to the given identifiation.
 * 
 * @param  identification
 *         The new identifiation for this Road.
 * @post   The identifiation of this new Road is equal to
 *         the given identifiation.
 *       | new.getIdentification() == identification
 * @throws IllegalArgumentException
 *         The given identifiation is not a valid identifiation for any
 *         Road.
 *       | ! isValidIdentification(getIdentification())
 */
@Raw
public void setIdentification(String identification) 
		throws IllegalArgumentException {
	if (isValidIdentification(identification)) {
		this.identification = identification;
		idArray.add(identification);}
	else {
	throw new IllegalArgumentException();
}}

/**
 * This method removes the identification of the old road
 * sets the new identification
 * @param id
 * @return The new identification number of the road. 
 * remove old identification from the arraylist. then check if the new identification number is valid.
 * then set the new identification number
 * 		|new.getIdentification = id
 * 			
 * 
 */
public void changeIdentification(String id) throws IllegalArgumentException {
	//DO THE DOCUMENTATION
	idArray.remove(this.getIdentification());
	this.setIdentification(id);
}

/**
 * 
 * @return
 * 		The maximum length of the identification string
 */
public static int getMaxLength() {
	return 3;
}

/**
 * 
 * @return
 * 		The minimum length of the identification string
 */
public static int getMinLength() {
	return 2;
}

//**********************************************************************************************//
// LENGTH

/**
 * Return the length of this road.
 */
@Basic @Raw
public int getLength() {
	return this.length;
}

/**
 * Check whether the given length is a valid length for
 * any road.
 *  
 * @param  length
 *         The length to check.
 * @return 
 *       | result == length>0
*/
public static boolean isValidLength(int length) {
	if(length>0) {
		return true;
	}
	return false;
}

/**
 * Set the length of this road to the given length.
 * 
 * @param  length
 *         The new length for this road.
 * @post   If the given length is a valid length for any road,
 *         the length of this new road is equal to the given
 *         length.
 *       | if (isValidLength(length))
 *       |   then new.getLength() == length
 */
@Raw 
public void setlength(int length) {
	if (!isValidLength(length)) {
		 this.length = 100;}
	else {
	this.length = length;}
}

//******************************************************************************************//
// ENDPOINTS

/**
 * Return the endpoints of this road.
 * Modify the endpoints so that they can take a location object as input and use location.getPoint() to transfer the coordinates into the endpoints
 */
@Basic @Raw @Immutable
public Location[] getEndpoints() {
	Location[]endpoint = new Location[2];
	endpoint[0] = this.endpoint1;
	endpoint[1] = this.endpoint2;
	return endpoint;
}

/**
 * Check whether this road can have the given endpoint as its endpoints.
 *  
 * @param  endpoint
 *         The endpoints to check.
 * @return True when the endpoints are between 0 and 70
 *       | 
*/
@Raw
public boolean canHaveAsEndpoints(Location endpoint) {
	if(endpoint.getCoordinates().length ==2) {
	if(endpoint.getCoordinates()[0]>=getMinEndpoint() && endpoint.getCoordinates()[1]>=getMinEndpoint()) {
		if(endpoint.getCoordinates()[0] <= getMaxEndpoint() && endpoint.getCoordinates()[1] <=getMaxEndpoint()) {
					return true; 
				}
			}}
	return false;
}

/**
 * 
 * @return	The highest possible value for the endpoints
 * 			|return 70
 */
public static double getMaxEndpoint() {
	return 70.0;
}

/**
 * 
 * @return	The lowest possible value for the endpoints
 * 			|return 0
 */
public static double getMinEndpoint() {
	return 0.0;
}

//*********************************************************************************************//
//SPEED LIMIT

/**
 * Return the speed limit of this speedLimit.
 */
@Basic @Raw
public float getSpeedLimit() {
	return this.speedLimit;
}

/**
 * Check whether the given speed limit is a valid speed limit for
 * any road.
 *  
 * @param  speedLimit
 *         The speedLimit to check.
 * @return 
 *       | result == true
 *       if(speedLimit > 0 && speedLimit <= SPEED_OF_LIGHT)
*/
public static boolean isValidSpeedLimit(float speedLimit) {
	if(speedLimit > 0 && speedLimit <= SPEED_OF_LIGHT) {
		return true;
	}
	return false;
}

/**
 * Set the speed limit of this road to the given speed limit.
 * 
 * @param  speedLimit
 *         The new speed limit for this road.
 * @post   The speed limit of this new road is equal to
 *         the given speed limit.
 *       | new.getSpeedLimit() == speedLimit
 * @throws IllegalArgumentException
 *         The given speed limit is not a valid speed limit for any
 *         road.
 *       | ! isValidSpeedLimit(getSpeedLimit())
 */
@Raw
public void setSpeedLimit(float speedLimit) 
		throws IllegalArgumentException{
	if (isValidSpeedLimit(speedLimit) && speedLimit>this.getAverageSpeed()) {
		this.speedLimit = speedLimit;
		}
	else {
	throw new IllegalArgumentException();}
}

//***********************************************************************************//
//AVERAGE SPEED

/**
 * Return the averageSpeed of this road.
 */
@Basic @Raw
public float getAverageSpeed() {
	return this.averageSpeed;
}

/**
 * Check whether the given averageSpeed is a valid averageSpeed for
 * any road.
 *  
 * @param  averageSpeed
 *         The averageSpeed to check.
 * @return 
 *       | result == averageSpeed>=0
*/
public static boolean isValidAverageSpeed(float averageSpeed) {
	if(averageSpeed >= 0) {
		return true;
	}
	return false;
}

/**
 * Set the averageSpeed of this road to the given averageSpeed.
 * 
 * @param  averageSpeed
 *         The new averageSpeed for this road.
 * @post   The averageSpeed of this new road is equal to
 *         the given averageSpeed.
 *       | new.getAverageSpeed() == averageSpeed
 * @throws IllegalArgumentException
 *         The given averageSpeed is not a valid averageSpeed for any
 *         road.
 *       | ! isValidAverageSpeed(getAverageSpeed())
 */
@Raw
public void setAverageSpeed(float averageSpeed) 
		throws IllegalArgumentException {
	if (isValidAverageSpeed(averageSpeed) &&  averageSpeed <=this.getSpeedLimit()) {
		this.averageSpeed = averageSpeed;}
	else {throw new IllegalArgumentException();}
}

//*****************************************************************************************//
//DELAYS

/**
 * Return the delay of this road.
 */
@Basic @Raw
public float getDelay(boolean direction) {
	if(direction == true) {
	return this.delay1;}
	return this.delay2;
}

/**
 * Check whether the given delay is a valid delay for
 * any road.
 *  
 * @param  delay
 *         The delay to check.
 * @return 
 *       | result == delay>0
*/
public static boolean isValidDelay(float delay) {
	if(delay >0)
		return true;
	return false;
}

/**
 * Set the delay of this road to the given delay.
 * 
 * @param  delay
 *         The new delay for this road.
 * @pre    The given delay must be a valid delay for any
 *         road.
 *       | isValidDelay(delay)
 * @post   The delay of this road is equal to the given
 *         delay.
 *       | new.getDelay() == delay
 */
@Raw
public void setDelay(float delay,boolean direction) {
	assert isValidDelay(delay);
	if(direction == true) {
	this.delay1 = delay;}
	else {
		this.delay2 = delay;}
	}

//*************************************************************************************//
//BLOCKED

/**
 * 
 * @param direction
 * 			The direction in which the road is blocked
 * @return	
 * 			The blockage status of the road
 */
public boolean getIsBlocked(boolean direction) {
	if(direction == true)
	{return this.isBlocked1;}
	else {
	return this.isBlocked2;}
}

// true - forward direction, false - backward direction. true - is blocked, false - is not blocked

/**
 * 
 * @param isBlocked
 * 			Blockage status of the road
 * @param direction
 * 			The direction in which the road is blocked
 * @post	
 * 			new.getBlocked(direction) = isBlocked
 */
public void setIsBlocked(boolean isBlocked,boolean direction) {
	if(direction == true) {
	this.isBlocked1 = isBlocked;}
	else {isBlocked2 = isBlocked;}
}

/**
 * Method to print the instance variables to the output stream
 */
public String toString() {
	return "The road " + this.getIdentification() + " has end points at " + this.getEndpoints()+" has a length of "+this.getLength()+" a speed limit of "+this.getSpeedLimit()+" and an average speed of "+this.getAverageSpeed();		
}

/**
 * 
 * @param pointsArray
 * A method to print a double array to the output stream
 * @return 
 */
public static void printArray(double[][] pointsArray){
	for(int i = 0;i<pointsArray.length;i++) {
		for(int j = 0;j<pointsArray[0].length;j++) {
			System.out.print(pointsArray[i][j]+" ");
		}
	}
}
//make new methods to return starting and ending locations of a road.
//Check the type of road and then return array of appropriate locations
/**
 * Function returning the possible starting locations of a given road
 * @return
 * 		| Location array of length 1 or 2
 */
public abstract Location[] getStartLocations();
/**
 * Function returning the possible ending locations of a given road
 * @return
 * 		| Location array of length 1 or 2
 */
public abstract Location[] getEndLocations();
/**
 * Function to reverse the direction of traversal of an alternating road
 */
public abstract void reverseTraversalDirection();
/**
 * Function to check if a given road is traversable
 * @param direction
 * @return
 * 		| result == true if road is traversable
 * 		| result == false if not
 */
public abstract boolean isTraversable(boolean direction);
/**
 * returns endpoint of road that does not match the tracker 
 * @param tracker
 * @return
 * 		|if(tracker == this.getEndpoints()[1])
 * 		|result == this.getEndpoints()[0]
 * 		|else
 * 		|result == this.getEndpoints()[1]
 */
public abstract Location getOtherLocation(Location tracker);
/**
 * checks if the new segment matches the end location of segment at the end of the route
 * @param other
 * @return
 * 		|result == true if other == this.getStartLocation()[0]
 * 		|else return == false
 */
public abstract Boolean isValidAddLocation(Location other);
}