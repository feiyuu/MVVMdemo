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
import android.content.Intent;
import android.net.Uri;

import com.feiyu.mvvmdemo.R;
import com.feiyu.mvvmdemo.data.bean.LibraryInfo;
import com.feiyu.mvvmdemo.databinding.AdapterLibraryBinding;
import com.feiyu.mvvmdemo.ui.adapter.SimpleDataBindingAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Create by KunMinX at 20/4/19
 */
public class DrawerAdapter extends SimpleDataBindingAdapter<LibraryInfo, AdapterLibraryBinding> {

    public DrawerAdapter(Context context) {
        super(context, R.layout.adapter_library, new DiffUtil.ItemCallback<LibraryInfo>() {
            @Override
            public boolean areItemsTheSame(@NonNull LibraryInfo oldItem, @NonNull LibraryInfo newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull LibraryInfo oldItem, @NonNull LibraryInfo newItem) {
                return oldItem.getTitle().equals(newItem.getTitle());
            }
        });

        setOnItemClickListener((item, position) -> {
            Uri uri = Uri.parse(item.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        });
    }

    @Override
    protected void onBindItem(AdapterLibraryBinding binding, LibraryInfo item, RecyclerView.ViewHolder holder) {
        binding.setInfo(item);
    }
}
