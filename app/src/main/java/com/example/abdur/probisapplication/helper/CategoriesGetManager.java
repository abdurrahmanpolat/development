package com.example.abdur.probisapplication.helper;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.abdur.probisapplication.BuildConfig;
import com.example.abdur.probisapplication.activity.fragment.CategoryProductFragment;
import com.example.abdur.probisapplication.helper.listener.CatAllProFragmentManager;
import com.example.abdur.probisapplication.activity.MainActivity;
import com.example.abdur.probisapplication.model.Category;
import com.example.abdur.probisapplication.R;

public class CategoriesGetManager implements CatAllProFragmentManager {

    private static CategoriesGetManager mInstance;

    private FragmentManager mfragmentManager;
    private MainActivity mainActivity;

    public static CategoriesGetManager getmInstance(MainActivity mainActivity)
    {
        if (mInstance == null)
        {
            mInstance = new CategoriesGetManager();
        }
        mInstance.configure(mainActivity);
        return mInstance;
    }

    private void configure(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mfragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragment(Category category) {
        showFragment(CategoryProductFragment.newInstance(category), false);
    }

    private void showFragment(CategoryProductFragment category_productFragment, boolean allowStateLoss) {
        FragmentManager fragmentManager = mfragmentManager;
        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.container, category_productFragment);
        if (allowStateLoss || !BuildConfig.DEBUG){
            ft.commitAllowingStateLoss();
        }
        else {
            ft.commit();
        }
        fragmentManager.executePendingTransactions();
    }
}
