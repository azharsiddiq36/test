package jbenastey.org.gopku;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by jbenastey on 29-May-17.
 */

public class UIN extends ListActivity {

    public void onCreate (Bundle icecle){
        super.onCreate(icecle);
        String [] layanan = new String [] {"Call Center",
                "Info Maps","Driving Direction",
                "Website","Search Info","Restoran nearme",
                "Exit"};
        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,layanan));
    }
    //fungsi untuk mendengarkan apa yang di klik
    protected void onListItemClick (ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
        Object o = this.getListAdapter().getItem(position);
        String pilihan = o.toString();
        tampilkanpilihan(pilihan);
    }

    private void tampilkanpilihan(String pilihan) {
        try{
            //handle pilihan
            Intent uin = null;
            if (pilihan.equals("Call Center")){
                String tel = "tel:081362136129";
                uin = new Intent(Intent.ACTION_DIAL, Uri.parse(tel));
            }
            else if (pilihan.equals("Info Maps")){
//                String geo = "geo:0.482323,101.419719"; //isi dengan posisi uin
//                uin = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
//                //set app default untuk buka intent ini apabila ada beberapa aplikasi yang dapat membuka
//                uin.setPackage("com.google.android.apps.maps");
                uin = new Intent(this,UINLocation.class);
            }
            else if (pilihan.equals("Driving Direction")){
                String geo = "google.navigation:q=0.463498,101.356844"; //isi dengan posisi uin
                uin = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            }
            else if (pilihan.equals("Website")){
                String web = "https://uin-suska.ac.id";
                uin = new Intent(Intent.ACTION_VIEW, Uri.parse(web));
            }
            else if (pilihan.equals("Search Info")){
                uin = new Intent(Intent.ACTION_WEB_SEARCH);
                uin.putExtra(SearchManager.QUERY,("UIN Suska Riau"));
            }
            else if (pilihan.equals("Restoran nearme")){
                //mencari restoran disekitar kita(baca gps) lokasi terdekat resto yang ada
                Uri dekatsaya = Uri.parse("geo:0,0?q=restaurants");
                uin = new Intent(Intent.ACTION_VIEW,dekatsaya);
                uin.setPackage("com.google.android.apps.maps");
            }
            else if (pilihan.equals("Exit")){
                finish();
            }
            startActivity(uin);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}