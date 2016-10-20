package mx.com.platzigram.platzigram.view.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mx.com.platzigram.platzigram.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfielFragment extends Fragment {


    public ProfielFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profiel, container, false);
    }

}
