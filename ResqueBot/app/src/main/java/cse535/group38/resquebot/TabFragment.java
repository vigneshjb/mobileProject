package cse535.group38.resquebot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cse535.group38.resquebot.model.Task;
import cse535.group38.resquebot.utils.DAO;

/**
 * Created by vignesh.jayabalan on 10/7/15.
 */
public class TabFragment extends Fragment {

    static int viewType = 1;
    int currentViewType;

    public TabFragment(){
        currentViewType =  viewType++;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View global = null;
        if (this.currentViewType == 1){
            RecyclerView rv = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
            setupRecyclerView(rv);
            global = rv;

            rv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        setupRecyclerView((RecyclerView) v);
                    }
                }
            });
        }
        else if (this.currentViewType == 2){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.new_task, container, false);
            global = rootView;
        }
        else if (this.currentViewType == 3){
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.upload_logs, container, false);
            global = rootView;
        }
        return global;
    }

    @Override
    public void onResume(){
        super.onResume();
        if (this.currentViewType == 1)
            setupRecyclerView((RecyclerView)this.getView());
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(getActivity(), getTaskDisplaylist()));
    }

    private List<Task> getTaskDisplaylist(){
        DAO dbUtil = new DAO(getContext());
        List<Task> allTasks = dbUtil.getAllTasks();
        return allTasks;
    }

    public static class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private List<Task> taskList;

        public SimpleRecyclerViewAdapter(Context context, List<Task> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            taskList = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_task, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            viewHolder.displayText = taskList.get(i).getDisplayText();
            viewHolder.task = taskList.get(i);
            viewHolder.taskDespUIComp.setText(null == taskList.get(i) ? "" : taskList.get(i).getDisplayText());

            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, TaskRudActivity.class);
                    intent.putExtra("taskObject", viewHolder.task);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String displayText;
            public Task task;
            public final View mView;
            public final TextView taskDespUIComp;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                taskDespUIComp = (TextView) view.findViewById(R.id.taskListItem);
            }

            @Override
            public String toString() {
                return super.toString() + " " + taskDespUIComp.getText();
            }
        }
    }
}
