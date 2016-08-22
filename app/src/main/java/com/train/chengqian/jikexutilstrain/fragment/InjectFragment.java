package com.train.chengqian.jikexutilstrain.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.train.chengqian.jikexutilstrain.R;
import com.train.chengqian.jikexutilstrain.adapter.ChildAdapter;
import com.train.chengqian.jikexutilstrain.domain.Child;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.inject_view)
public class InjectFragment extends Fragment {

    @ViewInject(R.id.listview)
    ListView listView;
    List<Child> children = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 1;i<100;i++){
            children.add(new Child("Name "+i));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(new ChildAdapter(getActivity(),children));
    }

    @Event(value = R.id.listview, type = AdapterView.OnItemClickListener.class)
    private void testOnItemClick(AdapterView<?> parent, View view, int position, long id){
        Snackbar.make(view,children.get(position).getName(),Snackbar.LENGTH_SHORT).show();
    }
}
