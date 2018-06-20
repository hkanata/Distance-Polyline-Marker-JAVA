package br.com.opba;

import java.util.ArrayList;
import java.util.List;

import br.com.tf.BdcInit;
import br.com.tf.Points;
import br.com.tf.Polyline;

public class StartMain {

	public static void main(String[] args) {
		
		Polyline polyline = new Polyline();
		List<Points> pointsLista = new ArrayList<Points>();
		
		Points p1 = new Points();
		p1.setLat(-18.999);
		p1.setLng(-43.658);
		pointsLista.add(p1);
		
		Points p2 = new Points();
		p2.setLat(-19.399);
		p2.setLng(-43.058);
		pointsLista.add(p2);
		
		polyline.setPoints(pointsLista);
		
		Points point = new Points();
		point.setLat(-19.399);
		point.setLng(-43.038);
		
		BdcInit bdcInit = new BdcInit();
		Double total = bdcInit.bdccGeoDistanceToPolyMtrs(polyline, point);
		
		System.out.println("" + total +" Meters");
	}

}
