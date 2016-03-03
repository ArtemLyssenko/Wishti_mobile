package com.prototype.wishti;

import android.app.Activity;
import android.app.FragmentManager;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import com.prototype.wishti.fragments.BaseFragment;

import java.util.List;

public class FragmentNavigator {

    private FragmentManager fragmentManager;

    private List<BaseFragment> fragments;

    private int currentFragmentIndex = 0;

    private BaseFragment currentFragment;

    private int resourceId;

    private ViewGroup fragmentRoot;

    private FragmentNavigator self;

    public FragmentNavigator(Activity activity, int resId, List<BaseFragment> listOfFragments){

        self = this;

        if(listOfFragments == null)
            throw new NullPointerException("listOfFragments must have not null value");

        this.fragmentRoot = (ViewGroup)activity.findViewById(resId);

        this.resourceId = resId;
        this.fragmentManager = activity.getFragmentManager();
        this.fragments = listOfFragments;
        this.currentFragment = listOfFragments.get(0);

        InjectFragment();
    }

    private void InjectFragment(){
        if(currentFragmentIndex == 0){
            this.fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_from_bottom_in, R.anim.slide_from_bottom_out)
                    .add(resourceId, currentFragment)
                    .commit();
        }else {
            this.fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.slide_from_bottom_in, R.anim.slide_from_bottom_out)
                    .replace(resourceId, currentFragment)
                    .commit();
        }

        fragmentRoot.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        fragmentRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                        currentFragment.onFragmentRendered();
                        currentFragment.onNavigate(self);
                        return true;
                    }
                });
    }

    public void InjectNextFragment()
    {
        currentFragmentIndex++;

        currentFragment = this.fragments.get(currentFragmentIndex);

        InjectFragment();
    }

    public BaseFragment getCurrentFragment(){
        return this.currentFragment;
    }
}
