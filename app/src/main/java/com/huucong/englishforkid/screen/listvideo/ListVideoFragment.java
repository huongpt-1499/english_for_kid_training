package com.huucong.englishforkid.screen.listvideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.huucong.englishforkid.R;
import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.utils.Constants;
import com.huucong.englishforkid.utils.common.Helper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListVideoFragment extends Fragment implements ListVideoContract.View,
    View.OnClickListener {
    private String mCategory;
    private ListVideoPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private EnglishVideoAdapter mEnglishVideoAdapter;
    private LinearLayout mLinearSearchBar;
    private String mTagSelectedButtonSearch;

    public ListVideoFragment(String category) {
        this.mCategory = category;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_video, container, false);
        init(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart(mCategory);
    }

    public void init(View rootView) {
        mPresenter = new ListVideoPresenter();
        mPresenter.setView(this);
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mEnglishVideoAdapter = new EnglishVideoAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mEnglishVideoAdapter);

        initSearchBar(rootView);
    }

    private void initSearchBar(View rootView) {
        mLinearSearchBar = rootView.findViewById(R.id.linear_search_bar);
        Button buttonSearchAll = rootView.findViewById(R.id.button_all);
        buttonSearchAll.setSelected(true);
        buttonSearchAll.setOnClickListener(this);
        mTagSelectedButtonSearch = (String) buttonSearchAll.getTag();
        /* add button search from A - Z */
        for (int i = 'A'; i <= 'Z'; i++) {
            Button button = new Button(getContext());
            button.setLayoutParams(rootView.findViewById(R.id.button_all).getLayoutParams());
            button.setPadding(Constants.ZERO, Constants.ZERO, Constants.ZERO, Constants.ZERO);
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            button.setTextColor(getResources().getColorStateList(R.color.selector_color_text_bsb));
            String label = String.valueOf((char) i);
            button.setText(label);

            /* tag is keyword to search video */
            button.setTag(label);
            button.setOnClickListener(this);
            mLinearSearchBar.addView(button);
        }
    }

    @Override
    public void showEnglishVideos(List<EnglishVideo> englishVideos) {
        mEnglishVideoAdapter.updateData(englishVideos);
    }

    @Override
    public void showErrorLoadingVideos(String error) {
        Helper.showShortToast(getContext(), error);
    }

    @Override
    public void onClick(View view) {
        if (view.isSelected()) return;
        mLinearSearchBar.findViewWithTag(mTagSelectedButtonSearch).setSelected(false);
        String tag = (String) view.getTag();
        mTagSelectedButtonSearch = tag;
        view.setSelected(true);
        if (getString(R.string.all).equals(tag)) mPresenter.loadVideo(mCategory);
        else mPresenter.searchVideo(mCategory, tag);
    }
}
