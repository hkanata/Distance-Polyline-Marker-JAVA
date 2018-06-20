package br.com.opba;

public class BdccGeo {

	private Double x;
	private Double y;
	private Double z;
	
	public BdccGeo(Double lat, Double lon) {
		
	    Double theta = (lon * Math.PI / 180.0);
	    Double rlat = bdccGeoGeocentricLatitude(lat * Math.PI / 180.0);
	    Double c = Math.cos(rlat);
	    this.x = c * Math.cos(theta);
	    this.y = c * Math.sin(theta);
	    this.z = Math.sin(rlat);
		
	}
	
	private Double bdccGeoGeocentricLatitude(Double geographicLatitude) {
	    Double flattening = 1.0 / 298.257223563; //WGS84
	    Double f = (1.0 - flattening) * (1.0 - flattening);
	    return Math.atan((Math.tan(geographicLatitude) * f));
	}
	
	public Double distanceToLineSegMtrs(BdccGeo geo1, BdccGeo geo2) {

	    //point on unit sphere above origin and normal to plane of geo1,geo2
	    //could be either side of the plane
		BdccGeo p2 = geo1.crossNormalize(geo2);
	    // intersection of GC normal to geo1/geo2 passing through p with GC geo1/geo2
		BdccGeo ip = bdccGeoGetIntersection(geo1, geo2, this, p2);
	    //need to check that ip or its antipode is between p1 and p2
	    Double d = geo1.distance(geo2);
	    Double d1p = geo1.distance(ip);
	    Double d2p = geo2.distance(ip);
	    //window.status = d + ", " + d1p + ", " + d2p;
		
	    //d >= d1p
	    if ((d >= d1p) && (d >= d2p)) {
	        return bdccGeoRadiansToMeters(this.distance(ip));
	    } else {
	        ip = ip.antipode();
	        d1p = geo1.distance(ip);
	        d2p = geo2.distance(ip);
	    }
	    if ((d >= d1p) && (d >= d2p)) {
	        return bdccGeoRadiansToMeters(this.distance(ip));
	    } else {
	        return bdccGeoRadiansToMeters(Math.min(geo1.distance(this), geo2.distance(this)));
	    }
	}
	
	public BdccGeo crossNormalize(BdccGeo b) {
	    Double x = (this.y * b.z) - (this.z * b.y);
	    Double y = (this.z * b.x) - (this.x * b.z);
	    Double z = (this.x * b.y) - (this.y * b.x);
	    Double L = Math.sqrt((x * x) + (y * y) + (z * z));
	    BdccGeo r = new BdccGeo(0.0, 0.0);
	    r.x = x / L;
	    r.y = y / L;
	    r.z = z / L;
	    return r;
	}

	public BdccGeo bdccGeoGetIntersection(BdccGeo geo1, BdccGeo geo2, BdccGeo geo3, BdccGeo geo4) {
		BdccGeo geoCross1 = geo1.crossNormalize(geo2);
		BdccGeo geoCross2 = geo3.crossNormalize(geo4);
	    return geoCross1.crossNormalize(geoCross2);
	}
	
	public Double distance(BdccGeo v2) {
	    return Math.atan2(v2.crossLength(this), v2.dot(this));
	}

	public Double crossLength (BdccGeo b) {
	    Double x = (this.y * b.z) - (this.z * b.y);
	    Double y = (this.z * b.x) - (this.x * b.z);
	    Double z = (this.x * b.y) - (this.y * b.x);
	    return Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	public Double dot(BdccGeo b) {
	    return ((this.x * b.x) + (this.y * b.y) + (this.z * b.z));
	}
	
	public Double bdccGeoRadiansToMeters(Double rad) {
	    return rad * 6378137.0; // WGS84 Equatorial Radius in Meters
	}

	public BdccGeo antipode() {
	    return scale(-1.0);
	}
	
	public BdccGeo scale(Double s) {
		BdccGeo r = new BdccGeo(0.0, 0.0);
	    r.x = this.x * s;
	    r.y = this.y * s;
	    r.z = this.z * s;
	    return r;
	}
}
