package com.train.chengqian.jikexutilstrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.train.chengqian.jikexutilstrain.R;
import com.train.chengqian.jikexutilstrain.domain.Child;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by cheng.qian on 2016/8/18.
 */
public class ChildAdapter extends BaseAdapter {
    private List<Child> children;
    private LayoutInflater inflater;

    public ChildAdapter(Context context, List<Child> children) {
        this.children = children;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int i) {
        return children.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.child_item, null);
            viewHolder = new ViewHolder();
            x.view().inject(viewHolder, view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Child child = children.get(i);
        viewHolder.childName.setText(child.getName());
        return view;
    }

//    class ViewHolder {
//        TextView childName;
//
//        public ViewHolder() {
//            childName = inflater.inflate(R.id.tv_child_name,null);
//        }
//    }

    class ViewHolder {
        @ViewInject(R.id.tv_child_name)
        TextView childName;
    }
}
