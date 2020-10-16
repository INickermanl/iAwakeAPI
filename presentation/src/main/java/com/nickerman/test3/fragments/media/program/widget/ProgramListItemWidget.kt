package com.nickerman.test3.fragments.media.program.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.dto.media_test.Program
import com.nickerman.test3.AbstractApplication
import com.nickerman.test3.databinding.WidgetProgramListItemBinding
import com.nickerman.test3.ui.adapter.holder.ListItemViewHolder
import com.nickerman.test3.ui.adapter.widget.AbstractItemWidget

class ProgramListItemWidget : AbstractItemWidget<Program>() {

    lateinit var binding: WidgetProgramListItemBinding

    init {
        AbstractApplication.instance.mainComponent.inject(this)
    }

    override fun getHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        selectAction: (Program) -> Unit
    ): ListItemViewHolder<Program> {
        binding = WidgetProgramListItemBinding.inflate(inflater, parent, false)
        return ListItemViewHolder(binding.root, this::onBindViewHolder, selectAction)
    }

    private fun onBindViewHolder(mediaResponse: Program) {
        itemData = mediaResponse
        binding.program = mediaResponse
    }
}