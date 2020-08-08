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

public class AlternatingRoad extends Road {
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
	public AlternatingRoad(String identification, Location l1, Location l2, int length, float speedLimit,
			float averageSpeed) {
		super(identification, l1, l2, length, speedLimit, averageSpeed);
	}
	/**
	 * 
	 * @param direction
	 * 			The direction in which the road is blocked
	 * @return	
	 * 			The blockage status of the road
	 * @throws  IllegalArgumentException
	 * 			The road is not traversable in that direction
	 *			|direction == false
	 */
@Override
public boolean getIsBlocked(boolean direction) {
	if(direction == false) {
		throw new IllegalArgumentException();
	}
	else {
		return super.getIsBlocked(true);
	}
}
/**
 * 
 * @param isBlocked
 * 			Blockage status of the road
 * @param direction
 * 			The direction in which the road is blocked
 * @post	
 * 			new.getBlocked(true) = isBlocked
 * @throws  IllegalArgumentException
 * 			The road is not traversable in that direction
 *			|direction == false
 */
@Override 
public void setIsBlocked(boolean isBlocked,boolean direction) {
	if(direction == false) {
		throw new IllegalArgumentException();
	}
	else {
		super.setIsBlocked(isBlocked,true);
	}
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
 @throws  IllegalArgumentException
 * 			The road is not traversable in that direction
 *			|direction == false
 *       
 */
@Override
public void setDelay(float delay,boolean direction) {
	if(direction == false) {
		throw new IllegalArgumentException();
	}
	else {
		super.setDelay(delay,true);
	}
}
/**
 * 
 * @param direction
 * 			The direction in which delay is required
 * @return	
 * 			result == super.getDelay(true
 * @throws  IllegalArgumentException
 * 			The road is not traversable in that direction
 *			|direction == false
 */
@Override 
public float getDelay(boolean direction) {
	if(direction == false) {
		throw new IllegalArgumentException();
	}
	else {
		return super.getDelay(true);
	}
}
/**
 * returns possible start locations of the road array of length 1
 */
@Override
public Location[] getStartLocations() {
	return new Location[] {this.getEndpoints()[0]};
}
/**
 * returns possible end locations of the road array of length 1
 */
@Override
public Location[] getEndLocations() {
	return new Location[] {this.getEndpoints()[1]};
}
/**
 * Reverses the traversal direction of the road
 * @post new.endpoint1 == this.endpoint2
 * @post new.endpoint2 == this.endpoint1
 */
@Override
public void reverseTraversalDirection() {
	Location[] l1 = this.getStartLocations();
	Location start = l1[0];
	Location[] l3 = this.getEndLocations();
	Location end = l3[0];
	this.endpoint2 = start;
	this.endpoint1 = end;
	
}
/**
 * returns if road is traversable in the forward direction
 * @return True
 * 		   |if(getIsBlocked(true))
 * @throws IllegalArgumentException
 * 		   Road is not traversable in said direction
 * 		   |direction == false
 */
@Override
public boolean isTraversable(boolean direction) {
	if(direction == false) {
		throw new IllegalArgumentException();
	}
if(getIsBlocked(true)) {
	return false;
}
	return true;
}
/**
 * returns endpoint of road that does not match the tracker 
 * @param tracker
 * @return
 * 		|if(tracker == this.getStartLocations()[0])
 * 		|result == this.getEndLocations()[0]
 * @throws IllegalArgumentException
 * 		Endpoints do not match	
 * 		|if(!tracker == this.getStartLocations()[0])
 */
@Override
public Location getOtherLocation(Location tracker) {
	if(tracker == this.getStartLocations()[0]) {
		return this.getEndLocations()[0];
	}
	else {
		throw new IllegalArgumentException();
	}
}
/**
 * checks if the new segment matches the end location of segment at the end of the route
 * @param other
 * @return
 * 		|result == true if other == this.getStartLocation()[0]
 * 		|else return == false
 */
@Override
public Boolean isValidAddLocation(Location other) {
	if(other == this.getStartLocations()[0]) {
		return true;
	}
	return false;
}


}
