package com.valerio.android.aplicacionprofequiz.Vista;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.valerio.android.aplicacionprofequiz.R;
import com.valerio.android.aplicacionprofequiz.Vista.adaptadore.TopAdapter;
import com.valerio.android.aplicacionprofequiz.Vista.modelo.Top;
import com.valerio.android.aplicacionprofequiz.Vista.net.ApiClient;
import com.valerio.android.aplicacionprofequiz.Vista.net.ApiTop;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private List<Top> top;
    private RecyclerView recyclerView;
    private TopAdapter topAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = rootView.findViewById(R.id.rv_top);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));


        showTop();

        return rootView;
    }
    public void showTop(){
        Call<List<Top>> call= ApiClient.getClient().create(ApiTop.class).getTop();
        call.enqueue(new Callback<List<Top>>() {
            @Override
            public void onResponse(Call<List<Top>> call, Response<List<Top>> response) {
                if(response.isSuccessful()){
                    top=response.body();
                    topAdapter=new TopAdapter(top, getActivity());
                    recyclerView.setAdapter(topAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Top>> call, Throwable t) {

            }
        });
    }
}