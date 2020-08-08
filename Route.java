package connections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import be.kuleuven.cs.som.annotate.*;


/**
* 
* @invar  Each route can have its starting location as starting location.
*        |canHaveAsStartingLocation(this.getStartingLocation())
* @invar  Each route can have its segments as its segments
* 		 |areValidSegments(this.getRouteSegments())
* 
* @author Ajay Sundaresan
* @author Ubaid ur Rehman
*/
public class Route {
	
/**
 * Initialize this new route with given starting location.
 * 
 * @param  startingLocation
 *         The starting location for this new route.
 * @param  roads
 * 		   The segments for this new route
 * @post   The starting location of this new route is equal to the given
 *         starting location.
 *         |new.getStartingLocation() == startingLocation
 * @post   The segments of this route are the given segments
 * 		   |new.getRouteSegments() == roads
 * @throws IllegalArgumentException
 *         This new route cannot have the given starting location as its starting location.
 *         |!canHaveAsStartingLocation(this.getStartingLocation())
 * @throws IllegalArgumentException
 *         This new route cannot have the given segments as its segments.
 *         |!areValidSegments(this.getRouteSegments())
 */
public Route(Location startingLocation,Object... roads) throws IllegalArgumentException {
	if (canHaveAsStartingLocation(startingLocation) == false)
		throw new IllegalArgumentException();
	else {
	this.startingLocation = startingLocation;
	}
	if(areValidSegments(roads) == true) {
		this.roadList = roads.clone();
	}
	else {
		throw new IllegalArgumentException();
	}	
}

/**
 * Variable registering the starting location of this route.
 */
private final Location startingLocation;
/**
* A variable containing the endpoint of the route
*/
Location other;
/**
* A variable containing the segments of the route
*/
Object[] roadList;
/**
* A variable containing all the locations of the route
*/
ArrayList<Location>locationList = new ArrayList<Location>();

/**
 * Return the starting location of this route.
 */
@Basic @Raw @Immutable
public Location getStartingLocation() {
	return this.startingLocation;
}

/**
 * Check whether this route can have the given starting location as its starting location.
 *  
 * @param  startingLocation
 *         The starting location to check.
 * @return False if the location is terminated or is null
 *       | if(startingLocation.isTerminated() == true | startingLocation == null)
 *       |result == false
 * @return True otherwise
 * 		 | else result == true
*/
@Raw
public static boolean canHaveAsStartingLocation(Location startingLocation) {
	if(startingLocation.isTerminated() == true | startingLocation == null)
		return false;
	return true;
}

/**
 * A method to return a copy of the segments of the given route
 */
public Object[] getRouteSegments() {
	Object[] segments =  this.roadList.clone();
	return segments;
}

/**
* 
* @param roads 
* 		  The segments to be checked
* @return True if the segments are valid for the route
* 		  |if(roads.length == 0)
* 		  |result == true
* 		  |for(each road in roads){
* 		  |if(road instanceof Road)
* 		  |tracker == road.getOtherLocation()
* 		  |if(road instanceof Route)
* 		  |if(tracker == road.getStartingLocation()
*		  |result == true
*		  False otherwise
*		  |result == false
*/
public boolean areValidSegments(Object... roads) {
	if(roads.length == 0) {
		this.other = this.getStartingLocation();
		return true;
	}
	Location tracker = this.getStartingLocation();
	this.locationList.add(tracker);
	for(Object road:roads) {
		if(road instanceof Road) {
			tracker = ((Road) road).getOtherLocation(tracker);
			this.locationList.add(tracker);
		}
		
		else if(road instanceof Route) {
			if(tracker == ((Route) road).getStartingLocation()) {
				tracker = ((Route) road).getAllLocations()[((Route) road).getAllLocations().length-1];
				Location[] arr1 = ((Route) road).getAllLocations();
				for(int i = 1;i<arr1.length;i++) {
					this.locationList.add(arr1[i]);
				}
			}
			else {
				return false;
			}
		}
	}
	this.other = tracker;
	return true;
	}

/**
 * 
 * @param segment
 *        The road to be added to the route
 * @pre	  the segment can be added
 * 		  |if(segment instanceof Road)
 * 		  |if(isValidAddLocation(segment)
 * 		  |else if(segment instanceof Route)
 * 		  |if(this.other == segment.getStartingLocation())
 * @post  The number of segments of this route is
 *	      increased by 1.
 *        |new.getRouteSegments().length == getRouteSegments().length + 1
 * @post  The segment is inserted at the end
 * 		  |new.getRouteSegments(new.getRouteSegments.length-1) == road 	   
 * @throws IllegalArgumentException
 *		  |other != road.getEndpoints()[0] | other != road.getEndpoints()[1]
 * @throws IllegalStateException
 * 		   if the route is no longer a valid route
 */
public void addRouteSegments(Object segment) throws IllegalStateException {
	ArrayList<Object>list = new ArrayList<Object>(Arrays.asList(this.roadList));
	if(segment != null) {
	if(segment instanceof Road) {
		if(((Road) segment).isValidAddLocation(this.other)) {
			list.add(segment);
			this.roadList = list.toArray(roadList);
			this.other = ((Road) segment).getOtherLocation(this.other);
			if(!areValidSegments(this.getRouteSegments()))
				throw new IllegalStateException();
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	else if(segment instanceof Route) {
		Object[] obArray = ((Route) segment).getRouteSegments();
		if(segment == this) {
			throw new IllegalArgumentException();
		}
		else {
		for(Object o:obArray) {
			if(o==this) {
				throw new IllegalArgumentException();
			}
		}}
		if(this.other == ((Route) segment).getStartingLocation()) {
			list.add(segment);
			this.roadList = list.toArray(roadList);
			this.other = ((Route) segment).getAllLocations()[((Route) segment).getAllLocations().length-1];
			if(!areValidSegments(this.getRouteSegments()))
				throw new IllegalStateException();
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	}
	else {
		throw new IllegalArgumentException();
	}
	}


/**
 * 
 * @param i
 * 		  The index at which the segment of the route must be removed
 * @post  The number of segments of this route is decreased by 1
 *        |new.getRouteSegments() == getRouteSegments() - 1
 * @post  This route no longer has the segment at the given index as
 *	      one of its segments.
 *	      |!new.getRouteSegments()[i] != getRouteSegments[i]
 * @post  The indices of segments beyond the removed segment are shifted one place to the left
 * @throws IllegalArgumentException
 * 		  |if(i ==0)
 * 		  |allLocations[1] != this.getStartingLocation()
 * 		  |allLocations[i+1] != track
 * @throws IndexOutOfBoundsException
 * 		  |i > list.size()-1
 * @throws IllegalStateException
 * 		   if the route is no longer a valid route
 */
public void removeRouteSegments(int i) throws IllegalArgumentException,IndexOutOfBoundsException,IllegalStateException {
	ArrayList<Object>list = new ArrayList<Object>(Arrays.asList(this.roadList));
	Location[] allLocations = this.getAllLocations();
	Location track; 
	 if(i==list.size()-1) {
		list.remove(i);
		roadList = new Road[(list.size())];
		this.roadList = list.toArray(roadList);
		if(!areValidSegments(this.getRouteSegments()))
			throw new IllegalStateException();
	 } 
	 else if(i == 0) {
		 if(allLocations[1] == this.getStartingLocation()) {
			 list.remove(i);
			 roadList = new Road[(list.size())];
				this.roadList = list.toArray(roadList);
				if(!areValidSegments(this.getRouteSegments()))
					throw new IllegalStateException();
		 }
		 else {
			 throw new IllegalArgumentException();
		 }
	 }
	 else if(i > list.size()-1) {
		 throw new IndexOutOfBoundsException();
	 }
	 else{ 
		 if(((Road) list.get(i-1)).getEndpoints()[0] == allLocations[i-1]) {
			 track = ((Road) list.get(i-1)).getEndpoints()[1];
		 }
		 else {
			 track = ((Road) list.get(i-1)).getEndpoints()[0];
		 }
		 if(allLocations[i+1] == track) {
			 list.remove(i);
			 roadList = new Road[(list.size())];
				this.roadList = list.toArray(roadList);
				if(!areValidSegments(this.getRouteSegments()))
					throw new IllegalStateException();
		 }
		 else {
			 throw new IllegalArgumentException();
		 }
	 }
}
/**
 * Method to retun total length of the route
 * @return totalLength
 * 		   |for road in this.getRouteSegments()
 * 		   |totalLength = totalLength+road[i].getLength();
 */

public int getRouteTotalLength() {
	int totalLength = 0;
	Object[] array = this.getRouteSegments();
	for(int i = 0;i<array.length;i++) {
		if(array[i] instanceof Road) {
			totalLength = totalLength + ((Road) array[i]).getLength();
		}
		else if(array[i] instanceof Route) {
			totalLength = totalLength + ((Route) array[i]).getRouteTotalLength();
		}
	}
	return totalLength;
}
/**
 * Method to check if route is traversable
 * @return False if route is not traversable
 * 		   |for road in this.getRouteSegments()
 * 		   |if(road[i].getIsBlocked(true) == true |road[i].getIsBlocked(falsee) == true)
 * 		   |result == false
 * 		   True if the route is indeed traversable
 * 		   |else result == true 
 */
public boolean isRouteTraversable() {
	Object[] array = this.getRouteSegments();
	for(int i = 0;i<array.length;i++) {
		if(((Road) array[i]).getIsBlocked(true) == true)
			return true;
	}
	return false;
}

/**
 * Method to return the locations that the route passes through
 */
public Location[] getAllLocations() {
	Object[] r = getRouteSegments();
	if(r.length == 0) {
		return new Location[] {this.getStartingLocation()};
	}
	ArrayList<Location>cloneLocations = new ArrayList<Location>();
	cloneLocations.addAll(this.locationList);
	Location[] locationArr = new Location[cloneLocations.size()];
	locationArr = cloneLocations.toArray(locationArr);
	return locationArr;
}
}
