/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package recyclerview.in.exoplayer.exoplayerinrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer.AspectRatioFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class ExoPlayerVideoRecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater inflater;
    private List<VideoInfo> videoInfoList = new ArrayList<>();


    public ExoPlayerVideoRecyclerViewAdapter(Context appContext, List<VideoInfo> videoInfoList) {
        this.videoInfoList = videoInfoList;
        inflater = LayoutInflater.from(appContext.getApplicationContext());

    }

    public VideoInfo getItem(int position) {

        if (position < videoInfoList.size()) {
            return videoInfoList.get(position);
        }

        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(inflater
                .inflate(R.layout.exoplayer_recycler_view_row, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoViewHolder) {
            setVideoViewHolder((VideoViewHolder) holder);
        }
    }

    private void setVideoViewHolder(VideoViewHolder holder) {
        holder.parent.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return videoInfoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        AspectRatioFrameLayout videoContainer;
        View parent;

        public VideoViewHolder(View v) {
            super(v);
            parent = v;
            videoContainer = (AspectRatioFrameLayout) v.findViewById(R.id.video_frame);
        }
    }

    public void onRelease() {
        if (videoInfoList != null) {
            videoInfoList.clear();
            videoInfoList = null;
        }
    }

}
