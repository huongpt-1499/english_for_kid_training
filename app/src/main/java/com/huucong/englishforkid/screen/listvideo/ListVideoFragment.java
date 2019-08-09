package com.huucong.englishforkid.screen.listvideo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huucong.englishforkid.R;
import com.huucong.englishforkid.data.model.EnglishVideo;
import com.huucong.englishforkid.utils.common.Helper;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListVideoFragment extends Fragment implements ListVideoContract.View {
    private String mCategory;
    private ListVideoPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private EnglishVideoAdapter mEnglishVideoAdapter;

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
    }

    @Override
    public void showEnglishVideos(List<EnglishVideo> englishVideos) {
        mEnglishVideoAdapter.updateData(englishVideos);
    }

    @Override
    public void showErrorLoadingVideos(String error) {
        Helper.showShortToast(getContext(), error);
    }
}
