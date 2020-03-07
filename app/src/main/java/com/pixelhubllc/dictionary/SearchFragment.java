package com.pixelhubllc.dictionary;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pixelhubllc.database.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    DatabaseAccess databaseAccess;

    private ListView wordIndexList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_search,container, false);

        databaseAccess = DatabaseAccess.getInstance(getActivity());

        EditText searchView = view.findViewById(R.id.searchboxEt);

        wordIndexList = view.findViewById(R.id.listview);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ArrayList<String> userList = new ArrayList<>();
                databaseAccess.open();
                List<String> quotes = databaseAccess.getWords();
                databaseAccess.close();
                for (String user : quotes){
                    if (user.toLowerCase().contains((s))){
                        userList.add(user);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, userList);
                wordIndexList.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;

    }
}
