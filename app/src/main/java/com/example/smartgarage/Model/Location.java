package com.example.smartgarage.Model;

public class Location {
   private String name;
   private String Lat;
   private String Lng;
}
//    Create Location Model save infor of Marker include name, Lat, Lng:
//
//        Make function getAllMarker from database:
//
//public List<Marker> getAllMarker() {
//        List<String> result = new ArrayList<>();
//        String selectQuery = "SELECT * FROM markers";
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//        do {
//        result.add(new Location(cursor.getString(cursor.getColumnIndex("name")), cursor.getFloat(cursor.getColumnIndex("lat")), cursor.getFloat(cursor.getColumnIndex("lng"))));
//        } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return result;
//        }

//    Add marker to Map:
//
//        for(Location l: db.getAllMarker){
//        mMap.addMarker(new MarkerOptions().position(new LatLng(l.getLat(), l.getLng())).title(l.getName());
//        }

        //transalate address to location #1 geocoder

//    public GeoPoint getLocationFromAddress(String strAddress){
//
//        Geocoder coder = new Geocoder(this);
//        List<Address> address;
//        GeoPoint p1 = null;
//
//        try {
//            address = coder.getFromLocationName(strAddress,5);
//            if (address==null) {
//                return null;
//            }
//            Address location=address.get(0);
//            location.getLatitude();
//            location.getLongitude();
//
//            p1 = new GeoPoint((double) (location.getLatitude() * 1E6),
//                    (double) (location.getLongitude() * 1E6));
//
//            return p1;
//        }
//    }


/*
****
* It throws the "java.io.IOException service not available" i already gave those permission and include the library...i can get map view...it throws that IOException at geocoder...

I just added a catch IOException after the try and it solved the problem

    catch(IOException ioEx){
        return null;
    }
 */


//translate 2 #updated google map api
//public LatLng getLocationFromAddress(Context context, String strAddress) {
//
//    Geocoder coder = new Geocoder(context);
//    List<Address> address;
//    LatLng p1 = null;
//
//    try {
//        // May throw an IOException
//        address = coder.getFromLocationName(strAddress, 5);
//        if (address == null) {
//            return null;
//        }
//
//        Address location = address.get(0);
//        p1 = new LatLng(location.getLatitude(), location.getLongitude() );
//
//    } catch (IOException ex) {
//
//        ex.printStackTrace();
//    }
//
//    return p1;
//}

////translate #3 google place api
//If you want to place your address in google map then easy way to use following
//
//    Intent searchAddress = new  Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+address));
//    startActivity(searchAddress);
//OR
//
//        if you needed to get lat long from your address then use Google Place Api following
//
//        create a method that returns a JSONObject with the response of the HTTP Call like following
//
//public static JSONObject getLocationInfo(String address) {
//        StringBuilder stringBuilder = new StringBuilder();
//        try {
//
//        address = address.replaceAll(" ","%20");
//
//        HttpPost httppost = new HttpPost("http://maps.google.com/maps/api/geocode/json?address=" + address + "&sensor=false");
//        HttpClient client = new DefaultHttpClient();
//        HttpResponse response;
//        stringBuilder = new StringBuilder();
//
//
//        response = client.execute(httppost);
//        HttpEntity entity = response.getEntity();
//        InputStream stream = entity.getContent();
//        int b;
//        while ((b = stream.read()) != -1) {
//        stringBuilder.append((char) b);
//        }
//        } catch (ClientProtocolException e) {
//        } catch (IOException e) {
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        try {
//        jsonObject = new JSONObject(stringBuilder.toString());
//        } catch (JSONException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//        }
//
//        return jsonObject;
//        }
//        now pass that JSONObject to getLatLong() method like following
//
//public static boolean getLatLong(JSONObject jsonObject) {
//
//        try {
//
//        longitute = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
//        .getJSONObject("geometry").getJSONObject("location")
//        .getDouble("lng");
//
//        latitude = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
//        .getJSONObject("geometry").getJSONObject("location")
//        .getDouble("lat");
//
//        } catch (JSONException e) {
//        return false;
//
//        }
//
//        return true;
//        }
//        I hope this helps to you nd others..!! Thank you..!!


//    transalate #5 simple code
//
//    The following code will work for google apiv2:
//
//public void convertAddress() {
//        if (address != null && !address.isEmpty()) {
//        try {
//        List<Address> addressList = geoCoder.getFromLocationName(address, 1);
//        if (addressList != null && addressList.size() > 0) {
//        double lat = addressList.get(0).getLatitude();
//        double lng = addressList.get(0).getLongitude();
//        }
//        } catch (Exception e) {
//        e.printStackTrace();
//        } // end catch
//        } // end if
//        } // end convertAddress
//        Where address is the String (123 Testing Rd City State zip) you want to convert to LatLng.

//transalate #6 get address of clicked place on map

/*

public boolean onTouchEvent(MotionEvent event, MapView mapView)
{
    //---when user lifts his finger---
    if (event.getAction() == 1)
    {
        GeoPoint p = mapView.getProjection().fromPixels(
            (int) event.getX(),
            (int) event.getY());

        Toast.makeText(getBaseContext(),
             p.getLatitudeE6() / 1E6 + "," +
             p.getLongitudeE6() /1E6 ,
             Toast.LENGTH_SHORT).show();
    }
    return false;
}
it works well.

To get the location's address we can use geocoder class.


 */
