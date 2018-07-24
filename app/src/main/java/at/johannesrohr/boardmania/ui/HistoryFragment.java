package at.johannesrohr.boardmania.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.johannesrohr.boardmania.Data.HistoryEntry;
import com.johannesrohr.boardmania.R;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;

public class HistoryFragment extends Fragment
{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private HistoryRecyclerViewAdapter adapter;

    public HistoryFragment()
    {
    }

    public static HistoryFragment newInstance(int columnCount)
    {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_historyfragment_list, container, false);

        if (view instanceof RecyclerView)
        {
            Realm realm = Realm.getDefaultInstance();
            List<HistoryEntry> hisEntry = new ArrayList<>();
            final List<HistoryEntry> histEntry = realm.where(HistoryEntry.class).findAll();
            for (HistoryEntry h: histEntry)
            {
                hisEntry.add(0,h);
            }


            adapter = new HistoryRecyclerViewAdapter(hisEntry);
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);

            HistoryRecyclerSectionItemDecoration sectionItemDecoration = new HistoryRecyclerSectionItemDecoration(
                    getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height), true, getSectionCallback(hisEntry));
            recyclerView.addItemDecoration(sectionItemDecoration);
        }
        return view;
    }

    private HistoryRecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<HistoryEntry> history)
    {
        return new HistoryRecyclerSectionItemDecoration.SectionCallback()
        {
            @Override
            public boolean isSection(int position)
            {
                if(position != 0)
                {
                    try
                    {
                        Date d1 = history.get(position).getTime();
                        Date d2 = history.get(position - 1).getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        return sdf.parse(d2.toString()).before(sdf.parse(d1.toString()));
                    }
                    catch(Exception e)
                    {

                    }
                }

                    return position == 0;
            }

            @Override
            public CharSequence getSectionHeader(int position)
            {
                return DateFormat.format("dd.MM.yyyy", history.get(position).getTime()).toString();


            }
        };

    }
}
