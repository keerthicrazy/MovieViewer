package com.contus.keerthi.movieviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ArrayList<RowItem> rowItems;
    ListView list_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowItems=new ArrayList();

        try {
            String s=new getMovies(this).execute("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=9d1e1af7f7c076c3d57db6928a054408").get();
            JSONObject json=new JSONObject(s);
            JSONArray jsonArray=json.getJSONArray("results");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject j=jsonArray.getJSONObject(i);
                RowItem item = new RowItem(j.get("original_title").toString(),j.get("overview").toString());
                rowItems.add(item);
            }

            list_v = (ListView) findViewById(R.id.List_view);
            CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                    R.layout.list_items, rowItems);
            list_v.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    /*
        rowItems = new ArrayList();
        for (int i = 0; i < name.length; i++) {
            RowItem item = new RowItem(images[i], name[i], number[i]);
            rowItems.add(item);
        }

        list_v = (ListView) findViewById(R.id.List_view);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.list_items, rowItems);
        list_v.setAdapter(adapter); */
    }
}
