package br.com.opba;

public class BdcInit {

	public Double bdccGeoDistanceToPolyMtrs(Polyline polyline,  Points point) {
	    Double d = Double.POSITIVE_INFINITY;
	    int i;
	    BdccGeo p = new BdccGeo(point.lat(), point.lng());
	    for (i = 0; i < (polyline.getPoints().size() - 1); i++) {
	        Points p1 = polyline.getPoints().get(i);
	        BdccGeo l1 = new BdccGeo(p1.lat(), p1.lng());
	        
	        Points p2 = polyline.getPoints().get(i + 1);
	        BdccGeo l2 = new BdccGeo(p2.lat(), p2.lng());
	        Double dp = p.distanceToLineSegMtrs(l1, l2);
	        if (dp < d) {
	            d = dp;
	        }
	    }
	    return d;
	}
}
