/*
 * Copyright 2018-2020 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.feiyu.mvvmdemo.ui.page.adapter;

import android.content.Context;
import android.graphics.Color;

import com.feiyu.mvvmdemo.R;
import com.feiyu.mvvmdemo.data.bean.TestAlbum;
import com.feiyu.mvvmdemo.databinding.AdapterPlayItemBinding;
import com.feiyu.mvvmdemo.player.PlayerManager;
import com.feiyu.mvvmdemo.ui.adapter.SimpleDataBindingAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Create by KunMinX at 20/4/19
 */
public class PlaylistAdapter extends SimpleDataBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding> {

    public PlaylistAdapter(Context context) {
        super(context, R.layout.adapter_play_item, new DiffUtil.ItemCallback<TestAlbum.TestMusic>() {
            @Override
            public boolean areItemsTheSame(@NonNull TestAlbum.TestMusic oldItem, @NonNull TestAlbum.TestMusic newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull TestAlbum.TestMusic oldItem, @NonNull TestAlbum.TestMusic newItem) {
                return oldItem.getMusicId().equals(newItem.getMusicId());
            }
        });

        setOnItemClickListener(((item, position) -> {
            PlayerManager.getInstance().playAudio(position);
        }));
    }

    @Override
    protected void onBindItem(AdapterPlayItemBinding binding, TestAlbum.TestMusic item, RecyclerView.ViewHolder holder) {
        binding.setAlbum(item);
        int currentIndex = PlayerManager.getInstance().getAlbumIndex();
        binding.ivPlayStatus.setColor(currentIndex == holder.getAdapterPosition()
                ? binding.getRoot().getContext().getResources().getColor(R.color.gray) : Color.TRANSPARENT);
    }
}
