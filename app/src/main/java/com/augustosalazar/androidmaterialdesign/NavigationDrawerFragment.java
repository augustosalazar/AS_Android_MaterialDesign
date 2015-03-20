package com.augustosalazar.androidmaterialdesign;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private static final String PREF_FILE_NAME = "testPref";
    private static final String KEY_USER_LEARNED_DRAWER = "userLearned";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private RecyclerView mRecyclerView;
    private ViewAdapter mAdapter;

    public NavigationDrawerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = readPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, false);
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
        Toast.makeText(getActivity(), "onCreate mUserLearnedDrawer  " + mUserLearnedDrawer + " mFromSavedInstanceState  " + mFromSavedInstanceState, Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mRecyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        mAdapter = new ViewAdapter(getActivity(),getData());
        mRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        return layout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int[] icons = {R.drawable.ic_number1,R.drawable.ic_number2,R.drawable.ic_number3,R.drawable.ic_number4};
        String[] titles = {"T1","T2","T3","T4"};
        for (int i= 0; i<icons.length && i<titles.length;i++){

            Information information = new Information();
            information.iconId = icons[i];
            information.title = titles[i];
            data.add(information);
        }
        return data;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (!mUserLearnedDrawer) {
                    super.onDrawerOpened(drawerView);
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer);
                    Toast.makeText(getActivity(), "Nunca visto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Ya fue visto", Toast.LENGTH_SHORT).show();
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().supportInvalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                setAlpha(toolbar, 1 - slideOffset / 2);
            }

        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
            Toast.makeText(getActivity(), " mDrawerLayout.openDrawer", Toast.LENGTH_SHORT).show();
            mUserLearnedDrawer = true;
            saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer);
        } else {
            Toast.makeText(getActivity(), " Not mDrawerLayout.openDrawer", Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void setAlpha(View view, float alpha) {
        if (Build.VERSION.SDK_INT < 11) {
            final AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
            animation.setDuration(0);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        } else view.setAlpha(alpha);
    }

    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue).commit();

    }

    public static boolean readPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
}
