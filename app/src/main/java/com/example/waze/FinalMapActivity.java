package com.example.waze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.preference.PreferenceManager;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.util.ArrayList;
import java.util.List;

public class FinalMapActivity extends AppCompatActivity {
    MapView map;
    ArrayList<areaInfo> area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_map);
        getSupportActionBar().hide();

        Intent inGet=getIntent();
        ArrayList<areaInfo> path=(ArrayList<areaInfo>)inGet.getSerializableExtra("areaInfo");

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        map = (MapView) findViewById(R.id.map);
        map.getTileProvider().clearTileCache();
        Configuration.getInstance().setCacheMapTileCount((short)12);
        Configuration.getInstance().setCacheMapTileOvershoot((short)12);
        // Create a custom tile source
        map.setTileSource(new OnlineTileSourceBase("", 1, 20, 512, ".png",
                new String[] { "https://a.tile.openstreetmap.org/" }) {
            @Override
            public String getTileURLString(long pMapTileIndex) {
                return getBaseUrl()
                        + MapTileIndex.getZoom(pMapTileIndex)
                        + "/" + MapTileIndex.getX(pMapTileIndex)
                        + "/" + MapTileIndex.getY(pMapTileIndex)
                        + mImageFilenameEnding;
            }
        });

        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        GeoPoint startPoint;
        startPoint = new GeoPoint(path.get(path.size()/2).latitude,path.get(path.size()/2).longitude);
        mapController.setZoom(7.1);
        mapController.setCenter(startPoint);
        final Context context = this;
        map.invalidate();
//        createmarker(21.1341f,79.0816f,"Dhantoli");
//        createmarker(21.1660f,79.0550f,"Seminary Hills");
//        createmarker(21.1364f,79.0567f,"Shivaji Nagar");
        for(int i=0;i<path.size();i++){
            createmarker(path.get(i).latitude,path.get(i).longitude,path.get(i).area_name);
        }
        drawPath(path);

//        ArrayList<Coordinates> path=new ArrayList<>();
//        path.add(new Coordinates(21.1341,79.0816));
//        path.add(new Coordinates(21.1660,79.0550));
//        path.add(new Coordinates(21.1364,79.0567));
//        drawPath(path);

        area = new ArrayList<>();
//        area.add(new areaInfo("Gondia",21.4549,80.1961));
//        area.add(new areaInfo("Bhandara",21.1777,79.6570));
//        area.add(new areaInfo("Nagpur",21.1458,79.0882));
//        area.add(new areaInfo("Wardha",20.7453,78.6022));
//        area.add(new areaInfo("Amravati",20.9320,77.7523));
//        area.add(new areaInfo("Yavatmal",20.3899,78.1307));
//        area.add(new areaInfo("Akola",20.7002, 77.0082));
//        area.add(new areaInfo("Bhusawal",21.0418,75.7876));
//        area.add(new areaInfo("Jalgaon",21.0077,75.5626));
//        area.add(new areaInfo("Dhule",20.9042,74.7749));
//        area.add(new areaInfo("Malegaon",20.5579,74.5089));
//        area.add(new areaInfo("Aurangabad",19.8762,75.3433));
//        area.add(new areaInfo("Nashik",19.9975, 73.7898));
//        area.add(new areaInfo("Ahmednagar",19.0948, 74.7480));
//        area.add(new areaInfo("Mumbai",19.0760, 72.8777));
//        area.add(new areaInfo("Pune",18.5204, 73.8567));
//        area.add(new areaInfo("Satara",17.6805, 74.0183));
//        area.add(new areaInfo("Ratnagiri",16.9902, 73.3120));
//        area.add(new areaInfo("Kolhapur",16.7050, 74.2433));
//        area.add(new areaInfo("Solapur",17.6599, 75.9064));
//        area.add(new areaInfo("Beed",18.9901, 75.7531));
//        area.add(new areaInfo("Jalna",19.8347,75.8816));
//        area.add(new areaInfo("Latur",18.4088, 76.5604));
//        area.add(new areaInfo("Parbhani",19.2608, 76.7748));
//        area.add(new areaInfo("Nanded",19.1383, 77.3210));
//        area.add(new areaInfo("Chandrapur",19.9615,79.2961));
//        area.add(new areaInfo("Gondia",21.4549,80.1961));



        area.add(new areaInfo("Gondia",21.4549,80.1961));
        area.add(new areaInfo("Nagpur",21.1458,79.0882));
        area.add(new areaInfo("Amravati",20.9320,77.7523));
        area.add(new areaInfo("Bhusawal",21.0418,75.7876));
        area.add(new areaInfo("Jalgaon",21.0077,75.5626));
        area.add(new areaInfo("Dhule",20.9042,74.7749));
        area.add(new areaInfo("Nashik",19.9975, 73.7898));
        area.add(new areaInfo("Mumbai",19.0760, 72.8777));
        area.add(new areaInfo("Ratnagiri",16.9902, 73.3120));
        area.add(new areaInfo("Kolhapur",16.7050, 74.2433));
        area.add(new areaInfo("Solapur",17.6599, 75.9064));
        area.add(new areaInfo("Latur",18.4088, 76.5604));
        area.add(new areaInfo("Chandrapur",19.9615,79.2961));
        area.add(new areaInfo("Gondia",21.4549,80.1961));


        ArrayList<GeoPoint> pt1=new ArrayList<>();
        for(int i=0;i<area.size();i++){
            pt1.add(new GeoPoint(area.get(i).latitude,area.get(i).longitude));
        }
//        updateUIWithPolygon(pt1 ,"area");
        util(pt1);
    }

    public void createmarker(double a,double b,String title) {
        if (map == null) {
            return;
        }
        Marker my_marker = new Marker(map);
        my_marker.setPosition(new GeoPoint(a, b));
        my_marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        my_marker.setImage(getResources().getDrawable(R.drawable.waze_loc_icon));
        my_marker.setTitle(title);
        my_marker.setPanToView(true);
        map.getOverlays().add(my_marker);
        my_marker.setIcon(getResources().getDrawable(R.drawable.waze_loc_icon));
        map.invalidate();
    }

    public void drawPath(ArrayList<areaInfo> al){
        Polyline line=new Polyline();
        line.setTitle("Your Path");
        line.setSubDescription(Polyline.class.getCanonicalName());
        line.setWidth(10f);
        line.setColor(getResources().getColor(R.color.red));
        ArrayList<GeoPoint> pt=new ArrayList<>();
        for(int i=0;i<al.size();i++){
            pt.add(new GeoPoint(al.get(i).latitude,al.get(i).longitude));
        }
        line.setPoints(pt);
        line.setGeodesic(true);
        line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, map));
        //Note, the info window will not show if you set the onclick listener
        //line can also attach click listeners to the line
        /*
        line.setOnClickListener(new Polyline.OnClickListener() {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos) {
                Toast.makeText(context, "Hello world!", Toast.LENGTH_LONG).show();
                return false;
            }
        });*/
        map.getOverlayManager().add(line);

    }

//    public void updateUIWithPolygon(ArrayList<GeoPoint> polygon, String name){
//        Polygon mDestinationPolygon=new Polygon();
//        List<Overlay> mapOverlays = map.getOverlays();
//        int location = -1;
//        if (mDestinationPolygon != null)
//            location = mapOverlays.indexOf(mDestinationPolygon);
//        mDestinationPolygon = new Polygon();
//        mDestinationPolygon.setFillColor(0x15FF0080);
//        mDestinationPolygon.setStrokeColor(0x800000FF);
//        mDestinationPolygon.setStrokeWidth(5.0f);
//        mDestinationPolygon.setTitle(name);
//        BoundingBox bb = null;
//        if (polygon != null){
//            mDestinationPolygon.setPoints(polygon);
//            bb = BoundingBox.fromGeoPoints(polygon);
//        }
//        if (location != -1)
//            mapOverlays.set(location, mDestinationPolygon);
//        else
//            mapOverlays.add(1, mDestinationPolygon); //insert just above the MapEventsOverlay.
//
//        map.invalidate();
//    }

    public boolean util(ArrayList<GeoPoint> geoData){
        final Polygon polygon = new Polygon();
        polygon.setPoints(geoData);
        polygon.setFillColor(0x15FF0080);
//        polygon.setStrokeColor(0x800000FF);
        polygon.setStrokeWidth(5.0f);
        polygon.setStrokeColor(getResources().getColor(R.color.teal_700));
//        polygon.setPatternBMP(BitmapFactory.decodeResource(getResources(), R.drawable.pattern));
        map.getOverlays().add(polygon);
        map.invalidate();
        return true;
    }
    public static class Coordinates{
        Double lat,lon;
        Coordinates(Double lat,Double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }
}