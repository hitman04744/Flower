package com.example.bohdan.flowerkievua.fragments;

/**
 * Created by Bohdan on 18.05.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bohdan.flowerkievua.DetailActivity;
import com.example.bohdan.flowerkievua.R;
import com.example.bohdan.flowerkievua.adapters.RVAdapter;
import com.example.bohdan.flowerkievua.utils.Flowers;
import com.example.bohdan.flowerkievua.utils.MySingleton;
import com.example.bohdan.flowerkievua.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    //    ArrayList<String> list;
    private List<Flowers> list;
    private String productImage;
    private String productName;
    private String productCost;
    private String productDetail;
    private RecyclerView rv;

    private int position = 1;

    private RVAdapter adapterBouqets;


    public PlaceholderFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        int spacingInPixels = getContext().getResources().getDimensionPixelSize(R.dimen.spacing);
        rv.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        rv.setLayoutManager(llm);
        getItems(position);
        return rootView;

//
    }

    public void getItems(int State) {
        switch (State) {
            case 1:


                list = new ArrayList<>();
                Log.d("Вызван метод", "Запрос элементов Букеты");
                String url = "http://flower.kiev.ua/bouquets_rus.txt";
                RequestQueue queue = Volley.newRequestQueue(getContext());
                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String[] lines = response.split("\n");
                        for (int i = 1; i < lines.length; i++) {
                            int indexUrl = lines[i].indexOf("jpg");
                            int indexNames = lines[1].indexOf("794");
                            int indexCost = lines[1].indexOf("250");
                            int indexDetail = lines[i].indexOf("tovar");
                            int indexDetailEnd = lines[i].indexOf(" ", indexDetail);
                            productImage = lines[i].substring(0, indexUrl + 3);
                            productName = lines[i].substring(indexNames + 3, indexNames + 26).trim();
                            productCost = lines[i].substring(indexCost, indexCost + 4).trim() + "₴";
                            productDetail = lines[i].substring(indexDetail, indexDetailEnd);
                            list.add(new Flowers(productName, productImage, productCost, productDetail));
                            adapterBouqets = new RVAdapter(getContext(), list);
                            rv.setAdapter(adapterBouqets);
                            adapterBouqets.SetOnItemClickListener(new RVAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(View v, int position) {
                                    Toast.makeText(getActivity().getApplicationContext(), "lololol " + position, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
//
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "lolo", Toast.LENGTH_SHORT).show();

                    }
                });
//                    queue.add(stringRequest);
                MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


                break;
            case 2:
                Log.d("Вызван метод", "Получить Цветы");

                list = new ArrayList<>();
                url = "http://flower.kiev.ua/flowers_rus.txt";
                queue = Volley.newRequestQueue(getContext());
                stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                String str = response.substring(232,291);
//                String str1 = response.substring(450,520);
                        String[] lines = response.split("\n");
                        for (int i = 1; i < lines.length; i++) {
                            int indexUrl = lines[i].indexOf("jpg");
                            int indexNames = lines[1].indexOf("816");
                            int indexCost = lines[1].indexOf("30");
                            int indexDetail = lines[i].indexOf("tovar");
                            int indexDetailEnd = lines[i].indexOf(" ", indexDetail);
                            productImage = lines[i].substring(0, indexUrl + 3);
                            productName = lines[i].substring(indexNames + 3, indexNames + 26).trim();
                            productCost = lines[i].substring(indexCost, indexCost + 4).trim() + "₴";
                            productDetail = lines[i].substring(indexDetail, indexDetailEnd);
                            list.add(new Flowers(productName, productImage, productCost, productDetail));
                            adapterBouqets = new RVAdapter(getContext(), list);
                            rv.setAdapter(adapterBouqets);
                            adapterBouqets.SetOnItemClickListener(new RVAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(View v, int position) {
                                    Toast.makeText(getActivity().getApplicationContext(), "lololol " + position, Toast.LENGTH_SHORT).show();
                                }
                            });
//                    Log.d("IMAGES",productImage);
//                    Log.d("NAmes",productName );
//                    Log.d("Cost",productCost );
                        }
//
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "lolo", Toast.LENGTH_SHORT).show();

                    }
                });
//                    queue.add(stringRequest);
                MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
                break;
            case 3:


                list = new ArrayList<>();

                Log.d("Вызван метод", "Получить Доп материалы");
                url = "http://flower.kiev.ua/related_products_rus.txt";
                queue = Volley.newRequestQueue(getContext());
                stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                String str = response.substring(232,291);
//                String str1 = response.substring(450,520);
                        String[] lines = response.split("\n");
                        for (int i = 1; i < lines.length; i++) {
                            int indexUrl = lines[i].indexOf("jpg");
                            int indexNames = lines[1].indexOf("799");
                            int index = lines[1].indexOf("Опубликовано");
                            int indexCost = lines[1].indexOf("20", index);
                            int indexDetail = lines[i].indexOf("tovar");
                            int indexDetailEnd = lines[i].indexOf(" ", indexDetail);

                            productImage = lines[i].substring(0, indexUrl + 3);
                            productName = lines[i].substring(indexNames + 3, indexNames + 26).trim();
                            productCost = lines[i].substring(indexCost - 2, indexCost + 4).trim() + "₴";
                            productDetail = lines[i].substring(indexDetail, indexDetailEnd);
                            list.add(new Flowers(productName, productImage, productCost, productDetail));
                            adapterBouqets = new RVAdapter(getContext(), list);
                            rv.setAdapter(adapterBouqets);
                            adapterBouqets.SetOnItemClickListener(new RVAdapter.OnItemClickListener() {
                                @Override
                                public void OnItemClick(View v, int position) {
//                                        Toast.makeText(getActivity().getApplicationContext(),"lololol " + position,Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(), DetailActivity.class));
                                }
                            });
//                    Log.d("IMAGES",productImage);
//                    Log.d("NAmes",productName );
//                    Log.d("Cost",productCost );
                            Log.d("Detail", productDetail);
                        }
//
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "lolo", Toast.LENGTH_SHORT).show();

                    }
                });
//                    queue.add(stringRequest);
                MySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

                break;
        }
    }
}