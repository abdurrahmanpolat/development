package com.example.abdur.probisapplication.helper;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.abdur.probisapplication.BuildConfig;
import com.example.abdur.probisapplication.activity.fragment.ProductDetailFragment;
import com.example.abdur.probisapplication.helper.listener.ProductDetailsManager;
import com.example.abdur.probisapplication.activity.MainActivity;
import com.example.abdur.probisapplication.model.Product;
import com.example.abdur.probisapplication.R;

public class ProductsGetManager implements ProductDetailsManager {

    private static ProductsGetManager mInstance;

    private FragmentManager mfragmentManager;
    private MainActivity mainActivity;

    public static ProductsGetManager getmInstance(MainActivity mainActivity)
    {
        if (mInstance == null)
        {
            mInstance = new ProductsGetManager();
        }
        mInstance.configure(mainActivity);
        return mInstance;
    }

    private void configure(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.mfragmentManager = mainActivity.getSupportFragmentManager();
    }

    //ProductDetailFragment

    @Override
    public void showDetailsFragment(Product product, String category_Id, String imageURL) {
        showDetailsFragment(ProductDetailFragment.newInstance(product, category_Id, imageURL), false);
    }

    private void showDetailsFragment(ProductDetailFragment productDetailFragment, boolean allowStateLoss) {

        FragmentManager fragmentManager = mfragmentManager;
        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.container, productDetailFragment);
        ft.addToBackStack(null);
        if (allowStateLoss || !BuildConfig.DEBUG)
        {
            ft.commitAllowingStateLoss();
        }
        else {
            ft.commit();
        }
        fragmentManager.executePendingTransactions();

    }

}
