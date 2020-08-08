package connections;

public class Main {

	public static void main(String[] args) {
		
		double[] coord_10_20;
		coord_10_20 = new double[] { 10.0, 20.0 };
		double[] coord_15_60;
		coord_15_60 = new double[] { 15.0, 60.0 };
		double[] coord_30_64;
		coord_30_64 = new double[] { 30.0, 64.0 };
		double[] coord_45_45;
		coord_45_45 = new double[] {45.0,45.0};
		Location leuven = new Location(coord_10_20, "Leuven");
		Location gent = new Location(coord_30_64,"Gent");
		Location antwerp = new Location(coord_15_60, "Antwerp");
		Location brussels = new Location(coord_45_45,"Brussels");
		OneWayRoad r1 = new OneWayRoad("A1",leuven,antwerp,1111, 19F, 10.66F);
		TwoWayRoad ncr = new TwoWayRoad("A2",brussels,antwerp,1111,19F,8F);
		AlternatingRoad r3 = new AlternatingRoad("A3",brussels,gent,1111,19F,10.66F);
		TwoWayRoad motorway = new TwoWayRoad("A4",antwerp,brussels,2222,30F,25F);
		//PRINT STATEMENTS FOR NCR 
		System.out.println("The road " + ncr.getIdentification() + " has endpoint1 at " +ncr.getEndpoints()[0].getAddress()+" has endpoint2 at " + ncr.getEndpoints()[1].getAddress()+" and has a length of "+ncr.getLength()+" a speed limit of "+ncr.getSpeedLimit()+" and an average speed of "+ncr.getAverageSpeed());
		System.out.println("The time taken to travel this road is " + ncr.getLength()/ncr.getAverageSpeed()+ " seconds");
		//PRINT STATEMENTS FOR MOTORWAY
		System.out.println("The road " + motorway.getIdentification() + " has endpoint1 at " +motorway.getEndpoints()[0].getAddress()+" has endpoint2 at " + motorway.getEndpoints()[1].getAddress()+" and has a length of "+motorway.getLength()+" a speed limit of "+motorway.getSpeedLimit()+" and an average speed of "+motorway.getAverageSpeed());
		System.out.println("The time taken to travel this road is " + motorway.getLength()/motorway.getAverageSpeed()+ " seconds");
		Route route = new Route(leuven,r1,motorway,r3);
		System.out.println("The total length of the route is " + route.getRouteTotalLength());
		System.out.println();
		System.out.println("The list of locations that the route goes through is: ");
		Location[] list = route.getAllLocations();
		for(Location l:list) {
		System.out.println(l.getAddress());}
		System.out.println();
		motorway.setIsBlocked(true,false);
		System.out.println("Is route traversable?");
		System.out.println(route.isRouteTraversable());
	}
}