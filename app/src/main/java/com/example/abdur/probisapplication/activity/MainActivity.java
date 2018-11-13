package com.example.abdur.probisapplication.activity;

import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abdur.probisapplication.R;
import com.example.abdur.probisapplication.adapter.CategoryAdapter;
import com.example.abdur.probisapplication.adapter.SliderAdapter;
import com.example.abdur.probisapplication.model.RecyclerViewItem;
import com.example.abdur.probisapplication.resClient.RetrofitApiClient;
import com.example.abdur.probisapplication.helper.CategoriesGetManager;
import com.example.abdur.probisapplication.helper.MultiLevelRecyclerView;
import com.example.abdur.probisapplication.helper.ProductsGetManager;
import com.example.abdur.probisapplication.helper.listener.CatAllProFragmentManager;
import com.example.abdur.probisapplication.helper.listener.Communicator;
import com.example.abdur.probisapplication.helper.listener.OnRecyclerItemClickListener;
import com.example.abdur.probisapplication.helper.listener.ProductDetailsManager;
import com.example.abdur.probisapplication.helper.listener.RetrofitApiInterface;
import com.example.abdur.probisapplication.model.Category;
import com.example.abdur.probisapplication.model.CategoryResult;
import com.example.abdur.probisapplication.model.Product;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Communicator {

    private SliderAdapter sliderAdapter;
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private Button mNextBtn, mBackBtn;
    private int mCurrentPage;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout linearLayout;
    private String mActivityTitle;

    private CategoryAdapter categoryAdapter;

    private CatAllProFragmentManager catAllProFragmentManager;
    private ProductDetailsManager productDetailsManager;

    private RetrofitApiInterface apiInterface;

    private List<Category> categories = new ArrayList<>();
    public List<RecyclerViewItem> categoryList = new ArrayList<>();
    private MultiLevelRecyclerView multiLevelRecyclerView;
    private MKLoader loadin_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadin_bar = (MKLoader) findViewById(R.id.loadin_bar);
        linearLayout = (LinearLayout) findViewById(R.id.nav_list);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        linearLayout.addView(listHeaderView);
        View listContentView = getLayoutInflater().inflate(R.layout.content_main, null, false);
        linearLayout.addView(listContentView);

        multiLevelRecyclerView = (MultiLevelRecyclerView) findViewById(R.id.rv_list);
        multiLevelRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        catAllProFragmentManager = CategoriesGetManager.getmInstance(this);
        productDetailsManager = ProductsGetManager.getmInstance(this);

        initData();
        loadin_bar.setVisibility(View.VISIBLE);


        // Eğer kendiniz tıklıyorsanız, o zaman yapabilirsiniz
        // multiLevelRecyclerView.removeItemClickListeners();
        multiLevelRecyclerView.setToggleItemOnClick(false);

        multiLevelRecyclerView.setAccordion(false);

        multiLevelRecyclerView.openTill(0, 1, 2, 3);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        setupDrawer();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.desc);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);
        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);
        // OnClickListener
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        mDotLayout.removeAllViews();
        mDots = new TextView[3];
        for (int i = 0; i < mDots.length; i++) {

            mDots[i] = new TextView(this);
            mDots[i].setTextSize(35);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if (i == 0) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("");

            } else if (i == mDots.length - 1) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");
            } else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private void initData() {

        apiInterface = RetrofitApiClient.getApiClient().create(RetrofitApiInterface.class);
        Call<CategoryResult> call = apiInterface.getCategory();
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if (response.body().getResult_message().getCode().equals("0000")) {
                    boolean defaulSelectItem = true;

                    categories = response.body().getResult();
                    for (RecyclerViewItem c : categories) {
                        if (c.getParentId().equals("0")) {
                            c.setLevel(0);
                            categoryList.add((RecyclerViewItem) c);
                        } else if (defaulSelectItem) {
                            defaulSelectItem = false;
                            if (catAllProFragmentManager != null) {
                                catAllProFragmentManager.showFragment((Category) c);
                            }
                        }
                    }

                    for (int i = 0; i < categoryList.size(); i++) {
                        for (int j = 0; j < categories.size(); j++) {
                            if (categories.get(j).getParentId().equals(categoryList.get(i).getId())) {
                                categories.get(j).setLevel(1);
                                categoryList.get(i).getChildren().add(categories.get(j));
                                if (categories.size() == 0)
                                    break;
                            }
                        }
                    }

                    for (int i = 0; i < categoryList.size(); i++) {
                        for (int k = 0; k < categoryList.get(i).getChildren().size(); k++) {
                            for (int j = 0; j < categories.size(); j++) {

                                if (categories.get(j).getParentId().equals(categoryList.get(i).getChildren().get(k).getId())) {
                                    categories.get(j).setLevel(2);
                                    categoryList.get(i).getChildren().get(k).getChildren().add(categories.get(j));
                                }
                            }
                        }
                    }

                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList, multiLevelRecyclerView);
                    multiLevelRecyclerView.setAdapter(categoryAdapter);
                    multiLevelRecyclerView.setOnItemClick(new OnRecyclerItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerViewItem item, int position) {
                            if (item.getChildren() == null || item.getChildren().size() == 0) {
                                mDrawerLayout.closeDrawer(GravityCompat.START);
                                catAllProFragmentManager.showFragment((Category) item);
                            } else {
                            }
                        }
                    });
                    categoryAdapter.notifyDataSetChanged();
                }
                loadin_bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                loadin_bar.setVisibility(View.GONE);
                Log.d("Babalak lak ", "hata");
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void respond(Product product, String categoryId, String imagePath) {
        productDetailsManager.showDetailsFragment(product, categoryId, imagePath);
    }
}
