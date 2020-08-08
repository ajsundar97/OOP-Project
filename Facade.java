package facade;

import java.util.Collection;

import connections.AlternatingRoad;
import connections.Location;
import connections.OneWayRoad;
import connections.Road;
import connections.Route;
import connections.TwoWayRoad;

/**
 * A facade to enable a collection of tests.
 * 
 * The facade is an interface, a concept that we have not covered in the course.
 * An interface in Java is very similar to an abstract class. It groups definitions
 * of abstract and non-abstract methods. By definition, all methods in an interface
 * are public, even if they are not explicitly qualified public. Methods with an
 * implementation in an interface are called default methods, and must have the
 * keyword default for that purpose. 
 * 
 * The facade assumes that you have at least a class Location, a class Road
 * and a class Route. If these classes are in some package (which they should be),
 * you must add import statements in the beginning of this file. In our solution,
 * these classes are all in a package named connections, hence the import statements
 * in the supplied version of the facade.
 * 
 * The facade is used by the test suite, and is some kind of intermediary
 * between the tests and your code. You must implement each method using
 * the code that you have written. To get some idea of how to do this,
 * the facade includes the implementation of the methods getRoadIdentification
 * and changeRoadIdentification in view of our solution.
 * Typically, you invoke the corresponding method from your code, you catch
 * any kind of exception that invocation may throw, and throw a new ModelException
 * as a signal that the execution was not successful.
 * 
 */
public interface Facade {
	
	/********************
	 * Location methods *
	 ********************/

	/**
	 * Return a new location with given coordinate, given address and no adjoining roads yet.
	 */
	default Location createLocation(double[] coordinates, String address) throws ModelException {
		try {
			Location location= new Location(coordinates,address);
			return location;
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Terminate the given location.
	 */
	default void terminateLocation(Location location) throws ModelException {
		try {
			location.terminate();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Check whether the given location is terminated.
	 */
	default boolean isTerminatedLocation(Location location) throws ModelException {
		try {
			return location.isTerminated();
		}catch (Throwable exc) {
			throw new ModelException();
		}
		
	}

	/**
	 * Return the coordinates of the given location.
	 *   The method returns an array of length 2 containing the latitude followed
	 *   by the longitude as two values of type double.
	 */
	default double[] getLocationCoordinates(Location location) throws ModelException {
		try {
			return location.getCoordinates();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the address of the given location.
	 */
	default String getLocationAddress(Location location) throws ModelException {
		try {
			return location.getAddress();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return a collection of all adjoining roads of the given location.
	 */
	default Collection<Road> getLocationAllAdjoiningRoads(Location location) throws ModelException {
		try {
			return location.getAllAdjoiningRoads();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Check whether the given location has the given road as one of its adjoining roads.
	 */
	default boolean locationHasAsAdjoiningRoad(Location location, Road road) throws ModelException {
		try {
			return location.canHaveAsAdjoiningRoad(road);
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/****************
	 * Road methods *
	 ****************/

	/**
	 * Return a new two-way road with the given identification, given start location, given
	 * end location, given length, given speed limit, given average speed, and with no delay nor
	 * blockage in the traveling direction.
	 */
	default Road createOneWayRoad(String identification, Location startLocation, Location endLocation, int length, float speedLimit,
			float averageSpeed) throws ModelException {
		try {
			OneWayRoad road = new OneWayRoad(identification, startLocation, endLocation, length, speedLimit, averageSpeed);
			return road;
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return a new one-way road with the given identification, given end points, given length,
	 * given speed limit, given average speed, and with no delays in both directions nor
	 * with blockages in both directions.
	 */
	default Road createTwoWayRoad(String identification, Location endPoint1, Location endPoint2, int length, float speedLimit,
			float averageSpeed) throws ModelException {
		try {
			TwoWayRoad road = new TwoWayRoad(identification, endPoint1, endPoint2, length, speedLimit, averageSpeed);
			return road;
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return a new alternating road with the given identification, given end points, given length, 
	 * given speed limit, given average speed, and with no delay nor blockage in the traveling
	 * direction.
	 *   The new alternating road will be traversable in the direction from its first end point
	 *   to its second end point.
	 */
	default Road createAlternatingRoad(String identification, Location startLocation, Location endLocation, int length, float speedLimit,
			float averageSpeed) throws ModelException {
		try {
			AlternatingRoad road = new AlternatingRoad(identification, startLocation, endLocation, length, speedLimit, averageSpeed);
			return road;
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Terminate the given road.
	 */
	default void terminateRoad(Road road) throws ModelException {
		try {
			 road.terminate();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Check whether the given road is terminated.
	 */
	default boolean isTerminatedRoad(Road road) throws ModelException {
		try {
			return road.isTerminated();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the identification of the given road.
	 */
	default String getRoadIdentification(Road road) throws ModelException {
		try {
			return road.getIdentification();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Change the identification of the given road to the given identification.
	 */
	default void changeRoadIdentification(Road road, String newIdentification) throws ModelException {
		try {
			road.changeIdentification(newIdentification);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the end points of the given road.
	 *   The method always returns an array of length 2.
	 */
	default Location[] getEndPoints(Road road) throws ModelException {
		try {
			return road.getEndpoints();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the start locations of the given road.
	 *   The method returns an array of length 1 or 2, with its elements in the same
	 *   order as the elements of the array returned by getEndPoints.
	 */
	default Location[] getStartLocations(Road road) throws ModelException {
		try {
			return road.getStartLocations();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the end locations of the given road.
	 *   The method returns an array of length 1 or 2, with its elements in the same
	 *   order as the elements of the array returned by getEndPoints.
	 */
	default Location[] getEndLocations(Road road) throws ModelException {
		try {
			return road.getEndLocations();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the length of the given road.
	 */
	default int getRoadLength(Road road) throws ModelException {
		try {
			return road.getLength();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Change the length of the given road to the given length.
	 */
	default void changeRoadLength(Road road, int newLength) throws ModelException {
		try {
			road.setlength(newLength);
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the speed limit for the given road.
	 */
	default float getRoadSpeedLimit(Road road) throws ModelException {
		try {
			return road.getSpeedLimit();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Change the speed limit of the given road to the given speed limit.
	 */
	default void changeRoadSpeedLimit(Road road, float newSpeedLimit) throws ModelException {
		try {
			road.setSpeedLimit(newSpeedLimit);
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Return the average speed for the given road.
	 */
	default float getRoadAverageSpeed(Road road) throws ModelException {
		try {
			return road.getAverageSpeed();
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Change the average speed of the given road to the given average speed.
	 */
	default void changeRoadAverageSpeed(Road road, float newAverageSpeed) throws ModelException {
		try {
			road.setAverageSpeed(newAverageSpeed);
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Get the current delay in the direction from the first end point to the second
	 * end point, if directionForth is true, and in the opposite direction if it 
	 * is false.
	 *    The method must throw ModelException if the given road is a one-way road or
	 *    an alternating road that cannot be traversed in the given direction.
	 */
	default float getRoadDelayinDirection(Road road, boolean directionForth) throws ModelException {
		try {
			return road.getDelay(directionForth);
		}catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Set the current delay in the direction from the first end point to the second
	 * end point to the given delay, if directionForth is true, and to the given delay
	 * in the opposite direction if directionForth is false.
	 *    The method must throw ModelException if the given road is a one-way road or
	 *    an alternating road that cannot be traversed in the given direction.
	 */
	default void changeRoadDelayinDirection(Road road, float delay, boolean directionForth) throws ModelException {
		try {
			road.setDelay(delay, directionForth);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Check whether the given road is blocked in the direction from the first
	 * end point to the second end point, if directionForth is true, and in the opposite
	 * direction if it is false.
	 *    The method must throw ModelException if the given road is a one-way road or
	 *    an alternating road that cannot be traversed in the given direction.
	 */
	default boolean getRoadIsBlocked(Road road, boolean directionForth) throws ModelException {
		try {
			return road.getIsBlocked(directionForth);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/**
	 * Set the blocked state of the given road in the direction from the first end point
	 * to the second end point according to the given flag, if directionForth is true,
	 * and according to the given flag in the opposite direction if directionForth is false.
	 *    The method must throw ModelException if the given road is a one-way road or
	 *    an alternating road that cannot be traversed in the given direction.
	 */
	default void changeRoadBlockedState(Road road, boolean flag, boolean directionForth) throws ModelException {
		try {
			road.setIsBlocked(flag, directionForth);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Reverse the direction in which the given road can be traversed.
	 *    The method throws ModelException if the given road is not an alternating road.
	 */
	default void reverseTraversalDirection(Road road) throws ModelException {
		try {
			road.reverseTraversalDirection();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

	/*****************
	 * Route methods *
	 *****************/
	
	/**
	 * Return a new route with given start location and given segments.
	 *   The method must throw ModelException if at least one of the given segments
	 *   is not a road or a route.
	 */
	default Route createRoute(Location startLocation, Object... segments) throws Exception {
		try {
			Route route = new Route(startLocation,segments);
			return route;
		} catch(Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Return the start location of the given route.
	 */
	default Location getRouteStartLocation(Route route) throws ModelException {
		try {
			return route.getStartingLocation();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Return the segments of the given route in the order from the
	 * start location to the end location.
	 */
	default Object[] getRouteSegments(Route route) throws ModelException {
		try {
			return route.getRouteSegments();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Add the given road at the end of the sequence of segments of the given route.
	 *   The method must throw ModelException if at least one of the given segments
	 *   is not a road or a route.
	 */
	default void addRouteSegment(Route route, Object segment) throws ModelException {
		try {
			 route.addRouteSegments(segment);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Remove the segment at the given index from the sequence of segments of the given route.
	 */
	default void removeRouteSegment(Route route, int index) throws ModelException {
		try {
			 route.removeRouteSegments(index);
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Return the total length of the given route.
	 */
	default int getRouteTotalLength(Route route) throws ModelException {
		try {
			return route.getRouteTotalLength();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Check whether the given route is traversable from its start location
	 * to its end location.
	 */
	default boolean isRouteTraversable(Route route) throws ModelException {
		try {
			return route.isRouteTraversable();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}
	
	/**
	 * Check whether the given route is traversable from its start location
	 * to its end location.
	 */
	default Location[] getAllLocations(Route route) throws ModelException {
		try {
			return route.getAllLocations();
		} catch (Throwable exc) {
			throw new ModelException();
		}
	}

}
