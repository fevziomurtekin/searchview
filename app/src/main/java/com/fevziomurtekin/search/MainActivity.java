package com.fevziomurtekin.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fevziomurtekin.searchview.SearchView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private String[] strings = new String[]{"Belgium","France","Brazil","Croatia"
            ,"Uruguay","England","Portugal","Switzerland","Spain","Denmark","Argentina"
            ,"Chile","Germany","Colombia","Sweden","Mexico","Netherlands","Poland","Wales"
            ,"Italy","Peru","USA","Tunisia","Austria","Turkey"};

    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (String s:strings) {
            stringList.add(s);
        }
        searchView      = new SearchView(getApplicationContext());
        recyclerView    = findViewById(R.id.recycler);
        recyclerView    .setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView    .setAdapter(new Adapter(this,stringList,strings));

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
       try{ ((Adapter)recyclerView.getAdapter()).updateList(editable.toString());}
       catch (Exception e){}
    }

    private static class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private Context context;
        private List<String> stringList = new ArrayList<>();
        private String[] strings        = new String[]{};

        public Adapter(Context context, List<String> stringList, String[] strings) {
            this.context    = context;
            this.stringList = stringList;
            this.strings    = strings;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            return new Adapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtItem.setText(stringList.get(position));
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }

        public void updateList(String search) {
            List<String>temps = new ArrayList<>();
            for (String ss:stringList) {
                if(ss.toLowerCase().contains(search.toLowerCase())){
                    temps.add(ss);
                }
            }
            this.stringList=temps;
            notifyDataSetChanged();

        }

        protected static class ViewHolder extends RecyclerView.ViewHolder {
            private TextView txtItem;

            public ViewHolder(View itemView) {
                super(itemView);
                txtItem = itemView.findViewById(R.id.txtItem);

            }
        }
    }
}
