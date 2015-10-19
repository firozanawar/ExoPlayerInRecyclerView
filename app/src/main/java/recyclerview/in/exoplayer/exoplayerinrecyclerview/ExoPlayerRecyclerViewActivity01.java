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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;


import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class ExoPlayerRecyclerViewActivity01 extends Activity {


    private List<VideoInfo> videoInfoList = new ArrayList<>();
    ExoPlayerVideoRecyclerViewAdapter adapter;

    private ExoPlayerVideoRecyclerView recycleView;

    private Context appContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exoplayer_custome_recycler_view);

        appContext = getApplicationContext();

        for (int index = 0; index < 10; index++) {
            videoInfoList.add(new VideoInfo("http://html5demos.com/assets/dizzy.mp4"));
        }

        recycleView = (ExoPlayerVideoRecyclerView) findViewById(R.id.video_demo_recycler_list);
        recycleView.setVideoInfoList(videoInfoList);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(appContext, VERTICAL, false);
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setStackFromEnd(true);
        recycleView.setLayoutManager(mLayoutManager);

        adapter = new ExoPlayerVideoRecyclerViewAdapter(this, videoInfoList);
        recycleView.setAdapter(adapter);

    }

    @Override
    public void onDestroy() {

        if (videoInfoList != null) {
            videoInfoList.clear();
            videoInfoList = null;
        }

        if (adapter != null) {
            adapter.onRelease();
            adapter = null;
        }

        if (recycleView != null) {
            recycleView.setAdapter(null);
            recycleView.onRelease();
        }

        super.onDestroy();
    }
}