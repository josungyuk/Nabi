package com.seoul.ddroad.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.seoul.ddroad.MainActivity;
import com.seoul.ddroad.R;
import com.seoul.ddroad.diary.DiaryRegActivity;
import com.seoul.ddroad.intro.LoginActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SettingFragment extends Fragment  {

    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDatabase noteDatabase;
    private List<Note> notes;
    private NotesAdapter notesAdapter;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private int pos;
    private Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn = (Button) view.findViewById(R.id.asd);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity().getApplicationContext(),"님 환영합니당",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity().getApplicationContext(), NoteListActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        return view;
    }
}
