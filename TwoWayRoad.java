package connections;
/**
 * Class of One Way Roads
 * @author Ajay Sundaresan
 ***
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
 */


public class TwoWayRoad extends Road {
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

	public TwoWayRoad(String identification, Location l1, Location l2, int length, float speedLimit,
			float averageSpeed) {
		super(identification, l1, l2, length, speedLimit, averageSpeed);
	}
	/**
	 * 
	 * @param direction
	 * 			The direction in which the road is blocked
	 * @return	
	 * 			The blockage status of the road
	 */
@Override
public boolean getIsBlocked(boolean direction) {
	return super.getIsBlocked(direction);
}
/**
 * 
 * @param isBlocked
 * 			Blockage status of the road
 * @param direction
 * 			The direction in which the road is blocked
 * @post	
 * 			new.getBlocked(direction) = isBlocked
 */
@Override 
public void setIsBlocked(boolean isBlocked,boolean direction) {
	super.setIsBlocked(isBlocked,direction);
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
@Override
public void setDelay(float delay,boolean direction) {
	super.setDelay(delay,direction);
}
/**
 * Return the delay of this road.
 */
@Override 
public float getDelay(boolean direction) {
	return super.getDelay(direction);
}
/**
 * Function returning the possible starting locations of a given road
 * @return
 * 		| Location array of length 2
 */
@Override
public Location[] getStartLocations() {
	return new Location[] {this.getEndpoints()[0],this.getEndpoints()[1]};
}
/**
 * Function returning the possible ending locations of a given road
 * @return
 * 		| Location array of length 2
 */
@Override
public Location[] getEndLocations() {
	return new Location[] {this.getEndpoints()[0],this.getEndpoints()[1]};
}
/**
 * Reverses the direction of travel
 * @throws IllegalArgumentException
 * 		   The direction for this type of road cannot be reversed
 */
@Override
public void reverseTraversalDirection() throws IllegalArgumentException {
	throw new IllegalArgumentException();
}
/**
 * returns if road is traversable in the forward direction
 * @return True
 * 		   |if(getIsBlocked(true))
 */
@Override
public boolean isTraversable(boolean direction) {
	if(getIsBlocked(true) == true) {
		return false;
	}
	return true;
}
/**
 * returns endpoint of road that does not match the tracker 
 * @param tracker
 * @return
 * 		|if(tracker == this.getEndpoints()[1])
 * 		|result == this.getEndpoints()[0]
 * 		|else
 * 		|result == this.getEndpoints()[1]
 * @throws IllegalArgumentException 
 * 		if both endpoints do not match
 * 		|if(!tracker == (this.getEndpoints()[0]&& !tracker == (this.getEndpoints()[1])
 */
@Override
public Location getOtherLocation(Location tracker) {
	if(tracker == (this.getEndpoints()[0])){
		return this.getEndpoints()[1];
	}
	else if(tracker == (this.getEndpoints()[1])){
		return this.getEndpoints()[0];
	}
	else {
		throw new IllegalArgumentException();
	}
	/**
	 * checks if the new segment matches the end location of segment at the end of the route
	 * @param other
	 * @return
	 * 		|result == true if other == this.getEndpoints()[0]
	 * 		|else if other== (this.getEndpoints()[1] result == true
	 * @throws IllegalArgumentException
	 * 		if both endpoints do not match
	 * 		if(!other == (this.getEndpoints()[0]&& !other == (this.getEndpoints()[1])
	 */
}
@Override
public Boolean isValidAddLocation(Location other) {
	if(other == (this.getEndpoints()[0])){
		return true;
	}
	else if(other== (this.getEndpoints()[1])){
		return true;
	}
	else {
		return false;
	}
}

}
