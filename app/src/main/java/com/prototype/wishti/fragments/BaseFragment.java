package com.prototype.wishti.fragments;

import android.app.Fragment;

import com.prototype.wishti.FragmentNavigator;
import com.prototype.wishti.interfaces.OnFragmentRenderedListener;


public abstract class BaseFragment extends Fragment implements OnFragmentRenderedListener {

    public abstract void onNavigate(final FragmentNavigator navigator);

}
