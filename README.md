# Distance-Polyline-Marker With Java

Calculate distance from polyline and marker

## Getting Started

You can calculate the distance between marker and polyline using Java directly

### Run

Follow the steps below

Calling the main function

```
BdcInit bdcInit = new BdcInit();
Double total = bdcInit.bdccGeoDistanceToPolyMtrs(polyline, point);
```

example:

```
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
```

## Authors

* **hkanata** - *Initial work* - [hkanata](https://github.com/hkanata)

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details
