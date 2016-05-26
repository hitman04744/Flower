package com.example.bohdan.flowerkievua;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bohdan.flowerkievua.utils.MySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    public String[] list;
    public int pos = 0;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        pos = getIntent().getIntExtra("position", 0);
        list = getIntent().getStringArrayExtra("DetailURL");
        Log.d("INTEGER ", pos + "");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(pos);
        mViewPager.setOffscreenPageLimit(3);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_SECTION_LIST = "section_List";
        private String title = "";
        private String excerpt = "";
        private String imageUrl = "";
        private ImageView detailImage;
        private TextView textView;
        private TextView textViewExcerpt;
        private TextView textViewNumber;
        private ProgressBar pb;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, String[] list) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Log.d("Fragment", "ADD " + sectionNumber);
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putStringArray(ARG_SECTION_LIST, list);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final JSONObject json;
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            pb = (ProgressBar) rootView.findViewById(R.id.progressBar);
            detailImage = (ImageView) rootView.findViewById(R.id.detailImageView);
            textView = (TextView) rootView.findViewById(R.id.section_label);
            textViewExcerpt = (TextView) rootView.findViewById(R.id.textView_Excerpt);
            textViewNumber = (TextView) rootView.findViewById(R.id.textView_Number);
            final String detailsList[] = getArguments().getStringArray(ARG_SECTION_LIST);
            final int position = getArguments().getInt(ARG_SECTION_NUMBER);
            pb.setVisibility(View.VISIBLE);

            RequestQueue queue = Volley.newRequestQueue(getContext());

            String url = "http://flower.kiev.ua/" + detailsList[position] + "?json=1";
            textView.setText(url);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONObject json = null;
                    JSONObject jsonImages;
                    try {
                        json = response.getJSONObject("post");
                        title = json.getString("title");
                        excerpt = json.getString("excerpt");
                        jsonImages = json.getJSONObject("thumbnail_images");
                        jsonImages.getJSONObject("full");
                        imageUrl = jsonImages.getJSONObject("full").getString("url");
                        Picasso.with(getContext()).load(jsonImages.getJSONObject("full").getString("url")).into(detailImage);
                        textView.setText(title);
                        textViewExcerpt.setText(excerpt);
                        textViewNumber.setText(position + 1 + "/" + detailsList.length);
                        pb.setVisibility(View.INVISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Json Error", Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            });
//            queue.add(jsObjRequest);
            MySingleton.getInstance(getContext()).addToRequestQueue(jsObjRequest);
//            textView.setText(detailsList[position]);
//            Picasso.with(getContext()).load(imageUrl).into(detailImage);
//            textView.setText(title);
//            textViewExcerpt.setText(excerpt);
//            textViewNumber.setText(position+1+"/"+detailsList.length);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).


            return PlaceholderFragment.newInstance(position, list);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.

            return list.length;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
//        }
    }
}
