package com.example.sse.customlistview_sse;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//Step-By-Step, Setting up the ListView

    private
    RecyclerView lvEpisodes;     //Reference to the listview GUI component
//    ListAdapter lvAdapter;   //Reference to the Adapter used to populate the listview.
    RecyclerView.Adapter lvAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvEpisodes = (RecyclerView) findViewById(R.id.lvEpisodes);

        layoutManager = new LinearLayoutManager(this);
        lvEpisodes.setLayoutManager(layoutManager);


        lvAdapter = new MyCustomAdapter(this.getBaseContext());  //instead of passing the boring default string adapter, let's pass our own, see class MyCustomAdapter below!
        lvEpisodes.setAdapter(lvAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);   //get rid of default behavior.

        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.my_test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mnu_zero) {
            Toast.makeText(getBaseContext(), "Menu Zero.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_one) {
            Toast.makeText(getBaseContext(), "Ring ring, Hi Mom.", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.mnu_three) {
             Toast.makeText(getBaseContext(), "Hangup it's a telemarketer.", Toast.LENGTH_LONG).show();
            return true;
        }

            return super.onOptionsItemSelected(item);  //if none of the above are true, do the default and return a boolean.
    }
}


//***************************************************************//
//create a  class that extends BaseAdapter
//Hit Alt-Ins to easily implement required BaseAdapter methods.
//***************************************************************//
//
//class m2 extends BaseAdapter{
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }
//}

//STEP 1: Create references to needed resources for the ListView Object.  String Arrays, Images, etc.

class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    private
     String episodes[];             //Keeping it simple.  Using Parallel arrays is the introductory way to store the List data.
     String  episodeDescriptions[];  //the "better" way is to encapsulate the list items into an object, then create an arraylist of objects.
//     int episodeImages[];         //this approach is fine for now.
     ArrayList<Integer> episodeImages;  //Well, we can use one arrayList too...  Just mixing it up here, Arrays or Templated ArrayLists, you choose.
    String episodeURL[];
//    ArrayList<String> episodes;
//    ArrayList<String> episodeDescriptions;

    Button btnRandom;
    Context context;   //Creating a reference to our context object, so we only have to get it once.  Context enables access to application specific resources.
                       // Eg, spawning & receiving intents, locating the various managers.

//STEP 2: Override the Constructor, be sure to:
    // grab the context, we will need it later, the callback gets it as a parm.
    // load the strings and images into object references.
    public MyCustomAdapter(Context aContext) {
//initializing our data in the constructor.
        context = aContext;  //saving the context we'll need it again.
        episodes = aContext.getResources().getStringArray(R.array.episodes);  //retrieving list of episodes predefined in strings-array "episodes" in strings.xml
        episodeDescriptions = aContext.getResources().getStringArray(R.array.episode_descriptions);
        episodeURL = aContext.getResources().getStringArray(R.array.episode_url);

//This is how you would do it if you were using an ArrayList, leaving code here for reference, though we could use it instead of the above.
//        episodes = (ArrayList<String>) Arrays.asList(aContext.getResources().getStringArray(R.array.episodes));  //retrieving list of episodes predefined in strings-array "episodes" in strings.xml
//        episodeDescriptions = (ArrayList<String>) Arrays.asList(aContext.getResources().getStringArray(R.array.episode_descriptions));  //Also casting to a friendly ArrayList.


        episodeImages = new ArrayList<Integer>();   //Could also use helper function "getDrawables(..)" below to auto-extract drawable resources, but keeping things as simple as possible.
        episodeImages.add(R.drawable.st_spocks_brain);
        episodeImages.add(R.drawable.st_arena__kirk_gorn);
        episodeImages.add(R.drawable.st_this_side_of_paradise__spock_in_love);
        episodeImages.add(R.drawable.st_mirror_mirror__evil_spock_and_good_kirk);
        episodeImages.add(R.drawable.st_platos_stepchildren__kirk_spock);
        episodeImages.add(R.drawable.st_the_naked_time__sulu_sword);
        episodeImages.add(R.drawable.st_the_trouble_with_tribbles__kirk_tribbles);
    }

//STEP 3: Override and implement getCount(..),
// ListView uses this to determine how many rows to render.

//    public int getCount() {
////        return episodes.size(); //all of the arrays are same length, so return length of any... ick!  But ok for now. :)
//        return episodes.length;   //all of the arrays are same length, so return length of any... ick!  But ok for now. :)
//                                  //Q: How else could we have done this (better)? ________________
//    }

//STEP 4: Override getItem/getItemId, we aren't using these, but we must override anyway.

    public Object getItem(int position) {
//        return episodes.get(position);  //In Case you want to use an ArrayList
        return episodes[position];        //really should be returning entire set of row data, but it's up to us, and we aren't using this call.
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvEpisodeTitle.setText(episodes[position]);
        holder.tvEpisodeDescription.setText(episodeDescriptions[position]);
        holder.imgEpisode.setImageResource(episodeImages.get(position).intValue());

        final String randomMsg = ((Integer)position).toString() +": "+ episodeDescriptions[position];
        final String randomURL = episodeURL[position];
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, randomMsg, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(randomURL));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;  //Another call we aren't using, but have to do something since we had to implement (base is abstract).
    }

    @Override
    public int getItemCount() {
        return episodes.length;
    }

//THIS IS WHERE THE ACTION HAPPENS.  getView(..) is how each row gets rendered.
//STEP 5: Easy as A-B-C
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        //position is the index of the row being rendered.
//        //convertView represents the Row (it may be null),
//        // parent is the layout that has the row Views.
//
////STEP 5a: Inflate the listview row based on the xml.
//        View row;  //this will refer to the row to be inflated or displayed if it's already been displayed. (listview_row.xml)
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        row = inflater.inflate(R.layout.listview_row, parent, false);  //
//
////// Let's optimize a bit by checking to see if we need to inflate, or if it's already been inflated...
//        if (convertView == null){  //indicates this is the first time we are creating this row.
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //Inflater's are awesome, they convert xml to Java Objects!
//            row = inflater.inflate(R.layout.listview_row, parent, false);
//        }
//        else
//        {
//            row = convertView;
//        }
//
////STEP 5b: Now that we have a valid row instance, we need to get references to the views within that row and fill with the appropriate text and images.
////        ImageView imgEpisode = (ImageView) row.findViewById(R.id.imgEpisode);  //Q: Notice we prefixed findViewByID with row, why?  A: Row, is the container.
////        TextView tvEpisodeTitle = (TextView) row.findViewById(R.id.tvEpisodeTitle);
////        TextView tvEpisodeDescription = (TextView) row.findViewById(R.id.tvEpisodeDescription);
//
//        tvEpisodeTitle.setText(episodes[position]);
//        tvEpisodeDescription.setText(episodeDescriptions[position]);
//        imgEpisode.setImageResource(episodeImages.get(position).intValue());
////
////        btnRandom = (Button) row.findViewById(R.id.btnRandom);
////        final String randomMsg = ((Integer)position).toString() +": "+ episodeDescriptions[position];
////        btnRandom.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(context, randomMsg, Toast.LENGTH_LONG).show();
////            }
////        });
//
////STEP 5c: That's it, the row has been inflated and filled with data, return it.
//        return row;  //once the row is fully constructed, return it.  Hey whatif we had buttons, can we target onClick Events within the rows, yep!
////return convertView;
//
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEpisode;
        TextView tvEpisodeTitle;
        TextView tvEpisodeDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgEpisode= (ImageView) itemView.findViewById(R.id.imgEpisode);
            tvEpisodeTitle = (TextView) itemView.findViewById(R.id.tvEpisodeTitle);
            tvEpisodeDescription = (TextView) itemView.findViewById(R.id.tvEpisodeDescription);

            btnRandom = (Button) itemView.findViewById(R.id.btnRandom);
    }


    ///Helper method to get the drawables...///
    ///this might prove useful later...///

//    public ArrayList<Drawable> getDrawables() {
//        Field[] drawablesFields =com.example.sse.customlistview_sse.R.drawable.class.getFields();
//        ArrayList<Drawable> drawables = new ArrayList<Drawable>();
//
//        String fieldName;
//        for (Field field : drawablesFields) {
//            try {
//                fieldName = field.getName();
//                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
//                if (fieldName.startsWith("animals_"))  //only add drawable resources that have our prefix.
//                    drawables.add(context.getResources().getDrawable(field.getInt(null)));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
}}



//EXTRA CREDIT ASSIGNMENT QUESTIONS:

// 1)
//      RecyclerView as its name suggests recycles Views once they get out of scope (screen) with the help of ViewHolder pattern.
//      So it is the view that is getting recycled, in this case, the listview_row.

// 2)
//      RecyclerView is allows for much better performance due to recycling the view. Additionally, it also provides more flexibility as it supports
//      grids and lists.


// 3)   recycler is easier to implement as there is no need to generate more variables for each view on the list, in addition,
//      animations are far easier to work with in RV than LV. Furthermore, RV implements ViewHolder naturally, while LV requires you to implement
//      the holder pattern yourself.

// 4)   Recycler view is viewed as an improvement/replacement of listView. Google is deprecating the view slowly so as to not cause any apps
//      that use this view to immediately become incompatible

// 5)   Developers need to ensure that the dependancy ("implementation 'com.android.support:recyclerview-v7:26.1.0'") for this view has been added to their gradle module