package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.databinding.NewsTextBottomSheetDialogBinding

class NewsTextBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val NEWS_TEXT_ARGUMENTS = "NEWS_TEXT_ARGUMENTS"
    }

    lateinit var newsTextBottomSheetDialogBinding: NewsTextBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        newsTextBottomSheetDialogBinding =
            NewsTextBottomSheetDialogBinding.inflate(layoutInflater, container, false)
        return newsTextBottomSheetDialogBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsTextBottomSheetDialogBinding
            .articleTextTextView.text = arguments?.getString(NEWS_TEXT_ARGUMENTS)
    }

}