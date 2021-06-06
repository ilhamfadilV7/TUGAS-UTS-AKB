package com.example.mysimpeapp10116906;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Info extends Fragment {
    //Nama  : Ilham fadil Azmi
    //Nim   : 10116906
    //Kelas : IF-14-K
    //TGL Pengerjaan : 06-07-2021
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info, container, false);
    }
}
