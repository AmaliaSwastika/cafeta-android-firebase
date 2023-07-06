package com.example.project11;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private ArrayList<ListDomain> listDomains1;
    private ArrayList<ListDomain> listDomains2;
    private RecyclerView recyclerViewList1;
    private RecyclerView recyclerViewList2;
    private NewAdapter adapter1;
    private NewAdapter adapter2;
    private ViewFlipper flipper;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewList1 = view.findViewById(R.id.view);
        recyclerViewList2 = view.findViewById(R.id.views);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList1.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList2.setLayoutManager(linearLayoutManager2);

        listDomains1 = new ArrayList<>();
        listDomains1.add(new ListDomain("Jaguar Cafe", "jaguar2"));
        listDomains1.add(new ListDomain("Specta Cafe", "specta2"));
        listDomains1.add(new ListDomain("Garden Cafe", "garden2"));
        listDomains1.add(new ListDomain("Gemilang Cafe", "gemilang2"));
        listDomains1.add(new ListDomain("Rame Rame Cafe", "ramerame2"));
        listDomains1.add(new ListDomain("Kongkow Cafe", "kongkow2"));

        adapter1 = new NewAdapter(listDomains1, recyclerViewList1, recyclerViewList2, new NewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getContext(), Outlets.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getContext(), Outlets2.class);
                    startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(getContext(), Outlets3.class);
                    startActivity(intent);
                }else if (position == 3) {
                    Intent intent = new Intent(getContext(), Outlets4.class);
                    startActivity(intent);
                }else if (position == 4) {
                    Intent intent = new Intent(getContext(), Outlets5.class);
                    startActivity(intent);
                }else if (position == 5) {
                    Intent intent = new Intent(getContext(), Outlets6.class);
                    startActivity(intent);
                }
                // Tambahkan logika lainnya untuk recyclerViewList1 di sini
            }
        });
        recyclerViewList1.setAdapter(adapter1);

        listDomains2 = new ArrayList<>();
        listDomains2.add(new ListDomain("Matcha Latte \n Rp 40.000", "matchaa"));
        listDomains2.add(new ListDomain("Caramel Macchiato Rp 60.000", "caramel"));
        listDomains2.add(new ListDomain("Frappuccino \n Rp 50.000", "frap"));
        listDomains2.add(new ListDomain("Croissant \n Rp 80.000", "croiss2"));
        listDomains2.add(new ListDomain("Pancake \n Rp 50.000", "pancake2"));
        listDomains2.add(new ListDomain("French Fries \n Rp 40.000", "kentang2"));

        adapter2 = new NewAdapter(listDomains2, recyclerViewList2, recyclerViewList1, new NewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getContext(), Menu.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getContext(), Menu2.class);
                    startActivity(intent);
                }else if (position == 2) {
                    Intent intent = new Intent(getContext(), Menu3.class);
                    startActivity(intent);
                }else if (position == 3) {
                    Intent intent = new Intent(getContext(), Menu4.class);
                    startActivity(intent);
                }else if (position == 4) {
                    Intent intent = new Intent(getContext(), Menu5.class);
                    startActivity(intent);
                }else if (position == 5) {
                    Intent intent = new Intent(getContext(), Menu6.class);
                    startActivity(intent);
                }
                // Tambahkan logika lainnya untuk recyclerViewList2 di sini
            }
        });
        recyclerViewList2.setAdapter(adapter2);

        int imgArray[] = {R.drawable.slid1, R.drawable.slid2, R.drawable.slid3};
        flipper = view.findViewById(R.id.flipper);

        for (int i = 0; i < imgArray.length; i++) {
            showImage(imgArray[i]);
        }

        return view;
    }

    public void showImage(int img) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(3000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        flipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
}
}