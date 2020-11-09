package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.news_text_bottom_sheet_dialog.*
import ru.kozirfm.f1news.R

class NewsTextBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val NEWS_TEXT_ARGUMENTS = "NEWS_TEXT_ARGUMENTS"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_text_bottom_sheet_dialog, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleTextTextView.text = arguments?.getString(NEWS_TEXT_ARGUMENTS)
    }

}