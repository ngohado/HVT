package com.hvt.hbapplication.ui.bookmark;

import com.hvt.hbapplication.R;
import com.hvt.hbapplication.data.model.FolkBookmark;
import com.hvt.hbapplication.network.ApiClient;
import com.hvt.hbapplication.ui.BasePresenter;

import java.util.Collections;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class BookmarkPresenter extends BasePresenter<BookmarkView> {

    private List<FolkBookmark> folksBookmarked;

    public BookmarkPresenter(ApiClient apiClient) {
        super(apiClient);
    }

    public void loadFolksBookmarked() {
        Disposable disposable = dataManager.getFolksBookmarked().subscribe(folkBookmarks -> {
            folksBookmarked = folkBookmarks;
            getView().displayFolksBookmarked(folkBookmarks == null ? Collections.emptyList() : folkBookmarks);

            if (folkBookmarks == null || folkBookmarks.isEmpty()) {
                getView().showEmptyText(true);
            } else {
                getView().showEmptyText(false);
            }
        }, throwable -> getView().showError(R.string.bookmark_load_error));
        compositeDisposable.add(disposable);
    }

    public void prepareForNavigateToDetailFolk(int position) {
        if (folksBookmarked != null && position < folksBookmarked.size()) {
            getView().navigateToDetailFolkByID(folksBookmarked.get(position).idFolk);
        }
    }

    public void updateFolkBookmarkSaveChange(int position) {
        if (folksBookmarked != null && position < folksBookmarked.size()) {
            FolkBookmark folkBookmarkChange = folksBookmarked.get(position);
            boolean oldState = folkBookmarkChange.isSelected;
            boolean newState = !oldState;
            folkBookmarkChange.isSelected = newState;

            Disposable disposable;
            if (newState) {
                disposable = dataManager.bookmarkFolk(folkBookmarkChange.idFolk, folkBookmarkChange.backgroundUrl, folkBookmarkChange.name).subscribe(result -> {
                    //do nothing
                }, throwable -> {
                    getView().showError(R.string.detail_save_failure);
                    folkBookmarkChange.isSelected = oldState;
                    getView().rollbackItemError(position);
                });
            } else {
                disposable = dataManager.unBookmarkFolk(folkBookmarkChange.idFolk).subscribe(result -> {
                    //do nothing
                }, throwable -> {
                    getView().showError(R.string.detail_unsave_failure);
                    folkBookmarkChange.isSelected = oldState;
                    getView().rollbackItemError(position);
                });
            }


            compositeDisposable.add(disposable);
        }
    }
}
