package com.etiennelawlor.quickreturn.activities;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.etiennelawlor.quickreturn.R;
import com.etiennelawlor.quickreturn.fragments.QuickReturnFooterListFragment;
import com.etiennelawlor.quickreturn.fragments.QuickReturnFooterListFragment2;
import com.etiennelawlor.quickreturn.fragments.QuickReturnFooterListFragment3;
import com.etiennelawlor.quickreturn.fragments.QuickReturnHeaderListFragment;
import com.etiennelawlor.quickreturn.fragments.QuickReturnHeaderListFragment2;
import com.etiennelawlor.quickreturn.fragments.QuickReturnHeaderListFragment3;
import com.etiennelawlor.quickreturn.fragments.QuickReturnWithExtraOnScrollListenerFragment;
import com.etiennelawlor.quickreturn.interfaces.QuickReturnInterface;
import com.etiennelawlor.quickreturn.library.utils.QuickReturnUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class QuickReturnListViewActivity extends QuickReturnBaseActivity implements ActionBar.TabListener, QuickReturnInterface {

    // region Member Variables
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private LinearLayout mTabsLinearLayout;

    @InjectView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @InjectView(R.id.pager)
    ViewPager mViewPager;
    // endregion

    // region Listeners
    private ViewPager.OnPageChangeListener mTabsOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d("QuickReturnScrollViewActivity", "onPageScrolled() : position - "+position);

        }

        @Override
        public void onPageSelected(int position) {
//            Log.d("QuickReturnScrollViewActivity", "onPageSelected() : position - "+position);

            for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
                TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);
                if(i == position){
                    tv.setTextColor(getResources().getColor(R.color.quick_return_primary));
                    tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf"));
                } else {
                    tv.setTextColor(getResources().getColor(android.R.color.darker_gray));
                    tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    // endregion

    // region Lifecycle Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_return_listview);
        ButterKnife.inject(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabs.setAllCaps(false);
        mTabs.setShouldExpand(true);
        mTabs.setTextSize(QuickReturnUtils.dp2px(this, 16));
        mTabs.setUnderlineColor(getResources().getColor(R.color.gray8));
        mTabs.setUnderlineHeight(QuickReturnUtils.dp2px(this, 1));
//        mTabs.setTabBackground(R.drawable.selector_bg_tab);
        mTabs.setIndicatorColorResource(R.color.quick_return_primary);
        mTabs.setIndicatorHeight(QuickReturnUtils.dp2px(this, 4));
        mTabs.setTextColor(getResources().getColor(android.R.color.darker_gray));
        mTabs.setOnPageChangeListener(mTabsOnPageChangeListener);
        mTabs.setViewPager(mViewPager);

        // Set first tab selected
        mTabsLinearLayout = ((LinearLayout)mTabs.getChildAt(0));

        for(int i=0; i < mTabsLinearLayout.getChildCount(); i++){
            TextView tv = (TextView) mTabsLinearLayout.getChildAt(i);

            if(i == 0){
                tv.setTextColor(getResources().getColor(R.color.quick_return_primary));
                tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf"));
            } else {
                tv.setTextColor(getResources().getColor(android.R.color.darker_gray));
                tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"));
            }
        }
    }
    // endregion

    // region ActionBar.TabListener Methods
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    // endregion

    // region QuickReturnInterface Methods
    @Override
    public PagerSlidingTabStrip getTabs() {
        return mTabs;
    }

    // endregion

    // region Inner Classes

    /**
     * A {@link android.support.v13.app.FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return QuickReturnHeaderListFragment.newInstance();
                case 1:
                    return QuickReturnHeaderListFragment3.newInstance();
                case 2:
                    return QuickReturnHeaderListFragment2.newInstance();
                case 3:
                    return QuickReturnFooterListFragment.newInstance();
                case 4:
                    return QuickReturnFooterListFragment3.newInstance();
                case 5:
                    return QuickReturnFooterListFragment2.newInstance();
                case 6:
                    return QuickReturnWithExtraOnScrollListenerFragment.newInstance();
                default:
                    return QuickReturnHeaderListFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "QRHeader";
                case 1:
                    return "QRHeader2";
                case 2:
                    return "SpeedyQRHeader";
                case 3:
                    return "QRFooter";
                case 4:
                    return "QRFooter2";
                case 5:
                    return "SpeedyQRFooter";
                case 6:
                    return "WithExtraOnScrollListener";
            }
            return null;
        }

    }

    // endregion

}
